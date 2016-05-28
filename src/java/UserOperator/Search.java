/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserOperator;

import UserInformation.Blog;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "Search", urlPatterns = {"/Search"})
public class Search extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static Connection con;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        con = Launcher.getConnection();
        PrintWriter out = response.getWriter();
        String result = "";
        try {
            String query = "select * from `blog` order by id desc";
            //Sort
            /*
            if(request.getParameter("user").equals("all")) {
               if(request.getParameter("sort").equals("time")) {
                   if(request.getParameter("rank").equals("up")) 
                        query = "select * from `blog`";
                   else
                       query = "select * from `blog` order by id desc";
               }
               else{
                   if(request.getParameter("rank").equals("up")) 
                        query = "select * from `blog` order by reader";
                   else
                       query = "select * from `blog` order by reader desc";
               }
            }else{
                if(request.getParameter("sort").equals("time")) {
                   if(request.getParameter("rank").equals("up")) 
                        query = "select * from `blog` where user='" + request.getParameter("username") + "'";
                   else
                       query = "select * from `blog` where user='" + request.getParameter("username") + "' order by id desc";
               }
               else{
                   if(request.getParameter("rank").equals("up")) 
                        query = "select * from `blog` where user='" + request.getParameter("username") + "' order by reader";
                   else
                       query = "select * from `blog` where user='" + request.getParameter("username") + "' order by reader desc";
               }
            }
            */
            ResultSet rs;
            rs = con.createStatement().executeQuery(query);
            Pattern p = Pattern.compile(".*"+request.getParameter("search")+".*",Pattern.CASE_INSENSITIVE);//Regular verification
            while(rs.next()) {
                Matcher m = p.matcher(rs.getString(request.getParameter("key")));
                Matcher m1=p.matcher(rs.getString("user"));
                //Matcher m2=p.matcher(rs.getString(request.getParameter("content")).toString());
                if (m.matches() || m1.matches()){
                    result+= "<li><a href=\"friend_blog.jsp?blogTittle="+rs.getString("tittle")+"&user="+rs.getString("user")+"\">"+Blog.replace(rs.getString("tittle"))+"</a><span class=\"float_right\">"+rs.getString("date")+"</span></li>";
                }
                
            }
            if(result.equals(""))
                result = "<li>No match</li>";
            request.getSession().setAttribute("searchResult", result);
            response.sendRedirect("searchResult.jsp");
        } catch (SQLException ex) {
            out.println(ex.toString());
           //response.sendError(404);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
