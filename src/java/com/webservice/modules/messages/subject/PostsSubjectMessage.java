package com.webservice.modules.messages.subject;

/**
 *
 * @author eden
 */
public class PostsSubjectMessage {
    private String post_id;
    private String id_forum;
    private String user_id;
    private String post_name;
    private String post_content;

    public void setPostId(String post_id){
        this.post_id = post_id;
    }
    
    public void setIdForum(String id_forum){
        this.id_forum = id_forum;
    }
    
    public void setUserId(String user_id){
        this.user_id = user_id;
    }
    
    public void setPostName(String post_name){
        this.post_name = post_name;
    }
    
    public void setPostContent(String post_content){
        this.post_content = post_content;
    }
    
    public String getPostId(){
        return this.post_id;
    }
    
    public String getIdForum(){
        return this.id_forum;
    }
    
    public String getUserId(){
        return this.user_id;
    }
    
    public String getPostName(){
        return this.post_name;
    }
    public String getPostContent(){
        return this.post_content;
    }
    
    
}
