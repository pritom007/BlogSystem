/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserOperator;

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
@WebServlet(name = "DeleteSort", urlPatterns = {"/DeleteSort"})
public class DeleteSort extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        con=Launcher.getConnection();
        User user = (User)request.getSession().getAttribute("userObject");
        //delete sort
        int id=0;
        String query = "select * from `sortof" + user.username +"` WHERE `sortName`='"+request.getParameter("deletesort")+"'";
        ResultSet rs;
        try {
            rs = con.createStatement().executeQuery(query);
             if (rs.next()) {
                 id = Integer.parseInt(rs.getString("id"));
             }
        } catch (SQLException ex) {
            out.println(ex.toString());
        }
        String[] update = {"DELETE FROM `myblog`.`sortof"+user.username+"` WHERE `id`='"+id+"'",
                        "UPDATE `myblog`.`blogof"+user.username+"` SET `sort`='Science' WHERE `sort`='"+request.getParameter("deletesort")+"' ;"
        };
        try {
            for (int i=0; i<update.length; i++) {
                con.createStatement().executeUpdate(update[i]);
            }
            //out.println(request.getParameter("sort")+id);
            response.sendRedirect("mainpage.jsp");
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
