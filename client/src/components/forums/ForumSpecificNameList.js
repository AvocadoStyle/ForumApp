import React, { Component } from 'react';
import ForumLoopItemsList from './ForumLoopItemsList';

class ForumSpecificNameList extends Component {
    state = {
        forumList: []
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
    componentDidMount(){
        let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/forums`
        fetch(url, this.header())
        .then(response => response.json())
        .then(data => this.setState({forumList: data}))
    }
    componentDidUpdate(props, state){  
            if(this.props.chosenForum !== props.chosenForum){
                let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/forums?forum_name=${this.props.chosenForum}`
                fetch(url, this.header())
                .then(response => response.json())
                .then(data => this.setState({forumList: data}))
            }
            else if(this.props.changed !== props.changed){
                let url = `http://localhost:8080/ReserveSeatWebService2/webresources/reservation/forums`
                fetch(url, this.header())
                .then(response => response.json())
                .then(data => this.setState({forumList: data}))
            }
    }

    
    render() {
        return (
            <ForumLoopItemsList forumList={this.state.forumList}></ForumLoopItemsList>
        );
    }
}

export default ForumSpecificNameList;