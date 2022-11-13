package com.webservice.modules.data;
import com.webservice.modules.data.PostsData;
import com.google.gson.Gson;
import com.webservice.modules.messages.subject.PostsSubjectMessage;
import com.webservice.modules.data.UsersData;
import java.sql.*;


/**
 *
 * @author eden
 */
public class PostsData extends DataConsumer {
    String GET_ALL_POSTS_QUERY = "SELECT * FROM Posts";
    String GET_USER_ID = " WHERE user_id= ";
    String GET_ROW_NUMBER_POSTS = "SELECT count(*) FROM Posts";
    String GET_ONE_POST = " WHERE post_id= ";
    String GET_POSTS_BY_FORUM_ID = " WHERE id_forum= ";
    String DELETE_POST_NAME_WITH_PERMISSION = "DELETE FROM Posts WHERE post_name= ";
    String DELETE_POST_NAME_WITH_PERMISSION_FOR_USER = " AND user_id= ";
    String DELETE_ONE_POST = "DELETE FROM Posts WHERE post_id= ";
    private final String GET_FIRST_ROW_POST_ID = "select post_id from Posts LIMIT 1";
    UsersData ud;
    CommentsData cd;
    public PostsData(Connection connect){
        super(connect);
        this.ud = new UsersData(this.getConnection());
        this.cd = new CommentsData(this.getConnection());
    }
    
    public String getAllPosts(){
        try{
            Statement statmentAll=this.getConnection().createStatement();  
            Statement statmentRows=this.getConnection().createStatement();  
            ResultSet resultSet = statmentAll.executeQuery(GET_ALL_POSTS_QUERY);
           
            ResultSet getRows = statmentRows.executeQuery(GET_ROW_NUMBER_POSTS);
            getRows.next();
            int numberOfRows = getRows.getInt(1);
            
            PostsSubjectMessage postsSubject[];
            postsSubject = new PostsSubjectMessage[numberOfRows]; // TODO: as the num of the rows
            int rowIndex = 0;

            while(resultSet.next()){
                postsSubject[rowIndex] = new PostsSubjectMessage();
                postsSubject[rowIndex].setPostId("" + resultSet.getObject(1));
                postsSubject[rowIndex].setIdForum("" + resultSet.getObject(2));
                postsSubject[rowIndex].setUserId("" + resultSet.getObject(3));
                postsSubject[rowIndex].setPostName("" + resultSet.getObject(4));
                postsSubject[rowIndex].setPostContent("" + resultSet.getObject(5));

                rowIndex++;
            }
            return new Gson().toJson(postsSubject);
            
        } catch(Exception e){
            return "" + e;
        }
    }
    
    public String getPost(String id){
        try{
            Statement stmnt=this.getConnection().createStatement();  
            ResultSet resultSet = stmnt.executeQuery((this.GET_ALL_POSTS_QUERY + this.GET_ONE_POST + id));  
            resultSet.next();
            PostsSubjectMessage ps;
            ps = new PostsSubjectMessage();
            ps.setPostId("" + resultSet.getObject(1));
            ps.setIdForum("" + resultSet.getObject(2));
            ps.setUserId("" + resultSet.getObject(3));
            ps.setPostName("" + resultSet.getObject(4));
            ps.setPostContent(""+resultSet.getObject(5));
            return new Gson().toJson(ps);
        } catch(Exception e){
            return "" + e;
        }
    }
    
