package UserInformation;

import UserOperator.Launcher;
import UserOperator.Register;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class User {
    public String username;
    public String signature = "Input your signature, let people know more about you";//have not completed
    public String pictureUrl = "photos/picture.jpg";
    static Connection con;
    public User() {
        
    }
    public User(String username) {
        this.username = username;
    }
    //rename the user name testing
    public static void renameUsername(Connection con,String oldName,String newName) {
            try {
                    String update = "UPDATE `users` SET `username`='" + newName + "' WHERE `username`='" + oldName + "';";
                    con.createStatement().executeUpdate(update);
            } catch (SQLException ex) {
                    // TODO Auto-generated catch block
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    //change the password
    public static void changePassword(Connection con,String newPW,String username) {
            try {
                    String update = "UPDATE `users` SET `password`='" + newPW + "' WHERE `username`='"+username+"';";
                    con.createStatement().executeUpdate(update);
            } catch (SQLException ex) {
                    // TODO Auto-generated catch block
                    Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    //Signature function (not done)
    public static void signature(String username,String signature,Connection con) {
        try {
            String update = "INSERT INTO `signatureof"+username+"` (`content`,`date`) VALUES (' " + signature + "',now());";
            con.createStatement().executeUpdate(update);
        } catch (SQLException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getSignature(String username,Connection con) {
        try {
            String query = "select * from `signatureof" + username +"` order by id desc limit 1";
            Statement st;
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next())
                return rs.getString("content");
            else
                return " Edit your signature, let people know more about you";
        } catch (SQLException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Edit your signature, let people know more about you";
    }
    public static String getPicture(String username) {
        try {
            String query = "select * from `pictureof" + username +"` order by id desc limit 1";
            Connection con=Launcher.getConnection();
            Statement st;
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next())
                return rs.getString("content");
            else
                return "photos/picture.jpg";
        } catch (SQLException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "photos/picture.jpg";
    }
    public static String getFriendList(String username) {
        String friendList="";
        String query="";
        con = Launcher.getConnection();
        query = "select * from `friendof" + username +"` order by id";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                    friendList+= "<li><a href=\"friend_page.jsp?name="+rs.getString("name")+"\">"+rs.getString("name")+"</a></li>";
                }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return friendList;
    }
    //information about user's gender
    public static String getGender(String username){
        con = Launcher.getConnection();
        String sex="";
        String query="select * from users where username='" + username +"';";
       try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                    sex+= rs.getString("sex");
                }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sex;
    
    }
}
