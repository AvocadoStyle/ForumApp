/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.webservice.Controller;

import com.google.gson.Gson; // converts POJO to JSON and back again
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext; 
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.webservice.modules.messages.subject.ForumSubjectMessage;
import com.webservice.modules.messages.subject.PostsSubjectMessage;
import com.webservice.modules.data.ForumsData;
import com.webservice.modules.data.PostsData;
import com.webservice.modules.data.UsersData;
import com.webservice.modules.data.CommentsData;
import javax.ws.rs.DELETE;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author eden
 */
@Path("reservation")
public class ReservationResource {
    Connection connection;
    public ReservationResource() {
    try{
        this.connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/reservation","admin","admin");
    } catch(Exception e){
        e.printStackTrace();
    }
    }
    
    
    // @@@@@remove it! not good!@@@@@@
    /**
     * reservation GET method, getting all the reservation from the DB
     * @return JSON format of the reservations
     */
    @GET
    @Produces("application/json")
    public String getUsersJson() {
        try {
            Statement statment=connection.createStatement();              
            Statement stmnt=connection.createStatement();
            
            ResultSet resultSet = statment.executeQuery("select * from Reservation");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            String query = "select count(*) from Reservation";
            ResultSet getRows = stmnt.executeQuery(query);
            getRows.next();
            int numberOfRows = getRows.getInt(1);
            TextMessage message[];
            message = new TextMessage[numberOfRows]; // TODO: as the num of the rows
            int rowIndex = 0;

            while(resultSet.next()){
                message[rowIndex] = new TextMessage();
                message[rowIndex].setNum("" + resultSet.getObject(1));
                message[rowIndex].setLocation("" + resultSet.getObject(2));
                message[rowIndex].setClasses("" + resultSet.getObject(3));
                message[rowIndex].setTaken("" + resultSet.getObject(4));
                rowIndex++;
            }
            return new Gson().toJson(message);
        } catch (Exception e){
            e.printStackTrace();
            return "" + e;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Comments
     */
    
    @GET
    @Path("comments") 
    @Produces("application/json")    
    public String getAllComments(){
        CommentsData cd;
        cd = new CommentsData(this.connection);
        return cd.getAllComments();
    }
    
    @GET
    @Path("comments/{comment_id}") 
    @Produces("application/json")    
    public String getSpecificComment(@PathParam("comment_id") String comment_id){
        CommentsData cd;
        cd = new CommentsData(this.connection);
        return cd.getSpecificComment(comment_id);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Posts
     */
    
    
    // not need it
//    @GET
//    @Path("posts")
//    @Produces("application/json")
//    public String getPosts(){
//        PostsData pd = new PostsData(this.connection);
//        return pd.getAllPosts();
//    }
    
    @GET
    @Path("posts/{id}")
    @Produces("application/json")
    public String getPost(@PathParam("id") String id){
        PostsData pd = new PostsData(this.connection);
        return pd.getPost(id);
    } 
    
    
    @GET
    @Path("posts") 
    @Produces("application/json")    
    public String getPostsByForum(@QueryParam("id_forum") String id_forum, @QueryParam("user_id") String user_id,
            @QueryParam("post_name") String post_name, @QueryParam("content") String content){
        
        PostsData pd;
        pd = new PostsData(this.connection);
        System.out.println("test inside!!!!!!!!!!!!!!!!11111111111111111111");
        if(id_forum != null && user_id != null && post_name != null && content != null){
            System.out.println("test inside2222222222222222222222222222222222222222222222");

            return pd.postPost(post_name, id_forum, user_id, content);
        } else if(id_forum != null){
            return pd.getPostsByForumId(id_forum);
        } else if(user_id != null) {
            return pd.getPostsByUserId(user_id);
        }else {
            return pd.getAllPosts();
        }
    }
    
    @POST
    @Path("posts")
    @Produces("application/json")
    public String postPost(@QueryParam("id_forum") String id_forum, @QueryParam("user_id") String user_id,
            @QueryParam("post_name") String post_name, @QueryParam("content") String content){
        PostsData pd;
        pd = new PostsData(this.connection);
        System.out.println("test inside!!!!!!!!!!!!!!!!11111111111111111111");
        if(id_forum != null && user_id != null && post_name != null && content != null){
            System.out.println("test inside2222222222222222222222222222222222222222222222");
            return pd.postPost(post_name, id_forum, user_id, content);
        }
        return "404 not valid";
    }
//    @GET
//    @Path("posts")
//    @Produces("application/json")
//    public String getPostsByUserId(@QueryParam("user_id") String user_id){
//        System.out.println("check11111111111111");
//        if(user_id != null){
//            System.out.println("check22222222222");
//            PostsData pd;
//            pd = new PostsData(this.connection); 
//            return pd.getPostsByUserId(user_id);
//        } else {
//            PostsData pd = new PostsData(this.connection);
//            return pd.getAllPosts();
//        }
//    }
    
    
    
    
    
    /**
     * Users
     */
    @GET
    @Path("users")
    @Produces("application/json")    
    public String getForumSubjects(){
        UsersData ud;
        ud = new UsersData(this.connection);  
        return ud.getAllUsers();
    }
    
    
    
    @GET
    @Path("users/{user_id}")
    @Produces("application/json")    
    public String getSpecificUser(@PathParam("user_id") String  user_id){
        UsersData ud;
        ud = new UsersData(this.connection);
        return ud.getUser(user_id);
    }
    
    @GET
    @Path("users/login")
    @Produces("application/json")
    public String Login(@QueryParam("user_name") String user_name, @QueryParam("password") String password){
        UsersData ud;
        ud = new UsersData(this.connection);  
        return ud.Login64(user_name, password);
    }
    
    
    @POST
    @Path("users/register")
    @Produces("application/json")
    public String Register(@QueryParam("user_name") String user_name, @QueryParam("password") String password){
    UsersData ud;
    ud = new UsersData(this.connection);  
    return "" + ud.Register64(user_name, password);
    }
    
    
    
    
    
    
    
    
    /**
     * Forums
     */
    
    /**
     * GET REQUEST - getting all the forums, using the ForumsData and connects to
     * the DB and withdraw the forums data.
     * @param forum_name optional - giving a forum name in the query 
     * for example: forums?forum_name=${this.props.chosenForum}
     * @return JSON format of the forums
     */
    @GET
    @Path("forums")
    @Produces("application/json")    
    public String getForumSubjects(@QueryParam("forum_name") String forum_name){
        if(forum_name == null){
            ForumsData fd;
            fd = new ForumsData(this.connection);  
            return fd.getAllForums();
        } else {
            ForumsData fd;  
            fd = new ForumsData(this.connection);
            return fd.getStartingNameForums(forum_name);
        }
    }
    
    /**
     * GET REQUEST - get a specific forum by forum id
     * /forums/${id_forum} for example
     * @param id the id_forum parameter
     * @return JSON format of the specific forum
     */
    @GET
    @Path("forums/{id}") 
    @Produces("application/json")    
    public String getForum(@PathParam( "id" ) String id){ 
        ForumsData fd;
        fd = new ForumsData(this.connection); 
        return fd.getForum(id); 
    }
    
    /**
     * POST REQUEST -  for updating or creating a forum.
     * @param add_forum_name query param for adding forum by name and the 
     * id_forum generates in ascending order.
     * @return the list of the forums (from getForumSubjects with ("") optional
     * parameter.
     */
    @POST 
    @Path("forums")
    @Consumes("application/json")
    @Produces("application/json")  
    public String postForum(@QueryParam("add_forum_name") String forum_name) {   
        ForumsData fd;    
        fd = new ForumsData(this.connection); 
        fd.postForum(forum_name);
        return this.getForumSubjects("");
        
    }
    
    /**
     * DELETE REQUEST - delete specific forum by name
     * @param forum_name the forum name to be deleted
     * @return 
     */
    @DELETE 
    @Path("forums")
    @Produces("application/json")    
    public String deleteForum(@QueryParam("delete_forum_name") String forum_name) {
            ForumsData fd;
            fd = new ForumsData(this.connection);  
            fd.deleteForum(forum_name);
            return this.getForumSubjects("");     
    }
    
    
    
    
     
    
    // private class that contains the message we wish to send
    class TextMessage {
        private String num; // message we're sending
        private String location;
        private String classes;
        private String taken;
        
        // returns the message
        public String getNum(){
            return this.num;
        }
        public String getLocation(){
            return this.location;
        }
        public String getClasses(){
            return this.classes;
        } 
        public String getTaken(){
            return this.taken;
        }        
        public String setNum(String num){
            return this.num = num;
        }
        public String setLocation(String location){
            return this.location = location;
        }
        public String setClasses(String classes){
            return this.classes = classes;
        } 
        public String setTaken(String taken){
            return this.taken = taken;
        }        
    } // end class TextMessage
}
