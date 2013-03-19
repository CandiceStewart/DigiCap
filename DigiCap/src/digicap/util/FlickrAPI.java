package digicap.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.RequestContext;
import com.aetrion.flickr.auth.Auth;
import com.aetrion.flickr.auth.AuthInterface;
import com.aetrion.flickr.people.User;
import com.aetrion.flickr.uploader.UploadMetaData;
import com.aetrion.flickr.uploader.Uploader;

/**
 * Demonstrates the authentication-process.
 * <p>
 * 
 * If you registered API keys, you find them with the shared secret at your <a
 * href="http://www.flickr.com/services/api/registered_keys.gne">list of API
 * keys</a>
 * 
 * Tutorial from @author mago Written by : Giang Nguyen Truong
 * @version $Id: AuthExample.java,v 1.6 2009/08/25 19:37:45 x-mago Exp $
 */
public class FlickrAPI {
	Flickr f;
	RequestContext requestContext;
	String frob = "72157632990150257-64349d2a45f089d8-602534";
	String frob2 = "72157632996286330-d82be87c8e4d5d68-796283";
	String token = "72157632990090815-be309cafa8641ff3";
	String token2 = "72157632991914273-232b4ffe9a222270";
	Properties properties = null;
	Uploader up = new Uploader("8a716ce6f081e461176cfd0cbe79105b",
			"b1fc76ac54f5c189");

	
	public FlickrAPI() throws ParserConfigurationException, IOException,
			SAXException, FlickrException {
	}

	/*
		Upload picture into flickr account that initialy set up with api key and secret key. 
		The uploading picture will be taken from the local computer by passing local path 
		Written by : Giang Nguyen Truong 
	*/
	@SuppressWarnings({ "deprecation", "unused" })
	public void upload(String filename) throws ParserConfigurationException, IOException{
		String apiKey = "486fd7d08b3503e4ec352b5d3d7dc12c";
		String secretKey = "09e5dbff193834c4";
		Flickr f;
		f = new Flickr(apiKey, secretKey,
				new REST("www.flickr.com"));
		Flickr.debugStream = false;
		User user = new User();
		user.setUsername("digicaphack");
		System.out.println("1");
		Uploader up = new Uploader(apiKey,
				secretKey);
	
		RequestContext requestContext;

		AuthInterface authInterface;

		InputStream in1 = new FileInputStream(filename);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int i;
		byte[] buffer = new byte[1024];
		while ((i = in1.read(buffer)) != -1) {
			out.write(buffer, 0, i);
		}
		in1.close();
		System.out.println("2");

		byte data[] = out.toByteArray();

		f = new Flickr(apiKey, secretKey, (new Flickr(apiKey)).getTransport());
		up = f.getUploader();
		System.out.println("3");
		authInterface = f.getAuthInterface();
		requestContext = RequestContext.getRequestContext();
		requestContext.setSharedSecret(secretKey);
		try {
			Auth auth = new Auth();
			auth.setToken(token2);
			requestContext.setAuth(auth);
			System.out.println("4");
			f.setAuth(auth);
			UploadMetaData uploadMetaData = new UploadMetaData();
			uploadMetaData.setTitle("DigiCap Upload");
			uploadMetaData.setPublicFlag(true);
			up.upload(data, uploadMetaData);
			System.out.println("5");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FlickrException e) {
			e.printStackTrace();
		}

		System.out.println("Success!");
	}
	
}