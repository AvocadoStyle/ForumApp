import { useState, useRef, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
const Register = (props) => {

    const navigate = useNavigate()

    const userNameRef = useRef();
    const userPasswordRef = useRef();

    const handleSubmit = (e) =>{
        e.preventDefault()
        const data = new FormData(e.target)
        let item = Object.fromEntries(data.entries())
        console.log(item)
        console.log(item.user_name)
        props.setUser(item.user_name)
        props.setPassword(item.password)
        const url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/users/register?user_name=${item.user_name}&password=${item.password}`
        fetch(url, {method:'POST'})
        navigate('/dashboard');
    }

    return(
        <div>
            <h1>Register page</h1>
            <form onSubmit={handleSubmit}>
                <label>UserName</label>
                <input name={"user_name"} placeholder={"UserName"} ref={userNameRef}/>

                <label>Password</label>
                <input name={"password"} placeholder={"Password"} ref={userPasswordRef}/>
                
                <button>Submit</button>
            </form>
        </div>
    );
}


export default Register;