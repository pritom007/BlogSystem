<%-- 
    Document   : detailspost
    Created on : Jun 28, 2015, 7:17:20 PM
    Author     : PritomKumar
--%>
<%@page import="java.sql.ResultSet"%>
<%@page import="UserOperator.Launcher"%>
<%@page import="java.sql.Connection"%>
<%@page import="UserInformation.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html style="background: url(blurred.jpg) no-repeat center center fixed;background-size: 100%;">
	<head>
		<title>Post In Details</title>
		<link type="text/css" rel="stylesheet" href="css/newblogpost.css" />
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
                <script src="js/script.js"></script>
                <script src="http://code.jquery.com/jquery-1.11.1.js"></script>
                <script>
                $(document).ready(function(){
                  $("#click").click(function(){
                    $("#commentsarehere").slideToggle("200");
                  });
                });
                </script>
                <style>
                    #cusername, #cemail{
                        height: 15px;
                        width:200px;
                        margin-right: 10px;
                        margin-bottom:10px;
                    }
                    #commentSubmit{
                            height: 35px;
                            width: 170px;
                            font-size: 16px;
                            cursor: pointer;
                            background-color: #606060;
                            color: #FFF;
                            border: none;
                            border-radius: 3px 3px 3px 3px;
                            margin-bottom: 15px;
                            transition: all 0.22s linear;
                    }
                    #commentSubmit:hover{
                            background: #400090;
                            color: white;
                            transition: all 0.22s linear;
                    }
                    #asset{
                        width: 850px;
                        resize:none;
                    }
                </style>
        </head>
	<body>
             <%
            User user = (User)request.getSession().getAttribute("userObject");
            request.setCharacterEncoding("UTF-8");
            Blog blog=new Blog(user.username,request.getParameter("blogTittle"),false);
            //Get a comment
            Connection con = Launcher.getConnection();
            Comment[] comments = new Comment[1];
            int number=0;//comments number
            int read=0;//read numbers
            int likeNum=0;//like number
            String query = "SELECT * FROM myblog.comment WHERE `userblogId` = '"+user.username+blog.id+"' order by id desc;";
            String query1="select * from `blogof" + user.username +"` where `id`='"+ blog.id +"'";
            System.out.println(user.username+" "+blog.id+" "+blog.reader);
            ResultSet rs,rs1,rs2;
            rs = con.createStatement().executeQuery(query);
            rs1 = con.createStatement().executeQuery(query1);
            rs2 = con.createStatement().executeQuery(query1);
            while (rs.next()) {
                number++;
            }
            //for the read blog
            if(rs1.next()){
                System.out.printf("reader "+rs1.getString("reader"));
                String update = "UPDATE `blogof" + user.username +"` SET `reader`='" + (Integer.parseInt(rs1.getString("reader"))+1) + "'WHERE `id`='" + Integer.parseInt(rs1.getString("id")) + "';";
                
                con.createStatement().executeUpdate(update);
                read=Integer.parseInt(rs1.getString("reader"));
                //likeNum=Integer.parseInt(rs1.getString("good"));
                System.out.println("number of likes "+rs1.getString("good"));
            }
            //get thelike number
            if(rs2.next()){
                likeNum=Integer.parseInt(rs1.getString("good"));
            }
            // for comments    
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
			
			<div id= "header" style="color:whitesmoke;">
			<div id="top">Details Blog Post</div>
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
                                     
                                        <span class="border_right"><span class="p" >Already read(<%=read%>)</span> </span> 
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
                
                            
                                
                <div class="placeforcomment" id="commentsarehere" style="display: none;">
                                    <h2>Comments:</h2>
                                     <%
                            if (number!=0) {
                                for (int i=0; i<number;i++) {             
                                     %>
                                    <br>
                                        <div class="asset_content">
                                             <a href="friend_page.jsp?name=<%=comments[i].user%>"><%=comments[i].user%></a>
                                            <br><br>
                                             <div>
                                            <%=comments[i].content%>
                                             </div>
                                        <p><span class="small float_right"><%=comments[i].date%></span><a href="DeleteComment?commentId=<%=comments[i].id%>&username=<%=user.username%>&user=<%=user.username%>&blogId=<%=blog.id%>"  class="float_right"> Delete</a></p>    
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
                                                <p>Fill in your details below to leave a comment:</p>
						<form action="Comment" method="post" name="mail_form">
                                                    <label>Username:</label><input type="text" id="cusername" name="username" required/>
                                                    <label> Email:</label><input type="text" id="cemail" name="mail" required /><br>
                                                    <input type="hidden"  id="blogId" name="blogId" value="<%=blog.id%>" />
                                                    <input name="user" id="commentName" value="<%=user.username%>" type="hidden" />
                                                    <input type="hidden" name="blogTittle" value="<%=blog.tittle%>" />
                                                    <textarea rows="5" name="content" id="asset"></textarea><br />
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