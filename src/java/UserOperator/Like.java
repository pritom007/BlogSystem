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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "Likenum", urlPatterns = {"/Likenum"})
public class Like extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        System.out.println("This is like num");
        
        User user = (User)request.getSession().getAttribute("userObject");
        String msg=null;
        PrintWriter out = response.getWriter();
        
        System.out.println(request.getParameter("username")+request.getParameter("blogId"));
        String tittle = java.net.URLEncoder.encode(request.getParameter("blogTittle"),"UTF-8");//for chinese characters -_-
        //out.print(Blog.likeBlog(request.getParameter("username"), request.getParameter("blogId")));
        if(Blog.likeUpdate(request.getParameter("username")+ request.getParameter("blogId"), user.username)){
        System.out.println(user.username+" username "+ request.getParameter("username"));
        //Blog.like(request.getParameter("username")+ request.getParameter("blogId"), request.getParameter("user"));
        Blog.likeBlog(request.getParameter("username"), request.getParameter("blogId"));
        System.out.println("before redirecting");
            if(user.username.equals(request.getParameter("username"))){
                response.sendRedirect("detailspost.jsp?blogTittle="+tittle+"#good1");
            }
            else{
            response.sendRedirect("detailspost.jsp?friend_blog.jsp="+tittle+"#good1");
            }
    }
        else{
            //response.sendError(404, "like didn't count");
            msg="detailspost.jsp?blogTittle="+tittle+"#good1";
            System.out.println(user.username+" username asdasd"+ request.getParameter("username"));
            if(user.username.equals(request.getParameter("username"))){
            out.print("<script>alert('you alread liked it!!!');location.href=\"detailspost.jsp?blogTittle="+tittle+"#good1\";</script>");
            //response.sendRedirect("detailspost.jsp?blogTittle="+java.net.URLEncoder.encode(request.getParameter("blogTittle"),"UTF-8")+"#good1");
            }
            else{
            out.print("<script>alert('you alread liked it!!!');location.href=\"friend_blog.jsp?blogTittle="+tittle+"#good1\";</script>");
            }
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
