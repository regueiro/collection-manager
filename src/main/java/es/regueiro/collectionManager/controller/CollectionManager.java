//package es.regueiro.collectionManager.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import es.regueiro.collectionManager.library.Library;
//import es.regueiro.collectionManager.model.artist.Artist;
//
//@Component
//public class CollectionManager {
//	@Autowired
//	private Library library;
//	@Autowired
//	private OldArtistController controller;
//	
//	public void testCollection() {
//		Artist artist = controller.createArtist("name");
//		library.addArtist(artist);
//		printCollection();
//	}
//	
//	
//	private void printCollection() {
//		for (Artist artist : library.getArtistList()) {
//			System.out.println(artist.getName());
//		}
//	}
//}
