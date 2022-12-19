import React, { Component } from 'react';
import {
    Link
  } from "react-router-dom";
class ForumPosts extends Component {
    state = {
        forumName: [],
        param:0,
        posts: [],
        users: [],
        specific_user: ''
    }

    header = () =>{
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

    componentDidMount() {
        let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/forums/${this.props.forumId}`
        fetch(url, this.header())
            .then(response => response.json())
            .then(data => this.setState({forumName: data}))
        url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/users`
        fetch(url, this.header())
        .then(response => response.json())
        .then(data => this.setState({users: data}))
    }


    componentDidUpdate(props, state) {
        if(this.state.param === 0){
            let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/forums/${this.props.forumId}`
            console.log("url is: ", url)
            fetch(url, this.header())                                  // todo: fix it TODO 
                .then(response => response.json())
                .then(data => this.setState({forumName: data}))
            url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/posts?id_forum=${this.props.forumId}`
            console.log("url is: ", url)
            fetch(url, this.header())
                .then(response => response.json())
                .then(data => this.setState({posts: data}))
            this.setState({param: 2})
        }
    
    // getUser(user_id){
        

    // }


    }
    render() {
        return (
            <div>

                
                <h1><b>Forum Name:</b> {this.state.forumName['forum_name']}</h1>
                <h2><b>Posts Are:</b> </h2>
                    { 
                        this.state.posts.map((post) => {
                            return(
                                <div key={post.post_name} className="col-lg-6 border">
                                    <h4>
                                        <b>Post Name:</b> {post.post_name}
                                    </h4>
                                        {this.state.users.map((user) =>{
                                            if(user.user_id == post.user_id){
                                                this.setState({specific_user: user.user_name})
                                            }
                                        })}
                                    <h6>
                                        User Name: {this.state.specific_user}
                                    </h6>

                                        {/* for(let i=0; i<globalThis.state.users; i++){ */}
                                            {/* if(this.state.users['user_id'] == post.user_id){ */}
                                                
                                            {/* } */}
                                        {/* } */}
                                        {/* User: {this.state.users[''+post.user_id].user_name} */}
                                    {/* <ForumInside key={post.post_id} item={post}></ForumInside> */}
                                    <Link to={`/forums/${this.props.forumId}/posts/${post.post_id}`}><button className="col-sm-4">get in post</button></Link>


                                </div>
                            )
                        })
                    }
                </div>
            );
    }
}

export default ForumPosts;