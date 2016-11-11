package mll.utility;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.junit.Test;

import mll.beans.SongMetadata;

public class RazunaUtilityTest {

	static Configuration conf=new Configuration();
	
	
	@Test
	public void testparseRazunaResponsePositive() throws Exception{
		
		String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response><responsecode>0</responsecode><message>success</message><assetid>38F26EC2DBB64E77BD1D083AB7989B57</assetid><filetype>aud</filetype><comingfrom><![CDATA[]]></comingfrom><renamefilebody><![CDATA[]]></renamefilebody></Response>";
		String assetId = "38F26EC2DBB64E77BD1D083AB7989B57";
		assertEquals(true, RazunaUtility.parseRazunaResponse(response).equals(assetId));
	}
	
	@Test
	public void testparseRazunaResponseNegative() throws Exception{
		
		String response = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response><responsecode>0</responsecode><message>success</message><filetype>aud</filetype><comingfrom><![CDATA[]]></comingfrom><renamefilebody><![CDATA[]]></renamefilebody></Response>";
		String assetId = "38F26EC2DBB64E77BD1D083AB7989B57";
		assertEquals(false, RazunaUtility.parseRazunaResponse(response).equals(assetId));
	}
	
	@Test
	public void testcreateJsonForCustomFieldsPositive() throws Exception{
		
		String customFields = "[[\"B8285E19-AEEC-44E9-9A77BC5A342DFEC0\",\"title\"],[\"CEE60877-0483-4CB5-BD42B3C3CD669FAD\",120],[\"AA0A51E3-1F43-4999-9BEB97FB1D87DB0A\",\"10/10/16\"],[\"763B2AA4-0A45-4F6F-B204305265F24FA0\",\"copyright\"],[\"00ADDE47-FC23-4852-91E5543CB8F182F1\",\"lyrics\"],[\"E56AFF86-20FC-4A54-B1D3FAFBCB6BB7BA\",\"primaryGenre\"],[\"5C24E0AC-96FF-48CF-97BB37F502EEC365\",\"secondaryGenre\"],[\"B18DD8C2-28D3-443B-893B831B36069C9A\",\"pubCompany\"],[\"A850B9B1-9009-4881-A273F3BC6F58BDAD\",\"pbo\"],[\"A5367524-F0C1-4A96-BADDC9E623CA787C\",\"Artists\"]]";

		assertEquals(true, RazunaUtility.createJsonForCustomFields(getSongMetaData()).equals(customFields));
	}
	
	@Test
	public void testcreateJsonForCustomFieldsNegative() throws Exception{
		
		String customFields = "[[\"B8285E19-AEEC-44E9-9A77BC5A342DFEC0\"],[\"CEE60877-0483-4CB5-BD42B3C3CD669FAD\",120],[\"763B2AA4-0A45-4F6F-B204305265F24FA0\",\"copyright\"],[\"E56AFF86-20FC-4A54-B1D3FAFBCB6BB7BA\",\"primaryGenre\"],[\"5C24E0AC-96FF-48CF-97BB37F502EEC365\",\"secondaryGenre\"],[\"B18DD8C2-28D3-443B-893B831B36069C9A\",\"pubCompany\"],[\"A850B9B1-9009-4881-A273F3BC6F58BDAD\",\"pbo\"]]";
		
		assertEquals(false, RazunaUtility.createJsonForCustomFields(getSongMetaData()).equals(customFields));
	}
	
	public SongMetadata getSongMetaData() throws ParseException{
		SongMetadata song = new SongMetadata();
	    song.setBeats_per_rate(120L);
	    song.setTitle("title");
	    song.setCopyright_number("copyright");    
	    song.setPublishing_company("pubCompany"); 
	    song.setPro("pbo");
	    song.setPrimary_genre("primaryGenre");
	    song.setSecondary_genre("secondaryGenre");
	    song.setFileName("Filename");
	    song.setContentURL(null);
		song.setSourceOfContent("HARDDRIVE");
		song.setContent("path/song".getBytes());
		song.setCopyright_date("10/10/16");
		song.setLyrics("lyrics");
		song.setArtists("Artists");
		return song;
	}
}
