<%-- 
    Document   : index
    Created on : Jun 28, 2015, 6:05:07 PM
    Author     : PritomKumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>My Blog</title>
		<link type="text/css" rel="stylesheet" href="css/log in.css" />
	</head>
	<body>
		<header>
			Welcome To My Blog
		</header>
		<div id="wrapper">

			<div id="container">
				<div id="top">
					<img src="pictures/user.png" id="logo" />
					Please Sign In
				</div>
				<form method="post" action="Login" name="login">

                                    <input type="text" id= "username" name="username" placeholder= "Username" required /><br><br>
					<input type="password" id= "password" name="password" placeholder="Password" required/><br><br>
					<button id="submit" type="submit" >Log In</button>
					<button id="cancel" type="reset" onclick="location.reload()">Cancel</button>
					<a id="create" href="reg.jsp">Click Here To Register</a>
						
				</form>
				
			</div>
				
		</div>
		
		<footer>
					<div class="footerwrapper">
						<p>Copyright &copy; 2015 Pritom, All Rights Reserved</p>
						<p>14SS Fudan University</p>
					</div>
				</footer>
	</body>
</html>