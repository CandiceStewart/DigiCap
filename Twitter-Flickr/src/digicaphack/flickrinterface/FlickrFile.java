package digicaphack.flickrinterface;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.aetrion.flickr.Flickr;
import com.aetrion.flickr.FlickrException;
import com.aetrion.flickr.REST;
import com.aetrion.flickr.RequestContext;
import com.aetrion.flickr.auth.Auth;
import com.aetrion.flickr.auth.AuthInterface;
import com.aetrion.flickr.auth.Permission;
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
 * @author mago
 * @version $Id: AuthExample.java,v 1.6 2009/08/25 19:37:45 x-mago Exp $
 */
public class FlickrFile {
	Flickr f;
	RequestContext requestContext;
	String frob = "72157632990150257-64349d2a45f089d8-602534";
	String frob2 = "72157632996286330-d82be87c8e4d5d68-796283";
	String token = "72157632990090815-be309cafa8641ff3";
	String token2 = "72157632991914273-232b4ffe9a222270";
	Properties properties = null;
	Uploader up = new Uploader("8a716ce6f081e461176cfd0cbe79105b",
			"b1fc76ac54f5c189");

	
	public FlickrFile() throws ParserConfigurationException, IOException,
			SAXException, FlickrException {
	}

	public void setup() throws IOException, ParserConfigurationException {
		String apiKey = "486fd7d08b3503e4ec352b5d3d7dc12c";
		String secretKey = "09e5dbff193834c4";
		Flickr f;
		InputStream in = null;
		f = new Flickr(apiKey, secretKey,
				new REST("www.flickr.com"));
		Flickr.debugStream = false;
	
		Uploader up = new Uploader(apiKey,
				secretKey);
	
		RequestContext requestContext;

		AuthInterface authInterface;
		String frob = "";
		// void setup()

		InputStream in1 = new FileInputStream("C:\\Users\\ADMIN\\Documents\\ohyeah.png");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int i;
		byte[] buffer = new byte[1024];
		while ((i = in1.read(buffer)) != -1) {
			out.write(buffer, 0, i);
		}
		in1.close();
		// byte[] result = out.toByteArray();

		byte data[] = out.toByteArray();
		// size(500, 500);

		f = new Flickr(apiKey, secretKey, (new Flickr(apiKey)).getTransport());
		up = f.getUploader();
		authInterface = f.getAuthInterface();
		requestContext = RequestContext.getRequestContext();
		requestContext.setSharedSecret(secretKey);
		try {

			frob = authInterface.getFrob();
			System.out.println(frob);
			URL joep = authInterface.buildAuthenticationUrl(Permission.WRITE,frob);
			System.out.println(joep.toExternalForm());
			System.out.println("Press enter to continue...");
			Scanner keyboard = new Scanner(System.in);
			keyboard.nextLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FlickrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Auth auth = new Auth();
			requestContext.setAuth(auth);
			// authInterface.addAuthToken();
			auth = authInterface.getToken(frob);
			auth.setPermission(Permission.WRITE);
			System.out.println("Token Is: " + auth.getToken());
			System.out.println("Permission for token: " + auth.getPermission());
			f.setAuth(auth);
			UploadMetaData uploadMetaData = new UploadMetaData();
			uploadMetaData.setTitle("hello world");
			up.upload(in1, uploadMetaData);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FlickrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void upload() throws ParserConfigurationException, IOException{
		String apiKey = "486fd7d08b3503e4ec352b5d3d7dc12c";
		String secretKey = "09e5dbff193834c4";
		Flickr f;
		InputStream in = null;
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
		// void setup()

		InputStream in1 = new FileInputStream("C:\\Users\\ADMIN\\Documents\\ohyeah.png");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int i;
		byte[] buffer = new byte[1024];
		while ((i = in1.read(buffer)) != -1) {
			out.write(buffer, 0, i);
		}
		in1.close();
		System.out.println("2");
		// byte[] result = out.toByteArray();

		byte data[] = out.toByteArray();
		// size(500, 500);

		f = new Flickr(apiKey, secretKey, (new Flickr(apiKey)).getTransport());
		up = f.getUploader();
		System.out.println("3");
		authInterface = f.getAuthInterface();
		requestContext = RequestContext.getRequestContext();
		requestContext.setSharedSecret(secretKey);
				try {
			Auth auth = new Auth();
			auth.setToken(token2);
			//auth.setUser(user);
			requestContext.setAuth(auth);
			System.out.println("4");
			f.setAuth(auth);
			UploadMetaData uploadMetaData = new UploadMetaData();
			uploadMetaData.setTitle("hello world");
			up.upload(data, uploadMetaData);
			System.out.println("5");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FlickrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Success!");
	}
	
	public static void main(String[] args) throws ParserConfigurationException,
			IOException, SAXException, FlickrException {
		new FlickrFile().upload();
	}
}