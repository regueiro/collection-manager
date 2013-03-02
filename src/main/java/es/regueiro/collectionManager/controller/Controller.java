package es.regueiro.collectionManager.controller;

import java.util.List;


import org.musicbrainz.controller.*;
import org.musicbrainz.model.entity.ReleaseGroupWs2;
import org.musicbrainz.model.searchresult.ArtistResultWs2;
import org.musicbrainz.model.searchresult.listelement.ArtistSearchResultsWs2;

public class Controller {

	private Artist artistControler = new Artist();
	private ReleaseGroup releaseControler = new ReleaseGroup();
	private Release concreteReleaseController = new Release();
	
	public List<ArtistResultWs2> searchArtist(String name) {
		artistControler.search(name);
		
		return artistControler.getFullSearchResultList();
	}
	

	
}