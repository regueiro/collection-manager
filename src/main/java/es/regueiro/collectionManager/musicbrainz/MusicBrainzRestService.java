package es.regueiro.collectionManager.musicbrainz;

import java.util.Date;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import es.regueiro.collectionManager.util.RestManager;

/**
 * The Class MusicBrainzRestService.
 */
@Component
public class MusicBrainzRestService {

	private static Object lock = new Object();
	private static Date lastQueryTimeStamp = new Date();
	// TODO check if this is the correct place to store this number
	private final static int maxQueriesPerSecond = 1;
	private final long waitTime = 1000 / maxQueriesPerSecond;

	private static final Logger logger = LoggerFactory
			.getLogger(MusicBrainzRestService.class);

	/** The rest manager used to execute the queries. */
	@Autowired
	private RestManager restManager;

	/**
	 * Sets the credentials for authentication in trakt.
	 * 
	 * @param username
	 *            your trakt username
	 * @param sha1password
	 *            your trakt password hashed with sha1
	 */
	public void setAuthentication(String username, String sha1password) {
		restManager.setAuthentication(MusicBrainzConfig.traktHost,
				MusicBrainzConfig.traktPort, username, sha1password);
	}

	private String constructSearchUrl(String URI, String queryName, String query) {
		StringBuilder builder = new StringBuilder();

		builder.append(MusicBrainzConfig.baseURL);
		if (!URI.startsWith("/")) {
			builder.append("/");
		}
		builder.append(URI);
		if (!URI.endsWith("/")) {
			builder.append("/");
		}
		builder.append("?query=");
		if (queryName != null && !StringUtils.isEmpty(queryName)) {
			builder.append(queryName);
		}
		builder.append(":");
		if (query != null && !StringUtils.isEmpty(query)) {
			builder.append(query);
		}
		return builder.toString();
	}

	private String constructLookupUrl(String entity, String mbid, String inc) {
		StringBuilder builder = new StringBuilder();

		builder.append(MusicBrainzConfig.baseURL);
		if (!entity.startsWith("/")) {
			builder.append("/");
		}
		builder.append(entity);
		if (!entity.endsWith("/")) {
			builder.append("/");
		}
		builder.append(mbid);
		if (inc != null && !StringUtils.isEmpty(inc)) {
			builder.append("?inc=");
			builder.append(inc);
		}
		return builder.toString();
	}

	/**
	 * Generate and execute a post query
	 * 
	 * @param <T>
	 *            the generic type. Matches returnType class
	 * @param URI
	 *            trakt URI for the query
	 * @param returnType
	 *            the desired return type of this query
	 * @param obj
	 *            the obj
	 * @return the result of the query
	 */
	public <T> T post(String URI, Class<T> returnType, Object obj,
			String queryName) {
		return post(URI, returnType, obj, null);
	}

	/**
	 * Generate and execute a post query
	 * 
	 * @param <T>
	 *            the generic type. Matches returnType class
	 * @param URI
	 *            trakt URI for the query
	 * @param returnType
	 *            the desired return type of this query
	 * @param obj
	 *            the obj
	 * @param query
	 *            the query parameters
	 * @return the result of the query
	 */
	public <T> T post(String URI, Class<T> returnType, Object obj,
			String queryName, String query) {

		throw new UnsupportedOperationException();
		// T result = restManager.getTemplate().postForObject(
		// constructUrl(URI, queryName, query), obj, returnType);
		// return result;
	}

	/**
	 * Generate and execute a get query
	 * 
	 * @param <T>
	 *            the generic type. Matches returnType class
	 * @param URI
	 *            trakt URI for the query
	 * @param returnType
	 *            the desired return type of this query
	 * @param query
	 *            the query parameters
	 * @return the result of the query
	 */
	public <T> T get(String URI, Class<T> returnType, String queryName) {
		return get(URI, returnType, null);
	}

	public <T> T search(String URI, Class<T> returnType, String queryName,
			String query) {

		String url = constructSearchUrl(URI, queryName, query);
		return get(url, returnType);
	}

	public <T> T lookup(String entity, String mbid, Class<T> returnType) {

		String url = constructLookupUrl(entity, mbid, null);
		return get(url, returnType);
	}

	public <T> T lookup(String entity, String mbid, Class<T> returnType,
			String inc) {

		String url = constructLookupUrl(entity, mbid, inc);
		return get(url, returnType);
	}

	/**
	 * Generates and executes a get query
	 * 
	 * This method could be called from different threads. Since we need to
	 * honour the maximum API calls limit imposed by MusicBrainz, all queries
	 * will be synchronised using a mutex object to ensure we don't surpass this
	 * limit.
	 * 
	 * @param <T>
	 *            The type that will be returned. Matches returnType class
	 * @param URI
	 *            MusicBrainz URI for the query
	 * @param returnType
	 *            the desired return type of this query
	 * @param query
	 *            the query parameters
	 * @return the result of the query
	 */
	private <T> T get(String url, Class<T> returnType) {

		T result;

		// Wait for the other possible threads to liberate the lock
		synchronized (lock) {

			// Find the current time
			Date currentDate = new Date();
			// and compare it with the time of the last query
			long timeSinceLastQuery = currentDate.getTime()
					- lastQueryTimeStamp.getTime();

			// The time we have to wait until the next possible query is the
			// difference between the maximum waiting time and the time
			// that passed since the last query completed.
			long timeLeftToWait = waitTime - timeSinceLastQuery;

			// if we have to wait
			if (timeLeftToWait > 0) {
				logger.info("Waiting for " + timeLeftToWait + " ms.");
				try {
					// we wait the needed time
					Thread.sleep(timeLeftToWait);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block for Thread.sleep
					e.printStackTrace();
				}
			}

			// Now we can launch the query
			// TODO add error response handling
			result = restManager.getTemplate().getForObject(url, returnType);

			// Finally, we update the time of the last query and release the
			// lock
			lastQueryTimeStamp = new Date();
		}

		return result;
	}
}