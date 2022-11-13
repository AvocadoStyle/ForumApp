package com.webservice.modules.messages.subject;

/**
 *
 * @author eden
 */
public class CommentsSubjectMessage {
    private String comment_id;
    private String user_id;
    private String post_id;
    private String content;

    
    
    public void setCommentId(String comment_id){
        this.comment_id = comment_id;
    }
    public void setUserId(String user_id){
        this.user_id = user_id;
    }
    public void setPostId(String post_id){
        this.post_id = post_id;
    }
    
    public void setContent(String content){
        this.content = content;
    }

    
    /*
    getters
    */
    public String getCommentId(){
        return this.comment_id;
    }
    public String getUserId(){
        return this.user_id;
    }
    public String getPostId(){
        return this.post_id;
    }
    
    public String getContent(){
        return this.content;
    }
    
    
}
