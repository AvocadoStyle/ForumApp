import React, { Component } from 'react';
// import logo512 from './../../public/logo512.png'
class Home extends Component {
    render() {
        return (
            <div>
                <h1>Welcome to the Forum WEB APP, have fun!</h1>
                <p>This is a Forum App part of the Open University workshop in order to accomplish BSC in Computer Science.</p>
                <h4 className="row-lg-5">
                    <img className="icon_img"src={require('./../../src/ubuntu.png')}></img>Built with Ubuntu OS
                </h4>
                <h4 className="row-lg-5">
                    <img className="icon_img"src={require('./../../src/react.png')}></img>Client side REACT JS
                </h4>
                <h4 className="row-lg-5">
                    <img className="icon_img"src={require('./../../src/java.png')}></img>Server side JAXRS Java
                </h4>
                <h4 className="row-lg-5">
                    <img className="icon_img"src={require('./../../src/mysql.png')}></img>MYSQL Database JDBC
                </h4>
                <h4 className="row-lg-5">
                    <img className="icon_img"src={require('./../../src/fish.png')}></img>PAYARA Server Intigrate
                </h4>
                <p>I Hope you guys enjoy inspecting my <b>MASTER PIECE!</b></p>
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

export default Home;