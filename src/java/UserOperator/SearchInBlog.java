/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserOperator;

import UserInformation.Blog;
import static UserOperator.Search.con;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author PritomKumar
 */
@WebServlet(name = "SearchBlog", urlPatterns = {"/SearchBlog"})
public class SearchInBlog extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        con = Launcher.getConnection();
        PrintWriter out = response.getWriter();
        String result = "";
         try {
            String query = "";
            //Sort
           
            ResultSet rs;
            rs = con.createStatement().executeQuery(query);
            Pattern p = Pattern.compile(".*"+request.getParameter("search")+".*");//Regular verification
            while(rs.next()) {
                Matcher m = p.matcher(rs.getString(request.getParameter("key")));
                if (m.matches()){
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

}
