package model;
import java.io.File;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import view.loggingInView;

public class Database{
	
	//"com.mysql.jdbc.Driver","jdbc:mysql://112.211.95.65:3306/","superuser","kathyemir","swdespa"
	//arielariel0
	private volatile static Database instance = null;
	private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	private String URL;
	private String USERNAME;
	private String PASSWORD;
	private String DATABASE;
	loggingInView entrance;
	
	public static Database getInstance() {
        if (instance == null) {
        	instance = new Database();
        }
		return instance;
	}
	
	public boolean setConnection(String URL, String USERNAME, String PASSWORD, String DATABASE) {

		this.URL = URL;
		this.USERNAME = USERNAME;
		this.PASSWORD = PASSWORD;
		this.DATABASE = DATABASE;
		
		if(getConnection() == null)
			return false;
		//CREATE TABLE IF NOT EXISTS
		
		String query = "CREATE TABLE IF NOT EXISTS accounts (UserID int NOT NULL AUTO_INCREMENT PRIMARY KEY, Username varchar(255), Password varchar(255));"; //creating table
		String query2 = "CREATE TABLE IF NOT EXISTS playlists(PlaylistID int NOT NULL AUTO_INCREMENT PRIMARY KEY, PlaylistName varchar(255), UserID int(11);";
		String query3 = "CREATE TABLE IF NOT EXISTS songs(SongID int NOT NULL AUTO_INCREMENT PRIMARY KEY, PlaylistID int(11), Title varchar(255), "
				+ "Artist varchar(255),Album varchar(255),Genre varchar(255), Year varchar(255));";
		String query4 = "CREATE TABLE IF NOT EXISTS user_playlists(PlaylistID int NOT NULL AUTO_INCREMENT PRIMARY KEY, UserID int(11), SongID int(11));";
		String query5 = "CREATE TABLE IF NOT EXISTS songData(SongID int NOT NULL AUTO_INCREMENT PRIMARY KEY, data BLOB);";
		
		
		String queryIncrement = "ALTER TABLE accounts auto_increment = 1";
		String queryIncrement2 = "ALTER TABLE playlists auto_increment = 1";
		String queryIncrement3 = "ALTER TABLE songs auto_increment = 1";
		String queryIncrement4 = "ALTER TABLE user_playlists auto_increment = 1";
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.execute();
			PreparedStatement ps2 = getConnection().prepareStatement(query2);
			ps2.execute();
			PreparedStatement ps3 = getConnection().prepareStatement(query3);
			ps3.execute();
			PreparedStatement ps4 = getConnection().prepareStatement(query4);
			ps4.execute();
			
			
			ps = getConnection().prepareStatement(queryIncrement);
			ps.execute();
			ps2 = getConnection().prepareStatement(queryIncrement2);
			ps2.execute();
			ps3 = getConnection().prepareStatement(queryIncrement3);
			ps3.execute();
			ps4 = getConnection().prepareStatement(queryIncrement4);
			ps4.execute();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		return true;
	}
	
