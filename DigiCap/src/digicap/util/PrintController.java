package digicap.util;

import gnu.io.SerialPort;
import java.io.IOException;   
   
/**
 * Used to access the serial controller without having to reference from page controllers
 * @author Sam
 *
 */
public class PrintController {  
	
	SerialPort serialPort;
	
	public PrintController() throws Exception { }
	
	
	/**
	 * Direct message to serial class
	 * @param message
	 */
	public void print(String message) {
		SerialToPrinter st = new SerialToPrinter();
		try {
			st.printToSerial(message);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
   
} 