package mll.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class Metadata implements Serializable
{
	private static final long serialVersionUID = 4923954637381909334L;
	private Song song;
	private List<Artist> artists = new ArrayList<Artist>();
	private List<Owner> owners = new ArrayList<Owner>();
	private List<Genre> genres = new ArrayList<Genre>();
	private JSONObject metadataJson = new JSONObject();
	
	public Song getSong() {
		return song;
	}
	public void setSong(Song song) {
		this.song = song;
	}
	public List<Artist> getArtists() {
		return artists;
	}
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}
	public List<Owner> getOwners() {
		return owners;
	}
	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public JSONObject getMetadataJson() {
		return metadataJson;
	}
	public void setMetadataJson(JSONObject metadataJson) {
		this.metadataJson = metadataJson;
	}
	
}
