<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="UserInformation.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Blog Registration</title>
		<link type="text/css" rel="stylesheet" href="css/register.css" />
		<script type="text/javascript" src="js/script.js"></script>
	</head>
	<body>

		<!--This is wrapper-->

		<div id="wrapper">

			<!--This is container for the form-->
			
			<div id="container">
				
				<h2>Sign up</h2>

				<!--This is form to get the value/as well as jsp-->
				
				<form class="form_for_register" action= "register1" method="post">

                                    <input type="text" name="username" value="" id= "login" placeholder="User Name" onblur="username_valid()" required/><div id="errname"></div>
						<input class="pass_conpass_email" type="email" name="mail" id= "email" placeholder="Email Address" required/><br>
						<input class="pass_conpass_email" type="password" name="password" id="pass" value="" placeholder="Password" onblur="password_valid1()" required/><div id="errpass"></div>
						<input class="pass_conpass_email" type="password" id="pass2"  value="" placeholder="Confirm Password" onblur="password_valid()" required/>
						<div class="column" role="radiogroup">
  							<input id="male" type="radio" value="Male" name="gender" checked></input>
                                                            <label>Male</label>
                                                        <input id="female" type="radio" value="Female" name="gender" aria-invalid="false"></input>
                                                            <label>Female</label>
						</div>
						<div class="chose_file"><h3><!--Choose Photo:--></h3>
						</div>
				        	<input type="file" class="pass_conpass_email" name="photo" hidden/>
						<button id="submit" type="submit">Create Account</button>
						<button id="cancel" type="reset" >Reset</button><br>
						<a id="already" href="index.jsp">Already Signed In</a>
				</form>
			</div>
		</div>
		<footer>
			<!--This is footer-->
					<div class="footerwrapper">

						<p>Copyright &copy; 2015 xxx, All Rights Reserved</p>
						<p>14SS Fudan University</p>

					</div>
				
				</footer>

		
	</body>
</html>