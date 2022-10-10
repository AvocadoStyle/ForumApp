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
    
    
}
