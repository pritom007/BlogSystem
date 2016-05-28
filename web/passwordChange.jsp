<%-- 
    Document   : passwordChang
    Created on : Jul 3, 2015, 6:30:00 PM
    Author     : PritomKumar
--%>

<%@page import="UserInformation.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.security.acl.Owner"%>
<%@ page import ="java.sql.*" %>
<%@page import="java.io.*"%>
<%
try{
    User user = (User)request.getSession().getAttribute("userObject");
    String pwd = request.getParameter("pw");
    String email = request.getParameter("email");
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myblog","root", "password");
    Statement st = con.createStatement();
    String sql="SELECT * FROM users WHERE username='"+user.username+"' and password='"+pwd+"';"; 
    
    ResultSet rs=null; 
    rs=st.executeQuery(sql);
    
    if(rs.next()){
    	if((user.username.equals(rs.getString(2))) && (pwd.equals(rs.getString(3)))){
    		System.out.println(rs.getString(2));
    		String update = "UPDATE `users` SET password='" + request.getParameter("pw1") + "' WHERE username='"+user.username+"';";
                con.createStatement().executeUpdate(update);
               
        	System.out.println("Successfully inserted the file into the database!");
 	    	
                response.sendRedirect("index.jsp");
        	con.close();
                rs.close();    	
    	
            }
        }
    }catch(Exception e){
	System.out.println("exception part may be"); 
	response.sendRedirect("editinfo.jsp");
	e.printStackTrace(); 
	
    }
%>
