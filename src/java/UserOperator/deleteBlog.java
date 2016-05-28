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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "deleteBlog", urlPatterns = {"/deleteBlog"})
public class deleteBlog extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static Connection con;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        con=Launcher.getConnection();
        User user = (User)request.getSession().getAttribute("userObject");
        //Get the original number of Posts
        String query = "select * from `sortof" + user.username +"` WHERE `sortName`='"+ request.getParameter("sortName")+"' ";
        ResultSet rs = null;
        int id = 0;
        int id2 =0;//Because the table is not reasonable, two tables are used, this is the ID of the blog in another table.
        int number=0;
        try {
            if (!request.getParameter("sortName").equals("Science")) {
              ResultSet rs1 = con.createStatement().executeQuery("select * from `sortof" + user.username +"` WHERE `id`='1' ");
              if (rs1.next()) {
                int number2 = Integer.parseInt(rs1.getString("number"))-1;
                con.createStatement().executeUpdate("UPDATE `myblog`.`sortof"+user.username+"` SET `number`='"+number2+"' WHERE `id`='1' ;");
              }
            }
            rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                id = Integer.parseInt(rs.getString("id"));
                number=Integer.parseInt(rs.getString("number"))-1;
            }
        } catch (SQLException ex) {
            out.println(ex.toString());
        }
        String query2 = "select * from `blog` where `tittle` = '"+Blog.getBlogTittle(user.username, request.getParameter("blogId"))+"'";
        try {
            rs = con.createStatement().executeQuery(query2);
            if (rs.next()) {
                id2 = Integer.parseInt(rs.getString("id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(deleteBlog.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Delete blog
        String[] update = {"DELETE FROM `myblog`.`blogof"+user.username+"` WHERE `id`='"+request.getParameter("blogId")+"'",
                            "DELETE FROM `myblog`.`blog` WHERE `id`='"+id2+"'",
                        "UPDATE `myblog`.`sortof"+user.username+"` SET `number`='"+number+"' WHERE `id`='"+id+"' ;"
        };
        try {
            for (int i=0; i<update.length; i++) {
                con.createStatement().executeUpdate(update[i]);
            }
            String sort = java.net.URLEncoder.encode(request.getParameter("sortName"),"UTF-8");//handel chinese
            response.sendRedirect("blog_list.jsp?sortName="+sort);
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
