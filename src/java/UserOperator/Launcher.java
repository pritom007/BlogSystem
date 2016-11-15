package UserOperator;

import encryption.Encryption;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Launcher {
    static String connectionUrl = "jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6144474?useUnicode=true&characterEncoding=UTF-8";
    static String username = "sql6144474";
    static String Password = "CXyef4z939";
    static Connection con;
    static Encryption encryp = new Encryption();
    public static Connection getConnection() {
        try {
                // class name for mysql driver
                Class.forName("com.mysql.jdbc.Driver");
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        try {
            con = DriverManager.getConnection(connectionUrl,username, Password);
            
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    //Add new user
    public static int insertUser(Connection con,String username,String password,String phone,String mail,String sex) {
        try {
            String query =  "select * from users(username, mail) values (' " + username + "',' " +  mail + "')";
            String query1 =  "select * from users(username) values ('"+username+"')";
            String query2 =  "select * from users(mail) values ('"+mail+"')";
            Statement st;
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            ResultSet rs1 = st.executeQuery(query1);
            ResultSet rs2 = st.executeQuery(query2);
            
               if(rs.next() || rs1.next()|| rs2.next()){
                   System.out.println("This will create alert");
                    return 2;
                }
        } catch (SQLException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String[] update = {"INSERT INTO `users` (`username`, `password`,`mail`,`sex`) VALUES ('" + username + "', '" + password + "', '" +  mail + "', '"+sex+"');",//add usr
             "CREATE TABLE `sql6144474`.`signatureof" +username+"` (`id` INT NOT NULL AUTO_INCREMENT,`content` VARCHAR(45) NOT NULL,`date` DATETIME NOT NULL,PRIMARY KEY (`id`));",//Create user signature table
             "CREATE TABLE `sql6144474`.`pictureof" +username+"` (`id` INT NOT NULL AUTO_INCREMENT,`content` VARCHAR(45) NOT NULL,`date` DATETIME NOT NULL,PRIMARY KEY (`id`));",//Create user avatar table
             "CREATE TABLE `sql6144474`.`blogof" +username+"` (`id` INT NOT NULL AUTO_INCREMENT,  `tittle` TEXT NULL,  `date` DATETIME NULL,  `content` TEXT NULL,  `lable` VARCHAR(45) NULL,  `sort` VARCHAR(45) NULL,  `reader` INT NULL,  `good` INT NULL,  `assement` INT NULL,  PRIMARY KEY (`id`));",
                //blog post
             "CREATE TABLE `sql6144474`.`sortof" +username+"` (`id` INT NOT NULL AUTO_INCREMENT,`sortName` VARCHAR(45) NOT NULL,`number` INT NOT NULL,PRIMARY KEY (`id`));",//博文分类表
             "CREATE TABLE `sql6144474`.`friendof" +username+"` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NULL,PRIMARY KEY (`id`));",//friend form
             "INSERT INTO `sql6144474`.`blogof" +username+"` (`tittle`, `date`, `content`, `lable`, `sort`, `reader`, `good`, `assement`) VALUES ('Welcome to myblog', now(), 'Welcome to myblog', 'Everybody', 'Science', '0', '0', '0');",//First blog
             "INSERT INTO `sql6144474`.`friendof" +username+"` (`name`) VALUES ('pritom');",//add friend
             "INSERT INTO `sql6144474`.`sortof" +username+"` (`sortName`, `number`) VALUES ('Science', '1');"//catagory
            };
            for (int i=0; i<update.length; i++) {
                con.createStatement().executeUpdate(update[i]);
            }
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            return 2;
        }
        
        return 0;
    }
    //login
    public static boolean login(Connection con, String username, String password) {
        String str = "";//password
        if (password.equals(str))
            return false;
        try {
            //To change body of generated methods, choose Tools | Templates.
            //
            System.out.println(password+" "+username);
            String query = "select * from `users` where username='" + username + "'";
            System.out.println(password+" "+username+" 1");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                str = rs.getString("password");
                System.out.println(password+" "+con.createStatement()+"2");
                
            }
            else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
            return str.equals(password);
    }
    //change pass
    public static boolean newPass(Connection con, String username, String password) throws SQLException {
        String str = "";//password
        
        if (password.equals(str))
            return false;
        try {
            //To change body of generated methods, choose Tools | Templates.
            //
            System.out.println(password+" "+username);
            String query = "select * from `users` where username='" + username + "'";
            System.out.println(password+" "+username+"1");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                str = rs.getString("password");
                System.out.println(password+" "+con.createStatement()+"2");
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
            return str.equals(password);
    }
}