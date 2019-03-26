package view;


import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import jaco.mp3.player.MP3Player;
import model.PlaylistList;
import model.SongList;
import view.AddSong;
import view.GuestView;
import view.SelectAccount;

import java.awt.BorderLayout;
import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;


import jaco.mp3.player.MP3Player;
import model.PlaylistList;
import model.SongList;
import model.account;

public class RegisteredUserView extends JFrame {
	private volatile static RegisteredUserView instance = null;
	MP3Player mp3 = new MP3Player(new File("currentSong.mp3"));

	private JPanel contentPane;
	public String currentUser;
	JButton btnPickPlaylist, btnPickSong, btnCreatePlaylist, btnUploadSong, btnEditSong, btnPlay, btnPause, btnNextSong, btnPreviousSong;
	JList yourSongsList, playlistListJList;
	JTextPane txtpnSongNameGenre;
	private JButton btnRefresh;
	JLabel lblUser;
	PlaylistList pl;
	
	public static RegisteredUserView getInstance() {
        if (instance == null) {
        	instance = new RegisteredUserView();
        }
		return instance;
	}

	public void setSong(String path) {
		mp3.stop();
		mp3 = new MP3Player(new File(path));
	}
	
	/**
	 * Create the frame.
	 */
	public RegisteredUserView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisteredUserView.class.getResource("/images/spotify.png")));
		setTitle("Not So Spotify");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 1189, 813);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 btnPlay = new JButton("");
		 btnPlay.setIcon(new ImageIcon("C:\\Users\\Antonello Santos\\Documents\\DesignChallenge2\\src\\images\\play-button.png"));
		btnPlay.addActionListener(new btn_Play());
		btnPlay.setBounds(492, 681, 89, 45);
		contentPane.add(btnPlay);
		
		 btnPause = new JButton("");
		 btnPause.setIcon(new ImageIcon(RegisteredUserView.class.getResource("/images/pause-button.png")));
		btnPause.addActionListener(new btn_Pause());
		btnPause.setBounds(593, 681, 89, 45);
		contentPane.add(btnPause);
		
		 btnNextSong = new JButton("");
		 btnNextSong.setIcon(new ImageIcon(RegisteredUserView.class.getResource("/images/skip-to-next-track.png")));
		btnNextSong.setBounds(705, 681, 89, 45);
		contentPane.add(btnNextSong);
		btnNextSong.addActionListener(new btn_nextSong());
		
		 btnPreviousSong = new JButton("");
		 btnPreviousSong.setIcon(new ImageIcon(RegisteredUserView.class.getResource("/images/back-track.png")));
		btnPreviousSong.setBounds(380, 681, 89, 45);
		contentPane.add(btnPreviousSong);
		btnPreviousSong.addActionListener(new btn_prevSong());
		
		playlistListJList = new JList();
		playlistListJList.setBounds(25, 93, 322, 558);
		contentPane.add(playlistListJList);
		for(int x = 0; x < pl.getPlaylistSize() ;x++) {
			playlistListJList.add(playlistListJList.getComponent(x));
		}
		
		JList playlistSongList = new JList();
		playlistSongList.setBounds(403, 139, 375, 224);
		contentPane.add(playlistSongList);
		
		 btnPickPlaylist = new JButton("Pick Playlist");
		btnPickPlaylist.setBounds(25, 664, 136, 45);
		contentPane.add(btnPickPlaylist);
		
		JButton btnPickSong = new JButton("Add Song to Playlist");
		btnPickSong.setBounds(957, 664, 190, 45);
		contentPane.add(btnPickSong);
		btnPickSong.addActionListener(new btn_addSongtoP());
		 
		txtpnSongNameGenre = new JTextPane();
		txtpnSongNameGenre.setBounds(403, 405, 375, 209);
		txtpnSongNameGenre.setText("Song Name: \r\nGenre: \r\n");
		contentPane.add(txtpnSongNameGenre);
		
		JLabel lblSongInfo = new JLabel("Song Info");
		lblSongInfo.setBounds(403, 373, 95, 26);
		contentPane.add(lblSongInfo);
		
		btnCreatePlaylist = new JButton("Create Playlist");
		btnCreatePlaylist.addActionListener(new btn_CreatePlaylist());
		btnCreatePlaylist.setBounds(53, 11, 118, 45);
		contentPane.add(btnCreatePlaylist);
		
		 btnUploadSong = new JButton("Upload Song");
		btnUploadSong.addActionListener(new btn_UploadSong());
		btnUploadSong.setBounds(1021, 11, 126, 45);
		contentPane.add(btnUploadSong);
		
		 yourSongsList = new JList();
		yourSongsList.setBounds(806, 94, 341, 557);
		contentPane.add(yourSongsList);
		
		JLabel lblSongsInPlaylist = new JLabel("Songs in Playlist");
		lblSongsInPlaylist.setBounds(403, 112, 101, 26);
		contentPane.add(lblSongsInPlaylist);
		
		JLabel lblPlaylists = new JLabel("Playlists");
		lblPlaylists.setBounds(53, 69, 95, 14);
		contentPane.add(lblPlaylists);
		
		JLabel lblYourSongs = new JLabel("Your Songs");
		lblYourSongs.setBounds(825, 69, 95, 14);
		contentPane.add(lblYourSongs);
		
		 btnEditSong = new JButton("Edit Song");
		btnEditSong.setBounds(905, 11, 89, 45);
		contentPane.add(btnEditSong);
		btnEditSong.addActionListener(new btn_editsong());
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new btn_Refresh());
		btnRefresh.setBounds(760, 21, 97, 25);
		contentPane.add(btnRefresh);
		
		lblUser = new JLabel("Current User: " + currentUser );
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUser.setBounds(346, 20, 342, 16);
		contentPane.add(lblUser);
		
		JButton shuffleButton = new JButton("");
		shuffleButton.setIcon(new ImageIcon(RegisteredUserView.class.getResource("/images/shuffle.png")));
		shuffleButton.setBounds(279, 681, 89, 45);
		contentPane.add(shuffleButton);
		shuffleButton.addActionListener(new btn_shuffle());
		
		JButton repeatButton = new JButton("");
		repeatButton.setIcon(new ImageIcon(RegisteredUserView.class.getResource("/images/repeat.png")));
		repeatButton.setBounds(806, 681, 89, 45);
		contentPane.add(repeatButton);
		repeatButton.addActionListener(new btn_repeat());
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(1050, 728, 97, 25);
		contentPane.add(logoutButton);
		logoutButton.addActionListener(new btn_Logout());
	}
	
	 class btn_Play implements ActionListener 
	 {

	     public void actionPerformed(ActionEvent e) 
	     {	 
	    	 mp3.play();

	     }
	 }
	
	 class btn_addSongtoP implements ActionListener 
	 {

	     public void actionPerformed(ActionEvent e) 
	     {
	        //JOptionPane.showMessageDialog(null,"Added " + new song);
	    	 //mp3.addToPlayList();

	     }
	 }
	 
	 class btn_Pause implements ActionListener 
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 mp3.pause();
		 }
	 }
	 
	 class btn_shuffle implements ActionListener 
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			if(mp3.isShuffle())
			{
				mp3.setShuffle(true);
			}
			else 
			{
				mp3.setShuffle(false);
			}
		 
		 }
	 }
	 
	 class btn_repeat implements ActionListener 
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 mp3.setRepeat(true);
		 }
	 }
	 class btn_UploadSong implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 AddSong.getInstance().setVisible(true);
		 }
	 }
	 
	 class btn_Refresh implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 
			 
			 SongList sList = new SongList();
			 DefaultListModel DLM = new DefaultListModel();
			 
			 for(int x = 0; x < sList.getSongSize(); x++)
			 DLM.addElement(sList.getSongList().get(x).getSongName());

			 yourSongsList.setModel(DLM);
			 
			 PlaylistList pList = new PlaylistList();
			 DefaultListModel DLM2 = new DefaultListModel();
			 
			 for(int x = 0; x < pList.getPlaylistSize(); x++)
			 DLM2.addElement(pList.getPlaylistList().get(x).getPlaylistName());

			 
			 
			 
		 }
	 }
	 
	 class btn_CreatePlaylist implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 
			 CreatePlaylist cp = new CreatePlaylist();
			 cp.setVisible(true);
			 
			 
		 }
	 }
	 
	 class btn_Logout implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			JOptionPane.showMessageDialog(null,"You have been Logged out!");
			SelectAccount.getInstance().setVisible(true);
			closingWindow();
		 }
	 }
	 
	 class btn_editsong implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			EditSongView.getInstance().setVisible(true);
			
		 }
	 }
	 
	 class btn_nextSong implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			mp3.skipForward();
			
		 }
	 }
	 
	 class btn_prevSong implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			mp3.skipBackward();
			
		 }
	 }
		public void closingWindow() {
			this.setVisible(false);
		}
		
		public void setUserName(String currentUser) {
			this.currentUser = currentUser;
			lblUser.setText("Current User: " + currentUser);
		}
}
