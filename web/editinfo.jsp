<%-- 
    Document   : editinfo
    Created on : Jun 28, 2015, 7:17:20 PM
    Author     : PritomKumar
--%>
<%@page import="UserInformation.Blog"%>
<%@page import="UserInformation.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html style="background: url(image/blurred.jpg) no-repeat center top;background-size: 100%;">
	<head>
		<title>Edit Information</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<link type="text/css" rel="stylesheet" href="css/editinfo.css" />
                <link type="text/css" rel="stylesheet" href="css/editinfo2.css" />

                <script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript">
        function tab(tab) {
        document.getElementById('tab1').style.display = 'none';
        document.getElementById('tab2').style.display = 'none';
        document.getElementById('tab3').style.display = 'none';
        document.getElementById('tab4').style.display = 'none';

        document.getElementById('li_tab1').setAttribute("class", "");
        document.getElementById('li_tab2').setAttribute("class", "");
        document.getElementById('li_tab3').setAttribute("class", "");
        document.getElementById('li_tab4').setAttribute("class", "");


        document.getElementById(tab).style.display = 'block';
        document.getElementById('li_'+tab).setAttribute("class", "active");
        }
</script>

	</head>
	
	<body>
            <%
            User user = (User)request.getSession().getAttribute("userObject");
            request.setCharacterEncoding("UTF-8");
            String userid=user.username;
            if (userid.equals(null))
                response.sendRedirect("index.jsp");
            else{
            //String operation = request.getParameter("op");
        %>
		<div id="wrapper">
			
			<div id="header">
				<h1 id="logo">Edit Information</h1>
			</div>
			<div id="toolbar">
				<ul class="menu">
				<li>
					<div class="menulist">
						<p><a href = "mainpage.jsp" class="link">HOME</a></p>
					</div>
				</li>
				<li>
					<div class="menulist">
						<p><a href="blog_list.jsp?sortName=Science" class="link">POSTS</a></p>
					</div>
				</li>
				<li>
					<div class="menulist">
						<p><a href = "Photo Gallery.html" class="link">PHOTOS</a></p>
					</div>
				</li>
				<li>
					<div class="menulist">
						<p><a href = "editinfo.jsp" class="link">EDIT INFO</a></p>
					</div>
				</li>
                                <li>
					<div class="menulist">
						<p><a href = "about.jsp" class="link">ABOUT</a></p>
					</div>
				</li>
				</ul>
				</div>
			
			<div id="tabs">
		<ul>
			<li id="li_tab1" onclick="tab('tab1')"><a>Change Picture</a></li>
			<li id="li_tab2" onclick="tab('tab2')"><a>Change Email</a></li>
			<li id="li_tab3" onclick="tab('tab3')"><a>Change Password</a></li>
                        <li id="li_tab4" onclick="tab('tab4')"><a>Add New Tags</a></li>


		</ul>
		<div id="Content_Area">
			<div id="tab1">
				
					<form action="uploadPic123" method="post" enctype="multipart/form-data">
                                            
                                            <div id="profilepic">
                                                <img src="<%=user.getPicture(user.username)%>" id="profilepic1" width="200px" height="210px">
                                            </div><br>
                                                <input type="file" class="upload" name="headpic"/>
                                                <br>
                                           <button id="submit" type="submit">Confirm</button>
                                           
                                        </form>
				
			</div>

			<div id="tab2" style="display: none;">
                            <form action="emailChange.jsp" name="emailchange" method="post">	
                                <p id="container1">
                                    <input type="password" id= "pass" name="pw" placeholder="type your password" onblur="password_valid1()"/><br>
                                    <input type="email" id= "email" name="email" placeholder="New Email Address"/>
                                        <br>
                                        <button id="submit" type="submit">Confirm</button>
                                </p>
                            </form>     
			</div>

			<div id="tab3" style="display: none;">
        			<form action="passwordChange.jsp" name="change_password" >     	
                                    <p id="container1">
                                        <input type="password" id= "pass0" name="pw" placeholder="Old Password" onblur="password_valid1()"/><br>
                                        <input type="password" id= "pass" name="pw1" placeholder="New Password" onblur="password_valid1()"/><br>
                                        <input type="password" id= "pass2" placeholder="Repeat New Password" onblur="password_valid()"/><br>
                                        <button id="submit" type="submit">Confirm</button>
                                    </p>
                                </form>    
			</div>
                        <div id="tab4" style="display: none;">
        			
                                    <div id="container1">
                                     <br>  
                                     <form action="EditSort" name="edittag" method="post">     	
                                        <label>Add new tags here:</label><br>
                                         <input type="text" id= "pass" name="editsort" placeholder="name of the tag" onblur="checktag()" onfocus="cancletagdiv()"/>
                                        <button id="submit" type="submit" method="post">Add</button><br>
                                        <span id="tag1"></span>
                                     </form> 
                                     <form action="DeleteSort" name="deletetag" method="post">     	
                                        <label>Delete the tags:</label><br>
                                         <input type="text" id= "deletetag" name="deletesort" placeholder="delete the tag" onblur="checktag2()" onfocus="cancletagdiv2()"/>
                                        <button id="submit" type="submit" method="post">Delete</button><br>
                                        <span id="tag2"></span>
                                        </form> 
                                    </div>
                                
			</div>                        

		</div>
	</div>
			
		</div>
		
		<footer>
			<div class="footerwrapper">
				<p>Copyright &copy; 2015 xxx, All Rights Reserved</p>
				<p>14SS Fudan University</p>
			</div>
			</footer>
	</body>
</html>
<%}%>