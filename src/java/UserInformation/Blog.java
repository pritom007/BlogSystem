/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInformation;

import UserOperator.Launcher;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lenovo
 */
public class Blog {
    public String tittle="", date="", content="", lable="", sort="", mail="", userofblog="";
    public int reader, good, assement, id;
    public static Connection con;
    public Blog(String username,String tittle,boolean cut) {
        con = Launcher.getConnection();
        String query = "select * from `blogof" + username +"` where `tittle`='"+ tittle +"' order by id desc";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                
                //tittle cant be more that 30 character
                this.tittle=replace(tittle);
                this.date=rs.getString("date");
                if (cut)
                    this.content=replaceBlog(cutBlog(rs.getString("content")));
                else
                     this.content=replaceBlog(rs.getString("content"));//kingeditor auto
                
                this.lable=handleLable(replace(rs.getString("lable")));
                this.sort=replace(rs.getString("sort"));
                this.reader=Integer.parseInt(rs.getString("reader"));
                this.good=Integer.parseInt(rs.getString("good"));
                this.assement=Integer.parseInt(rs.getString("assement"));
                 if(assement<0){
                this.assement=0;
                }
                this.id=Integer.parseInt(rs.getString("id"));
                //this.mail=getBlogMail(username);
                //this.userofblog=rs.getString("user");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //this is testing for friend blog content
     public Blog(String tittle,boolean cut) {
        con = Launcher.getConnection();
        String query = "select * from `blog` where  `tittle`='"+ tittle +"' order by id desc;";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                
                //tittle cant be more that 30 character
                this.tittle=replace(tittle);
                this.date=replace(rs.getString("date"));
                this.content=replaceBlog(cutBlog(rs.getString("content")));//kingeditor auto
                this.id=Integer.parseInt(rs.getString("id"));
                this.lable=handleLable(replace(rs.getString("lable")));
                this.sort=replace(rs.getString("sort"));
                this.reader=Integer.parseInt(rs.getString("reader"));
                this.good=Integer.parseInt(rs.getString("good"));
                this.assement=Integer.parseInt(rs.getString("assement"));
                 if(assement<0){
                this.assement=0;
                }
                this.id=Integer.parseInt(rs.getString("id"));
                //this.mail=getBlogMail(username);
                this.userofblog=rs.getString("user");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
//testing finish
    public static String cutBlog(String blog) {
        if(blog.length()>500) {
            blog=blog.substring(0, 299);
            blog+="\"</p><p>\"....\"</p>";
        }
        return blog;
    }
    public static String cutBlogTitle(String tittle) {
        if(tittle.length()>100) {
            tittle=tittle.substring(0, 50);
            tittle+="...";
        }
        return tittle;
    }
    public static String handleBlog(String blog) {
        String tempBlog=""; 
        String[] tokens = blog.split("\n");
        for (int i=0; i<tokens.length; i++) {
            tempBlog += "<p>" + tokens[i] + "</p>";
        }
        return tempBlog;
    }
    public static String handleLable(String lable) {
        String tempLable="";
        String[] tokens = lable.split(" ");
        for (int i=0; i<tokens.length; i++) {
            tempLable += "<a href=\"\">" + tokens[i] + " </a>";
        }
        return tempLable;
    }
    public static String replaceBlog(String str) {
        str=str.replace("align=\"left\"", "");
        str=str.replace("align=\"right\"", "");
        return str;
    }
    public static String replace(String str) {
        str=str.replace("<", "&#60;");
        str=str.replace(">", "&#62;");
        return str;
    }
    public static String getBlogList(String username,String sort) {
        String blogList="";
        String query="";
        con = Launcher.getConnection();
        if (sort.equals("Science"))
            query = "select * from `blogof" + username +"` order by id desc";
        else
            query = "select * from `blogof" + username +"` where `sort`='"+ sort +"' order by id desc limit 200";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
               blogList+= "<li><div class=\"dirlist\"><p><a href=\"detailspost.jsp?blogTittle="+rs.getString("tittle")+"\">"
                       +replace(rs.getString("tittle"))+"</a><span class=\"float_right\">"
                       +rs.getString("date")+"<a href=\"newblogpost.jsp?blogId="+rs.getString("id")+"\">[edit]</a><span> </span><a href=\"deleteBlog?blogId="+rs.getString("id")+"&sortName="+sort+"\">[delete]</a></span></p></div></li>";
            }
            if (blogList.equals(""))
                blogList+= "<li>You haven't include this category<a href=\"newblogpost.jsp#edit\">Write new one</a></li>";
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogList;
    }
    public static String getFriendBlogList(String username,String sort) {
        String blogList="";
        String query="";
        con = Launcher.getConnection();
        if (sort.equals("Science"))
            query = "select * from `blogof" + username +"` order by id desc";
        else
            query = "select * from `blogof" + username +"` where `sort`='"+ sort +"' order by id desc limit 200";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
               blogList+= "<li><a href=\"friend_blog.jsp?blogTittle="+rs.getString("tittle")+"\">"+replace(rs.getString("tittle"))+"</a><span class=\"float_right\">"+rs.getString("date")+"</span></li>";
            }
            if (blogList.equals(""))
                blogList+= "<li>ta bla bla bla...</li>";
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogList;
    } 
    public static String getSortList(String username,String sort) {
        String sortList="";
        String query="";
        con = Launcher.getConnection();
        query = "select * from `sortof" + username +"` order by id";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                if (rs.getString("sortName").equals(sort)){
                    //Selcet
                    sortList+= "<li class=\"chosen\" ><a href=\"blog_list.jsp?sortName="+rs.getString("sortName")+"\">"+rs.getString("sortName")+"</a>("+rs.getString("number")+")</li>";
                }
                else
                    sortList+= "<li><a href=\"blog_list.jsp?sortName="+rs.getString("sortName")+"\">"+rs.getString("sortName")+"</a>("+rs.getString("number")+")</li>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sortList;
    }
    //Get array for sortlist
    public static String[] getEditSortList(String username) {
        String[] sortList = new String[1];
        int i=0;
        int number=0;
        String query="";
        con = Launcher.getConnection();
        query = "select * from `sortof" + username +"` order by id desc";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                number++;
            }
            sortList= new String[number];
            String query1 = "select * from `sortof" + username +"` order by id";
            ResultSet rs1 = con.createStatement().executeQuery(query1);
            while (rs1.next()) {
                sortList[i] = rs1.getString("sortName");
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sortList;
    }
    public static String getFriendSortList(String username,String sort) {
        String sortList="";
        String query="";
        con = Launcher.getConnection();
        query = "select * from `sortof" + username +"` order by id";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                if (rs.getString("sortName").equals(sort))
                    sortList+= "<li class=\"chosen\" ><a href=\"friend_list.jsp?sortName="+rs.getString("sortName")+"\">"+rs.getString("sortName")+"</a>("+rs.getString("number")+")</li>";
                else
                    sortList+= "<li><a href=\"friend_list.jsp?sortName="+rs.getString("sortName")+"\">"+rs.getString("sortName")+"</a>("+rs.getString("number")+")</li>";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sortList;
    }
    //Read a blog post
    public static int readBlog(String username,String tittle) {
        String query = "select * from `blogof" + username +"` where `tittle`='"+ tittle +"'";
        int read=0;
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                String update = "UPDATE `blogof" + username +"` SET `reader`='" + (Integer.parseInt(rs.getString("reader"))+1) + "'WHERE `id`='" + Integer.parseInt(rs.getString("id")) + "';";
                read=Integer.parseInt(rs.getString("reader"));
                con.createStatement().executeUpdate(update);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        return read;
    }
    //Comment on a blog post
    public static void assetBlog(String username,String id) {
        String query = "select * from `blogof" + username +"` where `id`='"+ id +"'";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                String update = "UPDATE `blogof" + username +"` SET `assement`='" + (Integer.parseInt(rs.getString("assement"))+1) + "'WHERE `id`='" + id + "';";
                con.createStatement().executeUpdate(update);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     //cancle the comment
    public static void cancelAssetBlog(String username,String id) {
        String query = "select * from `blogof" + username +"` where `id`='"+ id +"'";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                String update = "UPDATE `blogof" + username +"` SET `assement`='" + (Integer.parseInt(rs.getString("assement"))-1) + "'WHERE `id`='" + id + "';";
                con.createStatement().executeUpdate(update);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //add like
    public static int likeBlog(String username,String id) {
        String query = "select * from `blogof" + username +"` where `id`='"+ id +"'";
        int good=0;
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                good=(Integer.parseInt(rs.getString("good"))+1);
                String update = "UPDATE `blogof" + username +"` SET `good`='" + good + "'WHERE `id`='" + id + "';";
                con.createStatement().executeUpdate(update);
                System.out.println("like update");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return good;
    }
    //undo like
    public static int cancelLikeBlog(String username,String id) {
        String query = "select * from `blogof" + username +"` where `id`='"+ id +"'";
        int good=0;
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                good=(Integer.parseInt(rs.getString("good"))-1);
                String update = "UPDATE `blogof" + username +"` SET `good`='" + good + "'WHERE `id`='" + id + "';";
                con.createStatement().executeUpdate(update);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return good;
    }
    //get the mail of the blogger
    public static String getBlogMail(String username) {
        String mail="";
        String query="";
        con = Launcher.getConnection();
        query = "select * from users where username='"+username+"';";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                mail = rs.getString("mail");
                System.out.println("this is mail : "+mail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mail;
    }
    
    public static String getBlogTittle(String username,String id) {
        String tittle="";
        String query="";
        con = Launcher.getConnection();
        query = "select * from `blogof" + username +"` where `id` = "+id;
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                tittle = rs.getString("tittle");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tittle;
    }
    public static String getBlogLable(String username,String id) {
        String lable="";
        String query="";
        con = Launcher.getConnection();
        query = "select * from `blogof" + username +"` where `id` = "+id;
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                lable = rs.getString("lable");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lable;
    }
    //My blog request
    public static String getBlogBefore(String username,String tittle1) {
        con = Launcher.getConnection();
        String query = "select * from `blogof" + username +"` where `tittle`='"+ tittle1 +"'";
        int id=1;
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                id = (Integer.parseInt(rs.getString("id"))-1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        String tittle="";
        String query1="";
        for(int i=0;i<50;i++) {//...
            query1 = "select * from `blogof" + username +"` where `id` = '" + (id-i)+"'";
            try {
                ResultSet rs = con.createStatement().executeQuery(query1);
                if (rs.next()) {
                    tittle = rs.getString("tittle");
                    break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!tittle.equals(""))
            return "<a href=\"blog.jsp?blogTittle="+tittle+"\">"+tittle+"</a>";
        return "This is the first article";
    }
    public static String getBlogLater(String username,String tittle1) {
        con = Launcher.getConnection();
        String query = "select * from `blogof" + username +"` where `tittle`='"+ tittle1 +"'";
        int id=1;
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                id = (Integer.parseInt(rs.getString("id"))+1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        String tittle="";
        String query1="";
        for(int i=0;i<50;i++) {//...
            query1 = "select * from `blogof" + username +"` where `id` = '" + (id+i)+"'";
            try {
                ResultSet rs = con.createStatement().executeQuery(query1);
                if (rs.next()) {
                    tittle = rs.getString("tittle");
                    break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!tittle.equals(""))
            return "<a href=\"blog.jsp?blogTittle="+tittle+"\">"+tittle+"</a>";
        return "This is the first article";
    }
    //Others blog request
     public static String getBlogBefore1(String username,String tittle1) {
        con = Launcher.getConnection();
        String query = "select * from `blogof" + username +"` where `tittle`='"+ tittle1 +"'";
        int id=1;
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                id = (Integer.parseInt(rs.getString("id"))-1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        String tittle="";
        String query1="";
        for(int i=0;i<50;i++) {//...
            query1 = "select * from `blogof" + username +"` where `id` = '" + (id-i)+"'";
            try {
                ResultSet rs = con.createStatement().executeQuery(query1);
                if (rs.next()) {
                    tittle = rs.getString("tittle");
                    break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!tittle.equals(""))
            return "<a href=\"friend_blog.jsp?blogTittle="+tittle+"\">"+tittle+"</a>";
        return "This is the first blog";
    }
    public static String getBlogLater1(String username,String tittle1) {
        con = Launcher.getConnection();
        String query = "select * from `blogof" + username +"` where `tittle`='"+ tittle1 +"'";
        int id=1;
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if (rs.next()) {
                id = (Integer.parseInt(rs.getString("id"))+1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        String tittle="";
        String query1="";
        for(int i=0;i<50;i++) {//...
            query1 = "select * from `blogof" + username +"` where `id` = '" + (id+i)+"'";
            try {
                ResultSet rs = con.createStatement().executeQuery(query1);
                if (rs.next()) {
                    tittle = rs.getString("tittle");
                    break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!tittle.equals(""))
            return "<a href=\"friend_blog.jsp?blogTittle="+tittle+"\">"+tittle+"</a>";
        return "This is the first blog";
    }
     public static String[] getLikers(String userblogId) {
        String[] users = new String[1];
        int number=0;
        con = Launcher.getConnection();
        String query = "select * from `like` where `userblogId`='"+ userblogId +"'";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                number++;
            }
            if(number!=0) {
                users = new String[number+1];
                users[0]="ok";
                int i=1;
                rs=con.createStatement().executeQuery(query);
                while (rs.next()) {
                    users[i]=rs.getString("user2");
                }
                return users;
            }
            else{
                users[0]="not";
                return users;
            }
        } catch (SQLException ex) {
            
        }
        return users;
    }
     //Cancel like
    public static void deleteLike(String userblogId, String user2) {
        String query = "select * from `like` where `userblogId`='"+ userblogId +"' and user2='"+user2+"'";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            if(rs.next()){
                String update="DELETE FROM `myblog`.`like` WHERE `id`='"+Integer.parseInt(rs.getString("id"))+"';";
                con.createStatement().executeUpdate(update);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     //like
    public static void like(String userblogId, String user2) {
        try {
            
            String update="INSERT INTO `myblog`.`like` (`userblogId`, `user2`, `date`) VALUES ('"+userblogId+"', '"+user2+"', now());";
            con.createStatement().executeUpdate(update);
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //improve the like system
    public static boolean likeUpdate(String userblogId, String user2){
         boolean likes=false;
         String query = "select * from `likepro` where userblogId='"+ userblogId +"' and user2='"+user2+"' and userblogiduser2='"+userblogId+user2+"';";
         System.out.println("before chacking the like query");
         System.out.println(userblogId+" "+user2);
         try {  
              System.out.println("try condition");
              ResultSet rs = con.createStatement().executeQuery(query);
              //System.out.println(user2+" see if rs has next "+rs.getString("user2"));
            if(rs.next()){
                System.out.println("if condition");
                likes=false;
            
            }
            else{                                
            System.out.println("else condition");
            String update="INSERT INTO `likepro` (`userblogId`, `user2`,`userblogiduser2`, `date`) VALUES ('"+userblogId+"', '"+user2+"', '"+userblogId+user2+"', now());";
            con.createStatement().executeUpdate(update);
            System.out.println("after the like query");
            likes=true;
            } 
         }catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(" the like query failed");
            return false;
        }
         return likes;
    }
    //get all the blog list
    public static String getAllblogs(){
        String query = "select * from `blog`;";
         int number=0;
         String blog="All Blogs";//"<li class=\"chosen\" ><a href=\"blog_list.jsp?sortName="+""+"\">"+"AllBlogs"+"</a>("+number+")</li>";
         try {  
          
              ResultSet rs = con.createStatement().executeQuery(query);
                while(rs.next()){
                  number++;
              }
                blog+="("+number+")";//"<li class=\"chosen\" ><a href=\"blog_list.jsp?sortName="+rs.getString("sortName")+"\">"+"AllBlogs"+"</a>("+number+")</li>";
         }catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
            return blog+"("+number+")";
           
        }
         return blog;
    }
    //get the bolgs of all
    public static String getAllBlogList(String username,String sort) {
        String blogList="";
        String query="";
        HttpSession session=null;
        con = Launcher.getConnection();
        if (sort.equals("Science"))
            query = "select * from blog order by id desc";
        else
            query = "select * from blog where `sort`='"+ sort +"' order by id desc limit 200";
        try {
            ResultSet rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                if(rs.getString("user").equals(username)){
               blogList+= "<li><div class=\"dirlist\"><p><a href=\"detailspost.jsp?blogTittle="+rs.getString("tittle")+"\">"
                       +replace(rs.getString("tittle"))+"</a><span class=\"float_right\">"
                       +rs.getString("date")+"<a href=\"newblogpost.jsp?blogId="+rs.getString("id")+"\">[edit]</a><span> </span><a href=\"deleteBlog?blogId="+rs.getString("id")+"&sortName="+sort+"\">[delete]</a></span></p></div></li>";     
               
            }
                else{
                    blogList+= "<li><input type='text' name='userofblog' value='"+rs.getString("user")+"' hidden/><input name='idofblog' value="+rs.getString("id")+"' hidden /><div class=\"dirlist\"><p><a href=\"friend_blog.jsp?blogTittle="+rs.getString("tittle")+"\">"
                       +replace(rs.getString("tittle"))+"</a><span class=\"float_right\">"
                       +rs.getString("date")+"</span></p></div></li>";
                    
                            
                    
                }
            }
            if (blogList.equals(""))
                blogList+= "<li>You haven't include this category<a href=\"newblogpost.jsp#edit\">Write new one</a></li>";
        } catch (SQLException ex) {
            Logger.getLogger(Blog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blogList;
    }
   
}
