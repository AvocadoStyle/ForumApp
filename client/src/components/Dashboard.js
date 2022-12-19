import React from 'react';
import { useContext, useEffect, useState } from 'react';
import { UserContext } from './../context/UserContext'

const Dashboard = ( {user_n} ) => {

    const {val, us, pass, priv, uid} = useContext(UserContext)
    const [user, setUser] = us
    const [password, setPassword] = pass
    const [userPrivilegs, setUserPrivilegs] = priv
    const [userId, setUserId] = uid

    const [posts, setPost] = useState([]);
    const [comments, setComments] = useState([]);


    const header = () =>{
        var myHeaders = new Headers();
        let basicHeader = `Basic ${localStorage.getItem("user")}:${localStorage.getItem("password")}`;
        myHeaders.append("Authorization", basicHeader);
        var requestOptions = {
            method: 'GET',
            headers: myHeaders,
            redirect: 'follow'
          };
        return requestOptions 
    }
    const getPostsOfUser = () =>{
        let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/posts?user_id=${userId}`
        fetch(url, header())
            .then(response => response.json())
            .then(data => setPost(data))
    }
    const getCommentsOfUser = () =>{
        let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/comments?user_id=${userId}`
        fetch(url, header())
        .then(response => response.json())
        .then(data => setComments(data))
    }
    useEffect(()=>{
        getPostsOfUser()
        getCommentsOfUser()
    }, [])

    return(
        <div>
            <h1>Hello {user}!</h1>
            <h5>we'll show the user number of posts, and number of comments here</h5>
            <p><b>posts number: {posts.length}</b></p>
            <p><b>comments number: </b></p>

            <h3>posts and comments:</h3>
            <br></br>
            <div>
                <div>

                <h4>my posts: </h4>
                {posts.map((post)=>{
                    return(<div post_id={post.post_id} user_id={post.user_id} key={post.post_id}><b>post name:</b> {post.post_name}</div>)}
                    )}
                </div>
                <br></br>
                <div>
                
                <h4>my comments: </h4>
                {comments.map((comment)=>{
                    return(<div comment_id={comment.comment_id} user_id={comment.user_id} key={comment.comment_id}><b>comment content:</b> {comment.content}</div>) 
                })}
                </div>
            </div>

        </div>
    );
}

export default Dashboard;