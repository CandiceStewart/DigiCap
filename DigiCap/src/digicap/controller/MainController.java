package digicap.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import digicap.util.PrintController;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MainController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("moment_text"));
		Date date = new Date();
		if (request.getParameter("imageupload") != null) {
			try {
				fileUpload(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if (request.getParameter("imageupload") != null) {
			System.out.println("Attribute: " + request.getParameter("imageupload"));
			File uploadFile = new File((String)request.getParameter("imageupload"));
			FileBody uploadFilePart = new FileBody(uploadFile);
			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("upload-file", uploadFilePart);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/upload");
			rd.forward(request, response);
		}
		else if (request.getParameter("moment_text") != null)
		{
			try {
				PrintController PC = new PrintController();
				PC.print(request.getParameter("moment_text"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/success.jsp");
			rd.forward(request, response);
		}
		
		
		
	}

	protected void fileUpload(HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Parse the request
		List items = upload.parseRequest(req);
		
		
		String fieldName = null;
        String fileName = null;
        String contentType = null;
        boolean isInMemory = false;
        long sizeInBytes = 0;
        
        
		// Process the uploaded items
		Iterator iter = items.iterator();
		while (iter.hasNext()) {
		    FileItem item = (FileItem) iter.next();
		    System.out.println(item.toString());
		 // Process a file upload
		    if (!item.isFormField()) {
		        fieldName = item.getFieldName();
		        fileName = item.getName();
		        contentType = item.getContentType();
		        isInMemory = item.isInMemory();
		        sizeInBytes = item.getSize();
		    }
		    
		 // Process a file upload
		    File uploadedFile = new File("C:/Temp/DigiCap/" + fileName);
		    item.write(uploadedFile);
		}
		
		
	}
}
