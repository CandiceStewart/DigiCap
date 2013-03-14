package digicap.core;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import digicap.util.QRCoder;
import digicap.util.SerialToPrinter;

public class MomentPoster {

	public MomentPoster()
	{
		
	}
	
	public boolean generateMoment(String message)
	{
		//, Date date, String image_local_url
		
		
		/*LinkedList<String> message_parts = new LinkedList<String>();
		
		String message_line = "";
		for (int i = 0; i < message.length(); i++)
		{
			message_line += message.charAt(i);
			if (i % 30 == 0 && i != 0)
			{
				message_parts.add(message_line);
				message_line = "";
			}
		}
		message_parts.add(message_line);
		Iterator<String> iter = message_parts.iterator();
		*/
		
		
		//Make message printable with date
			//Split message up into chunks of 50 characters
			//Generate header and footer
			//Print header
			//Print each chunk
			//Print date
			//Print footer
			//Line feed
		//Send message to Twitter
		//Send message to printer
		String qrcode = QRCoder.getCode("http://www.google.com/");
		//message = message + "~";
		message = '¬' + qrcode + '~';
		
		SerialToPrinter st = new SerialToPrinter();
		System.out.println("Printing: " + message);
		try {
			st.printToSerial(message);
			/*while(iter.hasNext()) {
				String message_to_send = (String)iter.next();
				st.printToSerial(message_to_send);
			}*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Upload image to Flickr
		//Generate QR code
		//Send QR code and date to printer
		
		return true;
	}
}
