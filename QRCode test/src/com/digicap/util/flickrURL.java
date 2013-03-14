package com.digicap.util;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;


public class flickrURL {			
	
	public static String getMostRecentURL()
	{
		String GET_PHOTO_ID_URL = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20flickr.photos.search%20where%20user_id%20%3D%20%2293966446%40N07%22%20and%20api_key%3D%228a716ce6f081e461176cfd0cbe79105b%22%20limit%201&diagnostics=true";
		String GET_URL_URL = "http://query.yahooapis.com/v1/public/yql?q=select%20url.urls%20from%20flickr.photos.info%20where%20photo_id%3D%22###%22%20and%20api_key%3D%228a716ce6f081e461176cfd0cbe79105b%22&diagnostics=true";
		String photoID = "";
		String photoURL = "";
		
		try
		{
			Document photoIDDoc = Jsoup.connect(GET_PHOTO_ID_URL).get();
			Elements elemID = photoIDDoc.getElementsByAttribute("id");
			Element singleID = elemID.get(0);
			
			photoID = singleID.id();
			
			GET_URL_URL = GET_URL_URL.replace("###", photoID);

			Document photoURLDoc = Jsoup.connect(GET_URL_URL).get();
			elemID = photoURLDoc.getElementsByTag("url");
			singleID = elemID.get(0);
			photoURL = singleID.html().replaceAll("<[^>]*>", "");
			
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
