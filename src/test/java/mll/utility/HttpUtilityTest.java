package mll.utility;
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class HttpUtilityTest {
	
	@Test
	public void testCallRazunaAPIFail()
	{
		
			HttpUtility util=new HttpUtility();
			HashMap<String,String> map=new HashMap<String,String>();
			map.put("method", "folderid");
			map.put("api_key", "1234");
			map.put("folder_name", "final");
			assertTrue(util.callRazunaAPI(map, new Configuration().Razuna_CREATE_FOLDER_METHOD).equalsIgnoreCase("failure"));
		
	}

}
