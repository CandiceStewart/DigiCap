package com.digicap.QRCode;
import org.jsoup.nodes.*;

public class QRCode {
	public static String getCode(String stringToEncode) {
		//Replace spaces in the string with escape sequence "%20"
		stringToEncode = stringToEncode.replace(" ", "%20");
		
		//Fetch the html page containing the generated QR Code
		Document derp = URLConnectionReader.getPage("http://asciiqr.com/index.php?i=&t=" + stringToEncode);
		
		//Get the div containing the QRCode
		Element div = derp.getElementById("QRAscii");
		
		String fullImage = "";
	    for (Node node : div.childNodes()) {
	    	//If this node is not a line break
	        if(!node.toString().equals("<br />"))
	        {
	        	//Replace "&nbsp" escape sequence with spaces
	        	fullImage += node.toString().replace("&nbsp;", " ");
	        }
	        else
	        {
	        	//Add a line break
	        	fullImage += "\n";
	        }
	    }
	    return fullImage;
	}
}

