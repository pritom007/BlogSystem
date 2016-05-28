<%-- 
    Document   : friend_blog.jsp
    Created on : Jul 5, 2015, 12:04:23 AM
    Author     : PritomKumar
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="UserOperator.Launcher"%>
<%@page import="java.sql.Connection"%>
<%@page import="UserInformation.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html style="
    background: rgb(43, 30, 30)">
	<head>
		<title>Post In Details</title>
		<link type="text/css" rel="stylesheet" href="css/newblogpost.css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
                <script src="js/script.js"></script>
                 <script>
                $(document).ready(function(){
                  $("#click").click(function(){
                    $("#commentsarehere").slideToggle("200");
                  });
                });
                </script>
	</head>
	<body>
             <%
            request.setCharacterEncoding("UTF-8");
            
            String name="";
            if (request.getSession().getAttribute("userObject")!=null) {
                User user1 = (User)request.getSession().getAttribute("userObject");
                name=user1.username; 
            }
            User user = new User("");
            if(request.getParameter("user")!=null) {
                Connection con = Launcher.getConnection();
                user = new User(request.getParameter("user"));
                //user.signature = User.getSignature(user.username,con);
                user.pictureUrl = User.getPicture(user.username);
            }
            else if (request.getSession().getAttribute("friendObject")!=null) {
               user = (User)request.getSession().getAttribute("friendObject");
            }
            Blog blog=new Blog(request.getParameter("blogTittle"),false);
            if(blog.tittle.equals(""))
                //response.sendError(404);
            Blog.readBlog(user.username,request.getParameter("blogTittle"));
            
            
            //for connecting the comment database
            Connection con = Launcher.getConnection();
            Comment[] comments = new Comment[1];
            int number=0;//comment number
            String query = "SELECT * FROM myblog.comment WHERE `userblogId` = '"+user.username+blog.id+"' order by id desc;";
            ResultSet rs;
            rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                number++;
                System.out.println(rs.getString("content")+" "+rs.getString("user2"));
            }
            if (number!=0) {
                comments = new Comment[number];
                rs = con.createStatement().executeQuery(query);
                for (int i=0; i<number; i++) {
                    if (rs.next()) {
                        comments[i] = new Comment();
                        comments[i].user=rs.getString("user2");
                        comments[i].content=rs.getString("content");
                        comments[i].date=rs.getString("date");
                        comments[i].mail=rs.getString("mail");
                        comments[i].id=rs.getString("id");
                    }
                }
            }
        %>
		<div id="wrapper">
			
			<div id= "header">
			<div id="top">Details Blog Post</div>
                        <%if(!name.equals("")) {%>
                    <a class="user" href='mainpage.jsp'>《<%=name%></a>
                <%}else {%>
                    <a class="user" href="index.jsp">《login</a>
                <%}%>
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
						<p><a href = "myPhotos.html" class="link">PHOTOS</a></p>
					</div>
				</li>
				<li>
					<div class="menulist">
						<p><a href = "editinfo.jsp" class="link">Edit Info</a></p>
					</div>
				</li>
				</ul>
				</div>
		</div>
			
			
		<div class="details" id="details">

			  	
			   	<h2 class="entry-title"><a id="tittle" href=""><%=request.getParameter("blogTittle")%></a></h2>
                                <p class="post-data" title="<%=blog.date%>"><%=blog.date%>
                                    <span>Label：<%=blog.lable%></span>
                                     <span>Tag：<a href="blog_list.jsp?sortName=<%=blog.sort%>"><%=blog.sort%></a></span>
			   	</p>
				<div class="entry">
                                    <br>
					<%=blog.content%>
				</div>
                                    <p>
                                     
                                        <span class="border_right"><span class="p" >Already read(<%=blog.reader%>)</span> </span> 
                                           <form action="Likenum" name="likeCount" method="post">
                                                <span class="border_center" id="like3">
                                                    <input type="hidden" name="blogId" id="blogId" value="<%=blog.id%>" />
                                                    <input type="hidden" name="username" id="username" value="<%=user.username%>" />
                                                    <input type="hidden" name="blogTittle" value="<%=blog.tittle%>" />
                                                    <input type="submit"  value="like" id="like1" /><span id="like2">(<%=blog.good%>)</span></span>
                                           </form>
                                        <span class="border_center" id="cancelLike"><a href="javascript:void(0)">Cancle like</a></span>
                                        <span><a href="javascript:void(0)" id="click">Comments</a>(<%=blog.assement%>)</span>
                                        <span class="float_right"><a href="newblogpost.jsp?blogId=<%=blog.id%>">[Edit]</a><span> </span><a href="deleteBlog?blogId=<%=blog.id%>&sortName=<%=blog.sort%>">[Delete]</a></span>
                                    </p>
                                    <hr>
				<div class="placeforcomment" id="commentsarehere">
                                    <h2>Comments:</h2>
                                     <%
                            if (number!=0) {
                                for (int i=0; i<number;i++) {             
                                     %>
                                    <br>
                                        <div class="asset_content">
                                             <a href="friend_page.jsp?name=<%=comments[i].user%>"><%=comments[i].user%></a><a href="DeleteComment?commentId=<%=comments[i].id%>&username=<%=user.username%>&user=<%=user.username%>&blogId=<%=blog.id%>"  class="float_right">Delete</a>
                                            <br><br>
                                             <div>
<!--                                            <input type="hidden" id="commentId" value="<=comments[i].id%>" />
                                            <input type="hidden" id="blogId" value="<=blog.id%>" />-->
                                            <%=comments[i].content%>
                                                </div>
                                        <p><span class="small float_right"><%=comments[i].date%></span></p>    
                                        </div>
                            <%}}else{%>
                                        <div class="asset_content">
                                         <span>post a comment...</span><br />
                                        </div>   
                             <%}%>
                                    <br>
                            </div>	
				<div class="comments-show">
                                    <h3 id="reply-title" class="comment-reply-title">Leave a Comment</h3>
                                            <!-- comment testing
                                            <input type="text" id="comment" name="comment" title="Enter your comment here..." placeholder="Enter your comment here..." style="height: 132px;overflow: hidden;word-wrap:break-word;resize:none;width: 59%;">
                                            </input>
                                            -->
                                                <p>Fill in your details below to leave a comment:</p>
						<form action="Comment" method="post" name="mail_form">
                                                    <input type="hidden" name="blogId" id="blogId" value="<%=blog.id%>" />
                                                    <input type="hidden" name="username" id="username" value="<%=user.username%>" />
                                                    <input name="user" id="commentName" value="<%=user.username%>" type="hidden" />
                                                    <input type="hidden" name="blogTittle" value="<%=blog.tittle%>" />
                                                    <input name="mail" id="mail" value="blog.com" type="hidden" />
                                                    <textarea rows="5" name="content" id="asset"></textarea><br />
                        <!--                            <p id="a">mail：<input onblur="mailCheck()" onfocus="clearError(this.id)" name="mail" id="mail" type="email" /></p>
                                                        <p>Nickname：<input id="commentuser" name="user" type="text" /></p>-->
                                                    <br />
                                                    <p class="center"><input id="commentSubmit" type="submit" value="Submit" /></p>   
                                                 </form>
		 		</div>
		</div>

						
		<!-- #respond -->
        </div>
                        <footer>
			<div class="footerwrapper" id="foot">
			</div>
			</footer>
	</body>
</html>