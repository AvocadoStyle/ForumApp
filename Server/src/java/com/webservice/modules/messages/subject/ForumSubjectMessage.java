/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.webservice.modules.messages.subject;


/**
 *
 * @author eden
 */
public class ForumSubjectMessage {
    private String id_forum;
    private String forum_name;
    
    public ForumSubjectMessage(){    
    }
    
    public void setIdForum(String id_forum){
        this.id_forum = id_forum;
    }   
    public void setForumName(String forum_name){
        this.forum_name = forum_name;
    }
    
    public String getIdForum(){
        return this.id_forum;
    }
    
    public String getForumName(){
        return this.forum_name;
    }
}