	public Connection getConnection () {
		try {
			Class.forName(DRIVER_NAME);
			Connection connection = DriverManager.getConnection(
					URL + 
					DATABASE + "?serverTimezone=UTC&useLegacyDatetimeCode=false&allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false", 
					USERNAME,
					PASSWORD);
			return connection;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	protected Database() {
		
	}
	
	public void addingAccount(account newAccount){ //Signing Up
		String x,y;
		//get getConnection() from db
		Connection cnt = getConnection();
		x = newAccount.getUsername();
		y = newAccount.getPassword();
		
		
		String query = "insert into accounts values ("+0+",'"+x+"','"+y+"')";


		//create string query
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.execute();
			
			//close all the resources
			ps.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return null;
	}
	
	public boolean loggingAccount(account registeredAccount) { //Logging In
		Connection cnt = getConnection(); 
		boolean loggedIn = false;
		
		String query = "SELECT * FROM swdespa.accounts WHERE Username = ('"+registeredAccount.getUsername()+"') AND Password = ('"+registeredAccount.getPassword()+"');";
		
		try {
			//create prepared statement
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			loggedIn = rs.next() == false;
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();
			
			
		

		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return loggedIn;
		
	}
	
//	public void writeBLOB(AddSong song) {
//			
//			Connection cnt = getConnection();
//			FileInputStream input = null;
//			PreparedStatement myStatement = null;
//			
//			String query = "SELECT * FROM swdespa.songdata SET data=? WHERE song = '"+song.songName+"'";
//			
//			//create string qu
//			
//			try {
//				myStatement = cnt.prepareStatement(query);
//				
//				File theSongFile = new File(song.fileName); //Place instead of song.getSongName()
//				input = new FileInputStream(theSongFile);
//				myStatement.setBinaryStream(1, input);
//				
//				System.out.println("Reading the MP3 file: " + theSongFile.getAbsolutePath());
//				System.out.println("Storing MP3 into the database " + theSongFile);
//				System.out.println(query);
//	
//			} catch (Exception ecx) {
//				ecx.printStackTrace();
//			} 
//			
//		}
//	
//	public void readBLOB() {
//		Connection cnt = getConnection();
//		Statement myReadingStatement = null;
//		ResultSet rs = null;
//		
//		InputStream input = null;
//		FileOutputStream output = null;
//		
//		try {
//			
//		}catch(Exception exc) {
//			exc.printStackTrace();
//		}finally {
//			if(input != null) {
//				//input.close();
//			}
//			//if(output)
//		}
//	}
	

	public void queryTemplate(String parameters) {
		
		//get getConnection() from db
		Connection cnt = getConnection();
		
		String query = "SELECT * FROM accounts";
		//create string qu
		
		try {
			//create prepared statement	
			PreparedStatement ps = cnt.prepareStatement(query);
			
			//get result and store in result set
			ResultSet rs = ps.executeQuery();
			
			//transform set into list
			while(rs.next()) {
				System.out.println(rs.getString("Username"));
				System.out.println(rs.getString("Password"));
			}
			
			//close all the resources
			ps.close();
			rs.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void addingSong(Song s){ //Signing Up
		String getSongName, getArtistName, getAlbumName, getGenre;
		String getYear;
		//get getConnection() from db
		Connection cnt = getConnection();
		getSongName = s.getSongName();
		getArtistName = s.getArtistName();
		getAlbumName = s.getAlbum();
		getGenre = s.getGenre();
		getYear = s.getYear();
		
		
		String query = "insert into songs values ("+0+",'"+getSongName+"','"
															+getArtistName+"','" 
															+getAlbumName+ "','"
															+getGenre+ "','"
															+getYear+ "')";

		System.out.print(query);
		//create string query
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.execute();
			
			//close all the resources
			ps.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return null;
	}

	
	public void addingPlaylist(Playlist p){ //Signing Up
		String getPlaylistName;
		
		
		//get getConnection() from db
		Connection cnt = getConnection();
		getPlaylistName = p.getPlaylistName();
		
		
		String query = "insert into songs values ("+0+",'"+getPlaylistName+"','"
															+0+ "','"
															+0+ "')";

		System.out.print(query);
		//create string query
		
		try {
			PreparedStatement ps = getConnection().prepareStatement(query);
			ps.execute();
			
			//close all the resources
			ps.close();
			cnt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return null;
	}
//	public void testerTemplate() {
//		String x = "dad";
//		String y = "d";
//		Connection cnt = getConnection(); 
//		String query = "SELECT * FROM accounts WHERE Username = ('"+x+"') AND Password = ('"+y+"')"; 
//		
//		try {
//			//create prepared statement
//			PreparedStatement ps = cnt.prepareStatement(query);
//			
//			//get result and store in result set
//			ResultSet rs = ps.executeQuery();
//			
//			//transform set into list
//			while(rs.next()) {
//				System.out.println(rs.getString("Username"));
//				System.out.println(rs.getString("Password"));
//			}
//			
//			//close all the resources
//			ps.close();
//			rs.close();
//			cnt.close();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			
//		}
//	}

}
