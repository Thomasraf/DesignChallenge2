
package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.RecentlyPlayedBuilder;
import jaco.mp3.player.MP3Player;
import model.Playlist;
import model.RecentlyPlayed;
import model.RecentlyPlayedList;
import model.Song;
import model.generalModel;
import view.HomeView.btn_CreatePlaylist;


import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;


import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class LibraryView extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	boolean evenClick = false;
	private JButton Artist_Dashboard;

	JButton btnCreatePlaylist, AddSongbtn, Profile, Refreshbtn, Playbtn, Nextbtn, Prevbtn, StopBtn, Artist_Music,
	Title_Dashboard, Genre_Dashboard, Album_Dashboard, Year_Dashboard;
	JList Title_list, Artist_list, Album_List, Genre_List, Year_List, Fave_List, Playlist_List, MP_List, RP_List;
	ArrayList<Song> userSongsMostPlayed, userSongs, userTitle, userArtist, userGenre, userAlbum, userYear, userFavorite;
	ArrayList<Playlist> userPlaylists;
	boolean songChangedInLibrary, playSongInPlaylist, songChangedInMP;

	private volatile static LibraryView instance = null;
	public static LibraryView getInstance() {
        if (instance == null) {
        	instance = new LibraryView();
        }
		return instance;

	}

	/**
	 * Create the frame.
	 */
	public LibraryView() {
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
		MainRectangle.setBounds(110, 579, 1036, 92);
		contentPane.add(MainRectangle);
		MainRectangle.setLayout(null);
		
		JPanel SongDetails = new JPanel();
		SongDetails.setBackground(new Color(30,58,42));
		SongDetails.setBounds(0, 0, 147, 101);
		MainRectangle.add(SongDetails);
		SongDetails.setLayout(null);
		
		JLabel SongName = new JLabel("(Song Name)");
		SongName.setHorizontalAlignment(SwingConstants.CENTER);
		SongName.setForeground(new Color(255, 255, 255));
		SongName.setFont(new Font("Calibri", Font.PLAIN, 14));
		SongName.setBounds(0, 0, 147, 40);
		SongDetails.add(SongName);
		
		JLabel Artist = new JLabel("(Artist)");
		Artist.setHorizontalAlignment(SwingConstants.CENTER);
		Artist.setForeground(Color.WHITE);
		Artist.setFont(new Font("Calibri", Font.PLAIN, 12));
		Artist.setBounds(0, 39, 147, 28);
		SongDetails.add(Artist);
		
		JLabel Album = new JLabel("(Album)");
		Album.setHorizontalAlignment(SwingConstants.CENTER);
		Album.setForeground(Color.WHITE);
		Album.setFont(new Font("Calibri", Font.PLAIN, 12));
		Album.setBounds(0, 62, 147, 28);
		SongDetails.add(Album);
		
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
		
		JButton Playbtn = new JButton("");
		Playbtn.setIcon(new ImageIcon(HomeView.class.getResource("/images2/play-button (2).png")));
		Playbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(evenClick) {
				Playbtn.setIcon(new ImageIcon(HomeView.class.getResource("/images2/play-button (2).png")));
				evenClick = false;
			}
				else {
					Playbtn.setIcon(new ImageIcon(HomeView.class.getResource("/images2/rounded-pause-button.png")));
					evenClick = true;
				}
				}
		});
		Playbtn.setBounds(413, 15, 78, 70);
		Playbtn.setBackground(new Color(30, 58, 42));
		Playbtn.setBorder(null);
		MainRectangle.add(Playbtn);
		
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
		
		JButton btnLogout = new JButton("");
		btnLogout.setBounds(10, 11, 39, 39);
		btnLogout.setIcon(new ImageIcon(LibraryView.class.getResource("/images2/logout.png")));
		btnLogout.setBorder(null);
		btnLogout.setBackground(new Color(30, 58, 42));
		TopBar.add(btnLogout);
		


		JTextField txtSearch = new JTextField();

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
		
		JButton ProfilePic = new JButton("");
		ProfilePic.setIcon(new ImageIcon(HomeView.class.getResource("/images2/user-avatar-main-picture.png")));
		ProfilePic.setBounds(478, 10, 40, 40);
		TopBar.add(ProfilePic);
		ProfilePic.setBackground(new Color(170, 187, 204));
		
		JButton Profile = new JButton("Profile Name");
		Profile.setBackground(new Color(30,58,42));
		Profile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Profile.setForeground(Color.WHITE);
		Profile.setBounds(520, 19, 145, 23);
		Profile.setBorder(null);
		TopBar.add(Profile);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(HomeView.class.getResource("/images2/notifications-button.png")));
		button_2.setBorder(null);
		button_2.setBackground(new Color(30, 58, 42));
		button_2.setBounds(1084, 11, 39, 39);
		TopBar.add(button_2);
		
		JButton Refreshbtn = new JButton("");
		Refreshbtn.setIcon(new ImageIcon(LibraryView.class.getResource("/images2/reload.png")));
		Refreshbtn.setBorder(null);
		Refreshbtn.setBackground(new Color(30, 58, 42));
		Refreshbtn.setBounds(1035, 11, 39, 39);
		TopBar.add(Refreshbtn);
		
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
		
		 Artist_Music = new JButton("Artist");
		Artist_Music.addActionListener(new Artist_Music_btn());
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
		
		JList RP_List = new JList();
		RP_List.setBounds(0, 33, 186, 201);
		RecentlyPlayedPanel.add(RP_List);
		
		JList MP_List = new JList();
		MP_List.setBounds(0, 287, 186, 138);
		RecentlyPlayedPanel.add(MP_List);
		
		JPanel Dashboard = new JPanel();
		Dashboard.setBackground(new Color(254, 254, 250));
		Dashboard.setBorder(null);
		Dashboard.setBounds(196, 72, 754, 496);
		contentPane.add(Dashboard);
		Dashboard.setLayout(null);
		
		JLabel Librarylbl = new JLabel("Library");
		Librarylbl.setBackground(new Color(254,254,250));
		Librarylbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		Librarylbl.setBounds(12, 11, 186, 23);
		Dashboard.add(Librarylbl);
		
		 Title_Dashboard = new JButton("Title");
		Title_Dashboard.addActionListener(new Title_Sort());
		Title_Dashboard.setHorizontalAlignment(SwingConstants.LEFT);
		Title_Dashboard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Title_Dashboard.setBackground(new Color(254, 254, 250));
		Title_Dashboard.setBounds(10, 50, 164, 30);
		Dashboard.add(Title_Dashboard);
		
		Artist_Dashboard = new JButton("Artist");
		Artist_Dashboard.addActionListener(new Artist_Sort());
		Artist_Dashboard.setHorizontalAlignment(SwingConstants.LEFT);
		Artist_Dashboard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Artist_Dashboard.setBackground(new Color(254, 254, 250));
		Artist_Dashboard.setBounds(173, 50, 164, 30);
		Dashboard.add(Artist_Dashboard);
		
		 Album_Dashboard = new JButton("Album");
		Album_Dashboard.addActionListener(new Album_Sort());
		Album_Dashboard.setHorizontalAlignment(SwingConstants.LEFT);
		Album_Dashboard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Album_Dashboard.setBackground(new Color(254, 254, 250));
		Album_Dashboard.setBounds(335, 50, 172, 30);
		Dashboard.add(Album_Dashboard);
		
		 Genre_Dashboard = new JButton("Genre");
		Genre_Dashboard.addActionListener(new Genre_Sort());
		Genre_Dashboard.setHorizontalAlignment(SwingConstants.LEFT);
		Genre_Dashboard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Genre_Dashboard.setBackground(new Color(254, 254, 250));
		Genre_Dashboard.setBounds(503, 50, 100, 30);
		Dashboard.add(Genre_Dashboard);
		
		JButton RemoveSongfromLibrary = new JButton("");
		RemoveSongfromLibrary.setIcon(new ImageIcon(LibraryView.class.getResource("/images2/prohibition (1).png")));
		RemoveSongfromLibrary.setBorder(null);
		RemoveSongfromLibrary.setBackground(new Color(254, 254, 250));
		RemoveSongfromLibrary.setBounds(705, 8, 39, 39);
		RemoveSongfromLibrary.setBorder(null);
		RemoveSongfromLibrary.setToolTipText("Remove Song from Library");
		Dashboard.add(RemoveSongfromLibrary);
		
		 Year_Dashboard = new JButton("Year");
		Year_Dashboard.addActionListener(new Year_Sort());
		Year_Dashboard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Year_Dashboard.setBackground(new Color(254, 254, 250));
		Year_Dashboard.setBounds(600, 50, 80, 30);
		Dashboard.add(Year_Dashboard);
		
		JButton Favorite_Dashboard = new JButton("");
		Favorite_Dashboard.setIcon(new ImageIcon(LibraryView.class.getResource("/images2/star.png")));
		Favorite_Dashboard.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Favorite_Dashboard.setBackground(new Color(254, 254, 250));
		Favorite_Dashboard.setBounds(680, 50, 74, 30);
		Dashboard.add(Favorite_Dashboard);
		
		JList Title_list = new JList();
		Title_list.setBounds(12, 79, 158, 417);
		Dashboard.add(Title_list);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBackground(new Color(254,254,250));
		btnNewButton_1.setBounds(10, 79, 164, 417);
		Dashboard.add(btnNewButton_1);
		
		JList Artist_list = new JList();
		Artist_list.setBounds(173, 79, 158, 417);
		Dashboard.add(Artist_list);
		
		JButton button_1 = new JButton("");
		button_1.setEnabled(false);
		button_1.setBackground(new Color(254,254,250));
		button_1.setBounds(173, 79, 164, 417);
		Dashboard.add(button_1);
		
		JList Album_List = new JList();
		Album_List.setBounds(335, 79, 170, 417);
		Dashboard.add(Album_List);
		
		JButton button_3 = new JButton("");
		button_3.setEnabled(false);
		button_3.setBackground(new Color(254,254,250));
		button_3.setBounds(335, 79, 172, 417);
		Dashboard.add(button_3);
		
		JList Genre_List = new JList();
		Genre_List.setBounds(503, 79, 98, 417);
		Dashboard.add(Genre_List);
		
		JButton button_4 = new JButton("");
		button_4.setEnabled(false);
		button_4.setBackground(new Color(254,254,250));
		button_4.setBounds(503, 79, 100, 417);
		Dashboard.add(button_4);
		
		JList Year_List = new JList();
		Year_List.setBounds(600, 79, 76, 417);
		Dashboard.add(Year_List);
		
		JButton button_5 = new JButton("");
		button_5.setEnabled(false);
		button_5.setBackground(new Color(254,254,250));
		button_5.setBounds(600, 79, 80, 417);
		Dashboard.add(button_5);
		
		JList Fave_List = new JList();
		Fave_List.setBounds(680, 79, 70, 417);
		Dashboard.add(Fave_List);
		
		JButton button_6 = new JButton("");
		button_6.setEnabled(false);
		button_6.setBackground(new Color(254,254,250));
		button_6.setBounds(680, 79, 74, 417);
		Dashboard.add(button_6);
		

		

	}

	
	class Refresh_btn implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 
			 //==========================================================   FOR LIBRARY STUFF
			 HomeView.getInstance().userSongs = generalModel.getInstance().gettingSongs(HomeView.getInstance().currentUser);
			 
			 DefaultListModel DLMTitle = new DefaultListModel();
			 DefaultListModel DLMArtist = new DefaultListModel();
			 DefaultListModel DLMGenre = new DefaultListModel();
			 DefaultListModel DLMAlbum = new DefaultListModel();
			 DefaultListModel DLMYear = new DefaultListModel();
			 DefaultListModel DLMFavorite = new DefaultListModel();
			 
			 for(int x = 0; x < HomeView.getInstance().userSongs.size(); x++) {
				 DLMTitle.addElement(HomeView.getInstance().userSongs.get(x).getSongName());
				 DLMArtist.addElement(HomeView.getInstance().userSongs.get(x).getArtistName());
				 DLMGenre.addElement(HomeView.getInstance().userSongs.get(x).getGenre());
				 DLMAlbum.addElement(HomeView.getInstance().userSongs.get(x).getAlbum());
				 DLMYear.addElement(HomeView.getInstance().userSongs.get(x).getYear());
				 DLMFavorite.addElement(HomeView.getInstance().userSongs.get(x).getFavorite());
			 }
			 LibraryView.getInstance().Title_list.setModel(DLMTitle);
			 LibraryView.getInstance().Artist_list.setModel(DLMArtist);
			 LibraryView.getInstance().Genre_List.setModel(DLMGenre);
			 LibraryView.getInstance().Album_List.setModel(DLMAlbum);
			 LibraryView.getInstance().Year_List.setModel(DLMYear);
			 LibraryView.getInstance().Fave_List.setModel(DLMFavorite);
			 
			 //==========================================================  FOR MOST PLAYED STUFF
			 HomeView.getInstance().userSongsMostPlayed = generalModel.getInstance().getMostPlayed(HomeView.getInstance().currentUser);
			 
			 DefaultListModel DLMMostPlayed = new DefaultListModel();
			 
			 for(int x = 0; x < HomeView.getInstance().userSongsMostPlayed.size(); x++)
				 DLMMostPlayed.addElement(HomeView.getInstance().userSongsMostPlayed.get(x).getSongName()  + " (" + HomeView.getInstance().userSongsMostPlayed.get(x).getCount() + ") ");
			 
			 HomeView.getInstance().MP_List.setModel(DLMMostPlayed);
			 MP_List.setModel(DLMMostPlayed);
			 //========================================================== FOR PLAYLISTS
			 HomeView.getInstance().userPlaylists = generalModel.getInstance().gettingPlaylists(HomeView.getInstance().currentUser);
			 DefaultListModel DLM2 = new DefaultListModel();
			
			 for(int y = 0; y < HomeView.getInstance().userPlaylists.size(); y++)
				 DLM2.addElement(HomeView.getInstance().userPlaylists.get(y).getPlaylistName());

			 HomeView.getInstance().Playlist_List.setModel(DLM2);
			 LibraryView.getInstance().Playlist_List.setModel(DLM2);
			//==========================================================
			 
			 
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
	
	class btn_AddSong implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 AddSong.getInstance().setVisible(true);
		 }
	 }
	
	class btn_Play implements ActionListener 
	 {

	     public void actionPerformed(ActionEvent e) 
	     {	 
	    	 
	    	 System.out.println("songChangedInLibrary: "+songChangedInLibrary);
		    	if(songChangedInLibrary == true) {
		    		HomeView.getInstance().mp3.pause();
			    	 int SongID = HomeView.getInstance().userSongs.get(Title_list.getSelectedIndex()).getSongID();
		    		 generalModel.getInstance().readSongData(SongID);
		    		 generalModel.getInstance().updateCount(SongID);
		    		 HomeView.getInstance().mp3 = new MP3Player(new File("currentSong.mp3"));
		    		 HomeView.getInstance().mp3.play();
			    	 songChangedInLibrary = false;
			    	 
			    	 RecentlyPlayed addedSong = new RecentlyPlayedBuilder()
							 .setSongID(HomeView.getInstance().userSongs.get(Title_list.getSelectedIndex()).getSongID())
							 .setUserName(HomeView.getInstance().userSongs.get(Title_list.getSelectedIndex()).getUserName())
							 .setSongName(HomeView.getInstance().userSongs.get(Title_list.getSelectedIndex()).getSongName())
							 .setArtistName(HomeView.getInstance().userSongs.get(Title_list.getSelectedIndex()).getArtistName())
							 .setAlbum(HomeView.getInstance().userSongs.get(Title_list.getSelectedIndex()).getAlbum())
							 .setGenre(HomeView.getInstance().userSongs.get(Title_list.getSelectedIndex()).getGenre())
							 .setYear(HomeView.getInstance().userSongs.get(Title_list.getSelectedIndex()).getYear())
							 .setPath(HomeView.getInstance().userSongs.get(Title_list.getSelectedIndex()).getPath())
							 .setCount(HomeView.getInstance().userSongs.get(Title_list.getSelectedIndex()).getCount())
							 .setFavorite(HomeView.getInstance().userSongs.get(Title_list.getSelectedIndex()).getFavorite())
							 .getSong();
			    	 
			    	 RecentlyPlayedList sList = new RecentlyPlayedList();
			    	 sList.addSong(addedSong);
			    	 DefaultListModel DLM2 = new DefaultListModel();
			    	 
					 for(int i = 0; i < sList.getSongListSize(); i++)
					 {
						 DLM2.addElement(sList.getSongList().get(i).getSongName());
					 }
					 RP_List.setModel(DLM2);
			    	 

		    	 }else if (songChangedInMP == true){
		    		 HomeView.getInstance().mp3.pause();
			    	 int SongID = HomeView.getInstance().userSongs.get(MP_List.getSelectedIndex()).getSongID();
		    		 generalModel.getInstance().readSongData(SongID);
		    		 generalModel.getInstance().updateCount(SongID);
		    		 HomeView.getInstance().mp3 = new MP3Player(new File("currentSong.mp3"));
		    		 HomeView.getInstance().mp3.play();
			    	 songChangedInMP = false;
			    	 
			    	 RecentlyPlayed addedSong = new RecentlyPlayedBuilder()
							 .setSongID(HomeView.getInstance().userSongs.get(MP_List.getSelectedIndex()).getSongID())
							 .setUserName(HomeView.getInstance().userSongs.get(MP_List.getSelectedIndex()).getUserName())
							 .setSongName(HomeView.getInstance().userSongs.get(MP_List.getSelectedIndex()).getSongName())
							 .setArtistName(HomeView.getInstance().userSongs.get(MP_List.getSelectedIndex()).getArtistName())
							 .setAlbum(HomeView.getInstance().userSongs.get(MP_List.getSelectedIndex()).getAlbum())
							 .setGenre(HomeView.getInstance().userSongs.get(MP_List.getSelectedIndex()).getGenre())
							 .setYear(HomeView.getInstance().userSongs.get(MP_List.getSelectedIndex()).getYear())
							 .setPath(HomeView.getInstance().userSongs.get(MP_List.getSelectedIndex()).getPath())
							 .setCount(HomeView.getInstance().userSongs.get(MP_List.getSelectedIndex()).getCount())
							 .setFavorite(HomeView.getInstance().userSongs.get(MP_List.getSelectedIndex()).getFavorite())
							 .getSong();
			    	 
			    	 RecentlyPlayedList sList = new RecentlyPlayedList();
			    	 sList.addSong(addedSong);
			    	 DefaultListModel DLM2 = new DefaultListModel();
			    	 
					 for(int i = 0; i < sList.getSongListSize(); i++)
					 {
						 DLM2.addElement(sList.getSongList().get(i).getSongName());
					 }
					 RP_List.setModel(DLM2);
		    	 }
		    	/* else if(playSongInPlaylist == true)
			     {
		    		 mp3.pause();
			    	 int SongID2 = userPlaylists.get(Playlist_List.getSelectedIndex()).getSongInPlaylist().get(yourSongsListJList.getSelectedIndex()).getSongID();
			    	 generalModel.getInstance().readSongData(SongID2);
			    	 generalModel.getInstance().updateCount(SongID2);
			    	 mp3 = new MP3Player(new File("currentSong.mp3"));
				     mp3.play();
				   	 playSongInPlaylist = false;
			     } */else 
		    	 {
			    	 if(HomeView.getInstance().songPaused == true)
			    	 {
			    	 HomeView.getInstance().mp3.pause();
			    	 HomeView.getInstance().songPaused = false;
			    	 }
			    	 else
			    	 {
			    	 HomeView.getInstance().mp3.play();
			    	 HomeView.getInstance().songPaused = true;
			    	 }
		    	 }
		    	
		    	 
	    	 
		    	
	     }
	 }
	class btn_Stop implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 HomeView.getInstance().mp3.stop();
		 }
	 }
	class btn_nextSong implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 HomeView.getInstance().mp3.skipForward();
			
		 }
	 }
	 
	 class btn_prevSong implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 HomeView.getInstance().mp3.skipBackward();
			
		 }
	 }
	 
	 class Artist_Music_btn implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 
		 }
	 }
	 
	 class Title_Sort implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 HomeView.getInstance().userSongs = generalModel.getInstance().getSortByTitle(HomeView.getInstance().currentUser);
			 
			 DefaultListModel DLMTitle = new DefaultListModel();
			 DefaultListModel DLMArtist = new DefaultListModel();
			 DefaultListModel DLMGenre = new DefaultListModel();
			 DefaultListModel DLMAlbum = new DefaultListModel();
			 DefaultListModel DLMYear = new DefaultListModel();
			 DefaultListModel DLMFavorite = new DefaultListModel();
			 
			 for(int x = 0; x < HomeView.getInstance().userSongs.size(); x++) {
				 DLMTitle.addElement(HomeView.getInstance().userSongs.get(x).getSongName());
				 DLMArtist.addElement(HomeView.getInstance().userSongs.get(x).getArtistName());
				 DLMGenre.addElement(HomeView.getInstance().userSongs.get(x).getGenre());
				 DLMAlbum.addElement(HomeView.getInstance().userSongs.get(x).getAlbum());
				 DLMYear.addElement(HomeView.getInstance().userSongs.get(x).getYear());
				 DLMFavorite.addElement(HomeView.getInstance().userSongs.get(x).getFavorite());
			 }
			 LibraryView.getInstance().Title_list.setModel(DLMTitle);
			 LibraryView.getInstance().Artist_list.setModel(DLMArtist);
			 LibraryView.getInstance().Genre_List.setModel(DLMGenre);
			 LibraryView.getInstance().Album_List.setModel(DLMAlbum);
			 LibraryView.getInstance().Year_List.setModel(DLMYear);
			 LibraryView.getInstance().Fave_List.setModel(DLMFavorite);
			 
			 
		 }
	 }
	 
	 class Artist_Sort implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 HomeView.getInstance().userSongs = generalModel.getInstance().getSortByArtist(HomeView.getInstance().currentUser);
			 
			 DefaultListModel DLMTitle = new DefaultListModel();
			 DefaultListModel DLMArtist = new DefaultListModel();
			 DefaultListModel DLMGenre = new DefaultListModel();
			 DefaultListModel DLMAlbum = new DefaultListModel();
			 DefaultListModel DLMYear = new DefaultListModel();
			 DefaultListModel DLMFavorite = new DefaultListModel();
			 
			 for(int x = 0; x < HomeView.getInstance().userSongs.size(); x++) {
				 DLMTitle.addElement(HomeView.getInstance().userSongs.get(x).getSongName());
				 DLMArtist.addElement(HomeView.getInstance().userSongs.get(x).getArtistName());
				 DLMGenre.addElement(HomeView.getInstance().userSongs.get(x).getGenre());
				 DLMAlbum.addElement(HomeView.getInstance().userSongs.get(x).getAlbum());
				 DLMYear.addElement(HomeView.getInstance().userSongs.get(x).getYear());
				 DLMFavorite.addElement(HomeView.getInstance().userSongs.get(x).getFavorite());
			 }
			 LibraryView.getInstance().Title_list.setModel(DLMTitle);
			 LibraryView.getInstance().Artist_list.setModel(DLMArtist);
			 LibraryView.getInstance().Genre_List.setModel(DLMGenre);
			 LibraryView.getInstance().Album_List.setModel(DLMAlbum);
			 LibraryView.getInstance().Year_List.setModel(DLMYear);
			 LibraryView.getInstance().Fave_List.setModel(DLMFavorite);
			 
			 
		 }
	 }
	 
	 class Genre_Sort implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 HomeView.getInstance().userSongs = generalModel.getInstance().getSongsByGenre(HomeView.getInstance().currentUser);
			 
			 DefaultListModel DLMTitle = new DefaultListModel();
			 DefaultListModel DLMArtist = new DefaultListModel();
			 DefaultListModel DLMGenre = new DefaultListModel();
			 DefaultListModel DLMAlbum = new DefaultListModel();
			 DefaultListModel DLMYear = new DefaultListModel();
			 DefaultListModel DLMFavorite = new DefaultListModel();
			 
			 for(int x = 0; x < HomeView.getInstance().userSongs.size(); x++) {
				 DLMTitle.addElement(HomeView.getInstance().userSongs.get(x).getSongName());
				 DLMArtist.addElement(HomeView.getInstance().userSongs.get(x).getArtistName());
				 DLMGenre.addElement(HomeView.getInstance().userSongs.get(x).getGenre());
				 DLMAlbum.addElement(HomeView.getInstance().userSongs.get(x).getAlbum());
				 DLMYear.addElement(HomeView.getInstance().userSongs.get(x).getYear());
				 DLMFavorite.addElement(HomeView.getInstance().userSongs.get(x).getFavorite());
			 }
			 LibraryView.getInstance().Title_list.setModel(DLMTitle);
			 LibraryView.getInstance().Artist_list.setModel(DLMArtist);
			 LibraryView.getInstance().Genre_List.setModel(DLMGenre);
			 LibraryView.getInstance().Album_List.setModel(DLMAlbum);
			 LibraryView.getInstance().Year_List.setModel(DLMYear);
			 LibraryView.getInstance().Fave_List.setModel(DLMFavorite);
			 
			 
		 }
	 }
	 
	 class Album_Sort implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 HomeView.getInstance().userSongs = generalModel.getInstance().getSongsByAlbum(HomeView.getInstance().currentUser);
			 
			 DefaultListModel DLMTitle = new DefaultListModel();
			 DefaultListModel DLMArtist = new DefaultListModel();
			 DefaultListModel DLMGenre = new DefaultListModel();
			 DefaultListModel DLMAlbum = new DefaultListModel();
			 DefaultListModel DLMYear = new DefaultListModel();
			 DefaultListModel DLMFavorite = new DefaultListModel();
			 
			 for(int x = 0; x < HomeView.getInstance().userSongs.size(); x++) {
				 DLMTitle.addElement(HomeView.getInstance().userSongs.get(x).getSongName());
				 DLMArtist.addElement(HomeView.getInstance().userSongs.get(x).getArtistName());
				 DLMGenre.addElement(HomeView.getInstance().userSongs.get(x).getGenre());
				 DLMAlbum.addElement(HomeView.getInstance().userSongs.get(x).getAlbum());
				 DLMYear.addElement(HomeView.getInstance().userSongs.get(x).getYear());
				 DLMFavorite.addElement(HomeView.getInstance().userSongs.get(x).getFavorite());
			 }
			 LibraryView.getInstance().Title_list.setModel(DLMTitle);
			 LibraryView.getInstance().Artist_list.setModel(DLMArtist);
			 LibraryView.getInstance().Genre_List.setModel(DLMGenre);
			 LibraryView.getInstance().Album_List.setModel(DLMAlbum);
			 LibraryView.getInstance().Year_List.setModel(DLMYear);
			 LibraryView.getInstance().Fave_List.setModel(DLMFavorite);
			 
			 
		 }
	 }
	
	 class Year_Sort implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 HomeView.getInstance().userSongs = generalModel.getInstance().getSongsByYear(HomeView.getInstance().currentUser);
			 
			 DefaultListModel DLMTitle = new DefaultListModel();
			 DefaultListModel DLMArtist = new DefaultListModel();
			 DefaultListModel DLMGenre = new DefaultListModel();
			 DefaultListModel DLMAlbum = new DefaultListModel();
			 DefaultListModel DLMYear = new DefaultListModel();
			 DefaultListModel DLMFavorite = new DefaultListModel();
			 
			 for(int x = 0; x < HomeView.getInstance().userSongs.size(); x++) {
				 DLMTitle.addElement(HomeView.getInstance().userSongs.get(x).getSongName());
				 DLMArtist.addElement(HomeView.getInstance().userSongs.get(x).getArtistName());
				 DLMGenre.addElement(HomeView.getInstance().userSongs.get(x).getGenre());
				 DLMAlbum.addElement(HomeView.getInstance().userSongs.get(x).getAlbum());
				 DLMYear.addElement(HomeView.getInstance().userSongs.get(x).getYear());
				 DLMFavorite.addElement(HomeView.getInstance().userSongs.get(x).getFavorite());
			 }
			 LibraryView.getInstance().Title_list.setModel(DLMTitle);
			 LibraryView.getInstance().Artist_list.setModel(DLMArtist);
			 LibraryView.getInstance().Genre_List.setModel(DLMGenre);
			 LibraryView.getInstance().Album_List.setModel(DLMAlbum);
			 LibraryView.getInstance().Year_List.setModel(DLMYear);
			 LibraryView.getInstance().Fave_List.setModel(DLMFavorite);
			 
			 
		 }
	 }
	 
	 class Favorite_Sort implements ActionListener
	 {
		 public void actionPerformed(ActionEvent e)
		 {
			 HomeView.getInstance().userSongs = generalModel.getInstance().getSongsByYear(HomeView.getInstance().currentUser);
			 
			 DefaultListModel DLMTitle = new DefaultListModel();
			 DefaultListModel DLMArtist = new DefaultListModel();
			 DefaultListModel DLMGenre = new DefaultListModel();
			 DefaultListModel DLMAlbum = new DefaultListModel();
			 DefaultListModel DLMYear = new DefaultListModel();
			 DefaultListModel DLMFavorite = new DefaultListModel();
			 
			 for(int x = 0; x < HomeView.getInstance().userSongs.size(); x++) {
				 DLMTitle.addElement(HomeView.getInstance().userSongs.get(x).getSongName());
				 DLMArtist.addElement(HomeView.getInstance().userSongs.get(x).getArtistName());
				 DLMGenre.addElement(HomeView.getInstance().userSongs.get(x).getGenre());
				 DLMAlbum.addElement(HomeView.getInstance().userSongs.get(x).getAlbum());
				 DLMYear.addElement(HomeView.getInstance().userSongs.get(x).getYear());
				 DLMFavorite.addElement(HomeView.getInstance().userSongs.get(x).getFavorite());
			 }
			 LibraryView.getInstance().Title_list.setModel(DLMTitle);
			 LibraryView.getInstance().Artist_list.setModel(DLMArtist);
			 LibraryView.getInstance().Genre_List.setModel(DLMGenre);
			 LibraryView.getInstance().Album_List.setModel(DLMAlbum);
			 LibraryView.getInstance().Year_List.setModel(DLMYear);
			 LibraryView.getInstance().Fave_List.setModel(DLMFavorite);
			 
			 
		 }
	 }
	 
	public void setUserName(String currentUser) {
		this.currentUser = currentUser;
		Profile.setText("Current User: " + currentUser);
	}

}

