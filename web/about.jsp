<%-- 
    Document   : about
    Created on : Jul 8, 2015, 9:31:14 AM
    Author     : PritomKumar
--%>

<%@page import="UserOperator.Launcher"%>
<%@page import="java.sql.Connection"%>
<%@page import="UserInformation.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="UserInformation.Blog"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%
            User user = (User)request.getSession().getAttribute("userObject");
            Connection con = Launcher.getConnection();
            Blog[] blogs = new Blog[1];//on register there is a blog
            int number=0;
            String number1="";
            int z;
            //Get blog from the database
            try {
                String query = "select * from `blogof" + user.username +"` order by id desc";
                ResultSet rs = con.createStatement().executeQuery(query);
                while (rs.next()) {
                    number++;
                }
                if (number>5) 
                    blogs = new Blog[5];
                else
                    blogs = new Blog[number];
                ResultSet rs1 = con.createStatement().executeQuery(query);
                for (int i=0; i< blogs.length; i++) {
                    if(rs1.next())
                        blogs[i] = new Blog(user.username,rs1.getString("tittle"),true);
                }
            } catch (Exception ex) {
               out.println(ex.toString());
            }
            
        %>
            <%
            session.setAttribute("username", user.username);
            %>
<!doctype html>
<html>
	<head>
		<title><%=user.username%>'s Blog</title>
		<link type="text/css" rel="stylesheet" href="css/aboutStyle.css" />

	</head>
	<body>
		<div id="wrapper">
			<div id="coverpic">
				<div id="header">
					<div id="logo">Welcome To My Blog</div>
				</div>
				<div id="nav">

					<ul>
						<li><a href="mainpage.jsp">HOME</a></li>
						<li><a href="blog_list.jsp?sortName=Science">POSTS</a></li>
						<li><a href="myPhotos.html">PHOTO GALLERY</a></li>
						<li><a href="editinfo.jsp">EDIT INFO</a></li>			   
                                                <li><a href="about.jsp">ABOUT</a></li>			   

                                        </ul>
				</div>
				<div id="profile">
					<img id="user" src="<%=user.getPicture(user.username)%>" /> 
					<div id="username_location">
				   		<h1 class="username" id="name"><a href="#userd_id"><%=user.username%></a></h1>
				   		<h3 class="username" id="location">Dhaka,Bangladesh</h3>
				 	</div>
			</div>
			<div id="content">
					<div id="para" style="font-family: cursive;">
						<p style="color: burlywood;">This is <strong><%=user.username%>'s Page</strong>
                                                    <br>
                                                    Sex:<%=User.getGender(user.username)%><br>
                                                    Age: 22 years<br><!--this is hard code-->
                                                    Favourites: watching movies,play games...etc.<br><!--this is hard code-->

					</div>
			</div>
			<footer>
				<div class="footerwrapper">
					<p>Copyright &copy; 2015 xxx, All Rights Reserved</p>
					<p>Images by @google search</p>
					<p>14SS Fudan University</p>
				</div>
			</footer>
		</div> 
		
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
		<script type="text/javascript" src="http://arrow.scrolltotop.com/arrow11.js"></script>
		<noscript>Not seeing a <a href="http://www.scrolltotop.com/">Scroll to Top Button</a></noscript>
	</body>
</html>