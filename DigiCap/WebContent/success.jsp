<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
	out.println("<html>");
	out.println("<head>");
	out.println("<title>JSP File upload</title>");  
	
	out.println("<link rel='stylesheet' type='text/css' href='/DigiCap/css/digicap_style.css'>");
	out.println("<link href='http://fonts.googleapis.com/css?family=Carrois+Gothic|Didact+Gothic' rel='stylesheet' type='text/css'>");
	out.println("<script src='http://yui.yahooapis.com/3.8.1/build/yui/yui-min.js'></script>");
	out.println("<script>YUI().use('node-load', function (Y) { Y.one('#wrapper').load('success.html'); });</script>");
	out.println("</head>");
	out.println("<body>");
	out.println("<div id='wrapper'>");
	out.println("</div>");
	out.println("</body>");
	out.println("</html>");
%>
</body>
</html>