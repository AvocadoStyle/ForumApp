/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.webservice.modules.messages.subject;

/**
 *
 * @author eden
 */
public class UsersSubjectMessage {
    private String user_id;
    private String user_name;
    private String user_privilegs;
    private String password;
    
    public UsersSubjectMessage(){    
    }
    
    public void setUserId(String user_id){
        this.user_id = user_id;
    }   
    public void setUserName(String user_name){
        this.user_name = user_name;
    }
    
    public String setUserPrivilegs(String user_privilegs){
        return this.user_privilegs = user_privilegs;
    }
    
    public String setPassword(String password){
        return this.password = password;
    }
    
    public String getUserId(){
        return this.user_id;
    }
    
    public String getUserName(){
        return this.user_name;
    }
    
    public String getUserPrivilegs(){
        return user_privilegs;
    }
    
    public String getPassword(){
        return password;
    }
}
