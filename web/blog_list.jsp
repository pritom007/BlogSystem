<%-- 
    Document   : mainpage
    Created on : Jun 28, 2015, 7:17:20 PM
    Author     : PritomKumar
--%>
<%@page import="UserInformation.Blog"%>
<%@page import="UserInformation.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="background-size: 100%;">
	<head>
		<title>Blog Posts</title>
		<link type="text/css" rel="stylesheet" href="css/directory.css" />
                <script type="text/javascript" src="js/function.js"></script>
<style type="text/css">

</style>
<script>
    
//this is for tabs in blog_list
function tab(tab) {
document.getElementById('tab1').style.display = 'none';
document.getElementById('tab2').style.display = 'none';


document.getElementById('li_tab1').setAttribute("class", "");
document.getElementById('li_tab2').setAttribute("class", "");


document.getElementById(tab).style.display = 'block';
document.getElementById('li_'+tab).setAttribute("class", "active");
}

</script>
 
	</head>
	
	<body>
		<%
            User user = (User)request.getSession().getAttribute("userObject");
            request.setCharacterEncoding("UTF-8");
            String sortName = request.getParameter("sortName");
        %>
            <!-- this is the wrapper div-->
            <div id="wrapper">
			<!-- the head of the blog -->
			
                        <div id= "header">
			<div id="top">Blog Posts</div>
                        
                        <!-- this is menu-->
			<div id="toolbar">
				<ul class="menu">
				<li>
					<div class="menulist">
						<p><a href = "mainpage.jsp" class="link">HOME</a></p>
					</div>
				</li>
				<li>
					<div class="menulist">
						<p><a href = "blog_list.jsp?sortName=<%=sortName%>" class="link">POSTS</a></p>
					</div>
				</li>
				<li>
					<div class="menulist">
						<p><a href = "myPhotos.html" class="link">PHOTOS</a></p>
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
		</div>
			
			
			<!-- this div has side bar and blog lists-->
			<div id="content">
                         <!-- this is the side bar div-->   
			<div id="sidebar">
			<h1>Categories</h1><br>
			<h4>
			<div id="tab">
                            <ul class="title">
                            	<li id="li_tab1" onclick="tab('tab1')"><a><%=Blog.getSortList(user.username,sortName)%></a></li>
                                <li id="li_tab2"  onclick="tab('tab2')"><a><%=Blog.getAllblogs()%></a></li>
                        </ul>
			</div>
                        <br>
			</h4>
			</div>
				<!-- this is the blog list div-->
				<div class="post" id="Content_Area">
					<div id="tab1">
					
						<button id="newpost" type="" onclick="edit()">New Post</button>
					
                                    <ol class="dir">
                                      <%=Blog.getBlogList(user.username,sortName)%>
				</ol>
				</div>
				<div id="tab2" style="display: none;">
					
						<button id="newpost" type="submit" onclick="edit()">New Post</button>
					
                                    <ol class="dir">
                                      
                                      <%=Blog.getAllBlogList(user.username,"Science")%>
                                      
                                    </ol>
				</div>

				</div>
			</div>
			
		</div>
		<footer>
			<div class="footerwrapper">
				<p>Copyright &copy; 2015 XXX, All Rights Reserved</p>
				<p>14SS Fudan University</p>
			</div>
			</footer>
		
	</body>
</html>