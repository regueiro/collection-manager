package es.regueiro.collectionManager.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.regueiro.collectionManager.ui.controller.MainWindowController;

public class SpringWin {

	private JFrame frame;
	private ClassPathXmlApplicationContext context;
	private MainWindowController controller;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpringWin window = new SpringWin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SpringWin() {
		context = new ClassPathXmlApplicationContext("collection-beans.xml");

		controller = (MainWindowController) context
				.getBean("mainWindowController");
		
		initialize();
//		controller.addArtist("name", "sortName", "musicBrainzID", "discogsURL");
		controller.populate();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
