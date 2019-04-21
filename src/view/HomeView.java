package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jaco.mp3.player.MP3Player;
import model.Playlist;
import model.PlaylistList;
import model.Song;
import model.SongList;
import model.generalModel;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class HomeView extends JFrame {
	
	ListenerView listener;
	private JPanel contentPane;
	private JTextField txtSearch;
	boolean evenClick = false;
	private volatile static HomeView instance = null;
	MP3Player mp3 = new MP3Player(new File("currentSong.mp3"));
	RegisteredUserProfile profile;
	public String currentUser;
	JButton btnPickPlaylist, btnPickSong, btnCreatePlaylist, btnUploadSong, btnEditSong, btnPlay, btnPause, btnNextSong, btnPreviousSong, btnByGenre,Profile;
	JList yourSongsList, yourSongsListJList, playlistListJList;
	JTextPane txtpnSongNameGenre;
	private JButton btnRefresh;
	JLabel lblUser;
	PlaylistList pl;
	SongList sl;
	ArrayList<Song> userSongs,userSongsUpdated;
	ArrayList<Playlist> userPlaylist;
	boolean songChanged;
	private JButton btnProfile;
	boolean playSongInPlaylist;
	private JButton btnByAlbum;
	private JButton btnByYear;
	ArrayList<Playlist> userPlaylists;
	
	public static HomeView getInstance() {
        if (instance == null) {
        	instance = new HomeView();
        }
		return instance;
	}
	

	/**
	 * Create the frame.
	 */
	public HomeView() {
		setBackground(new Color(254,254,250));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1152, 700);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(254,254,250));
		contentPane.setForeground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel MainRectangle = new JPanel();
		MainRectangle.setBackground(new Color(30,58,42));
		MainRectangle.setBounds(119, 579, 1027, 92);
		contentPane.add(MainRectangle);
		MainRectangle.setLayout(null);
		
		JPanel SongDetails = new JPanel();
		SongDetails.setBackground(new Color(30,58,42));
		SongDetails.setBounds(0, 0, 191, 101);
		MainRectangle.add(SongDetails);
		SongDetails.setLayout(null);
		
		txtpnSongNameGenre = new JTextPane();
		txtpnSongNameGenre.setBounds(0, 0, 191, 90);
		SongDetails.add(txtpnSongNameGenre);
		txtpnSongNameGenre.setText("Song Name:\r\nArtist:\r\nAlbum:\r\nGenre:\r\nYear:");
		
		JButton Shufflebtn = new JButton("");
		Shufflebtn.setIcon(new ImageIcon(HomeView.class.getResource("/images2/shuffle (4).png")));
		Shufflebtn.setBackground(new Color(30,58,42));
		Shufflebtn.setBounds(290, 31, 39, 39);
		Shufflebtn.setBorder(null);
		MainRectangle.add(Shufflebtn);
		
		JButton Nextbtn = new JButton("");
		Nextbtn.setIcon(new ImageIcon(HomeView.class.getResource("/images2/next (2).png")));
		Nextbtn.setBackground(new Color(30, 58, 42));
		Nextbtn.setBounds(512, 31, 39, 39);
		Nextbtn.setBorder(null);
		MainRectangle.add(Nextbtn);
		
		JButton Prevbtn = new JButton("");
		Prevbtn.setIcon(new ImageIcon(HomeView.class.getResource("/images2/back (2).png")));
		Prevbtn.setBackground(new Color(30, 58, 42));
		Prevbtn.setBounds(355, 31, 39, 39);
		Prevbtn.setBorder(null);
		MainRectangle.add(Prevbtn);
		
		btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon(HomeView.class.getResource("/images2/play-button (2).png")));
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(evenClick) {
					btnPlay.setIcon(new ImageIcon(HomeView.class.getResource("/images2/play-button (2).png")));
				evenClick = false;
			}
				else {
					btnPlay.setIcon(new ImageIcon(HomeView.class.getResource("/images2/rounded-pause-button.png")));
					evenClick = true;
				}
				}
		});
		btnPlay.setBounds(413, 15, 78, 70);
		btnPlay.setBackground(new Color(30, 58, 42));
		btnPlay.setBorder(null);
		MainRectangle.add(btnPlay);
		
		JButton Repeatbtn = new JButton("");
		Repeatbtn.setIcon(new ImageIcon(HomeView.class.getResource("/images2/repeat.png")));
		Repeatbtn.setBackground(new Color(30, 58, 42));
		Repeatbtn.setBounds(577, 31, 39, 39);
		Repeatbtn.setBorder(null);
		MainRectangle.add(Repeatbtn);
		
		JButton Queuebtn = new JButton("");
		Queuebtn.setIcon(new ImageIcon(HomeView.class.getResource("/images2/list (1).png")));
		Queuebtn.setBorder(null);
		Queuebtn.setBackground(new Color(30, 58, 42));
		Queuebtn.setBounds(769, 31, 39, 39);
		MainRectangle.add(Queuebtn);
		
		JButton StopBtn = new JButton("");
		StopBtn.setIcon(new ImageIcon(HomeView.class.getResource("/images2/stop (3).png")));
		StopBtn.setBorder(null);
		StopBtn.setBackground(new Color(30, 58, 42));
		StopBtn.setBounds(818, 31, 39, 39);
		MainRectangle.add(StopBtn);
		
		JButton Volumebtn = new JButton("");
		Volumebtn.setIcon(new ImageIcon(HomeView.class.getResource("/images2/speaker (2).png")));
		Volumebtn.setBorder(null);
		Volumebtn.setBackground(new Color(30, 58, 42));
		Volumebtn.setBounds(867, 31, 39, 39);
		MainRectangle.add(Volumebtn);
		
		
		JButton NewAlbumPic = new JButton("");
		NewAlbumPic.setIcon(new ImageIcon(HomeView.class.getResource("/images2/photo.png")));
		NewAlbumPic.setBounds(0, 579, 119, 92);
		contentPane.add(NewAlbumPic);
		NewAlbumPic.setBackground(new Color(170, 187, 204));
		
		JPanel TopBar = new JPanel();
		TopBar.setBackground(new Color(30, 58, 42));
		TopBar.setBounds(0, 0, 1152, 61);
		contentPane.add(TopBar);
		TopBar.setLayout(null);
		
		JButton button = new JButton("");
		button.setBounds(10, 11, 39, 39);
		button.setIcon(new ImageIcon(HomeView.class.getResource("/images2/expand-button.png")));
		button.setBorder(null);
		button.setBackground(new Color(30, 58, 42));
		TopBar.add(button);
		
		txtSearch = new JTextField();
		txtSearch.setForeground(SystemColor.desktop);
		txtSearch.setText("Search");
		txtSearch.setHorizontalAlignment(SwingConstants.LEFT);
		txtSearch.setBounds(95, 11, 170, 39);
		TopBar.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton SearchBtn = new JButton("");
		SearchBtn.setIcon(new ImageIcon(HomeView.class.getResource("/images2/magnifying-glass (1).png")));
		SearchBtn.setBorder(null);
		SearchBtn.setBackground(new Color(30,58,42));
		SearchBtn.setBounds(55, 11, 39, 39);
		SearchBtn.setBorder(null);
		TopBar.add(SearchBtn);
		
		btnProfile = new JButton("");
		btnProfile.setIcon(new ImageIcon(HomeView.class.getResource("/images2/user-avatar-main-picture.png")));
		btnProfile.setBounds(478, 10, 40, 40);
		TopBar.add(btnProfile);
		btnProfile.setBackground(new Color(170, 187, 204));
		btnProfile.addActionListener(new btn_Profile());
		
		Profile = new JButton(currentUser);
		Profile.setBackground(new Color(30,58,42));
		Profile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Profile.setForeground(Color.WHITE);
		Profile.setBounds(520, 19, 145, 23);
		Profile.setBorder(null);
		TopBar.add(Profile);
		
		JButton Notificationbtn = new JButton("");
		Notificationbtn.setIcon(new ImageIcon(HomeView.class.getResource("/images2/notifications-button.png")));
		Notificationbtn.setBorder(null);
		Notificationbtn.setBackground(new Color(30, 58, 42));
		Notificationbtn.setBounds(1084, 11, 39, 39);
		TopBar.add(Notificationbtn);
		
		btnRefresh = new JButton("");
		btnRefresh.setIcon(new ImageIcon(HomeView.class.getResource("/images2/reload.png")));
		btnRefresh.setBorder(null);
		btnRefresh.setBackground(new Color(30, 58, 42));
		btnRefresh.setBounds(1035, 11, 39, 39);
		TopBar.add(btnRefresh);
		btnRefresh.addActionListener(new btn_Refresh());
		
		JPanel MusicPanel = new JPanel();
		MusicPanel.setBackground(new Color(254, 254, 250));
		MusicPanel.setBounds(0, 62, 186, 514);
		contentPane.add(MusicPanel);
		MusicPanel.setLayout(null);
		
		JButton btnNewButton = new JButton("New Playlist");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setIcon(new ImageIcon(HomeView.class.getResource("/images2/add-circular-outlined-button (1).png")));
		btnNewButton.setBounds(0, 429, 186, 88);
		MusicPanel.add(btnNewButton);
		
		JLabel MusicLbl = new JLabel("Music");
		MusicLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		MusicLbl.setBackground(new Color(254, 254, 250));
		MusicLbl.setHorizontalAlignment(SwingConstants.CENTER);
		MusicLbl.setBounds(0, 0, 186, 34);
		MusicPanel.add(MusicLbl);
		
		JButton Library = new JButton("Library");
		Library.setBackground(new Color(242, 203, 155));
		Library.setHorizontalAlignment(SwingConstants.LEFT);
		Library.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Library.setBounds(0, 33, 186, 30);
		MusicPanel.add(Library);
		
		JButton Artist_Music = new JButton("Artist");
		Artist_Music.setHorizontalAlignment(SwingConstants.LEFT);
		Artist_Music.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Artist_Music.setBackground(new Color(254, 254, 250));
		Artist_Music.setBounds(0, 62, 186, 30);
		MusicPanel.add(Artist_Music);
		
		JButton Genre_Music = new JButton("Genre");
		Genre_Music.setHorizontalAlignment(SwingConstants.LEFT);
		Genre_Music.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Genre_Music.setBackground(new Color(254, 254, 250));
		Genre_Music.setBounds(0, 119, 186, 30);
		MusicPanel.add(Genre_Music);
		
		JButton Albums_Music = new JButton("Albums");
		Albums_Music.setHorizontalAlignment(SwingConstants.LEFT);
		Albums_Music.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Albums_Music.setBackground(new Color(242, 203, 155));
		Albums_Music.setBounds(0, 90, 186, 30);
		MusicPanel.add(Albums_Music);
		
		JButton Playlist_Name3 = new JButton("");
		Playlist_Name3.setEnabled(false);
		Playlist_Name3.setHorizontalAlignment(SwingConstants.LEFT);
		Playlist_Name3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Playlist_Name3.setBackground(new Color(254, 254, 250));
		Playlist_Name3.setBounds(0, 232, 186, 30);
		MusicPanel.add(Playlist_Name3);
		
		JButton Playlist_Name2 = new JButton("");
		Playlist_Name2.setEnabled(false);
		Playlist_Name2.setHorizontalAlignment(SwingConstants.LEFT);
		Playlist_Name2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Playlist_Name2.setBackground(new Color(242, 203, 155));
		Playlist_Name2.setBounds(0, 203, 186, 30);
		MusicPanel.add(Playlist_Name2);
		
		JButton Playlist_Name1 = new JButton("");
		Playlist_Name1.setEnabled(false);
		Playlist_Name1.setHorizontalAlignment(SwingConstants.LEFT);
		Playlist_Name1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Playlist_Name1.setBackground(new Color(254, 254, 250));
		Playlist_Name1.setBounds(0, 175, 186, 30);
		MusicPanel.add(Playlist_Name1);
		
		JButton Playlists_Music = new JButton("Playlists");
		Playlists_Music.setHorizontalAlignment(SwingConstants.LEFT);
		Playlists_Music.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Playlists_Music.setBackground(new Color(242, 203, 155));
		Playlists_Music.setBounds(0, 146, 186, 30);
		MusicPanel.add(Playlists_Music);
		
		JButton Playlist_Name7 = new JButton("");
		Playlist_Name7.setEnabled(false);
		Playlist_Name7.setHorizontalAlignment(SwingConstants.LEFT);
		Playlist_Name7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Playlist_Name7.setBackground(new Color(254, 254, 250));
		Playlist_Name7.setBounds(0, 344, 186, 30);
		MusicPanel.add(Playlist_Name7);
		
		JButton Playlist_Name6 = new JButton("");
		Playlist_Name6.setEnabled(false);
		Playlist_Name6.setHorizontalAlignment(SwingConstants.LEFT);
		Playlist_Name6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Playlist_Name6.setBackground(new Color(242, 203, 155));
		Playlist_Name6.setBounds(0, 315, 186, 30);
		MusicPanel.add(Playlist_Name6);
		
		JButton Playlist_Name5 = new JButton("");
		Playlist_Name5.setEnabled(false);
		Playlist_Name5.setHorizontalAlignment(SwingConstants.LEFT);
		Playlist_Name5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Playlist_Name5.setBackground(new Color(254, 254, 250));
		Playlist_Name5.setBounds(0, 287, 186, 30);
		MusicPanel.add(Playlist_Name5);
		
		JButton Playlist_Name4 = new JButton("");
		Playlist_Name4.setEnabled(false);
		Playlist_Name4.setHorizontalAlignment(SwingConstants.LEFT);
		Playlist_Name4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Playlist_Name4.setBackground(new Color(242, 203, 155));
		Playlist_Name4.setBounds(0, 258, 186, 30);
		MusicPanel.add(Playlist_Name4);
		
		JButton Playlist_Name9 = new JButton("");
		Playlist_Name9.setEnabled(false);
		Playlist_Name9.setHorizontalAlignment(SwingConstants.LEFT);
		Playlist_Name9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Playlist_Name9.setBackground(new Color(254, 254, 250));
		Playlist_Name9.setBounds(0, 401, 186, 30);
		MusicPanel.add(Playlist_Name9);
		
		JButton Playlist_Name8 = new JButton("");
		Playlist_Name8.setEnabled(false);
		Playlist_Name8.setHorizontalAlignment(SwingConstants.LEFT);
		Playlist_Name8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Playlist_Name8.setBackground(new Color(242, 203, 155));
		Playlist_Name8.setBounds(0, 372, 186, 30);
		MusicPanel.add(Playlist_Name8);
		
		JList Playlist_List = new JList();
		Playlist_List.setBounds(0, 175, 186, 253);
		MusicPanel.add(Playlist_List);
		
		JPanel RecentlyPlayedPanel = new JPanel();
		RecentlyPlayedPanel.setLayout(null);
		RecentlyPlayedPanel.setBackground(new Color(254, 254, 250));
		RecentlyPlayedPanel.setBounds(960, 62, 186, 514);
		contentPane.add(RecentlyPlayedPanel);
		
		JLabel label = new JLabel("Recently Played");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBackground(new Color(254, 254, 250));
		label.setBounds(0, 0, 186, 34);
		RecentlyPlayedPanel.add(label);
		
		JButton RPSONG_1 = new JButton("");
		RPSONG_1.setEnabled(false);
		RPSONG_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		RPSONG_1.setHorizontalAlignment(SwingConstants.LEFT);
		RPSONG_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RPSONG_1.setBackground(new Color(242, 203, 155));
		RPSONG_1.setBounds(0, 33, 186, 30);
		RecentlyPlayedPanel.add(RPSONG_1);
		
		JButton RPSONG_2 = new JButton("");
		RPSONG_2.setEnabled(false);
		RPSONG_2.setHorizontalAlignment(SwingConstants.LEFT);
		RPSONG_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RPSONG_2.setBackground(new Color(254, 254, 250));
		RPSONG_2.setBounds(0, 62, 186, 30);
		RecentlyPlayedPanel.add(RPSONG_2);
		
		JButton RPSONG_4 = new JButton("");
		RPSONG_4.setEnabled(false);
		RPSONG_4.setHorizontalAlignment(SwingConstants.LEFT);
		RPSONG_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RPSONG_4.setBackground(new Color(254, 254, 250));
		RPSONG_4.setBounds(0, 119, 186, 30);
		RecentlyPlayedPanel.add(RPSONG_4);
		
		JButton RPSONG_3 = new JButton("");
		RPSONG_3.setEnabled(false);
		RPSONG_3.setHorizontalAlignment(SwingConstants.LEFT);
		RPSONG_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RPSONG_3.setBackground(new Color(242, 203, 155));
		RPSONG_3.setBounds(0, 90, 186, 30);
		RecentlyPlayedPanel.add(RPSONG_3);
		
		JButton RPSONG_7 = new JButton("");
		RPSONG_7.setEnabled(false);
		RPSONG_7.setHorizontalAlignment(SwingConstants.LEFT);
		RPSONG_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RPSONG_7.setBackground(new Color(242, 203, 155));
		RPSONG_7.setBounds(0, 203, 186, 30);
		RecentlyPlayedPanel.add(RPSONG_7);
		
		JButton RPSONG_6 = new JButton("");
		RPSONG_6.setEnabled(false);
		RPSONG_6.setHorizontalAlignment(SwingConstants.LEFT);
		RPSONG_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RPSONG_6.setBackground(new Color(254, 254, 250));
		RPSONG_6.setBounds(0, 175, 186, 30);
		RecentlyPlayedPanel.add(RPSONG_6);
		
		JButton RPSONG_5 = new JButton("");
		RPSONG_5.setEnabled(false);
		RPSONG_5.setHorizontalAlignment(SwingConstants.LEFT);
		RPSONG_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RPSONG_5.setBackground(new Color(242, 203, 155));
		RPSONG_5.setBounds(0, 146, 186, 30);
		RecentlyPlayedPanel.add(RPSONG_5);
		
		JButton MPSONG_3 = new JButton("");
		MPSONG_3.setEnabled(false);
		MPSONG_3.setHorizontalAlignment(SwingConstants.LEFT);
		MPSONG_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MPSONG_3.setBackground(new Color(254, 254, 250));
		MPSONG_3.setBounds(0, 344, 186, 30);
		RecentlyPlayedPanel.add(MPSONG_3);
		
		JButton MPSONG_2 = new JButton("");
		MPSONG_2.setEnabled(false);
		MPSONG_2.setHorizontalAlignment(SwingConstants.LEFT);
		MPSONG_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MPSONG_2.setBackground(new Color(242, 203, 155));
		MPSONG_2.setBounds(0, 315, 186, 30);
		RecentlyPlayedPanel.add(MPSONG_2);
		
		JButton MPSONG_1 = new JButton("");
		MPSONG_1.setEnabled(false);
		MPSONG_1.setHorizontalAlignment(SwingConstants.LEFT);
		MPSONG_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MPSONG_1.setBackground(new Color(254, 254, 250));
		MPSONG_1.setBounds(0, 287, 186, 30);
		RecentlyPlayedPanel.add(MPSONG_1);
		
		JButton MPSONG_5 = new JButton("");
		MPSONG_5.setEnabled(false);
		MPSONG_5.setHorizontalAlignment(SwingConstants.LEFT);
		MPSONG_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MPSONG_5.setBackground(new Color(254, 254, 250));
		MPSONG_5.setBounds(0, 401, 186, 30);
		RecentlyPlayedPanel.add(MPSONG_5);
		
		JButton MPSONG_4 = new JButton("");
		MPSONG_4.setEnabled(false);
		MPSONG_4.setHorizontalAlignment(SwingConstants.LEFT);
		MPSONG_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MPSONG_4.setBackground(new Color(242, 203, 155));
		MPSONG_4.setBounds(0, 372, 186, 30);
		RecentlyPlayedPanel.add(MPSONG_4);
		
		JButton AddSongbtn = new JButton("Add Song");
		AddSongbtn.setIcon(new ImageIcon(HomeView.class.getResource("/images2/add-circular-outlined-button (1).png")));
		AddSongbtn.setHorizontalAlignment(SwingConstants.LEFT);
		AddSongbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		AddSongbtn.setBounds(0, 426, 186, 88);
		RecentlyPlayedPanel.add(AddSongbtn);
		
		JLabel MostPlayedSongs = new JLabel("Most Played ");
		MostPlayedSongs.setHorizontalAlignment(SwingConstants.CENTER);
		MostPlayedSongs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		MostPlayedSongs.setBackground(new Color(254, 254, 250));
		MostPlayedSongs.setBounds(0, 231, 186, 61);
		RecentlyPlayedPanel.add(MostPlayedSongs);
		
		JList RecentlyPlayedList = new JList();
		RecentlyPlayedList.setBounds(0, 33, 186, 197);
		RecentlyPlayedPanel.add(RecentlyPlayedList);
		
		JList RP_List = new JList();
		RP_List.setBounds(0, 33, 186, 200);
		RecentlyPlayedPanel.add(RP_List);
		
		JList MP_List = new JList();
		MP_List.setBounds(0, 287, 186, 139);
		RecentlyPlayedPanel.add(MP_List);
		
		JPanel Dashboard = new JPanel();
		Dashboard.setBackground(new Color(254, 254, 250));
		Dashboard.setBorder(null);
		Dashboard.setBounds(196, 72, 754, 496);
		contentPane.add(Dashboard);
		Dashboard.setLayout(null);
		
		JLabel PlaylistArt_1 = new JLabel("ALBUM ART");
		PlaylistArt_1.setIcon(new ImageIcon(HomeView.class.getResource("/images2/KDRLK.png")));
		PlaylistArt_1.setBackground(new Color(254,254,250));
		PlaylistArt_1.setHorizontalAlignment(SwingConstants.CENTER);
		PlaylistArt_1.setBounds(51, 27, 164, 164);
		Dashboard.add(PlaylistArt_1);
		
		JLabel PlaylistArt_2 = new JLabel("ALBUM ART");
		PlaylistArt_2.setIcon(new ImageIcon(HomeView.class.getResource("/images2/khalid.png")));
		PlaylistArt_2.setHorizontalAlignment(SwingConstants.CENTER);
		PlaylistArt_2.setBackground(new Color(254, 254, 250));
		PlaylistArt_2.setBounds(285, 27, 164, 164);
		Dashboard.add(PlaylistArt_2);
		
		JLabel PlaylistArt_3 = new JLabel("ALBUM ART");
		PlaylistArt_3.setIcon(new ImageIcon(HomeView.class.getResource("/images2/Africa.png")));
		PlaylistArt_3.setHorizontalAlignment(SwingConstants.CENTER);
		PlaylistArt_3.setBackground(new Color(254, 254, 250));
		PlaylistArt_3.setBounds(530, 27, 164, 164);
		Dashboard.add(PlaylistArt_3);
		
		JLabel PlaylistArt_4 = new JLabel("ALBUM ART");
		PlaylistArt_4.setIcon(new ImageIcon(HomeView.class.getResource("/images2/images.png")));
		PlaylistArt_4.setHorizontalAlignment(SwingConstants.CENTER);
		PlaylistArt_4.setBackground(new Color(254, 254, 250));
		PlaylistArt_4.setBounds(51, 242, 164, 164);
		Dashboard.add(PlaylistArt_4);
		
		JLabel PlaylistArt_5 = new JLabel("ALBUM ART");
		PlaylistArt_5.setIcon(new ImageIcon(HomeView.class.getResource("/images2/brownbag.png")));
		PlaylistArt_5.setHorizontalAlignment(SwingConstants.CENTER);
		PlaylistArt_5.setBackground(new Color(254, 254, 250));
		PlaylistArt_5.setBounds(285, 242, 164, 164);
		Dashboard.add(PlaylistArt_5);
		
		JLabel PlaylistArt_6 = new JLabel("ALBUM ART");
		PlaylistArt_6.setIcon(new ImageIcon(HomeView.class.getResource("/images2/yourname.png")));
		PlaylistArt_6.setHorizontalAlignment(SwingConstants.CENTER);
		PlaylistArt_6.setBackground(new Color(254, 254, 250));
		PlaylistArt_6.setBounds(530, 242, 164, 164);
		Dashboard.add(PlaylistArt_6);
		
		JButton AlbumName_1 = new JButton("Dec my Ave");
		AlbumName_1.setHorizontalAlignment(SwingConstants.LEFT);
		AlbumName_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		AlbumName_1.setBackground(new Color(254, 254, 250));
		AlbumName_1.setBounds(51, 197, 164, 23);
		AlbumName_1.setBorder(null);
		Dashboard.add(AlbumName_1);
		
		JButton AlbumName_2 = new JButton("Khalid Beats ");
		AlbumName_2.setHorizontalAlignment(SwingConstants.LEFT);
		AlbumName_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		AlbumName_2.setBackground(new Color(254, 254, 250));
		AlbumName_2.setBounds(285, 197, 164, 23);
		AlbumName_2.setBorder(null);
		Dashboard.add(AlbumName_2);
		
		JButton AlbumName_3 = new JButton("Toto by Africa");
		AlbumName_3.setHorizontalAlignment(SwingConstants.LEFT);
		AlbumName_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		AlbumName_3.setBackground(new Color(254, 254, 250));
		AlbumName_3.setBounds(530, 197, 175, 23);
		AlbumName_3.setBorder(null);
		Dashboard.add(AlbumName_3);
		
		JButton AlbumName_4 = new JButton("Old People Music - 80's");
		AlbumName_4.setHorizontalAlignment(SwingConstants.LEFT);
		AlbumName_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		AlbumName_4.setBackground(new Color(254, 254, 250));
		AlbumName_4.setBounds(51, 418, 164, 23);
		AlbumName_4.setBorder(null);
		Dashboard.add(AlbumName_4);
		
		JButton AlbumName_5 = new JButton("Brownbag Music");
		AlbumName_5.setHorizontalAlignment(SwingConstants.LEFT);
		AlbumName_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		AlbumName_5.setBackground(new Color(254, 254, 250));
		AlbumName_5.setBounds(285, 418, 164, 23);
		AlbumName_5.setBorder(null);
		Dashboard.add(AlbumName_5);
		
		JButton AlbumName_6 = new JButton("Your Name. (Original Motion Picture Soundtrack)");
		AlbumName_6.setHorizontalAlignment(SwingConstants.LEFT);
		AlbumName_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		AlbumName_6.setBackground(new Color(254, 254, 250));
		AlbumName_6.setBounds(530, 418, 175, 23);
		AlbumName_6.setBorder(null);
		Dashboard.add(AlbumName_6);
		
		JLabel lblMadeForYou = new JLabel("Playlists you might like");
		lblMadeForYou.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMadeForYou.setBounds(0, 0, 186, 23);
		Dashboard.add(lblMadeForYou);
		

		

	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	class btn_Refresh implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 
			 userSongs = generalModel.getInstance().gettingSongs(currentUser);
			 userPlaylists = generalModel.getInstance().getUserPlaylist(currentUser);
			 
			 DefaultListModel DLM = new DefaultListModel();
			 
			 for(int x = 0; x < userSongs.size(); x++)
				 DLM.addElement(userSongs.get(x).getSongName());
			 
			 yourSongsList.setModel(DLM);
			 //============================================== Above is songs to the JList
			 
			 userPlaylist = generalModel.getInstance().gettingPlaylists(currentUser);
			 
			 DefaultListModel DLM2 = new DefaultListModel();
			 

			 for(int y = 0; y < userPlaylist.size(); y++)
				 DLM2.addElement(userPlaylist.get(y).getPlaylistName());

			 
			 playlistListJList.setModel(DLM2);
			 
			 //============================================== Above is playlists to the JList
			 SongList sList = new SongList();
			 PlaylistList pList1 = new PlaylistList();
			 
		 }
	 }
	
	class btn_Profile implements ActionListener{
		 
		 public void actionPerformed(ActionEvent e)
		 {
			 String username;
			 username = currentUser;
//			 RegisteredUserProfile.getInstance().setVisible(true);
//			 System.out.println(currentUser); for testing
//			 RegisteredUserProfile.getInstance().getUserName(username);		
			 ListenerView.getInstance().setVisible(true);
			 ListenerView.getInstance().getUsername(username);
		 }
	 }
	
	public void setUserName(String currentUser) {
		this.currentUser = currentUser;
		Profile.setText(currentUser);
	}
}
