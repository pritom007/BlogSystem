/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserOperator;

import UserInformation.User;
import com.jspsmart.upload.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lenovo
 */
@WebServlet(name = "uploadPic123", urlPatterns = {"/uploadPic123"})
public class uploadPic extends HttpServlet {

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
        HttpSession session=null;
        con = Launcher.getConnection();//Get links
        PrintWriter out = response.getWriter();
        SmartUpload sm = new SmartUpload();
        //2 call SM initialization function
        sm.initialize(this.getServletConfig(), request, response);
        //3.Set coding format
        try {
            //4.The component that receives the upload.
            sm.upload();
            //5.Get overloaded Request
            Request req = sm.getRequest();
            //6.get files
            Files files= sm.getFiles();
            //Access to users
            User user = (User)request.getSession().getAttribute("userObject");
            //7.Set file name 
            String name = user.username + "picture";
            //Take a name for the picture
            try {
                String query = "select * from `pictureof" + user.username +"` order by id desc limit 1";
                Statement st;
                st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                if (rs.next())
                    name += (Integer.parseInt(rs.getString("id"))+1);
                else
                    name += "";
            } catch (SQLException ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Upload file
            com.jspsmart.upload.File file = files.getFile(0);
            //Get file name
            String ext = file.getFileExt();
            //6.Save the received file on the hard disk.
            files.getFile(0).saveAs("C:/Users/PritomKumar/Documents/NetBeansProjects/14302016002_finalsubmit/web/photos/"+name+"."+ext);
            user.pictureUrl="photos/"+name+"."+ext;
            request.getSession().setAttribute("userObject",user);
            try {
                String update = "INSERT INTO `pictureof"+user.username+"` (`content`,`date`) VALUES (' " + user.pictureUrl + "',now());";
                con.createStatement().executeUpdate(update);
                //session.setAttribute(name, file);
                //out.print("<script>location.href=\"mainpage.jsp\"</script>");
                response.sendRedirect("index.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ServletException | IOException | SmartUploadException e) {
                // TODO Auto-generated catch block
            
                out.println(e.toString());
                e.printStackTrace();
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
