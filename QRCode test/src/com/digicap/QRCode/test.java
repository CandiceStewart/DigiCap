package com.digicap.QRCode;

import java.util.List;

import org.jsoup.nodes.Document;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Document derp = URLConnectionReader.getPage("http://asciiqr.com/index.php?i=&t=http://github.com");
	
		System.out.println(derp);
	}
}
