package com.webservice.modules.data;
import com.webservice.modules.data.UsersData;
import com.google.gson.Gson;
import com.webservice.modules.messages.subject.UsersSubjectMessage;
import java.sql.*;
import com.webservice.modules.encryption.decrypt;
import com.webservice.modules.encryption.encrypt;



/**
 *
 * @author eden
 */
public class UsersData extends DataConsumer {
    private final String GET_ALL_USERS = "SELECT * from Users";
    private final String GET_ROW_NUMBER_USERS = "select count(*) from Users";
    private final String GET_LOGIN_USER = "select * from Users where user_name=";
    private final String GET_LOGIN_PASSWORD = " AND password=";
    private final String GET_USERID_FROM_USERNAME = "select user_id from Users where user_name= ";
    private final String GET_ONE_USER = "select * from Users where user_id=";
    
    public UsersData(Connection connect){
        super(connect);
    }
    
    public String getAllUsers(){
                try{
            Statement statmentAll=this.getConnection().createStatement();  
            Statement statmentRows=this.getConnection().createStatement();  
            ResultSet resultSet = statmentAll.executeQuery(GET_ALL_USERS);
           
            ResultSet getRows = statmentRows.executeQuery(GET_ROW_NUMBER_USERS);
            getRows.next();
            int numberOfRows = getRows.getInt(1);
            UsersSubjectMessage usersSubjects[];
            usersSubjects = new UsersSubjectMessage[numberOfRows]; // TODO: as the num of the rows
            int rowIndex = 0;

            while(resultSet.next()){
                usersSubjects[rowIndex] = new UsersSubjectMessage();
                usersSubjects[rowIndex].setUserId("" + resultSet.getObject(1));
                usersSubjects[rowIndex].setUserName("" + resultSet.getObject(2));
                usersSubjects[rowIndex].setUserPrivilegs("" + resultSet.getObject(3));
                usersSubjects[rowIndex].setPassword("" + resultSet.getObject(4));
                rowIndex++;
            }
            return new Gson().toJson(usersSubjects);
            
        } catch(Exception e){
            return "" + e;
        }
    }
    
    
    /**
     * login JWT
     * @param user_name
     * @param password
     * @return 
     */
    public String Login(String user_name, String password){
        try{
            password = encrypt.encrypt(password);
            System.out.println(password);
            String query = GET_LOGIN_USER + "\"" +  user_name + "\"" + GET_LOGIN_PASSWORD + "\"" +password + "\"";
            Statement statmentAll=this.getConnection().createStatement();  
            ResultSet resultSet = statmentAll.executeQuery(query);
        
            UsersSubjectMessage usm;
            usm = new UsersSubjectMessage();
        
            while(resultSet.next()){
                usm.setUserId("" + resultSet.getObject(1));
                usm.setUserName("" + resultSet.getObject(2));
                usm.setUserPrivilegs("" + resultSet.getObject(3));
                usm.setPassword("" + resultSet.getObject(4));
            }
            
            if((usm.getUserName().equals(user_name)) && (usm.getPassword().equals(password)))
            {
                return new Gson().toJson(usm);   
            }else{
                return "not good!";
            }
//            }
//            else return "{" + "not Valid username or password" + ":" + "exit" + "}";
        } catch(Exception e){
            return "" + e;
        }
    }
    /**
     * register JWT
     * @param user_name
     * @param password
     * @return 
     */
    public Boolean Register(String user_name, String password){
        try{
            password = encrypt.encrypt(password);
            System.out.println(password);
            Statement sttmLastId=this.getConnection().createStatement();
            ResultSet checkId = sttmLastId.executeQuery("SELECT MAX(user_id) from Users");
            checkId.next();
            int new_id = checkId.getInt(1) + 1;
            PreparedStatement ps = null; 
            ResultSet rs = null;
            String sql ="INSERT INTO Users(user_id, user_name, user_privilegs, password) VALUES ("+ new_id + ", \""+ user_name + "\", " + 0 +", \"" + password + "\")";
            ps = this.getConnection().prepareStatement(sql, new String[]{"example"});
            ps.execute();
            return true;
        } catch(Exception e){
            return false;
        }
    }
    
    
    
    
    /**
     * LOGIN with BASIC AUTH 64BIT
     * @param user_name
     * @param password
     * @return 
     */
    public String Login64(String user_name, String password){
        try{
            System.out.println(password);
            String query = GET_LOGIN_USER + "\"" +  user_name + "\"" + GET_LOGIN_PASSWORD + "\"" +password + "\"";
            Statement statmentAll=this.getConnection().createStatement();  
            ResultSet resultSet = statmentAll.executeQuery(query);
            UsersSubjectMessage usm;
            usm = new UsersSubjectMessage();
        
            while(resultSet.next()){
                usm.setUserId("" + resultSet.getObject(1));
                usm.setUserName("" + resultSet.getObject(2));
                usm.setUserPrivilegs("" + resultSet.getObject(3));
                usm.setPassword("" + resultSet.getObject(4));
            }
            
            if((usm.getUserName().equals(user_name)) && (usm.getPassword().equals(password)))
            {
                return new Gson().toJson(usm);   
            }else{
                return "not good!";
            }
//            }
//            else return "{" + "not Valid username or password" + ":" + "exit" + "}";
        } catch(Exception e){
            return "" + e;
        }
    }
    public Boolean isUserAndPassExists(String user_name, String password){
        try{
            System.out.println("debug in user=" + user_name + " password=" + password);
            String query = GET_LOGIN_USER + "\"" +  user_name + "\"" + GET_LOGIN_PASSWORD + "\"" +password + "\";";
            Statement statmentAll=this.getConnection().createStatement();  
            System.out.println("debug in 15");
            ResultSet resultSet = statmentAll.executeQuery(query);
            UsersSubjectMessage usm;
            usm = new UsersSubjectMessage();
             System.out.println("debug in 16");
            while(resultSet.next()){
                usm.setUserId("" + resultSet.getObject(1));
                usm.setUserName("" + resultSet.getObject(2));
                usm.setUserPrivilegs("" + resultSet.getObject(3));
                usm.setPassword("" + resultSet.getObject(4));
            }
             System.out.println("debug in 17");
             System.out.println("debug in 18: is it equals? " + usm.getUserName());
            return ((usm.getUserName().equals(user_name)) && (usm.getPassword().equals(password)));
        } catch(Exception e) { 
            System.out.println("exception is: " + e);
            return false;
        }
    }
    
