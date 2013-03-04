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
import java.awt.FlowLayout;
import org.jdesktop.swingx.JXTree;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;

@Component
public class MainWindow implements BeanFactoryAware {

	private JFrame frame;
	@Autowired
	private MainWindowController controller;
	private ArtistDialog artistDialog;
	private BeanFactory factory;
	private JXList list;

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
						new MigLayout("", "[300.00]10px[grow]", "[][][grow]"));
				
				JPanel panel = new JPanel();
				frame.getContentPane().add(panel, "cell 0 0 2 1,grow");
				panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
				
				JToolBar toolBar_2 = new JToolBar();
				panel.add(toolBar_2);
				toolBar_2.setFloatable(false);
				
						JXButton btnAddArtist = new JXButton();
						toolBar_2.add(btnAddArtist);
						btnAddArtist.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								artistDialog = (ArtistDialog) factory.getBean("artistDialog");
								artistDialog.setLocationRelativeTo(frame);
								if (artistDialog.showDialog()) {
									list.setSelectedValue(artistDialog.getArtistName(), true);
								}
							}
						});
						btnAddArtist.setIcon(null);
						btnAddArtist.setText("Add Artist");
						
								JXButton btnEditArtist = new JXButton();
								toolBar_2.add(btnEditArtist);
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
								btnEditArtist.setIcon(null);
								btnEditArtist.setText("Edit Artist");
								
										JXButton btnRemoveArtist = new JXButton();
										toolBar_2.add(btnRemoveArtist);
										btnRemoveArtist.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												controller.removeArtist(list.getSelectedValue());
											}
										});
										btnRemoveArtist.setIcon(null);
										btnRemoveArtist.setText("Remove Artist");
										
										JToolBar toolBar_3 = new JToolBar();
										panel.add(toolBar_3);
										toolBar_3.setFloatable(false);
										
												JXButton btnAddRelease = new JXButton();
												toolBar_3.add(btnAddRelease);
												btnAddRelease.setIcon(null);
												btnAddRelease.setText("Add Release");
												
														JXButton btnEditSelected = new JXButton();
														toolBar_3.add(btnEditSelected);
														btnEditSelected.setIcon(null);
														btnEditSelected.setText("Edit Selected");
														
																JXButton btnDeleteSelected = new JXButton();
																toolBar_3.add(btnDeleteSelected);
																btnDeleteSelected.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent e) {
																	}
																});
																btnDeleteSelected.setIcon(null);
																btnDeleteSelected.setText("Delete Selected");
		
				JScrollPane scrollPane = new JScrollPane();
				frame.getContentPane().add(scrollPane, "flowx,cell 0 1 1 2,grow");
				
				list = new JXList();
				list.setAutoCreateRowSorter(true);
				list.setBorder(new EmptyBorder(5, 5, 5, 5));
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				list.setModel(controller.getArtistListModel());
				list.setSortable(true);
				list.setComparator(null);
				list.setSortOrder(SortOrder.ASCENDING);
				((DefaultSortController<?>) list.getRowSorter()).sort();
				scrollPane.setViewportView(list);
				
				JPanel panel_1 = new JPanel();
				frame.getContentPane().add(panel_1, "cell 1 1,grow");
				panel_1.setLayout(new MigLayout("", "[26px][grow]", "[14px][]"));
				
				JXLabel lblArtist = new JXLabel();
				lblArtist.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblArtist.setText("Audioslave");
				panel_1.add(lblArtist, "cell 0 0,alignx left,aligny center");
				
				JPanel panel_3 = new JPanel();
				panel_1.add(panel_3, "cell 1 0,alignx right,growy");
				panel_3.setLayout(new MigLayout("", "[][]", "[14px]"));
				
				JButton lblMb = new JButton();
				lblMb.setMargin(new Insets(2, 2, 2, 0));
				panel_3.add(lblMb, "cell 0 0");
				lblMb.setText("MB");
				
				JButton lblDg = new JButton();
				lblDg.setMargin(new Insets(2, 2, 2, 0));
				lblDg.setEnabled(false);
				panel_3.add(lblDg, "cell 1 0");
				lblDg.setText("DG");
				
				JPanel panel_2 = new JPanel();
				panel_1.add(panel_2, "cell 0 1 2 1,grow");
				panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 0));
				
				JXLabel lblReleases = new JXLabel();
				lblReleases.setText("17 Releases:");
				panel_2.add(lblReleases);
				
				JXLabel lblOwned = new JXLabel();
				lblOwned.setForeground(new Color(0, 128, 0));
				panel_2.add(lblOwned);
				lblOwned.setText("10 Owned");
				
				JXLabel label = new JXLabel();
				panel_2.add(label);
				label.setText("-");
				
				JXLabel lblPending = new JXLabel();
				lblPending.setForeground(new Color(0, 0, 128));
				panel_2.add(lblPending);
				lblPending.setText("3 Pending");
				
				JXLabel label_1 = new JXLabel();
				panel_2.add(label_1);
				label_1.setText("-");
				
				JXLabel lblMissing = new JXLabel();
				lblMissing.setForeground(new Color(165, 42, 42));
				panel_2.add(lblMissing);
				lblMissing.setText("5 Missing");
		
				JScrollPane scrollPane_1 = new JScrollPane();
				frame.getContentPane().add(scrollPane_1, "cell 1 2,grow");
				
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
