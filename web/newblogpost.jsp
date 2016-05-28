 <%-- 
    Document   : newblogpost
    Created on : Jun 28, 2015, 7:17:20 PM
    Author     : PritomKumar
--%>
<%@page import="UserInformation.Blog"%>
<%@page import="UserInformation.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background: url(image/blurred.jpg) no-repeat center top;background-size:1500px 100%">
	<head>
		<title>Blog Post</title>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link type="text/css" rel="stylesheet" href="css/newblogpost.css" />
                <script src="js\jquery-2.1.4.js"></script>
                <script src="js\function.js"></script>
		<script type="text/javascript" src="ckeditor\ckeditor.js"></script>
                
	</head>
	
	<body>
             <%
            User user = (User)request.getSession().getAttribute("userObject");
            String[] sortList = Blog.getEditSortList(user.username);
            %>
            
		<div id="wrapper">		
                    <div id= "header">
                        <div id="top">New Blog Posts</div>
                            <div id="toolbar">
				<ul class="menu">
                                    <li>
					<div class="menulist">
                                            <p><a href = "mainpage.jsp" class="link">HOME</a></p>
					</div>
                                    </li>
                                    <li>
					<div class="menulist">
                                            <p><a href = "blog_list.jsp?sortName=Science" class="link">POSTS</a></p>
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
                    
		<!--Post new article here..... -->				
                    
                <div class="newpost">
                        <%
                        String id = request.getParameter("blogId");
                        System.out.println(request.getParameter("operation"));
                        if (id==null) {
                        %>
			<form method="post" name="editBlog" action="EditBlog">
                            <input type="hidden" name="operation" value="create" />

                            
                            <fieldset style="height:auto; width:auto">
 				 <legend><h3>Post New Article</h3></legend>
                                            <label style="margin-left: 34px"/>Username:<input type="text" id="username" name="nameofuser" value="" required></input><br>
                                            <lable style="margin-left: 4px;"/>Email Address:<input type="email" name="email" placeholder="name@domain.com"  value="" required><br><br>
                                            <lable style="margin-left: 66px"/>Title:<input type="text" name="tittle" id="tittle"  value="" size="30" onblur="checkTitle()" onfocus="checkTitle2()" required><div id="alert"></div>
                                            <br><br>
                                            <textarea class="newartical" id="newarticle" placeholder="" name="content" cols="60" rows="5" onkeypress="if (this.value.length > 100) { return false; }"/></textarea><br>
                                                <!--this is for the editor-->
                                                <script type="text/javascript">
                                                    CKEDITOR.replace( 'newarticle' );
                                                </script>
                                                <!--this is for the editor finish-->
                                                <!-- testing tag option-->
                                            <P id="tag1">Tag：<input class="tag" name="lable" type="text" placeholder="Fill in the label,let more people see your blog" /><span class="small gray">Use space between tags</span></p>
                                            <!-- tesing tag-->
                                           
                                            <select name="sort" id="Catagory"  required="">
  						<option value="" disabled selected>Select your option</option>
                                                <%
                                                for (int i=0; i<sortList.length; i++) {
                                                %>
                                                 <option value="<%=sortList[i]%>"><%=sortList[i]%></option>
                                                <%}%>
                                                <!--
  						<option value="science">Science</option>
  						<option value="food">Food</option>
  						<option value="travel">Travel</option>
  						<option value="fashion">Fashion</option>
  						<option value="other">Other</option>
                                                -->
                                            </select><br>
                                            <button type="submit" class="postbutton">Post</button>
                            </fieldset>
			</form>
                        <%}else {
                          String tittle = Blog.getBlogTittle(user.username, request.getParameter("blogId"));
                          //String mail=Blog.getBlogMail(user.username);
                          Blog blog=new Blog(user.username,tittle,false);
                          blog.lable=Blog.getBlogLable(user.username, request.getParameter("blogId"));// primery lable was added to the tag
                          blog.content=Blog.replace(blog.content);//replace character
                          //blog.mail=Blog.getBlogMail(user.username);
                          
                    %>
                    <form method="post" name="editBlog" action="EditBlog">
                            <input type="hidden" name="operation" value="edit" />
                            <input type="hidden" value="<%=id%>" name="blogId" />
                                <fieldset style="height:auto; width:auto">
 				 <legend><h3>Post New Article</h3></legend>
                                            <label style="margin-left: 34px"/>Username:<input type="text" id="username" name="nameofuser" value="<%=user.username%>"  disabled="disabled" required></input><br>
                                            <lable style="margin-left: 4px;"/>Email Address:<input type="email" name="email"  value="<%=Blog.getBlogMail(user.username)%>" disabled="disabled" required><br><br>
                                            <lable style="margin-left: 66px"/>Title:<input type="text" name="tittle" id="tittle" value="<%=tittle%>" onblur="checkTitle()" onfocus="checkTitle2()"  size="30" required><div id="alert"></div>
                                            <br><br>
                                            <textarea class="newartical" id="newarticle" placeholder="" name="content"/><%=blog.content%></textarea><br>
                                            <!--this is for the editor-->
                                                <script type="text/javascript">
                                                    CKEDITOR.replace( 'newarticle' );
                                                </script>
                                                <!--this is for the editor finish-->
                                            <!-- testing tag option-->
                                            <P id="tag1">Tag：<input class="tag" name="lable" type="text" value="<%=blog.lable%>" placeholder="Fill in the label,let more people see your blog" /><span class="small gray">Use space between tags</span></p>
                                            <!-- testing tag-->
                                           
                                            <select name="Category"  required="">
  						<%
                                    for (int i=0; i<sortList.length; i++) {
                                %>
                                    <option value="<%=sortList[i]%>"><%=sortList[i]%></option>
                                <%}%>
                                            </select><br>
                                            <button type="submit" class="postbutton">Post</button>
                            </fieldset>
			</form>
                        <%}%>
                    </div>
						<div id="science"></div>
		</div>						
	</body>
</html>
