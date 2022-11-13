package com.webservice.modules.data;
import com.webservice.modules.data.MessagesData;
import com.google.gson.Gson;
import com.webservice.modules.messages.subject.MessagesSubjectMessage;
import com.webservice.modules.data.UsersData;
import com.webservice.modules.data.PostsData;
import java.sql.*;


/**
 *
 * @author eden
 */
public class MessagesData extends DataConsumer {
//    private final String a = 1;
    private final String GET_ALL_MESSAGES_QUERY = "SELECT * FROM Messages";
    private final String GET_ROW_NUMBER_MESSAGES = "SELECT count(*) FROM Messages";
    private final String GET_SPECIFIC_MESSAGE = " WHERE message_id=";
    private final String GET_BY_USER_ID = " WHERE user_id=";
    private final String DELETE_ONE_MESSAGE = "DELETE FROM Messages WHERE message_id= ";
    private final String GET_FIRST_ROW_MESSAGE_ID = "select message_id from Messages LIMIT 1";
    
    
    
    private UsersData ud;
    private PostsData pd; 


    
    
    
    public MessagesData(Connection connect){
        super(connect);
    }

    public String getAllMessages(){
        try{
            Statement statmentAll=this.getConnection().createStatement();  
            Statement statmentRows=this.getConnection().createStatement();  
            ResultSet resultSet = statmentAll.executeQuery(GET_ALL_MESSAGES_QUERY);
           
            ResultSet getRows = statmentRows.executeQuery(GET_ROW_NUMBER_MESSAGES);
            getRows.next();
            int numberOfRows = getRows.getInt(1);
            
            MessagesSubjectMessage messageSubject[];
            messageSubject = new MessagesSubjectMessage[numberOfRows]; // TODO: as the num of the rows
            int rowIndex = 0;

            while(resultSet.next()){
                messageSubject[rowIndex] = new MessagesSubjectMessage();
                messageSubject[rowIndex].setMessageId("" + resultSet.getObject(1));
                messageSubject[rowIndex].setUserId("" + resultSet.getObject(2));
                messageSubject[rowIndex].setUserName("" + resultSet.getObject(3));
                messageSubject[rowIndex].setContent("" + resultSet.getObject(4));

                rowIndex++;
            }
            return new Gson().toJson(messageSubject);
            
        } catch(Exception e){
            return "" + e;
        }

    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public int getNumberOfRowsMessages(){
        try{
            Statement statmentRows=this.getConnection().createStatement();  
            ResultSet getRows = statmentRows.executeQuery(GET_ROW_NUMBER_MESSAGES);
            getRows.next();
            int numberOfRows = getRows.getInt(1);
            return numberOfRows;   
        } catch(Exception e){
            return 0;
        }
    }
    
    public String getFirstRowMessageId(){
        try{
            Statement statmentAll=this.getConnection().createStatement();  
            ResultSet resultSet = statmentAll.executeQuery("select * FROM Messages LIMIT 0,1");
            resultSet.next();
            return "" + resultSet.getObject(1);
        } catch(Exception e){
            return "" + e;
        }

    }
    
    public void deleteFirstRowMessages(){
        try{
            String mid = getFirstRowMessageId();
            this.deleteMessage(mid);
        } catch(Exception e){
            return;
        }
    }
    
    
    
    
    
    
    
    
    
    
    public String postMessage(String user_id, String user_name, String content){
        try{
            if(getNumberOfRowsMessages() >= MAX_ROW_DEL){
                deleteFirstRowMessages();
            }
            Statement sttmLastId=this.getConnection().createStatement();
            ResultSet checkId = sttmLastId.executeQuery("SELECT MAX(message_id) from Messages");
            checkId.next();
            int new_id = checkId.getInt(1) + 1;
            System.out.println("new id is: " + new_id);
            PreparedStatement ps = null; 
            ResultSet rs = null;
            String sql ="INSERT INTO Messages(message_id, user_id, user_name, content) VALUES ("
                    + new_id + ", \""+ user_id + "\", \"" + user_name + "\", \"" + content + "\")";
            System.out.println(sql);
            ps = this.getConnection().prepareStatement(sql, new String[]{"example"});
            ps.execute();
            return this.getAllMessages();
        } catch(Exception e){
            return "";
        }
    }
    
    
    
    
    
    
    
    
    public void deleteMessage(String message_id){
        try{ 
            Statement stmnttt=this.getConnection().createStatement(); //connect to database
            String deleteForum = this.DELETE_ONE_MESSAGE + "\"" + message_id + "\"";
            PreparedStatement ps = null;
            ps = this.getConnection().prepareStatement(deleteForum, new String[]{"example"});
            ps.execute();
        } catch(Exception e){
            
        }
    }
    
    
    
//    
//    public void deleteComment(String comment_id){
//        try{ 
//            Statement stmnttt=this.getConnection().createStatement(); //connect to database
//            String deleteForum = this.DELETE_ONE_COMMENT + "\"" + comment_id + "\"";
//            PreparedStatement ps = null;
//            ps = this.getConnection().prepareStatement(deleteForum, new String[]{"example"});
//            ps.execute();
//        } catch(Exception e){
//            
//        }
//    }
    
    
}
