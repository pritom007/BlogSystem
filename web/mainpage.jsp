<%-- 
    Document   : mainpage
    Created on : Jun 28, 2015, 7:17:20 PM
    Author     : PritomKumar
--%>
<%@page import="UserOperator.Launcher"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html; charset=UTF-8" import="UserInformation.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="background-size: 100%;">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/mainStyle.css" type="text/css" media="all" />
        <script src="js/jquery-2.1.4.js"></script>
        <script src="js/script.js"></script>
        <title>Welcome to my blog</title>
	</head>
	<body>
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
            <!-- this is the wrapper div-->
		<div id="wrapper">

			<!--This is the top of my blog   -->
			
		<div id="coverpic">
			<div id="header">
				<div id="logo">Welcome To My Blog</div>
			</div>
			
			<!-- this is for audio
			<audio src="i_will_return.mid"  autoplay >	
			</audio>
			---->

			<!--This is the toolbar-->
			
			<div id="nav">

					<ul>
						<li><a href="mainpage.jsp">HOME</a></li>
						<li><a href="blog_list.jsp?sortName=Science">POSTS</a></li>
						<li><a href="myPhotos.html">PHOTOS</a></li>
						<li><a href="editinfo.jsp">EDIT INFO</a></li>
                                                <li><a href="about.jsp">ABOUT</a></li>
					   
					 <form action="Search" method="post" id="searchForm">
						
                                                <input type="text" name="search" size="20" style="height:20px " placeholder="Search in Web" />
						<button type="submit" id="BingSearch"><img src="image/search.jpg" id="search_Pic"/></button>
                                                    Search options：
                                                        <select name="key" id="selectsearch">
                                                            <option value="tittle">Tittle</option>
                                                            <option value="content" name="content">Content</option>
                                                            <option value="lable">Label</option>
                                                            <option value="sort">Tag</option>
                                                        </select>
                                            </form>
                            </ul>
			</div>
			
			<!-- profile picture part -->
			
			<div id="profile">
				<div id="profilepic">
                                        <a href="about.jsp">
                                            <img id="user" src="<%=user.getPicture(user.username)%>" /> 
                                         </a>   
                                            <div id="username_location">
                                                <h1 class="username" href="about.jsp" id="name"><%=user.username%></h1>
                                                <h3 class="username" id="location">Dhaka,Bangladesh</h3>
				 	</div>
				 </div>
				 <!--Cover picture-->
				 
				 <div id="editcoverpic">
				 	<h1 class="username" id="changecoverpic"><a href="logout.jsp">logout</a></h1>
				 </div>
				 <div id="clock">
				 	
				 </div>
			</div>


		</div>
			
			<!-- this is for the bar -->
			
			<div id="sidebar"><h3>Categories</h3>
                            <p>
                             <%=Blog.getSortList(user.username,"")%>
                            </p>
			</div>
			<!--Contents of my blog-->
			<div id="content">
				
				<%
                        for (int i=0;i<blogs.length;i++) {
                            if(i==blogs.length-1) {
                    %>
<!--                                -->
                                <div class="right2">
                            <%}else{%>
                                <div class="right1">
                            <%}%>
				
				<div class="post">
					<span class="title"><h2><a id="atitle" href="detailspost.jsp?blogTittle=<%=blogs[i].tittle%>"><%=blogs[i].tittle%></a></h2><time class="date_time" datetime="2015-03-14 20:00"><h6> <%=blogs[i].date%></h6></span><br></time> 
					<p> 
                                        <%=blogs[i].content%>
                                        <br>
                                        </p>
                                        <p> 
                                        
                                            <span class="border_right"><a id="atitle" href="detailspost.jsp?blogTittle=<%=blogs[i].tittle%>">Already read</a>(<%=blogs[i].reader%>) </span>                                       </form>
                                            <span class="padding_left"><a id="atitle" href="detailspost.jsp?blogTittle=<%=blogs[i].tittle%>#asset"> comments</a>(<%=blogs[i].assement%>)</span>
                                            <span class="float_right"><a id="atitle" href="detailspost.jsp?blogTittle=<%=blogs[i].tittle%>">continue reading...</a></span>
                                        </p>  
				</div>

				<hr width=99.8%></hr><br>
				                <%}%>
			</div>
			
			<!--Footer or Copyright part-->
			
			<footer>
			<div class="footerwrapper" id="foot">
			</div>
			</footer>
		</div>
		
		<!--this is for the scroll to top button-->
		
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
		<script type="text/javascript" src="http://arrow.scrolltotop.com/arrow11.js"></script>
		<noscript>Not seeing a <a href="http://www.scrolltotop.com/">Scroll to Top Button</a>? Go to our FAQ page for more info.</noscript>
		
		<script src="js/script.js"></script>
	</body>
</html>