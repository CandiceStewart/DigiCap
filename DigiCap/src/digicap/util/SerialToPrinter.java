package digicap.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;

/*
 * Code sourced using an RXTX tutorial - changed accordingly
 * RXTX: http://rxtx.qbang.org
 * Actual tutorial not known
 */

public class SerialToPrinter implements SerialPortEventListener {
	SerialPort serialPort;
	private static final String PORT_NAMES[] = { 
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
			"/dev/ttyUSB0", // Linux
			"COM9", // Windows
			//Ideally this would be a Wi-Fi connection, but for the purposes of this hack COM9 was used
			//(Couldn't find a way to auto-find the Arduino
	};
	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	private BufferedReader input;
	//The output stream to the port
	private OutputStream output;
	//Milliseconds to block while waiting for port open
	private static final int TIME_OUT = 2000;
	//Default bits per second for COM port.
	private static final int DATA_RATE = 19200;

	@SuppressWarnings("rawtypes")
	public void initialize() {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				String inputLine=input.readLine();
				System.out.println(inputLine);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

	
	/**
	 * Custom method for printing DigiCap message to the printer
	 * Converts message parameter to bytes and sends to COM port
	 * 
	 * @param message
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void printToSerial(String message) throws IOException, InterruptedException {
		initialize();
		
		System.out.println("Started");
		Thread.sleep(1500);
		byte[] bytes = (message+"\n").getBytes(); 
		
		for (int i = 0; i < bytes.length; i++)
		{
			output.write(bytes[i]);
		}
		Thread.sleep(500);
		close();
	}
	
}