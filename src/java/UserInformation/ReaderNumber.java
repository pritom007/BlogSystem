/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInformation;

import static UserOperator.Comment.con;
import UserOperator.Launcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PritomKumar
 */
@WebServlet(name = "ReaderNumber", urlPatterns = {"/ReaderNumber"})
public class ReaderNumber extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=null;
        User user1 = (User)request.getSession().getAttribute("userObject");
        ResultSet rs=null;
        con=Launcher.getConnection();
        request.setCharacterEncoding("UTF-8");
        String update = "SELECT FROM `blogof`."+user1.username+" (`id`, `tittle`) VALUES (?,'"+ session.getAttribute("numberofreader")+"')";
        try {
             rs=con.createStatement().executeQuery(update);
            if(rs.next()){
                update="INSERT INTO `blogof`."+user1.username+" (`reader`) VALUES (?,'"+ Integer.parseInt(rs.getString("reader"))+1+"')";
            con.createStatement().executeUpdate(update);
            response.sendRedirect(session.getAttribute("path").toString());
            }
            else {
               response.sendRedirect(session.getAttribute("path").toString());
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
