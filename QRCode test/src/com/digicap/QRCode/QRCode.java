package com.digicap.QRCode;

import java.net.URL;

import org.jsoup.nodes.*;
// org.apache.commons.lang.StringEscapeUtils;

public class QRCode {

	public static void getCode(String urlString) {
		//Document derp = URLConnectionReader.getPage("http://asciiqr.com/index.php?i=&t=http://github.com");
		Document derp = URLConnectionReader.getPage("http://localhost/parsetest.html");
		
		Element elem = derp.getElementById("QRAscii");
		
		System.out.println(elem.toString().replaceAll("<br />", "\n"));
		//StringEscapeUtils util = new StringEscapeUtils();
	}
	
	
	
	//URL url = new URL("http://asciiqr.com/index.php?i=&t=http://github.com");
	
	
}

