<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="digicap.core.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.output.*" %>

<%
	
	File file ;
	int maxFileSize = 5000 * 1024;
	int maxMemSize = 5000 * 1024;
	ServletContext context = pageContext.getServletContext();
	String filePath = context.getInitParameter("file-upload");
	
	 // Verify the content type
	 String contentType = request.getContentType();
	 if ((contentType.indexOf("multipart/form-data") >= 0)) {
	
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    // maximum size that will be stored in memory
	    factory.setSizeThreshold(maxMemSize);
	    // Location to save data that is larger than maxMemSize.
	    factory.setRepository(new File("c:\\temp"));
	
	    // Create a new file upload handler
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    // maximum file size to be uploaded.
	    upload.setSizeMax( maxFileSize );
	    try{ 
	       // Parse the request to get file items.
	       List fileItems = upload.parseRequest(request);
	
	       // Process the uploaded file items
	       Iterator i = fileItems.iterator();
	
	       out.println("<html>");
	       out.println("<head>");
	       out.println("<title>JSP File upload</title>");  
	
			out.println("<link rel='stylesheet' type='text/css' href='/DigiCap/css/digicap_style.css'>");
			out.println("<link href='http://fonts.googleapis.com/css?family=Carrois+Gothic|Didact+Gothic' rel='stylesheet' type='text/css'>");
			out.println("<script src='http://yui.yahooapis.com/3.8.1/build/yui/yui-min.js'></script>");
			out.println("<script>YUI().use('node-load', function (Y) { Y.one('#wrapper').load('success.html'); });</script>");
	       out.println("</head>");
	       out.println("<body>");
	       while ( i.hasNext () ) 
	       {
		       FileItem fi = (FileItem)i.next();
		       if ( !fi.isFormField () )
		       {
		       // Get the uploaded file parameters
		       String fieldName = fi.getFieldName();
		       String fileName = fi.getName();
		       boolean isInMemory = fi.isInMemory();
		       long sizeInBytes = fi.getSize();
		       // Write the file
		       if( fileName.lastIndexOf("\\") >= 0 ){
		       file = new File( filePath + 
		       fileName.substring( fileName.lastIndexOf("\\"))) ;
		       }else{
		       file = new File( filePath + 
		       fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		       }
		       fi.write( file ) ;
		       MomentPoster MP = new MomentPoster();
		       MP.generateMomentQR(file.toString());
		       }
	       }
	       
	       out.println("<div id='wrapper'>");
	       out.println("</div>");
	       out.println("</body>");
	       out.println("</html>");
	    }
	    catch(Exception ex) {
			System.out.println(ex);
			System.out.println(ex.getStackTrace().toString());
		}
	}
	else {
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Servlet upload</title>");  
	out.println("</head>");
	out.println("<body>");
	out.println("<p>No file uploaded</p>"); 
	out.println("</body>");
	out.println("</html>");
	}
%>