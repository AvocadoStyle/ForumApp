import React, { Component } from 'react';
import './App.css';
import NavbarForum from './components/NavbarForum';

class App extends Component {
    state = {
        chosenForum: "search forum",
        home: '/',
        about: '/about',
        forums: '/forums',
        specificForum: "/forums/:forumId",
        specificPostInsideSpecificForum: "/forums/:forumId/posts/:postId",
        form: '/form',
        login: '/login',
        register: '/register',
        dashboard: '/dashboard'
    }

    setChosenForum = (val) => {
        this.setState({chosenForum: val})
    }
    render() {
        return (
            <div className="App">
                <NavbarForum state={this.state} setChosenForum={this.setChosenForum} chosenForum={this.state.chosenForum}></NavbarForum>
            </div>
        );
    }
}

export default App;