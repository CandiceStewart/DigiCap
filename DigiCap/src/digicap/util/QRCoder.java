package digicap.util;

import com.rosaloves.bitlyj.Url;
import static com.rosaloves.bitlyj.Bitly.*;

import org.jsoup.nodes.*;

public class QRCoder {

	@SuppressWarnings("unused")
	public static String getCode(String urlString) {
		Url url = as("synckt", "R_aef30d4dc6733fd0563f86302fcc4ceb").call(shorten(urlString));

		Document derp = URLConnectionReader.getPage("http://asciiqr.com/index.php?i=&t=" + url.getShortUrl());
		
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
	    
	    fullImage = fullImage.replace("█", ",");
	    fullImage = fullImage.replace("▄", ".");
	    fullImage = fullImage.replace("▀", "/");
	    fullImage = fullImage.replace(" ", ";");
	    
	    return fullImage;
	}	
}

