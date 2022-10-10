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
    const [forum, setForum] = useState([])
    const [post, setPost] = useState([]) // the owner of the post
    const [userPost, setUserPost] = useState([])


    const [comments, setComments] = useState([])


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

    useEffect(()=>{
        // let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/users`
        // fetch(url, header())
        // .then(response => response.json())
        // .then(data => setUsers(data))


        let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/posts/${postId}`
        fetch(url, header())
            .then(response => response.json())
            .then(data => {
                setPost(data)
                console.log(data)
                url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/users/${data.user_id}`
                return fetch(url, header())
            })
            .then(response => response.json())
            .then(data => setUserPost(data))




        // let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/users/${post.user_id}`
        // fetch(url, header())
        // .then(response => response.json())
        // .then(data => setUserPost(data))
        
        // url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/forums/${forumId}`
        // fetch(url, header())
        //     .then(response => response.json())
        //     .then(data => setForum(data))
    },[])

    const handleSubmit = (e) =>{
        e.preventDefault()
        const data = new FormData(e.target)
        let item = Object.fromEntries(data.entries())
        console.log(item.comment_content)
        console.log("by user: ", user)
        console.log("with password: ", password)
        console.log("and privilegs: ", userPrivilegs)
        console.log("and userid: ", userId)
        
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

                </div>
            </div>


            <div>
                <form onSubmit={handleSubmit}>
                    <label>
                        <b>make a comment:</b>
                    </label>
                        <br></br>
                        <textarea name={"comment_content"}  placeholder={"comment content"} rows={3} cols={30}/>
                        <br></br>
                        <button type='submit'>submit</button>
                </form>
            </div>

            {/* {console.log("users are:: ", users)} */}
            {/* {console.log("forum is:: ", forum)} */}
            {/* {console.log("post are:: ", post)} */}
        
        </div>
    );
};

export default SpecificPost;