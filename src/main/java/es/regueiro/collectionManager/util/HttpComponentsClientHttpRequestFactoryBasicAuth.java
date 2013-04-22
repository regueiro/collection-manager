//TODO comment
package es.regueiro.collectionManager.util;

import java.net.URI;

import org.apache.http.HttpHost;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class HttpComponentsClientHttpRequestFactoryBasicAuth extends
		HttpComponentsClientHttpRequestFactory {
	DefaultHttpClient client;

	public HttpComponentsClientHttpRequestFactoryBasicAuth(
			DefaultHttpClient httpclient) {
		super(httpclient);

		this.client = httpclient;
	}

	@Override
	protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
		// AuthCache authCache = new BasicAuthCache();
		// BasicScheme basicAuth = new BasicScheme();
		HttpHost targetHost = new HttpHost(uri.getHost(), uri.getPort());
		// authCache.put(targetHost, basicAuth);
		// BasicHttpContext localcontext = new BasicHttpContext();
		// localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);
		//
		// UsernamePasswordCredentials credentials = new
		// UsernamePasswordCredentials(
		// username, sha1pass);
		// this.client.getCredentialsProvider().setCredentials(
		// new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT,
		// AuthScope.ANY_REALM), credentials);

		// this.client.getCredentialsProvider().setCredentials(
		// new AuthScope(targetHost.getHostName(), targetHost.getPort()),
		// new UsernamePasswordCredentials(username, sha1pass));

		this.client.getParams().setParameter("http.useragent", "collection-manager/0.1-test ( https://github.com/regueiro/collection-manager )");
		// Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();
		// Generate BASIC scheme object and add it to the local auth cache
		BasicScheme basicAuth = new BasicScheme();
		authCache.put(targetHost, basicAuth);

		// Add AuthCache to the execution context
		BasicHttpContext localcontext = new BasicHttpContext();
		localcontext.setAttribute(ClientContext.AUTH_CACHE, authCache);

		return localcontext;
	}
}