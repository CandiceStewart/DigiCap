package digicap.QRCode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class URLConnectionReader {
	
    public static Document getPage(String urlString) {
    	try{
    		
    		Document doc = Jsoup.connect(urlString).get();
        	return doc;
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		return null;
    	}
    }
}