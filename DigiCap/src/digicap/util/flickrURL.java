package digicap.util;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;


public class FlickrURL {			
	
	public static String getMostRecentURL()
	{
		String GET_PHOTO_ID_URL = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20flickr.photos.search%20where%20user_id%20%3D%20%2293966446%40N07%22%20and%20api_key%3D%228a716ce6f081e461176cfd0cbe79105b%22%20limit%201&diagnostics=true";
		String GET_URL = "http://www.flickr.com/photos/93966446@N07/";
		String photoID = "";
		String photoURL = "";
		
		try
		{
			Document photoIDDoc = Jsoup.connect(GET_PHOTO_ID_URL).get();
			Elements elemID = photoIDDoc.getElementsByAttribute("id");
			Element singleID = elemID.get(0);
			
			photoID = singleID.id();
			
			photoURL = GET_URL += photoID;
			
			System.out.println(photoURL);
			
			return photoURL;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
