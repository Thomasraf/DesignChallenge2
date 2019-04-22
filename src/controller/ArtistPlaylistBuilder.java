package controller;

import model.ArtistPlaylist;

public class ArtistPlaylistBuilder {
	int playlistid;
	String name;
	int userid;
	String description;
	String path;
	
	public void setID(int id)
	{
		this.playlistid = id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setUserID(int id)
	{
		this.userid = id;
	}
	
	public void setDescription(String desc)
	{
		this.description = desc;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
	
	public ArtistPlaylist getPlaylist()
	{
		return new ArtistPlaylist(playlistid, name, userid, description, path);
	}
}
