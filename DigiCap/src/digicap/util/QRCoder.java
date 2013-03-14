package digicap.util;


import java.net.URL;

import org.jsoup.nodes.*;

public class QRCoder {

	public static String getCode(String urlString) {
		Document derp = URLConnectionReader.getPage("http://asciiqr.com/index.php?i=&t=" + urlString);
		//Document derp = URLConnectionReader.getPage("http://localhost/parsetest.html");
		
		Element div = derp.getElementById("QRAscii");
		
		int i = 0;
		String fullImage = "";
		
	    for (Node node : div.childNodes()) {
	        i++;
	        if(!node.toString().equals("<br />"))
	        {
	        	fullImage += node.toString().replace("&nbsp;", " ");
	        }
	        else
	        {
	        	fullImage += "\n";
	        }
	    }
	    
	    return fullImage;
	}
	
	
	
	//URL url = new URL("http://asciiqr.com/index.php?i=&t=http://github.com");
	
	
}

