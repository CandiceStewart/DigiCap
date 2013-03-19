package digicaphack.twitterinterface;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

public class TwitterFile {
	public TwitterFile(){}
	/*
	 * update Twitter status bypassed status string, that will post to my twitter account
	 * The reason I'm not using digicap account is whenever I tried to log in 
	 * They always ask me are you human ? and i found it not very fun lol.
	 * No worries I don't use twitter anyway :) 
	 *  
	 * 
	 * Giang
	 * */
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
	 * statusFormat will format status string ... 
	 * 
	 * Giang 
	 * */
	private String statusFormat(String status){
		String right_status = status.replaceAll(" ", "%20");	
		return right_status ; 
				
	}
	
	public static void main(String[] args) throws HttpException, IOException{
	//	System.out.println(new TwitterFile().statusFormat("im an apple"));
	//	String something = new TwitterFile().statusFormat("i'm an apple");
	//	new TwitterFile().updateTwitter(something);
		
	
	}
}
