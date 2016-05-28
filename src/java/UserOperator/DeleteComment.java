/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserOperator;

import UserInformation.Blog;
import UserInformation.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "DeleteComment", urlPatterns = {"/DeleteComment"})
public class DeleteComment extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("UTF-8");
        con=Launcher.getConnection();
        String update ="DELETE FROM `myblog`.`comment` WHERE `id`='"+request.getParameter("commentId")+"';";
        try {
            con.createStatement().executeUpdate(update);
            Blog.cancelAssetBlog(request.getParameter("username"), request.getParameter("blogId"));
            if (request.getParameter("username").equals(request.getParameter("user"))) {
                String tittle = Blog.getBlogTittle(request.getParameter("username"),request.getParameter("blogId"));
                tittle = java.net.URLEncoder.encode(tittle,"UTF-8");//for chinese
                response.sendRedirect("detailspost.jsp?blogTittle="+tittle+"#asset1");
            }
            else {
                User user = new User(request.getParameter("username"));
                request.getSession().setAttribute("friendObject", user);
                String tittle = Blog.getBlogTittle(request.getParameter("username"),request.getParameter("blogId"));
                tittle = java.net.URLEncoder.encode(tittle,"UTF-8");//for chinese
                response.sendRedirect("friend_blog.jsp?blogTittle="+tittle+"#asset1");
            }
        } catch (SQLException ex) {
            out.println(ex.toString());
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
