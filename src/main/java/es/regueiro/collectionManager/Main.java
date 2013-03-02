package es.regueiro.collectionManager;

import java.util.List;

import javax.swing.UIManager;

import org.musicbrainz.controller.Artist;
import org.musicbrainz.model.searchresult.ArtistResultWs2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.regueiro.collectionManager.ui.MainWindow;
import es.regueiro.collectionManager.ui.controller.MainWindowController;

public class Main {

	private static ClassPathXmlApplicationContext context;

	public static void main(String[] args) {

		context = new ClassPathXmlApplicationContext(
				"/es/regueiro/collectionManager/collection-beans.xml");

		MainWindow window = (MainWindow) context.getBean("mainWindow");

		window.run();

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
