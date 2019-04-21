package model;

public class ArtistPlaylist {
	private int playlistid;
	private String name;
	private int userid;
	private String description;
	private String path;
	
	public ArtistPlaylist(int playlistid, String name, int userid, String description, String path)
	{
		this.playlistid = playlistid;
		this.name = name;
		this.userid = userid;
		this.description = description;
		this.path = path;
	}
	
	public int getID()
	{
		return playlistid;
	}
	
	public void setID(int id)
	{
		this.playlistid = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getUserID()
	{
		return userid;
	}
	
	public void setUserID(int id)
	{
		this.userid = id;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String desc)
	{
		this.description = desc;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public void setPath(String path)
	{
		this.path = path;
	}
}
