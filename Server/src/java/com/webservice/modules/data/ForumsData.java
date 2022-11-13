package com.webservice.modules.data;
import com.webservice.modules.data.ForumsData;
import com.google.gson.Gson;
import com.webservice.modules.messages.subject.ForumSubjectMessage;
import java.sql.*;

/**
 *
 * @author eden
 */
public class ForumsData extends DataConsumer {
    private final String GET_ALL_FORUMS = "SELECT * from Forums";
    private final String GET_ROW_NUMBER_FORUMS = "select count(*) from Forums";
    private final String GET_ONE_FORUM = "SELECT * FROM Forums WHERE id_forum = ";
    private final String GET_FORUMS_BY_STARTING_NAME = "SELECT * FROM Forums WHERE forum_name like ";
    private final String GET_ROW_NUMBER_FORUMS_BY_STRING_NAME = "SELECT count(*) FROM Forums WHERE forum_name like ";
    private final String DELETE_ONE_FORUM = "DELETE FROM Forums WHERE forum_name= ";
    public ForumsData(Connection connect){
        super(connect);
    }
    
    public String getAllForums(){
        try{
            Statement statmentAll=this.getConnection().createStatement();  
            Statement statmentRows=this.getConnection().createStatement();  
            ResultSet resultSet = statmentAll.executeQuery(GET_ALL_FORUMS);
           
            ResultSet getRows = statmentRows.executeQuery(GET_ROW_NUMBER_FORUMS);
            getRows.next();
            int numberOfRows = getRows.getInt(1);
            
            
            
            
            
            ForumSubjectMessage forumSubjects[];
            forumSubjects = new ForumSubjectMessage[numberOfRows]; // TODO: as the num of the rows
            int rowIndex = 0;

            while(resultSet.next()){
                forumSubjects[rowIndex] = new ForumSubjectMessage();
                forumSubjects[rowIndex].setIdForum("" + resultSet.getObject(1));
                forumSubjects[rowIndex].setForumName("" + resultSet.getObject(2));
                rowIndex++;
            }
            return new Gson().toJson(forumSubjects);
            
        } catch(Exception e){
            return "" + e;
        }
    }
    
    public String getForum(String id){
        try{
            Statement stmnt=this.getConnection().createStatement();  
            ResultSet resultSet = stmnt.executeQuery((this.GET_ONE_FORUM + id));
            resultSet.next();
            ForumSubjectMessage fs;
            fs = new ForumSubjectMessage();
            fs.setIdForum("" + resultSet.getObject(1));
            fs.setForumName("" + resultSet.getObject(2));
            return new Gson().toJson(fs);
        } catch(Exception e){
            return "" + e;
        }
    }
    
    public String getStartingNameForums(String forum_name){
        try{
            Statement stmnt=this.getConnection().createStatement();  
            Statement stmntRows=this.getConnection().createStatement();  
            String forumNameSearch = "\"" + forum_name + "%\"";
//            String forumNameSearch = forum_name + "%";
            ResultSet resultSet = stmnt.executeQuery((this.GET_FORUMS_BY_STARTING_NAME + forumNameSearch));
            ResultSet getRows = stmntRows.executeQuery((this.GET_ROW_NUMBER_FORUMS_BY_STRING_NAME + forumNameSearch)); 

            getRows.next();
            int numberOfRows = getRows.getInt(1);
             
            ForumSubjectMessage forumSubjects[];
            forumSubjects = new ForumSubjectMessage[numberOfRows];
            int rowIndex = 0;
            while(resultSet.next()){
                forumSubjects[rowIndex] = new ForumSubjectMessage();
                forumSubjects[rowIndex].setIdForum("" + resultSet.getObject(1));
                forumSubjects[rowIndex].setForumName("" + resultSet.getObject(2));
                rowIndex++;
            }
//            return this.GET_FORUMS_BY_STARTING_NAME + forumNameSearch;
            return new Gson().toJson(forumSubjects);
        } catch(Exception e){
            return "" + e;
        }
    }
    
    public void postForum(String forum_name){
        try{
            Statement sttmLastId=this.getConnection().createStatement();
//            String forum_name_pure = new Gson().fromJson(forum_name, ForumSubjectMessage.class).getForumName();
            ResultSet checkId = sttmLastId.executeQuery("SELECT MAX(id_forum) from Forums");
            checkId.next();
            int new_id = checkId.getInt(1) + 1;
            PreparedStatement ps = null; 
            ResultSet rs = null;
            String sql ="INSERT INTO Forums(id_forum, forum_name) VALUES ("+ new_id + ", \""+ forum_name + "\")";
            ps = this.getConnection().prepareStatement(sql, new String[]{"example"});
            ps.execute();
        } catch(Exception e){
            
        }
    }
    
    public void deleteForum(String forum_name){
        try{ 
            Statement stmnttt=this.getConnection().createStatement(); //connect to database
            String deleteForum = this.DELETE_ONE_FORUM + "\"" + forum_name + "\"";
            PreparedStatement ps = null;
            ps = this.getConnection().prepareStatement(deleteForum, new String[]{"example"});
            ps.execute();
        } catch(Exception e){
            
        }
    }
    
}
