package model;

public class ArtistSong {
	private int songid;
	private String name;
	private int artistid;
	private int genreid;
	private int albumid;
	
	public ArtistSong(int songid, String name, int artistid, int genreid, int albumid)
	{
		this.songid = songid;
		this.name = name;
		this.artistid = artistid;
		this.albumid = albumid;
		this.genreid = genreid;
	}
}
