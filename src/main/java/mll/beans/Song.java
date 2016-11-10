package mll.beans;

import java.io.Serializable;
import java.util.Date;

public class Song implements Serializable
{
	private static final long serialVersionUID = -6457237205216286747L;
	
	private String asset_id;
	private long musician_id;
	
	public String getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}
	public long getMusician_id() {
		return musician_id;
	}
	public void setMusician_id(long userId) {
		this.musician_id = userId;
	}
	
}
