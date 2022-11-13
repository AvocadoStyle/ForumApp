package com.webservice.modules.data;
import com.webservice.modules.data.CommentsData;
import com.google.gson.Gson;
import com.webservice.modules.messages.subject.CommentsSubjectMessage;
import com.webservice.modules.data.UsersData;
import com.webservice.modules.data.PostsData;
import java.sql.*;


/**
 *
 * @author eden
 */
public class CommentsData extends DataConsumer {
//    private final String a = 1;
    private final String GET_ALL_COMMENTS_QUERY = "SELECT * FROM Comments";
    private final String GET_ROW_NUMBER_COMMENTS = "SELECT count(*) FROM Comments";
    private final String GET_SPECIFIC_COMMENT = " WHERE comment_id=";
    private final String GET_BY_USER_ID = " WHERE user_id=";
    private final String GET_BY_POST_ID = " WHERE post_id=";
    private final String DELETE_ONE_COMMENT = "DELETE FROM Comments WHERE comment_id= ";
    private final String DELETE_POST_COMMENTS = "DELETE FROM Comments WHERE post_id= ";
    private final String GET_FIRST_ROW_COMMENT_ID = "select comment_id from Comments LIMIT 1";
    
    
    
    private UsersData ud;
    private PostsData pd; 


    
    
    
    public CommentsData(Connection connect){
        super(connect);
    }

    public String getAllComments(){
        try{
            Statement statmentAll=this.getConnection().createStatement();  
            Statement statmentRows=this.getConnection().createStatement();  
            ResultSet resultSet = statmentAll.executeQuery(GET_ALL_COMMENTS_QUERY);
           
            ResultSet getRows = statmentRows.executeQuery(GET_ROW_NUMBER_COMMENTS);
            getRows.next();
            int numberOfRows = getRows.getInt(1);
            
            CommentsSubjectMessage commentSubject[];
            commentSubject = new CommentsSubjectMessage[numberOfRows]; // TODO: as the num of the rows
            int rowIndex = 0;

            while(resultSet.next()){
                commentSubject[rowIndex] = new CommentsSubjectMessage();
                commentSubject[rowIndex].setCommentId("" + resultSet.getObject(1));
                commentSubject[rowIndex].setUserId("" + resultSet.getObject(2));
                commentSubject[rowIndex].setPostId("" + resultSet.getObject(3));
                commentSubject[rowIndex].setContent("" + resultSet.getObject(4));

                rowIndex++;
            }
            return new Gson().toJson(commentSubject);
            
        } catch(Exception e){
            return "" + e;
        }

    }
    
    public String getSpecificComment(String comment_id){
        try{
            Statement statmentAll=this.getConnection().createStatement();  
            Statement statmentRows=this.getConnection().createStatement();  
            ResultSet resultSet = statmentAll.executeQuery(GET_ALL_COMMENTS_QUERY + GET_SPECIFIC_COMMENT + "\"" + comment_id + "\"");
           
            ResultSet getRows = statmentRows.executeQuery(GET_ROW_NUMBER_COMMENTS + GET_SPECIFIC_COMMENT + "\"" + comment_id + "\"");
            getRows.next();
            int numberOfRows = getRows.getInt(1);
            
            CommentsSubjectMessage commentSubject[];
            commentSubject = new CommentsSubjectMessage[numberOfRows]; // TODO: as the num of the rows
            int rowIndex = 0;

            while(resultSet.next()){
                commentSubject[rowIndex] = new CommentsSubjectMessage();
                commentSubject[rowIndex].setCommentId("" + resultSet.getObject(1));
                commentSubject[rowIndex].setUserId("" + resultSet.getObject(2));
                commentSubject[rowIndex].setPostId("" + resultSet.getObject(3));
                commentSubject[rowIndex].setContent("" + resultSet.getObject(4));

                rowIndex++;
            }
            return new Gson().toJson(commentSubject);
            
        } catch(Exception e){
            return "" + e;
        }

    }
    
    
    
    public String getCommentsByUserId(String user_id){
        try{
            Statement statmentAll=this.getConnection().createStatement();  
            Statement statmentRows=this.getConnection().createStatement();  
            ResultSet resultSet = statmentAll.executeQuery(GET_ALL_COMMENTS_QUERY + this.GET_BY_USER_ID + "\"" + user_id + "\"");
           
            ResultSet getRows = statmentRows.executeQuery(GET_ROW_NUMBER_COMMENTS + this.GET_BY_USER_ID + "\"" + user_id + "\"");
            getRows.next();
            int numberOfRows = getRows.getInt(1);
            
            CommentsSubjectMessage commentSubject[];
            commentSubject = new CommentsSubjectMessage[numberOfRows]; // TODO: as the num of the rows
            int rowIndex = 0;

            while(resultSet.next()){
                commentSubject[rowIndex] = new CommentsSubjectMessage();
                commentSubject[rowIndex].setCommentId("" + resultSet.getObject(1));
                commentSubject[rowIndex].setUserId("" + resultSet.getObject(2));
                commentSubject[rowIndex].setPostId("" + resultSet.getObject(3));
                commentSubject[rowIndex].setContent("" + resultSet.getObject(4));

                rowIndex++;
            }
            return new Gson().toJson(commentSubject);
            
        } catch(Exception e){
            return "" + e;
        }

    }
    
