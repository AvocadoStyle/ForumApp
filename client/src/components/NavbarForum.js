// import React from 'react';
import React, { useEffect, useState } from "react";
import Error from './Error'
import {
    BrowserRouter as Router,
    Routes,
    Route,
    /*Link,*/
    NavLink
  } from "react-router-dom";

import SharedLayout from './SharedLayout'
import Form from './forums/Form';
import Home from './Home';
import About from './About';
import Login from './Login';
import AllForums from './forums/AllForums';
import ForumLook from './forums/ForumLook';
import Dashboard from "./Dashboard";
import ProtectedRoute from "./ProtectedRoute";
import Register from "./Register";
import SpecificPost from "./posts/SpecificPost"
import { UserContext }from "./../context/UserContext"

import BigChat from './chat/BigChat'


  function NavbarForum(props) {


    const [value, setValue] = useState('');
    const [time, setTime] = useState('');
    const [user, setUser] = useState('need to login')
    const [password, setPassword] = useState('')
    const [userPrivilegs, setUserPrivilegs] = useState('')
    const [userId, setUserId] = useState('')
    const [posts, setPosts] = useState([])
    const [chatMessages, setChatMessages] = useState([])


    // useState(() => {
    //     setInterval(()=>{
    //         let d = new Date();
    //         setValue(`${d.getHours()}:${d.getMinutes()}:${d.getSeconds()}`)
    //         }
    //     , 1000)
    // }, [])

    // const handletime = () =>{
    //         setInterval(()=>{
    //         let d = new Date();
    //         setTime(`${d.getHours()}:${d.getMinutes()}:${d.getSeconds()}`)
    //         }
    //     , 1000)
    // }

    useState(()=>{
        setUser(localStorage.getItem("user"))
        setPassword(localStorage.getItem("password"))
        setUserPrivilegs(localStorage.getItem("user_privilegs"))
        setUserId(localStorage.getItem("user_id"))

    }, [user, setUser, password, setPassword, userPrivilegs, setUserPrivilegs, userId, setUserId])

    const handleLogout = (e) =>{
        e.preventDefault()
        localStorage.removeItem('user_id')
        localStorage.removeItem('user')
        localStorage.removeItem('user_privilegs')
        localStorage.removeItem('password')
        setUser('')
        setPassword('')
        setUserPrivilegs('')
        setUserId('')

    }


    return (
        <Router>
            <div>
            <nav className="App-header">
                {/* <h1 className="App-title">Search for Forum</h1> */}
                {/* <Link to={props.state.home}>Home</Link>  |
                <Link to={props.state.about}>About</Link> |  
                <Link to={props.state.forums}>All Forums</Link> |  
                <Link to={props.state.form}>Specific Forum Search</Link>    */}
                <ul>
                    <li>
                    <NavLink to={props.state.home} style={({isActive}) => { return {color: (isActive ? 'blue': 'green')};}}>Home</NavLink>  
                    </li>
                    <li>
                    <NavLink to={props.state.about} style={({isActive}) => { return {color: (isActive ? 'blue': 'green')};}}>About</NavLink>  
                    </li>
                    <li>
                    <NavLink to={props.state.forums} style={({isActive}) => { return {color: (isActive ? 'blue': 'green')};}}>All Forums</NavLink> 
                    </li>
                    <li>
                    <NavLink to={props.state.form} style={({isActive}) => { return {color: (isActive ? 'blue': 'green')};}}>Specific Forum Search</NavLink> 
                    </li>
                    <li>
                    <NavLink to={props.state.dashboard} style={({isActive}) => { return {color: (isActive ? 'blue': 'green')};}}>Dashboard profile</NavLink> 
                    </li>
                    <li>
                    <NavLink to={props.state.bigChat} style={({isActive}) => { return {color: (isActive ? 'blue': 'green')};}}>Chat</NavLink> 
                    </li>
                    <li>
                    <NavLink to={props.state.login} style={({isActive}) => { return {color: (isActive ? 'blue': 'green')};}}>Login</NavLink> 
                    </li>
                    <li>
                    <NavLink to={props.state.register} style={({isActive}) => { return {color: (isActive ? 'blue': 'green')};}}>Register</NavLink>
                    </li>
                    <li>
                    <a onClick={handleLogout} type="button">Logout</a>
                    </li>
                    <li>
                        username: {user} {/*<b>time:</b>{time} {/*handletime()*/}
                    </li>
                </ul>
            </nav>
                    <UserContext.Provider value={{val:[value, setValue], us:[user, setUser], pass:[password, setPassword],
                    priv:[userPrivilegs, setUserPrivilegs], uid:[userId, setUserId], bchat:[chatMessages, setChatMessages]}}>
            <Routes>
                    <Route path={props.state.home} element={<SharedLayout/>}>

                        <Route index element={<Home/>}/>
                        <Route path={props.state.about} element={<About/>} />
                        <Route path={props.state.forums} element={<ProtectedRoute user={user}><AllForums chosenForum={props.chosenForum}/></ProtectedRoute>} />
                        <Route path={props.state.form} element={<ProtectedRoute user={user}><Form setChosenForum={props.setChosenForum} chosenForum={props.chosenForum}/></ProtectedRoute>} />
                        <Route path={props.state.login} element={<Login/>}/>
                        <Route path={props.state.register} element={<Register setUser={setUser} user={user} password={password} setPassword={setPassword}/>} />
                        <Route path={props.state.dashboard} element={<ProtectedRoute user={user}><Dashboard user_n={user}/></ProtectedRoute>}/>
                        <Route path={props.state.specificForum} element={<ForumLook/>}></Route>
                        <Route path={props.state.specificPostInsideSpecificForum} element={<SpecificPost/>}></Route>
                        <Route path={props.state.bigChat} element={<ProtectedRoute user={user}><BigChat></BigChat></ProtectedRoute>}/>
                        <Route path='*' element={<Error/>}/>    
                    </Route>    
            </Routes> 
                    </UserContext.Provider>


            </div>

        </Router>
);  
}

export default NavbarForum;