package es.regueiro.collectionManager.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.lang3.StringUtils;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import es.regueiro.collectionManager.ui.controller.MainWindowController;
import net.miginfocom.swing.MigLayout;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@Component
@Lazy
public class ArtistDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JXLabel lblMusicbrainzId;
	private JXLabel lblArtistName;
	private JXLabel lblSortName;
	private JXLabel lblDiscogsUrl;

	private String artistName;
	private String artistSortName;
	private String musicBrainzID;
	private String discogsURL;
	
	private boolean returnValue = false;
	private JXTextField txtfldName = new JXTextField();
	private JXTextField txtfldSortName = new JXTextField();
	private JXTextField txtfldId = new JXTextField();
	private JXTextField txtfldDiscogsUrl = new JXTextField();

	@Autowired
	private MainWindowController controller;
	/**
	 * Create the dialog.
	 */

	public boolean showDialog() {
		this.setVisible(true);
		return returnValue;
	}
	private void validateInput() {
//		String tempName = txtfldName.getText();
//		String tempSortName = txtfldSortName.getText();
//		String tempMBID = txtfldId.getText();
//		String tempDiscogs = txtfldDiscogsUrl.getText();
//		URL tempDiscogsURL = null;
//
//		boolean valid = true;
//
//		if (StringUtils.isEmpty(tempName)) {
//			valid = false;
//			throw new IllegalArgumentException(
//					"The entered artist name can not be blank");
//		}
//
//		if (!StringUtils.isEmpty(tempMBID)) {
//			String idRegex = "[0-9a-fA-F]{8}(?:-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}";
//			Pattern pattern = Pattern.compile(idRegex);
//			Matcher matcher = pattern.matcher(tempMBID);
//
//			if (!matcher.matches()) {
//				valid = false;
//				throw new IllegalArgumentException(
//						"The entered Musicbrainz ID is invalid");
//			}
//		}
//		if (!StringUtils.isEmpty(tempDiscogs)) {
//			try {
//				URL url = new URL(tempDiscogs);
//				tempDiscogsURL = url;
//			} catch (MalformedURLException exception) {
//				valid = false;
//				tempDiscogsURL = null;
//				throw new IllegalArgumentException(
//						"The entered discogs url is invalid");
//			}
//		} else {
//			tempDiscogsURL = null;
//		}
//
//		if (valid) {
//			this.artistName = tempName;
//			if (StringUtils.isEmpty(tempSortName)) {
//				this.artistSortName = tempName;
//			} else {
//				this.artistSortName = tempSortName;
//			}
//			if (!StringUtils.isEmpty(tempMBID)) {
//				this.musicBrainzID = tempMBID;
//			} else {
//				this.musicBrainzID = "";
//			}
//
//			this.discogsURL = tempDiscogsURL;
//		}
	}
	
	private void setValues() {

			this.artistName = txtfldName.getText();
			if (StringUtils.isEmpty(txtfldSortName.getText())) {
				this.artistSortName = txtfldName.getText();
			} else {
				this.artistSortName = txtfldSortName.getText();
			}
			if (!StringUtils.isEmpty(txtfldId.getText())) {
				this.musicBrainzID = txtfldId.getText();
			} else {
				this.musicBrainzID = "";
			}

			this.discogsURL = txtfldDiscogsUrl.getText();
					
			controller.addArtist(getArtistName(),
					getArtistSortName(),
					getMusicBrainzID(),
					getDiscogsURL());
	}

	/**
	 * @return the artistName
	 */
	public String getArtistName() {
		return artistName;
	}

	/**
	 * @return the artistSortName
	 */
	public String getArtistSortName() {
		return artistSortName;
	}

	/**
	 * @return the musicBrainzID
	 */
	public String getMusicBrainzID() {
		return musicBrainzID;
	}

	/**
	 * @return the discogsURL
	 */
	public String getDiscogsURL() {
		return discogsURL;
	}
	
	public ArtistDialog(String name, String sortName, String musicBrainzID, String digscogsURL) {
		this();
		txtfldName.setText(name);
		txtfldSortName.setText(sortName);
		txtfldId.setText(musicBrainzID);
		txtfldDiscogsUrl.setText(digscogsURL);
	}
	
	public ArtistDialog() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ArtistDialog.class.getResource("/glyph/user.png")));
		setTitle("Add artist");
		setBounds(100, 100, 390, 202);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("",
				"[89.00px]10px[235px,grow,fill]",
				"[20px]10px[]10px[14px]10px[]"));
		{
			lblArtistName = new JXLabel();
			lblArtistName.setText("Artist Name");
			contentPanel.add(lblArtistName,
					"cell 0 0,alignx right,aligny center");
		}
		{
			lblArtistName.setLabelFor(txtfldName);
			txtfldName.setPrompt("Name");
			contentPanel.add(txtfldName, "cell 1 0,growx,aligny top");
		}
		{
			lblSortName = new JXLabel();
			lblSortName.setText("Sort Name");
			contentPanel.add(lblSortName, "cell 0 1,alignx trailing");
		}
		{
			lblSortName.setLabelFor(txtfldSortName);
			txtfldSortName.setPrompt("Sort Name");
			contentPanel.add(txtfldSortName, "cell 1 1,growx");
		}
		{
			lblMusicbrainzId = new JXLabel();
			lblMusicbrainzId.setText("MusicBrainz ID");
			contentPanel.add(lblMusicbrainzId,
					"cell 0 2,alignx right,aligny center");
		}
		{
			lblMusicbrainzId.setLabelFor(txtfldId);
			txtfldId.setPrompt("ID");
			contentPanel.add(txtfldId, "cell 1 2,grow");
		}
		{
			lblDiscogsUrl = new JXLabel();
			lblDiscogsUrl.setText("Discogs URL");
			contentPanel.add(lblDiscogsUrl, "cell 0 3,alignx trailing");
		}
		{
			lblDiscogsUrl.setLabelFor(txtfldDiscogsUrl);
			txtfldDiscogsUrl.setPrompt("Discogs URL");
			contentPanel.add(txtfldDiscogsUrl, "cell 1 3,growx");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
//							validateInput();
							setValues();
							returnValue = true;
							dispose();
						} catch (IllegalArgumentException ex) {
							JOptionPane.showMessageDialog(
									contentPanel.getParent(), ex.getMessage(),
									"Input Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						returnValue = false;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
