package digicap.util;

import java.io.IOException;  
import java.io.OutputStream;  
   
public class CommPortSender {  
   
    static OutputStream out;  
      
    public static void setWriterStream(OutputStream out) {  
        CommPortSender.out = out;  
    }  
      
    public static void send(byte[] bytes) {  
        try {  
            System.out.println("SENDING: " + new String(bytes, 0, bytes.length));  
            System.out.println("BYTES: " + bytes[0]);
            // sending through serial port is simply writing into OutputStream  
            
            //String strbytes = String.format("%040x", new BigInteger(bytes));
            //System.out.println("SENDING HEX: " + strbytes);
              
            //out.write(bytes);
           //out.write(bytes, 0, bytes.length);
	        out.write('a');
	        out.write(bytes);
	        out.write(1);
            out.flush();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}  