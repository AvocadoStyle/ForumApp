import React, { useState, useEffect, useContext } from 'react';
import { Link, useParams } from 'react-router-dom'
import {UserContext} from './../../context/UserContext'
    



const SpecificPost = () => {


    const {val, us, pass, priv, uid, psts} = useContext(UserContext)
    const [user, setUser] = us
    const [password, setPassword] = pass
    const [userPrivilegs, setUserPrivilegs] = priv
    const [userId, setUserId] = uid
    // const [posts, setPosts] = psts 




    const { forumId, postId } = useParams();
    const [users, setUsers] = useState([])
    // const [forum, setForum] = useState([])
    const [post, setPost] = useState([]) // the owner of the post
    const [userPost, setUserPost] = useState([])


    const [comments, setComments] = useState([])


    let user_name = ''
    let user_id = 0
    let comment_id = 0

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
    const getFetch = () =>{
        let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/posts/${postId}`
        fetch(url, header())
            .then(response => response.json())
            .then(data => {
                setPost(data)
                // console.log(data)
                url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/users/${data.user_id}`
                return fetch(url, header())
            })
            .then(response => response.json())
            .then(data => setUserPost(data))
        url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/comments?post_id=${postId}`
        fetch(url, header())
        .then(response => response.json())
        .then(data => setComments(data))
        


        url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/users`
        fetch(url, header())
        .then(response => response.json())
        .then(data => setUsers(data))
    }
    useEffect(()=>{
        
        // url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/forums/${forumId}`
        // fetch(url, header())
        //     .then(response => response.json())
        //     .then(data => setForum(data))
        getFetch()
        const interval = setInterval(()=>{
            getFetch()
        }, 5000)
        return()=>clearInterval(interval)
    },[])

    const getUserName = (user_id) =>{
        let spec_user;
        let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/users/${user_id}`
        fetch(url, header())
        .then(response => response.json())
        .then(data => spec_user = data)
        return spec_user
    }

    const handleSubmit = (e) =>{
        e.preventDefault()
        const data = new FormData(e.target)
        let item = Object.fromEntries(data.entries())
        
        let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/comments?user_id=${userId}&post_id=${postId}&content=${item.comment_content}`
        fetch(url, {method: 'POST'})
        .then(response => response.json())
        .then(data => data)
    }

    const deleteButtonUser = (user_id, com_id)=>{
        if(userId == user_id || userId == 1){
            // comment_id = com_id;
            return <button onClick={e=>handleDelete(e, com_id)}>delete comment</button>
            // {<button>eden</button>}
        }

    }

    const handleDelete = (e, com_id) =>{
        e.preventDefault();
        let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/comments?comment_id=${com_id}`
        fetch(url, {method: 'DELETE'});
        // .then(response => response.json())
        // .then(data => data)
    }

    return (
        <div>
            <div className={"clsborder"}>
                <h5><b>Post Name:</b> {post.post_name}</h5>
                {/* {console.log(userPost)} */}
                <h5><b>User Name:</b> {userPost.user_name}</h5>
                <p><b>Post Content:</b> {post.post_content}</p>
            </div>

            <div>
                <p><b>comments:</b></p>
                <div className="clsborder">
                    {comments.map((comment)=>{
                        return(
                            <div key={comment.comment_id} user_id={comment.user_id} className="clsborder_forum">
                                {users.map((user) =>{
                                        if(user.user_id === comment.user_id){
                                            user_name = user.user_name                      
                                        }
                                
                                })}
                                <p><b>user:</b> <b>id: </b>{comment.user_id}</p>
                                <p><b>name: </b>{user_name}</p>
                                <p><b>comment id:</b>{comment.comment_id}</p>
                                <p><b>content:</b> {comment.content}</p>
                                
                                {deleteButtonUser(comment.user_id, comment.comment_id)}
                            </div>
                            )
                    })}
                </div>
            </div>


            <footer>
                <form onSubmit={handleSubmit}>
                    <label>
                        <b>make a comment:</b>
                    </label>
                        <br></br>
                        <textarea name={"comment_content"}  placeholder={"comment content"} rows={3} cols={30}/>
                        <br></br>
                        <button type='submit'>submit</button>
                </form>
            </footer>

            {/* {console.log("users are:: ", users)} */}
            {/* {console.log("forum is:: ", forum)} */}
            {/* {console.log("post are:: ", post)} */}
        
        </div>
    );
};

export default SpecificPost;