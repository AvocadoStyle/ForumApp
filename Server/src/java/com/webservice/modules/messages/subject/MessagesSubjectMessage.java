package com.webservice.modules.messages.subject;

/**
 *
 * @author eden
 */
public class MessagesSubjectMessage {
    private String message_id;
    private String user_id;
    private String user_name;
    private String content;

    
    
    public void setMessageId(String message_id){
        this.message_id = message_id;
    }
    public void setUserId(String user_id){
        this.user_id = user_id;
    }
    public void setUserName(String user_name){
        this.user_name = user_name;
    }
    
    public void setContent(String content){
        this.content = content;
    }

    
    /*
    getters
    */
    public String getMessageId(){
        return this.message_id;
    }
    public String getUserId(){
        return this.user_id;
    }
    public String getUserName(){
        return this.user_name;
    }
    
    public String getContent(){
        return this.content;
    }
    
    
}
