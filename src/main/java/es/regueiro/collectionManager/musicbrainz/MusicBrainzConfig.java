//TODO comment
package es.regueiro.collectionManager.musicbrainz;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MusicBrainzConfig {
	//burn public static final String apiKey = "b8a0872e6d764f1545f5a736d6745c0e";
	public static final String apiKey = "09b9868e9ac49808db86d036e94cde1b";
	public static final String traktHost = "www.musicbrainz.org";
	public static final int traktPort = 80;
	public static final String baseURL = "http://www.musicbrainz.org/ws/2";
	
	public static Map<String,String> getBaseMap() {
		Map<String,String> vars = new HashMap<String, String>();
		vars.put("apiKey", MusicBrainzConfig.apiKey);
		vars.put("baseURL", MusicBrainzConfig.baseURL);
		
		return Collections.unmodifiableMap(vars);
	}
}


