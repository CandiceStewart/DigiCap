//ARDUINO 1.0 COMPATIBLE ONLY

#include <SoftwareSerial.h>
#include <Thermal.h>

int printer_RX_Pin = 2;
int printer_TX_Pin = 3;

Thermal printer(printer_RX_Pin, printer_TX_Pin, 19200);

void setup(){
  
  Serial.begin(19200);
////////////////////////////////////////////////////////////////////
//Following are in setup, but do not need to be. Use them anywhere. 
//Just here so they do not just keep printing over and over
//Printer will ignore commands during printing, so use delay(3000)
//after prints to ensure it see everything you want to print.
//SOME FUNCTIONS WILL FEED A LINE WHEN CALLED TO SOLIDIFY SETTING
////////////////////////////////////////////////////////////////////
 
 printer.justify('L'); //sets text justification (right, left, center) accepts 'L', 'C', 'R'
 
 printer.setSize('S'); // set type size, accepts 'S', 'M', 'L'
}

//Prints message
void printString(String s){
  printer.justify('C');
  printer.setSize('S'); 
  printer.print(s);
}
 
//Prints header for message 
void printHeader() {
  printer.setSize('M'); 
  printer.println("++++++++++++++++++++++++++++++++");
  printer.justify('C');
  printer.println("DigiCap Moment");
  printer.justify('L');
  printer.println("--------------------------------");
  printer.feed();
}

//Prints footer for message
void printFooter() {
  printer.feed();
  printer.setSize('M'); 
  printer.println("--------------------------------");
  printer.justify('C');
  time_t t = now();
  char buffer[32];
  // Format: Mo, 15.06.2009 20:20:00
  strftime (buffer, 32, "%a, %d.%m.%Y %H:%M:%S", ptm);
  printer.println(buffer);
  printer.justify('L');
  printer.println("++++++++++++++++++++++++++++++++");
  printer.feed();
}

//Prints QR code
void printQR(String s) {
   
  s.replace('a',219);
  s.replace('b',220);
  s.replace('c',223);
  s.replace('d',219);
  printer.print(s);
}


//Arduino loop - checks for serial input
void loop(){
  String content = "";
  
  //While serial is available, pull data
  while(Serial.available()) {
      String content = "";
      char content_array[300];
  
      for (int j = 0; j < 300; j++)
      {
        content_array[j] = ' ';
      }
      
      //Get message data
      Serial.readBytesUntil('~', content_array, 300);
      Serial.flush();
    
      for (int i = 0; i < 300; i++) {          
        content.concat(content_array[i]);
      }
    
      Serial.println("Content in while: " + content);
      
      //Print message
      //  Header
      //  Message or QR code
      //  Footer
      printHeader();
      if (content_array[0] == '#')
        printQR(content);
      else
        printString(content);
      printFooter();
    }       
}
