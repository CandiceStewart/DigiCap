<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/DigiCap/css/digicap_style.css">
<link href='http://fonts.googleapis.com/css?family=Carrois+Gothic|Didact+Gothic' rel='stylesheet' type='text/css'>
<title>DigiCap</title>

<script src="http://yui.yahooapis.com/3.8.1/build/yui/yui-min.js"></script>

<script>
    YUI().use('node', function(Y) {
        var a = Y.one( 'a#info_button' );
        function handleClick ( e ) {
            var popup = window.open( 
                a.get('href'), 
                'DigiCapInfo',
        	    'toolbar=0,scrollbars=1,location=1,statusbar=1,menubar=0,resizable=1,width=500,height=300,left=200,top=200'
        	);
        	e.preventDefault();
        }
        a.on( 'click', handleClick );
    });
</script>


</head>
<body>
	<div id="wrapper">
			<div id="content">
				<div id="logo">
					<a href="/DigiCap"><img src="/DigiCap/img/digicap_logo.png"/></a>
				</div>
				<div id="input_area">
					<div id="text_moment">
						<p class="big_text">Store some words..</p>
						<form id="text_form" action="/DigiCap/" method="post">
							<textarea id="text_input" name="moment_text" type="textarea" maxlength="255" form="text_form" wrap="virtual"></textarea>
							<br/>
							<input class="digicap_button" id="text_input_submit" type="submit" value="Submit"/>
						</form>
					</div>
					
					<div id="or_partition">
						<img src="/DigiCap/img/grey.png"><br/>
						<span id="or_text">or</span><br/>
						<img src="/DigiCap/img/grey.png">
					</div>
					
					<div id="image_moment">
						<form id="mainform" enctype="multipart/form-data" action="/DigiCap/upload.jsp" method="post" >
							<p class="big_text" id="image_title">Store an image..</p>
							<input name="imageupload" id="imageupload" type="file" /><br />
							<input id="image_button" class="digicap_button" name="submit" id="submit" type="submit" value="Submit"/>
						</form>
					</div>
				</div>
			</div>
		<div id="info">
			<a href="info.html" id="info_button">
				<img src="/DigiCap/img/info_us.png" width="50px" height="50px" id="home" onmouseover="this.src='/DigiCap/img/info_s.png'" onmouseout="this.src='/DigiCap/img/info_us.png'"/>
			</a>
		</div>
		
	</div>
</body>
</html>