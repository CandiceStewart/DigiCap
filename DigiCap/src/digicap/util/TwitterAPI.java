package digicap.util;

import java.io.IOException;



import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;


/*
 * TwitterAPI class is used to update Twitter status using yql request.
 * YQL request will be processed via HTTP post method.  
 *   
 * Giang
 * */
public class TwitterAPI {
	public TwitterAPI(){}
	
	public void updateTwitter(String status) throws HttpException, IOException{
		status = statusFormat(status);
		
		String request = "https://query.yahooapis.com/v1/public/yql?q=INSERT%20INTO%20twitter.status%20(oauth_consumer_secret%2C%20status%2C%20oauth_consumer_key%2C%20oauth_token%2C%20oauth_token_secret)%20VALUES%20(%20%09'tdeANGyFbmYUXY2dJ8lNxJWGv2sTsRRubhqDu4GFKI'%2C%20%22"+
		 status +
				"%22%2C%20%09'tNl709x2kj4L1Tx5JmDwg'%20%20%2C%20'1263276841-kpdeVVs8O4iB4klfUpNpeKVkYvZd9YXl3fQP43w'%2C%20'fqts6OtsKIFxDwWeNlzdrCi7T3h9T2qb0O9xfOMi1Y')%3B&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
		
		 HttpClient client = new HttpClient();
		 PostMethod method = new PostMethod(request);
			
		int statusCode = client.executeMethod(method);
		if (statusCode != HttpStatus.SC_OK) {
			System.err.println("Method failed: " + method.getStatusLine());
		} else {
		System.out.println("Oh yeah !");
		}
	}
	
	/*
	 * statusFormat will format status string such as space or new line  
	 * 
	 * Giang 
	 * */
	private String statusFormat(String status){
		String right_status = status.replaceAll(" ", "%20").replaceAll("\n", "%0A").replaceAll("\r", "%0A");
		//right_status = right_status.replaceAll(), "%0A");

		right_status = right_status.replace(System.getProperty("line.separator"), "");
		return right_status ; 
				
	}
}
