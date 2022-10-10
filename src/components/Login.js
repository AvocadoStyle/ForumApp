import { useState, useRef, useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { URLSearchParamsInit } from 'react-router-dom';
import {UserContext} from './../context/UserContext'
const LOGIN_URL = '/login';
const Login = (props) => {

    const navigate = useNavigate()
    
    const userNameRef = useRef();
    const userPasswordRef = useRef();

    // const {user, setUser} = useContext(UserContext);
    // const {password, setPassword} = useContext(UserContext);
    const {val, us, pass, priv, uid} = useContext(UserContext)
    const [user, setUser] = us
    const [password, setPassword] = pass
    const [userPrivilegs, setUserPrivilegs] = priv
    const [userId, setUserId] = uid



    const handleSubmit = async (e) =>{
        e.preventDefault()


        const data = new FormData(e.target)
        let item = Object.fromEntries(data.entries())
        var myHeaders = new Headers();
        console.log("user: " + item.user_name + "password: " + item.password);
        myHeaders.append("Authorization", `Basic ${item.user_name}:${item.password}`);
        var requestOptions = {
            method: 'GET',
            headers: myHeaders,
            redirect: 'follow'
          };

        
        let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/users/login?user_name=${item.user_name}&password=${item.password}`
        fetch(url, requestOptions)
        .then(response => response.json())
        .then(data => {
            if(data != null){
                // props.setUser(data.user_name)
                // props.setPassword(data.password)
                setUser(data.user_name)
                setPassword(data.password)
                setUserPrivilegs(data.user_privilegs)
                setUserId(data.user_id)
                // setValue('so it have been changed')
                console.log("user is: " + user + " password is: " + password)
                console.log("good")
                // setting to the locat storage
                localStorage.setItem("user", data.user_name)
                localStorage.setItem("password", data.password)
                localStorage.setItem("user_privilegs", data.user_privilegs)
                localStorage.setItem("user_id", data.user_id)


                navigate('/dashboard');
            }
        })
        .catch(function(){
            navigate('/error')
        })
    }

    return(
        <div>
            <h1>Login Page</h1>
            <form onSubmit={handleSubmit}>
                <label>UserName</label>
                <input type="text" name={"user_name"} placeholder={"UserName"} ref={userNameRef}/>

                <label>Password</label>
                <input type="password" name={"password"} placeholder={"Password"} ref={userPasswordRef}/>
                
                <button>Submit</button>
            </form>
        </div>
    );
}




export default Login;