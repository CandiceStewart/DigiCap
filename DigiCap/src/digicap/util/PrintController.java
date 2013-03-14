package digicap.util;

import gnu.io.SerialPort;
import java.io.IOException;   
   
public class PrintController {  
	
	SerialPort serialPort;
	
	public PrintController() throws Exception
	{        
	}
	
	public void print(String message) {
		SerialToPrinter st = new SerialToPrinter();
		try {
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