package UserOperator;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Launcher_1 {
    static String connectionUrl = "jdbc:mysql://localhost:3306/myblog?useUnicode=true&characterEncoding=UTF-8";
    static String username = "root";
    static String Password = "password";
    static Connection con;
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
    //添加用户
    public static int insertUser(Connection con,String username,String password,String phone,String mail) {
        try {
            String[] query = {"select * from `users` where phone='" + phone + "'",
                               "select * from `users` where mail='" + mail + "'",
                               "select * from `users` where username='" + username + "'",            
            };
            Statement st;
            st = con.createStatement();
            if (phone.equals("")) {//邮箱注册
                ResultSet rs = st.executeQuery(query[1]);
                if (rs.next())
                    return 2;
                ResultSet rs1 = st.executeQuery(query[2]);
                if (rs1.next())
                    return 3;
            }
            else {//手机注册
                ResultSet rs = st.executeQuery(query[0]);
                if (rs.next())
                    return 1;
                ResultSet rs1 = st.executeQuery(query[2]);
                if (rs1.next())
                    return 3;
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(Launcher_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String[] update = {"INSERT INTO `users` (`username`, `password`,`phone`,`mail`) VALUES ('" + username + "', '" + password + "', '" + phone + "', '" + mail + "');",//添加用户
             "CREATE TABLE `myblog3`.`signatureof" +username+"` (`id` INT NOT NULL AUTO_INCREMENT,`content` VARCHAR(45) NOT NULL,`date` DATETIME NOT NULL,PRIMARY KEY (`id`));",//创建用户签名表
             "CREATE TABLE `myblog3`.`pictureof" +username+"` (`id` INT NOT NULL AUTO_INCREMENT,`content` VARCHAR(45) NOT NULL,`date` DATETIME NOT NULL,PRIMARY KEY (`id`));",//创建用户头像表
             "CREATE TABLE `myblog3`.`blogof" +username+"` (`id` INT NOT NULL AUTO_INCREMENT,  `tittle` TEXT NULL,  `date` DATETIME NULL,  `content` TEXT NULL,  `lable` VARCHAR(45) NULL,  `sort` VARCHAR(45) NULL,  `reader` INT NULL,  `good` INT NULL,  `assement` INT NULL,  PRIMARY KEY (`id`));",
                //博文表
             "CREATE TABLE `myblog3`.`sortof" +username+"` (`id` INT NOT NULL AUTO_INCREMENT,`sortName` VARCHAR(45) NOT NULL,`number` INT NOT NULL,PRIMARY KEY (`id`));",//博文分类表
             "CREATE TABLE `myblog3`.`friendof" +username+"` (`id` INT NOT NULL AUTO_INCREMENT,`name` VARCHAR(45) NULL,PRIMARY KEY (`id`));",//好友表
             "INSERT INTO `myblog3`.`blogof" +username+"` (`tittle`, `date`, `content`, `lable`, `sort`, `reader`, `good`, `assement`) VALUES ('欢迎使用myblog', now(), '欢迎使用myblog，祝您旅途愉快', '大众', '全部博文', '0', '0', '0');",//第一篇博客
             "INSERT INTO `myblog3`.`friendof" +username+"` (`name`) VALUES ('duocai');",//加好友
             "INSERT INTO `myblog3`.`sortof" +username+"` (`sortName`, `number`) VALUES ('全部博文', '1');"//初始化博文分类
            };
            for (int i=0; i<update.length; i++) {
                con.createStatement().executeUpdate(update[i]);
            }
        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    //登录检查
    public static boolean login(Connection con, String username, String password) {
        String str = "";//密码
        if (password.equals(str))
            return false;
        try {
            //To change body of generated methods, choose Tools | Templates.
            //众里寻他千百度，蓦然回首，那人却在灯火阑珊处
            String query = "select * from `users` where username='" + username + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                str = rs.getString("password");
            }
            else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Launcher_1.class.getName()).log(Level.SEVERE, null, ex);
        }
            return str.equals(password);
    }
}