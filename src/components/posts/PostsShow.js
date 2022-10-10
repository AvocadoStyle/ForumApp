import React, { useState, useEffect, useContext } from 'react';
import { Link, useParams } from 'react-router-dom'
import {UserContext} from './../../context/UserContext'

const PostsShow = (props) => {
    const { forumId } = useParams();

    const {val, us, pass, priv, uid, psts} = useContext(UserContext)
    const [user, setUser] = us
    const [password, setPassword] = pass
    const [userPrivilegs, setUserPrivilegs] = priv
    const [userId, setUserId] = uid
    // const [posts, setPosts] = psts 

    let user_name = '';
    const [users, setUsers] = useState([])
    const [forum, setForum] = useState([])
    const [posts, setPosts] = useState([])
    const [specific_user, setSpecificUser] = useState('')
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

    const getFetch = () => {
        let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/users`
        fetch(url, header())
        .then(response => response.json())
        .then(data => setUsers(data))
        url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/forums/${forumId}`
        fetch(url, header())
        .then(response => response.json())
        .then(data => setForum(data))
        url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/posts?id_forum=${forumId}`
        fetch(url, header())
            .then(response => response.json())
            .then(data => setPosts(data))
    }

    useEffect(()=>{
        getFetch()
        const interval = setInterval(()=>{
            getFetch()
        }, 10000)
        return()=>clearInterval(interval)
    }, [])

    return (

        <div>
            <div>
                <h1><b>Forum Name:</b> {forum.forum_name}</h1>
                <h2><b>Posts Are:</b> </h2>
            </div>
                {posts.map((post) => {
                    return(
                        <div key={post.post_name} className="clsborder_forum" /*className="col-lg-6 border"*/>
                            <h2>
                                Post Name: {post.post_name}
                            </h2>
                                {users.map((userr) =>{
                                    if(userr.user_id == post.user_id){
                                        user_name = userr.user_name
                                        // {console.log(userr.user_name)}
                                        // setSpecificUser(userr.user_name)
                                        // this.setState({specific_user: user.user_name})
                                        // this.props.setSpecificUser(user.user_name)
                                    }
                                })}
                            <h4>User Name: {user_name} </h4>
                            <Link to={`/forums/${forumId}/posts/${post.post_id}`}><button /*className="col-sm-4"*/>get in post</button></Link>
                        </div>
                    )
                    console.log(post)
                })}
        </div>
    )
}


export default PostsShow;