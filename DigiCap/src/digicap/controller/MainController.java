package digicap.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;

import digicap.core.MomentPoster;

/**
 * Servlet implementation class MainController
 * 
 * Handles GET and POST HTTP/1.1 requests
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MainController() {}

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Displays main page to user
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Determines the button clicked and forwards to either:
	 * 		- Text post: Sends text to MomentPoster
	 * 		- Image post: Redirects to upload page for JSP upload
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MomentPoster MP = new MomentPoster();
		if (request.getParameter("imageupload") != null) //Text upload
		{
			File uploadFile = new File((String)request.getParameter("imageupload"));
			FileBody uploadFilePart = new FileBody(uploadFile);
			
			MultipartEntity reqEntity = new MultipartEntity(); //Required to make request multipart
			reqEntity.addPart("upload-file", uploadFilePart);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/upload");
			rd.forward(request, response);
		}
		else if (request.getParameter("moment_text") != null) //Image upload
		{
			try {
				//Send message to MomentPoster
				String message = request.getParameter("moment_text");
				MP.generateMoment(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/success.jsp");
			rd.forward(request, response);
		}
	}
}
