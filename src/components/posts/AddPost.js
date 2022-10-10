import React, { useState, useEffect, useContext } from 'react';
import { Link, useParams } from 'react-router-dom'
import {UserContext} from './../../context/UserContext'

const AddPost = (props) => {
    const { forumId } = useParams();

    const {val, us, pass, priv, uid, psts} = useContext(UserContext)
    const [user, setUser] = us
    // const [password, setPassword] = pass
    // const [userPrivilegs, setUserPrivilegs] = priv
    const [userId, setUserId] = uid
    const header = () =>{
        var myHeaders = new Headers();
        let basicHeader = `Basic ${localStorage.getItem("user")}:${localStorage.getItem("password")}`;
        myHeaders.append("Authorization", basicHeader);
        var requestOptions = {
            method: 'POST',
            headers: myHeaders,
            redirect: 'follow'
          };
        return requestOptions 
    }
    const handleSubmit = (e) =>{
        e.preventDefault()
        const data = new FormData(e.target)
        let item = Object.fromEntries(data.entries())
        console.log(item)
        // console.log(item.user_name)
        // props.setUser(item.user_name)
        // props.setPassword(item.password)
        const url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/posts?post_name=${item.post_name}&id_forum=${forumId}&user_id=${userId}&content=${item.post_content}`
        fetch(url, header())
        // navigate('/dashboard');
    }

    return(
        <div>
            <form onSubmit={handleSubmit}>
                <label>Post Name: </label>
                <br></br>
                <input name={"post_name"} placeholder={"post name"}/>
                <br></br>
                <label>Content: </label>
                <br></br>
                {/* <input name={"post_content"} placeholder={"post content"}/> */}
                <textarea rows={3} cols={30} name={"post_content"} placeholder={"post content"}/>
                <br></br>
                <button type='submit'>Submit</button>
            </form>
        </div>
    )

}

export default AddPost;