    public Boolean isUserAndPassExistsAdminPermissions(String user_name, String password){
        try{
            System.out.println("debug in user=" + user_name + " password=" + password);
            String query = GET_LOGIN_USER + "\"" +  user_name + "\"" + GET_LOGIN_PASSWORD + "\"" +password + "\";";
            Statement statmentAll=this.getConnection().createStatement();  
            System.out.println("debug in 15");
            ResultSet resultSet = statmentAll.executeQuery(query);
            UsersSubjectMessage usm;
            usm = new UsersSubjectMessage();
             System.out.println("debug in 16");
            while(resultSet.next()){
                usm.setUserId("" + resultSet.getObject(1));
                usm.setUserName("" + resultSet.getObject(2));
                usm.setUserPrivilegs("" + resultSet.getObject(3));
                usm.setPassword("" + resultSet.getObject(4));
            }
             System.out.println("debug in 17");
             System.out.println("debug in 18: is it equals? " + usm.getUserName());
             //for admin permissions privilegs 1
             System.out.println("authroize: " + usm.getUserPrivilegs());
            return ((usm.getUserName().equals(user_name)) && (usm.getPassword().equals(password))
                    && usm.getUserPrivilegs().equals("true"));
        } catch(Exception e) { 
            System.out.println("exception is: " + e);
            return false;
        }
    }
    
    /**
     * REGISTER with BASIC AUTH 64BIT
     * @param user_name
     * @param password
     * @return 
     */
    public Boolean Register64(String user_name, String password){
        try{
            Statement sttmLastId=this.getConnection().createStatement();
            ResultSet checkId = sttmLastId.executeQuery("SELECT MAX(user_id) from Users");
            checkId.next();
            int new_id = checkId.getInt(1) + 1;
            PreparedStatement ps = null; 
            ResultSet rs = null;
            String sql ="INSERT INTO Users(user_id, user_name, user_privilegs, password) VALUES ("+ new_id + ", \""+ user_name + "\", " + 0 +", \"" + password + "\")";
            ps = this.getConnection().prepareStatement(sql, new String[]{"example"});
            ps.execute();
            return true;
        } catch(Exception e){
            return false;
        }
    }
//    
//    public String getUserIdFromUserName(String user_name){
//        return ""
//    }
    
    public String getUser(String user_id){
        try{
            Statement stmnt=this.getConnection().createStatement();  
            ResultSet resultSet = stmnt.executeQuery((this.GET_ONE_USER + user_id));
            resultSet.next();
            UsersSubjectMessage um;
            um = new UsersSubjectMessage();
            um.setUserId("" + resultSet.getObject(1));
            um.setUserName("" + resultSet.getObject(2));
            um.setUserPrivilegs("" + resultSet.getObject(3));
            um.setPassword("" + resultSet.getObject(4));
            return new Gson().toJson(um);
        } catch(Exception e){
            return "" + e;
        }
    }
}
