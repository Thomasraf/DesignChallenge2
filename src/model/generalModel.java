package model;

import java.util.ArrayList;

import controller.SongBuilder;
import view.LoggingInView;
import view.SigningUpView;
import view.ArtistLoggingInView;

public class generalModel {

	private volatile static generalModel modelInstance = null;
	
	public static generalModel getInstance() {
        if (modelInstance == null) {
        	modelInstance = new generalModel();
        }
		return modelInstance;
	
	}
	
	public void getSongData(Song s)
	{
		int SongID = Database.getInstance().addingSong(s);
		Database.getInstance().writeSongBLOB(SongID, s.getPath());
	}
	
	public void getPlaylistData(Playlist p)
	{
		Database.getInstance().addingPlaylist(p);
		Database.getInstance().writePlaylistBLOB(p.getPlaylistName(),p.getPath(),p.getDescription());
	}
	
	public int getAccountData(account x, String path) { //SIGNING UP
		if(Database.getInstance().addingAccount(x) == false) {
			//SigningUpView.getInstance().signingSuccessful();
			Database.getInstance().writeDisplayPictureBLOB(x.getUsername(),path);
			return 1;
		}
		else{
			//SigningUpView.getInstance().signingFailed();
			return 0;
		}
	}
	
	public void checkingAccountData(account w) { //LOGGING IN
		if(Database.getInstance().loggingAccount(w) == true) {
			LoggingInView.getInstance().entranceAllowed();
		}
		else {
			LoggingInView.getInstance().entranceDenied();
		}
	}
	
	public void checkingArtistAccountData(account w) { //LOGGING IN
		if(Database.getInstance().loggingArtistAccount(w) == true) {
			ArtistLoggingInView.getInstance().entranceAllowed();
		}
		else {
			ArtistLoggingInView.getInstance().entranceDenied();
		}
	}
	
	public void getUserPlaylistData(Playlist p)
	{
		Database.getInstance().addingUserPlaylist(p);
	}
	
	public ArrayList<Song> gettingSongs(String t) {
		return Database.getInstance().getSongs(t);
	}
	
	public ArrayList<Playlist> gettingPlaylists(String t){
		return Database.getInstance().getPlaylist(t);
	}
	
	public void readSongData(int SongID) {
		Database.getInstance().readBLOB(SongID);
	}
	
	public void readDisplayPicture(String username) {
		Database.getInstance().readDisplayPictureBLOB(username);
	}

	public void updateCount(int SongID) {
		Database.getInstance().countUpdate(SongID);
	}
	public void favoritingPlaylist(String PlaylistID, String PlaylistName) {
		Database.getInstance().favoritingPlaylist(PlaylistID, PlaylistName);
	}
	public void makingPrivatePlaylist(String PlaylistIDPrivate,String PlaylistNamePrivate) {
		Database.getInstance().privacyPlaylist(PlaylistIDPrivate,PlaylistNamePrivate);
	}
	
	public void favoritingSongs(String SongID, String SongName) {
		Database.getInstance().favoritingSong(SongID, SongName);
	}

	public void gettingSongCounts(String username){
		
	}
	public ArrayList<Playlist> gettingFavoritePlaylist(String t) {
		return Database.getInstance().getFavoritePlaylist(t);
	}
	
	public ArrayList<Playlist> gettingPrivatePlaylists(String t){
		return Database.getInstance().getPrivatePlaylist(t);
	}
	
	public ArrayList<Song> gettingFavoriteSong(String t)
	{
		return Database.getInstance().getFavoriteSong(t);
	}

	//=========================================================================== Everything Sorting
	
	public ArrayList<Song> getSongsByGenre()
	{
		return Database.getInstance().getSongsByGenre();
	}
	
	public ArrayList<Song> getSongsByAlbum()
	{
		return Database.getInstance().getSongsByAlbum();
	}
	
	public ArrayList<Song> getSongsByYear()
	{
		return Database.getInstance().getSongsByYear();
	}
	
	public ArrayList<Song> getMostPlayed()
	{
		return Database.getInstance().getMostPlayed();
	}
	
	public ArrayList<Playlist> getUserPlaylist(String username)
	{
		return Database.getInstance().gettingUserPlaylist(username);
	}
	
	public ArrayList<Song> getSongStuff() {
		return Database.getInstance().gettingSongs();
	}
	
	//=========================================================================== Everything Editing
	public void editSongData(String username,String oldSongName, String newSongName,String newArtistName, String newAlbumName,String newGenreName ,String newYearDate) {
		Database.getInstance().editSongName(username,oldSongName,newSongName);
		Database.getInstance().editArtistName(username,oldSongName,newSongName,newArtistName);
		Database.getInstance().editAlbumName(username,oldSongName,newSongName,newAlbumName);
		Database.getInstance().editGenreName(username,oldSongName,newSongName,newGenreName);
		Database.getInstance().editYearDate(username,oldSongName,newSongName,newYearDate);
	}
}

