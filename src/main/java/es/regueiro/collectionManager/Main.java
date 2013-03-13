package es.regueiro.collectionManager;

import java.util.List;

import javax.swing.UIManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


import es.regueiro.collectionManager.model.Artist;
import es.regueiro.collectionManager.persistency.ArtistDAO;
import es.regueiro.collectionManager.ui.MainWindow;
import es.regueiro.collectionManager.ui.controller.MainWindowController;

public class Main {

	
	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {
		
		
		context = new ClassPathXmlApplicationContext(
				"collection-beans.xml");

//		MainWindow window = (MainWindow) context.getBean("mainWindow");
//
//		window.run();

//		dao = (ArtistDAO) context.getBean("hibernateArtistDAO");
//		dao.toString();
//		
		HibTest test = (HibTest) context.getBean("hibTest");
		test.test();
		
		// Artist controller = new Artist();
		//
		// controller.search("Audio");
		//
		// List<ArtistResultWs2> results;
		// results = controller.getFullSearchResultList();
		//
		// for (ArtistResultWs2 result:results) {
		// System.out.println(result.getArtist().getName());
		// }
	}

}
