import React, { Component } from 'react';
import {
    // BrowserRouter as Router,
    // Routes,
    // Route,
    Link
  } from "react-router-dom";
class About extends Component {
    render() {
        return (
            <div>
                <h1>The creator of this Forum is Eden Refael.</h1>
                <h4>Details about Eden:</h4>
                <ul>
                    <ul>
                        <a href="https://edenraf@hotmail.com">email</a> 
                    </ul>
                    <ul>
                        <a href="https://www.linkedin.com/edenrefael">Linkedin</a> 
                    </ul>
                    <ul>
                        <a href="https://www.facebook.com/edenraf">Facebook</a> 
                    </ul>
                    <ul>
                        <a href="https://www.instagram.com/edenrefaelov">Instagram</a> 
                    </ul>
                </ul>

            </div>
        );
    }
}

export default About;