    public String getPostsByForumId(String id){
        try{
            Statement statmentAll=this.getConnection().createStatement();
            Statement statmentRows=this.getConnection().createStatement();
            
            ResultSet resultSetPosts = statmentAll.executeQuery((GET_ALL_POSTS_QUERY + this.GET_POSTS_BY_FORUM_ID + id));
            ResultSet resultSetPostsRows = statmentRows.executeQuery((GET_ROW_NUMBER_POSTS + this.GET_POSTS_BY_FORUM_ID + id));
            
            resultSetPostsRows.next();
            int numberOfRows = resultSetPostsRows.getInt(1);
            PostsSubjectMessage postsSubject[];
            postsSubject = new PostsSubjectMessage[numberOfRows]; // TODO: as the num of the rows
            int rowIndex = 0;

            while(resultSetPosts.next()){
                postsSubject[rowIndex] = new PostsSubjectMessage();
                postsSubject[rowIndex].setPostId("" + resultSetPosts.getObject(1));
                postsSubject[rowIndex].setIdForum("" + resultSetPosts.getObject(2));
                postsSubject[rowIndex].setUserId("" + resultSetPosts.getObject(3));
                postsSubject[rowIndex].setPostName("" + resultSetPosts.getObject(4));
                rowIndex++;
            }
            return new Gson().toJson(postsSubject);
        } catch(Exception e){
            return "" + e;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public int getNumberOfRowsPosts(){
        try{
            Statement statmentRows=this.getConnection().createStatement();  
            ResultSet getRows = statmentRows.executeQuery(GET_ROW_NUMBER_POSTS);
            getRows.next();
            int numberOfRows = getRows.getInt(1);
            return numberOfRows;   
        } catch(Exception e){
            return 0;
        }
    }
    
    public String getFirstRowPostId(){
        try{
            Statement statmentAll=this.getConnection().createStatement();  
            ResultSet resultSet = statmentAll.executeQuery("select * FROM Posts LIMIT 0,1");
            resultSet.next();
            return "" + resultSet.getObject(1);
        } catch(Exception e){
            return "" + e;
        }

    }
    
    public void deleteFirstRowComments(){
        try{
            String pid = getFirstRowPostId();
            this.deletePost(pid);
        } catch(Exception e){
            return;
        }
    }
    
    
    
    
    
    
    
    
    
    
    public String postPost(String post_name, String id_forum, String user_id, String content){
        try{
            if(getNumberOfRowsPosts() >= MAX_ROW_DEL){
                deleteFirstRowComments();
            }
            Statement sttmLastId=this.getConnection().createStatement();
            ResultSet checkId = sttmLastId.executeQuery("SELECT MAX(post_id) from Posts");
            checkId.next();
            int new_id = checkId.getInt(1) + 1;
            PreparedStatement ps = null; 
            ResultSet rs = null;
            String sql ="INSERT INTO Posts(post_id, id_forum, user_id, post_name, content) VALUES ("
                    + new_id + ", \""+ id_forum + "\", \"" + user_id + "\", \"" + post_name + "\", \""+ content +"\")";
            ps = this.getConnection().prepareStatement(sql, new String[]{"example"});
            ps.execute();
            return this.getAllPosts();
        } catch(Exception e){
            return "";
        }
    }
    
    public String getPostsByUserId(String user_id){
        try{
            Statement statmentAll=this.getConnection().createStatement();  
            Statement statmentRows=this.getConnection().createStatement();  
            ResultSet resultSet = statmentAll.executeQuery(GET_ALL_POSTS_QUERY + GET_USER_ID + "\"" + user_id + "\"");
           
            ResultSet getRows = statmentRows.executeQuery(GET_ROW_NUMBER_POSTS + GET_USER_ID + "\"" + user_id + "\"");
            getRows.next();
            int numberOfRows = getRows.getInt(1);
            
            PostsSubjectMessage postsSubject[];
            postsSubject = new PostsSubjectMessage[numberOfRows]; // TODO: as the num of the rows
            int rowIndex = 0;

            while(resultSet.next()){
                postsSubject[rowIndex] = new PostsSubjectMessage();
                postsSubject[rowIndex].setPostId("" + resultSet.getObject(1));
                postsSubject[rowIndex].setIdForum("" + resultSet.getObject(2));
                postsSubject[rowIndex].setUserId("" + resultSet.getObject(3));
                postsSubject[rowIndex].setPostName("" + resultSet.getObject(4));
                postsSubject[rowIndex].setPostContent("" + resultSet.getObject(5));

                rowIndex++;
            }
            return new Gson().toJson(postsSubject);
            
        } catch(Exception e){
            return "" + e;
        }
    }
    
    
    
    
    public void deletePost(String post_id){
        try{ 
            cd.deleteCommentsByPost(post_id);
            Statement stmnttt=this.getConnection().createStatement(); //connect to database
            String deleteForum = this.DELETE_ONE_POST + "\"" + post_id + "\"";
            PreparedStatement ps = null;
            ps = this.getConnection().prepareStatement(deleteForum, new String[]{"example"});
            ps.execute();
        } catch(Exception e){
            
        }
    }
    
    
    /*
    """
        String DELETE_POST_NAME_WITH_PERMISSION = "DELETE FROM Posts WHERE post_name= ";
        String DELETE_POST_NAME_WITH_PERMISSION_FOR_USER = " AND user_id= ";
    """*/
//    public void deleteForum(String post_name, String user_name){
//        try{ 
//            Statement stmnttt=this.getConnection().createStatement(); //connect to database
//            String deletePostSpecificUser = this.DELETE_POST_NAME_WITH_PERMISSION + "\"" + post_name + "\"";
//            this.ud.get
//            deletePostSpecificUser += deletePostSpecificUser + 
//            PreparedStatement ps = null;
//            ps = this.getConnection().prepareStatement(deletePostSpecificUser, new String[]{"example"});
//            ps.execute();
//        } catch(Exception e){
//            
//        }
//    }
            
            
            
            
            
            
            
            
            
            
            
            
            
}
