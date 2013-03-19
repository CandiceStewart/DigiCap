package digicap.core;

import java.io.IOException;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.httpclient.HttpException;
import org.xml.sax.SAXException;
import com.aetrion.flickr.FlickrException;
import digicap.util.FlickrAPI;
import digicap.util.QRCoder;
import digicap.util.SerialToPrinter;
import digicap.util.TwitterAPI;
import digicap.util.FlickrURL;



public class MomentPoster {

	public MomentPoster() {}
	
	/**
	 * Uploads local image to Flickr
	 * Gets QR code for Flickr URL
	 * Sends data to serial class
	 * 
	 * @param file - String of the filename on the local (server) machine
	 */
	public void generateMomentQR(String file)
	{
		System.out.println("File upload beginning: " + file);
		FlickrAPI fAPI;
		try {
			//Upload file to Flickr
			fAPI = new FlickrAPI();
			fAPI.upload(file);
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (FlickrException e1) {
			e1.printStackTrace();
		}
		
		//Get most recent (just uploaded) URL from Flickr
		String url = FlickrURL.getMostRecentURL();
		
		//Get QR code for URL
		String qrcode = QRCoder.getCode(url);
		
		//Generate date and concatenate message for printer
		Date date = new Date();
		qrcode = '#' + qrcode + "\n\n" + date.toString() +'~';
		
		SerialToPrinter st = new SerialToPrinter();
		
		try {
			//Send QR code message to printer controller class
			st.printToSerial(qrcode);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Posts message to Twitter
	 * Sends data to serial class
	 * 
	 * @param message - String of the filename on the local (server) machine
	 */
	public void generateMoment(String message)
	{
		TwitterAPI tAPI = new TwitterAPI();
		try {
			//Send message to Twitter post class
			tAPI.updateTwitter(message);
		} catch (HttpException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//Generate date and concatenate to message
		Date date = new Date();
		message = message + "\n\n" + date.toString() + '~';
		
		SerialToPrinter st = new SerialToPrinter();
		System.out.println("Printing: " + message);
		try {
			//Send message to printer
			st.printToSerial(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
