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
@WebServlet(name = "EditBlog", urlPatterns = {"/EditBlog"})
public class EditBlog extends HttpServlet {

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
        System.out.println("ttt");
        PrintWriter out = response.getWriter();
        System.out.println("xul");
        request.setCharacterEncoding("UTF-8");
        System.out.println("zas");
        con=Launcher.getConnection();
        System.out.println("kal");
        User user = (User)request.getSession().getAttribute("userObject");
        System.out.println("456");
        System.out.println(request.getParameter("operation"));
        if (request.getParameter("operation").equals("create")) {
            //Get the original number of posts, plus one
           System.out.println("7");
            int id = 0;
            int number=0;
            try {
                String query = "select * from `sortof" + user.username +"` WHERE `sortName`='"+ request.getParameter("sort")+"' ";
                ResultSet rs = null;
                if (!request.getParameter("sort").equals("Science")) {
                  ResultSet rs1 = con.createStatement().executeQuery("select * from `sortof" + user.username +"` WHERE `id`='1' ");
                  if (rs1.next()) {
                    int number2 = Integer.parseInt(rs1.getString("number"))+1;
                    con.createStatement().executeUpdate("UPDATE `myblog`.`sortof"+user.username+"` SET `number`='"+number2+"' WHERE `id`='1' ;");
                  System.out.println("8");
                  }
                }
                rs = con.createStatement().executeQuery(query);
                if (rs.next()) {
                    id = Integer.parseInt(rs.getString("id"));
                    number=Integer.parseInt(rs.getString("number"))+1;
                System.out.println("9");
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditBlog.class.getName()).log(Level.SEVERE, null, ex);
            }
                //Insert a blog post
            String[] update = { "INSERT INTO `blogof"+user.username+"` (`tittle`, `date`, `content`, `lable`, `sort`, `reader`, `good`,`assement`) VALUES ('"
                    + request.getParameter("tittle") + "', now(), '"+ request.getParameter("content").replace("'", "\"")+ "', '"+ request.getParameter("lable") + "', '"
                    + request.getParameter("sort")+ "','0','0','0');",
                    "INSERT INTO `blog` (`tittle`, `date`, `content`, `lable`, `sort`, `reader`, `good`,`assement`,`user`) VALUES ('"
                    + request.getParameter("tittle") + "', now(), '"+ request.getParameter("content").replace("'", "\"")+ "', '"+ request.getParameter("lable") + "', '"
                    + request.getParameter("sort")+ "','0','0','0','"+user.username+"');",
                    "UPDATE `myblog`.`sortof"+user.username+"` SET `number`='"+number+"' WHERE `id`='"+id+"' ;"
            };

            try {
                for (int i=0; i<update.length; i++) {
                    con.createStatement().executeUpdate(update[i]);
                }
                Blog[] blogs = new Blog[1];//An article was set during the registration
                int numberofBlog=0;
                //get blog from the database
                try {
                    String query = "select * from `blogof" + user.username +"` order by id desc";
                    ResultSet rs = con.createStatement().executeQuery(query);
                    while (rs.next()) {
                        numberofBlog++;
                    }
                    if (numberofBlog>5) 
                        blogs = new Blog[5];
                    else
                        blogs = new Blog[numberofBlog];
                    ResultSet rs1 = con.createStatement().executeQuery(query);
                    for (int i=0; i< blogs.length; i++) {
                        if(rs1.next())
                            blogs[i] = new Blog(user.username,rs1.getString("tittle"),true);
                    }
                } catch (Exception ex) {
                   out.println(ex.toString());
                }
                System.out.println("10");
                response.sendRedirect("mainpage.jsp");
            } catch (SQLException ex) {
                System.out.println("1233456");
                out.println(ex.toString());
            }
        } 
        else {//To edit the blog post. Remove old blog
            int id2=0;
            String query2 = "select * from `blog` where `tittle` = '"+Blog.getBlogTittle(user.username, request.getParameter("blogId"))+"'";
            try {
               ResultSet rs = con.createStatement().executeQuery(query2);
                if (rs.next()) {
                    System.out.println("11");
                    id2 = Integer.parseInt(rs.getString("id"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(deleteBlog.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Delete blog
            String[] update = {"DELETE FROM `myblog`.`blogof"+user.username+"` WHERE `id`='"+request.getParameter("blogId")+"'",
                    "DELETE FROM `myblog`.`blog` WHERE `id`='"+id2+"'"
            };
            try {
                for (int i=0; i<update.length; i++) {
                    con.createStatement().executeUpdate(update[i]);
                System.out.println("12");
                }
            } catch (SQLException ex) {
                System.out.println("16");
                out.println(ex.toString());
            }
            //Insert a blog post
        String[] update1 = { "INSERT INTO `blogof"+user.username+"` (`tittle`, `date`, `content`, `lable`, `sort`, `reader`, `good`,`assement`) VALUES ('"
                + request.getParameter("tittle") + "', now(), '"+ request.getParameter("content").replace("'", "\"")+ "', '"+ request.getParameter("lable") + "', '"
                + request.getParameter("sort")+ "','0','0','0');",
                "INSERT INTO `blog` (`tittle`, `date`, `content`, `lable`, `sort`, `reader`, `good`,`assement`,`user`) VALUES ('"
                    + request.getParameter("tittle") + "', now(), '"+ request.getParameter("content").replace("'", "\"")+ "', '"+ request.getParameter("lable") + "', '"
                    + request.getParameter("sort")+ "','0','0','0','"+user.username+"');"
        };
        
        try {
            for (int i=0; i<update1.length; i++) {
                con.createStatement().executeUpdate(update1[i]);
                System.out.println("13");
            }
            Blog[] blogs = new Blog[1];//There was an article in the registration
            int numberofBlog=0;
            //get blog from the database
            try {
                String query1 = "select * from `blogof" + user.username +"` order by id desc";
                ResultSet rs1 = con.createStatement().executeQuery(query1);
                while (rs1.next()) {
                    numberofBlog++;
                }
                if (numberofBlog>5) 
                    blogs = new Blog[5];
                else
                    blogs = new Blog[numberofBlog];
                ResultSet rs2 = con.createStatement().executeQuery(query1);
                for (int i=0; i< blogs.length; i++) {
                    if(rs2.next())
                        blogs[i] = new Blog(user.username,rs2.getString("tittle"),true);
                }
            } catch (Exception ex) {
                System.out.println("15");
               out.println(ex.toString());
            }
            System.out.println("14");
            response.sendRedirect("mainpage.jsp");
        } catch (SQLException ex) {
            out.println(ex.toString());
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
