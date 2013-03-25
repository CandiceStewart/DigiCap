package digicap.util;

import com.rosaloves.bitlyj.Url;
import static com.rosaloves.bitlyj.Bitly.*;

import org.jsoup.nodes.*;

public class QRCoder {

	@SuppressWarnings("unused")
	public static String getCode(String urlString) {
		Url url = as("synckt", "R_aef30d4dc6733fd0563f86302fcc4ceb").call(shorten(urlString)); //API Key

		//Get an HTML document of the page containing the UTF-8 QRCode for our URL from asciiqr.com. 
		Document derp = URLConnectionReader.getPage("http://asciiqr.com/index.php?i=&t=" + url.getShortUrl());
		
		//Get div box from page containing the QRCode
		Element div = derp.getElementById("QRAscii");
		
		int i = 0;
		String fullImage = "";
		
		//Read the QRCode in line-by-line
	    for (Node node : div.childNodes()) {
	        i++;
	        //If this line isn't a <br> tag..
	        if(!node.toString().equals("<br />"))
	        {	
	        	//Add the line to the output string.  Replace escape codes for spaces.
	        	fullImage += node.toString().replace("&nbsp;", " ");
	        }
	        else //If you hit a <br> tag..
	        {
	        	//Add a new line to the string.
	        	fullImage += "\n";
	        }
	    }
	    
	    //Convert UTF-8 characters to a set of ASCII characters.
	    fullImage = fullImage.replace("█", ",");
	    fullImage = fullImage.replace("▄", ".");
	    fullImage = fullImage.replace("▀", "/");
	    fullImage = fullImage.replace(" ", ";");
	    
	    return fullImage;
	}	
}

