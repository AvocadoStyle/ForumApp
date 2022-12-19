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
                <h1>The creator of this Multi Platform Forums APP is Eden Refael</h1>
                <h4>Details about Eden</h4>
                <p>Eden is a software engineer, Studied in the Open University of Israel.</p>
                <p>Eden is working in a HighTech startup company at Tel-Aviv.</p>
                <p>Living in Qiryat-Yam in a big Penthouse with a fancy lifestyle</p>
                <p>Eden is willing to accomplish many achievements that will contribute the society.</p>
                <img src='https://scontent.ftlv1-1.fna.fbcdn.net/v/t39.30808-6/274979797_5540690489277877_5153379840598692738_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=B4whNdI70H8AX9L29Lg&_nc_ht=scontent.ftlv1-1.fna&oh=00_AT_wzvuFNOhf6Gqsh3sQjxY-YNowH0jYsMbHqg4p5bpNkw&oe=634A3E71'
                 alt="Avatar" className="avatar_img"></img>
                <footer>
                    <ul>
                        <li>
                            <a href="https://edenraf@hotmail.com"><img className="icon_img"src={require('./../../src/email.png')}></img>email</a>
                        </li>
                        <li>
                            <a href="https://www.linkedin.com/edenrefael"><img className="icon_img"src={require('./../../src/linkedin.png')}></img>Linkedin</a> 
                        </li>
                        <li>
                            <a href="https://www.facebook.com/edenraf"><img className="icon_img"src={require('./../../src/facebook.png')}></img>Facebook</a> 
                        </li>
                        <li>
                            <a href="https://www.instagram.com/edenrefaelov"><img className="icon_img"src={require('./../../src/instagram.png')}></img>Instagram</a> 
                        </li>
                        <li>
                        <a href="https://www.whatsapp.com/edenrefaelov"><img className="icon_img"src={require('./../../src/whatsapp.png')}></img>whatsapp</a> 

                        </li>
                    </ul>
                </footer>

            </div>
        );
    }
}

export default About;