package es.regueiro.collectionManager.ui;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import org.jdesktop.swingx.JXSearchField;

import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import org.jdesktop.swingx.JXTreeTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jdesktop.swingx.sort.DefaultSortController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.regueiro.collectionManager.ui.controller.MainWindowController;
import javax.swing.SortOrder;

@Component
public class MainWindow implements BeanFactoryAware {

	private JFrame frame;
	@Autowired
	private MainWindowController controller;
	private JXList list; 
	private ArtistDialog artistDialog;
	private BeanFactory factory;


	public void run() {
		frame.setVisible(true);
	}


	public MainWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	@PostConstruct
	private void initialize() {
		controller.populate();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1066, 736);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane()
				.setLayout(
						new MigLayout("", "[300]10px[]10px[grow,right]",
								"[]10px[grow]"));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel,
				"flowx,cell 0 0 2 1,alignx left,growy");
		panel.setLayout(new MigLayout("", "[]20px[]", "[]"));

		JToolBar toolBar = new JToolBar();
		panel.add(toolBar, "cell 0 0,alignx center,aligny top");
		toolBar.setFloatable(false);

		JXButton btnAddArtist = new JXButton();
		btnAddArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				artistDialog = (ArtistDialog) factory.getBean("artistDialog");
				artistDialog.setLocationRelativeTo(frame);
				if (artistDialog.showDialog()) {
					list.setSelectedValue(artistDialog.getArtistName(), true);
				}
			}
		});
		btnAddArtist.setIcon(new ImageIcon(MainWindow.class
				.getResource("/glyph/user.png")));
		btnAddArtist.setText("Add Artist");
		toolBar.add(btnAddArtist);

		JXButton btnEditArtist = new JXButton();
		btnEditArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// String artistName = (String) list.getSelectedValue();
				// Artist artist = controller.get
				// ArtistDialog editArtistDialog = new ArtistDialog();
				// editArtistDialog.setLocationRelativeTo(frame);
				// if (addArtistDialog.showDialog()) {
				// controller.addArtist(addArtistDialog.getArtistName(),
				// addArtistDialog.getArtistSortName(),
				// addArtistDialog.getMusicBrainzID(),
				// addArtistDialog.getDiscogsURL());
				// list.setSelectedValue(addArtistDialog.getArtistName(), true);
				// }
			}
		});
		btnEditArtist.setIcon(new ImageIcon(MainWindow.class
				.getResource("/glyph/edit.png")));
		toolBar.add(btnEditArtist);
		btnEditArtist.setText("Edit Artist");

		JXButton btnRemoveArtist = new JXButton();
		btnRemoveArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.removeArtist(list.getSelectedValue());
			}
		});
		btnRemoveArtist.setIcon(new ImageIcon(MainWindow.class
				.getResource("/glyph/remove_2.png")));
		btnRemoveArtist.setText("Remove Artist");
		toolBar.add(btnRemoveArtist);

		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		panel.add(toolBar_1, "cell 1 0,alignx center,aligny top");

		JXButton btnAddRelease = new JXButton();
		toolBar_1.add(btnAddRelease);
		btnAddRelease.setIcon(new ImageIcon(MainWindow.class
				.getResource("/glyph/music.png")));
		btnAddRelease.setText("Add Release");

		JXButton btnEditSelected = new JXButton();
		toolBar_1.add(btnEditSelected);
		btnEditSelected.setIcon(new ImageIcon(MainWindow.class
				.getResource("/glyph/albums.png")));
		btnEditSelected.setText("Edit Selected");

		JXButton btnDeleteSelected = new JXButton();
		toolBar_1.add(btnDeleteSelected);
		btnDeleteSelected.setIcon(new ImageIcon(MainWindow.class
				.getResource("/glyph/remove.png")));
		btnDeleteSelected.setText("Delete Selected");

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Artists",
				"Releases" }));
		frame.getContentPane().add(comboBox, "flowx,cell 2 0");

		JXSearchField srchfldSearchRelease = new JXSearchField();
		srchfldSearchRelease.setHorizontalAlignment(SwingConstants.TRAILING);
		srchfldSearchRelease.setMaximumSize(new Dimension(500, 2147483647));
		srchfldSearchRelease.setPrompt("Search Release");
		srchfldSearchRelease.setToolTipText("Search Release");
		frame.getContentPane().add(srchfldSearchRelease, "cell 2 0,growx");

		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, "cell 0 1,grow");

		JXLabel lblArtists = new JXLabel();
		lblArtists.setBorder(new EmptyBorder(5, 5, 5, 5));
		lblArtists.setHorizontalAlignment(SwingConstants.LEFT);
		lblArtists.setPreferredSize(new Dimension(150, 25));
		lblArtists.setMinimumSize(new Dimension(0, 20));
		lblArtists.setText("Artists");
		scrollPane.setColumnHeaderView(lblArtists);

		list = new JXList();
		list.setAutoCreateRowSorter(true);
		list.setBorder(new EmptyBorder(5, 5, 5, 5));
		lblArtists.setLabelFor(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(controller.getArtistListModel());
		list.setSortable(true);
		list.setComparator(null);
		list.setSortOrder(SortOrder.ASCENDING);
		((DefaultSortController<?>) list.getRowSorter()).sort();

		scrollPane.setViewportView(list);

		JScrollPane scrollPane_1 = new JScrollPane();
		frame.getContentPane().add(scrollPane_1, "cell 1 1 2 1,grow");

		JXTreeTable treeTable = new JXTreeTable();
		scrollPane_1.setViewportView(treeTable);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNewLibrary = new JMenuItem("New Library");
		mnFile.add(mntmNewLibrary);

		JMenuItem mntmOpenLibrary = new JMenuItem("Open Library");
		mnFile.add(mntmOpenLibrary);

		JMenuItem mntmSaveLibrary = new JMenuItem("Save Library");
		mnFile.add(mntmSaveLibrary);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);

		JMenu mnNewMenu = new JMenu("Artists");
		menuBar.add(mnNewMenu);

		JMenuItem mntmAdd = new JMenuItem("Add");
		mnNewMenu.add(mntmAdd);

		JMenuItem mntmEdit = new JMenuItem("Edit");
		mnNewMenu.add(mntmEdit);

		JRadioButtonMenuItem rdbtnmntmDelete = new JRadioButtonMenuItem(
				"Delete");
		mnNewMenu.add(rdbtnmntmDelete);

		JMenu mnReleases = new JMenu("Releases");
		menuBar.add(mnReleases);

		JMenuItem mntmAdd_1 = new JMenuItem("Add");
		mnReleases.add(mntmAdd_1);

		JMenuItem mntmEdit_1 = new JMenuItem("Edit");
		mnReleases.add(mntmEdit_1);

		JMenuItem mntmDelete = new JMenuItem("Delete");
		mnReleases.add(mntmDelete);

		JMenu mnMusicbrainz = new JMenu("Musicbrainz");
		menuBar.add(mnMusicbrainz);

		JMenuItem mntmImportArtist = new JMenuItem("Import Artist");
		mnMusicbrainz.add(mntmImportArtist);

		JMenuItem mntmImportDiscography = new JMenuItem("Import Discography");
		mnMusicbrainz.add(mntmImportDiscography);

		JMenuItem mntmImportSingleRelease = new JMenuItem(
				"Import Single Release");
		mnMusicbrainz.add(mntmImportSingleRelease);

		JMenu mnDiscogs = new JMenu("Discogs");
		menuBar.add(mnDiscogs);

		JMenuItem menuItem = new JMenuItem("Import Artist");
		mnDiscogs.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("Import Discography");
		mnDiscogs.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("Import Single Release");
		mnDiscogs.add(menuItem_2);

		JMenu mnAbout = new JMenu("Help");
		menuBar.add(mnAbout);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnAbout.add(mntmAbout);
	}

	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		this.factory = arg0;
	}

}