    public String getCommentsByPostId(String post_id){
        try{
            Statement statmentAll=this.getConnection().createStatement();  
            Statement statmentRows=this.getConnection().createStatement();  
            ResultSet resultSet = statmentAll.executeQuery(GET_ALL_COMMENTS_QUERY + this.GET_BY_POST_ID + "\"" + post_id + "\"");
           
            ResultSet getRows = statmentRows.executeQuery(GET_ROW_NUMBER_COMMENTS + this.GET_BY_POST_ID + "\"" + post_id + "\"");
            getRows.next();
            int numberOfRows = getRows.getInt(1);
            
            CommentsSubjectMessage commentSubject[];
            commentSubject = new CommentsSubjectMessage[numberOfRows]; // TODO: as the num of the rows
            int rowIndex = 0;

            while(resultSet.next()){
                commentSubject[rowIndex] = new CommentsSubjectMessage();
                commentSubject[rowIndex].setCommentId("" + resultSet.getObject(1));
                commentSubject[rowIndex].setUserId("" + resultSet.getObject(2));
                commentSubject[rowIndex].setPostId("" + resultSet.getObject(3));
                commentSubject[rowIndex].setContent("" + resultSet.getObject(4));

                rowIndex++;
            }
            return new Gson().toJson(commentSubject);
            
        } catch(Exception e){
            return "" + e;
        }

    }
    
    /*
                Statement statmentRows=this.getConnection().createStatement();  
            ResultSet getRows = statmentRows.executeQuery(GET_ROW_NUMBER_COMMENTS);
            getRows.next();
            int numberOfRows = getRows.getInt(1);
    */
    public int getNumberOfRowsComments(){
        try{
            Statement statmentRows=this.getConnection().createStatement();  
            ResultSet getRows = statmentRows.executeQuery(GET_ROW_NUMBER_COMMENTS);
            getRows.next();
            int numberOfRows = getRows.getInt(1);
            return numberOfRows;   
        } catch(Exception e){
            return 0;
        }
    }
    
    public String getFirstRowCommentId(){
        try{
            Statement statmentAll=this.getConnection().createStatement();  
            ResultSet resultSet = statmentAll.executeQuery("select * FROM Comments LIMIT 0,1");
            resultSet.next();
            return "" + resultSet.getObject(1);
        } catch(Exception e){
            return "" + e;
        }

    }
    
    public void deleteFirstRowComments(){
        try{
            String cid = getFirstRowCommentId();
            this.deleteComment(cid);
        } catch(Exception e){
            return;
        }
    }
    
    
    public String postComment(String user_id, String post_id, String content){
        try{
            if(getNumberOfRowsComments() >= MAX_ROW_DEL){
                deleteFirstRowComments();
            }
            Statement sttmLastId=this.getConnection().createStatement();
            ResultSet checkId = sttmLastId.executeQuery("SELECT MAX(comment_id) from Comments");
            checkId.next();
            int new_id = checkId.getInt(1) + 1;
            System.out.println("new id is: " + new_id);
            PreparedStatement ps = null; 
            ResultSet rs = null;
            String sql ="INSERT INTO Comments(comment_id, user_id, post_id, content) VALUES ("
                    + new_id + ", \""+ user_id + "\", \"" + post_id + "\", \"" + content + "\")";
            System.out.println(sql);
            ps = this.getConnection().prepareStatement(sql, new String[]{"example"});
            ps.execute();
            return this.getAllComments();
        } catch(Exception e){
            return "";
        }
    }
    
    public void deleteComment(String comment_id){
        try{ 
            Statement stmnttt=this.getConnection().createStatement(); //connect to database
            String deleteForum = this.DELETE_ONE_COMMENT + "\"" + comment_id + "\"";
            PreparedStatement ps = null;
            ps = this.getConnection().prepareStatement(deleteForum, new String[]{"example"});
            ps.execute();
        } catch(Exception e){
            
        }
    }
    
    public void deleteCommentsByPost(String pid){
        try{ 
            Statement stmnttt=this.getConnection().createStatement(); //connect to database
            String deleteForum = this.DELETE_POST_COMMENTS + "\"" + pid + "\"";
            PreparedStatement ps = null;
            ps = this.getConnection().prepareStatement(deleteForum, new String[]{"example"});
            ps.execute();
        } catch(Exception e){
            
        }
    }    
    
}
