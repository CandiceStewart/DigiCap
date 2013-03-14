package digicap.util;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;

import java.io.IOException;  
import java.io.OutputStream; 

import java.util.Enumeration;  
   
public class PrintController {  
	
	SerialPort serialPort;
	
	public PrintController() throws Exception
	{
		
		//list();
		 // connects to the port which name (e.g. COM1) is in the first argument  
        //connect("COM9");  
          
        // send HELO message through serial port using protocol implementation  
        //Thread.sleep(2000);
        //CommPortSender.send(new ProtocolImpl().getMessage("Testing"));  

        
        
        //new CommPortReceiver(serialPort.getInputStream()).start();  
        
        //disconnect();
        //CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM9");  
        
	}
	
	public void print(String message) {
		SerialTest st = new SerialTest();
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
   
	
	public void connect(String portName) throws Exception {  
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);  
   
        if (portIdentifier.isCurrentlyOwned()) {  
            System.out.println("Port in use!");  
        } else {  
        	System.out.println("Alles gut!");
            // points who owns the port and connection timeout  
            serialPort = (SerialPort) portIdentifier.open("PrintController", 2000);  
              
            // setup connection parameters  
            serialPort.setSerialPortParams(  
                19200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);  
   
            // setup serial port writer  
            CommPortSender.setWriterStream(serialPort.getOutputStream());  
               
        }  
    }  
	
	public void disconnect() {
		    if (serialPort != null) {
		        try {
		            // close the i/o streams.
		        	serialPort.getInputStream().close();
		            serialPort.getOutputStream().close();
		        } catch (IOException ex) {
		            // don't care
		        }
		        // Close the port.
		        serialPort.close();
		    }
		}

	
    public void list() {  
        Enumeration ports = CommPortIdentifier.getPortIdentifiers();  
          
        while(ports.hasMoreElements())  
            System.out.println("Ports: " + ((CommPortIdentifier)ports.nextElement()).getName());  
    }  
    
    
   
} 