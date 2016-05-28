<%-- 
    Document   : searchResult
    Created on : Jul 1, 2015, 11:16:54 AM
    Author     : PritomKumar
--%>

<%@page import="UserInformation.Blog"%>
<%@page import="UserInformation.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="background-size:1500px 2300px">
	<head>
		<title>Blog Posts</title>
		<link type="text/css" rel="stylesheet" href="css/directory.css" />
                <script type="text/javascript" src="js/function.js"></script>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	
	<body>
		 <%
                     User user = (User)request.getSession().getAttribute("userObject");
                     
            if(request.getSession().getAttribute("userObject")==null)
                response.sendError(404);
            if(request.getSession().getAttribute("searchResult")==null)
                response.sendError(404);
            
            request.setCharacterEncoding("UTF-8");
            String sortName = request.getParameter("sortName");
            String searchResult = (String)request.getSession().getAttribute("searchResult");
            System.out.println("aaaaaaaaaaaaaaaa");
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
		</div>
			<!-- this div has side bar and blog lists-->
                <div id="content">
                         <!-- this is the side bar div-->   
                    <div id="sidebar">
			<h1>Categories</h1><br>
                            <h4>
                                <p>   
                                <%=Blog.getSortList(user.username,sortName)%>
                                </p>
                            </h4>
                    </div>
				<!-- this is the blog list div-->
                    <div class="post">
                            <button id="newpost" type="submit" onclick="edit()">New Post</button>
                                 <ol class="dir">
					<%=searchResult%>
                                 </ol>

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