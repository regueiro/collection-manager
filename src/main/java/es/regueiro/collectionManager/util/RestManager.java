//TODO comment
package es.regueiro.collectionManager.util;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestManager {

	private DefaultHttpClient httpclient;
	private HttpComponentsClientHttpRequestFactory requestFactory;
	private RestTemplate restTemplate;

	public RestManager() {
		httpclient = new DefaultHttpClient();

		requestFactory = new HttpComponentsClientHttpRequestFactoryBasicAuth(
				httpclient);
		restTemplate = new RestTemplate(requestFactory);
	}

	public RestTemplate getTemplate() {
		return restTemplate;
	}

	public void setAuthentication(String host, int port, String username, String sha1password) {

		httpclient.getCredentialsProvider().setCredentials(
				new AuthScope(host, port),
				new UsernamePasswordCredentials(username, sha1password));
	}
}