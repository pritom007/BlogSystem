<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
          pageEncoding="ISO-8859-1"%>
           <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"                                "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
     </head>
    <body>
     <%@ page import="java.io.*"%>
 <%@ page import="java.sql.*"%>
 <%@ page import="java.util.*"%>
 <%@ page import="java.text.*"%>
 <%@ page import="javax.servlet.*"%>
 <%@ page import="javax.servlet.http.*"%>
 <%@ page import="javax.servlet.http.HttpSession"%>
 <%@ page language="java"%>
 <%@ page session="true"%>
 <%
   try{
    //PrintWriter out=response.getWriter();

		  out.println("Retrieve Image Example!");
		  String driverName = "com.mysql.jdbc.Driver";
		  String url = "jdbc:mysql://localhost:3306/";
		  String dbName = "finalproject";
		  String userName = "root";
	      String password = "password";
	      Connection con = null;
	      Class.forName(driverName);
	      con = DriverManager.getConnection(url+dbName,userName,password);
	      Statement st = con.createStatement();
	      String pString=session.getAttribute("userid").toString();
	      System.out.println(pString);
     	  PreparedStatement pre1 = con.prepareStatement("select * from register_user where  username='" + pString+"'");
 		  ResultSet rs1=pre1.executeQuery();
 		 System.out.println(pString);
	while(rs1.next()){
		
		byte[] bytearray1 = new byte[4096];  
        int size1=0;  
        InputStream sImage1;  
        sImage1 = rs1.getBinaryStream(5);
        response.reset();  
        response.setContentType("image/jpeg");  
        response.addHeader("Content-Disposition","filename=logo.jpg");  
        System.out.println(pString);
        
        while((size1=sImage1.read(bytearray1))!= -1 ){  
               response.getOutputStream().write(bytearray1,0,size1);
               
               System.out.println("asdasd");
        }  
        response.flushBuffer(); 
        sImage1.close();  
        rs1.close();  
     }
  pre1.close();
  con.close();  
  }
 catch (Exception e){
         out.println(e.getMessage());
        }
%>
  </body>
    </html>