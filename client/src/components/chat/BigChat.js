import React, { useState, useEffect, useContext } from 'react';
import { Link, useParams } from 'react-router-dom'
import {UserContext} from './../../context/UserContext'
    



const BigChat = () => {


    const {val, us, pass, priv, uid, psts, bchat} = useContext(UserContext)
    const [user, setUser] = us
    const [password, setPassword] = pass
    const [userPrivilegs, setUserPrivilegs] = priv
    const [userId, setUserId] = uid

    const [chatMessages, setChatMessages] = bchat

    const [users, setUsers] = useState([])



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
        let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/users`
        fetch(url, header())
        .then(response => response.json())
        .then(data => setUsers(data))

        url = 'http://localhost:8080/ReserveSeatWebService2/webresources/reservation/messages'
        fetch(url, header())
        .then(response => response.json())
        .then(data => setChatMessages(data))
    }
    useEffect(()=>{
        getFetch()
        const interval = setInterval(()=>{
            getFetch()
        }, 5000)
        return()=>clearInterval(interval)
    },[])

    const handleSubmit = (e) =>{
        e.preventDefault()
        const data = new FormData(e.target)
        let item = Object.fromEntries(data.entries())
        
        // setChatMessages(chatMessages.concat(chat_message))
        let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/messages?user_id=${userId}&user_name=${user}&content=${item.content}`
        fetch(url, {method: 'POST'})
        .then(response => response.json())
        .then(data => data)
    }

    return (
        <div>
            <div className={"clsborder"}>
                {/* <h5><b>Post Name:</b> {post.post_name}</h5> */}
                {/* {console.log(userPost)} */}
                {/* <h5><b>User Name:</b> {userPost.user_name}</h5> */}
                {/* <p><b>Post Content:</b> {post.post_content}</p> */}
                <h1>Big Chat have fun!</h1>
            </div>

            <div>
                <p><b>messages:</b></p>
                <div className="clsborder">
                {console.log(chatMessages)}
                    {chatMessages.map((chatMessage)=>{
                        return(
                            <div className="clsborder_forum">
                                {users.map((user) =>{
                                        if(user.user_id === chatMessage.user_id){
                                            user_name = user.user_name                      
                                        }
                                })}
                                <p><b>user:</b> <b>id: </b>{chatMessage.user_id}</p>
                                <p><b>name: </b>{chatMessage.user_name}</p>
                                <p><b>message id:</b>{chatMessage.message_id}</p>
                                <p><b>content:</b> {chatMessage.content}</p>
                                
                                {/* {deleteButtonUser(comment.user_id, comment.comment_id)} */}
                            </div>
                            )
                    })}
                </div>
            </div>


            <div>
                <form onSubmit={handleSubmit}>
                    <label>
                        <b>send a message:</b>
                    </label>
                        <br></br>
                        <textarea name={"content"}  placeholder={"content"} rows={3} cols={30}/>
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

export default BigChat;