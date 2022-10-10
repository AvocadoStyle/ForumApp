/**
 *   critical update: not in use anymore in contrast we'll use `ForumLoopItemsList` (we'll always use it to make Forum items, 
 *   we'll allways use the `ForumLoopItems` 
 * 
 * */


import React, { Component } from 'react';
import ForumLoopItemsList from './ForumLoopItemsList';

class ForumList extends Component {
    state = {
        forumList: []
    }


    componentDidMount(){
            let url = "http://localhost:8080/ReserveSeatWebService2/webresources/reservation/forums"
            fetch(url)
            .then(response => response.json())
            .then(data => this.setState({forumList: data}))
            setInterval(()=>{      
                let url = "http://localhost:8080/ReserveSeatWebService2/webresources/reservation/forums"
                fetch(url)
                .then(response => response.json())
                .then(data => this.setState({forumList: data}))}, 1000)
    }
    
    render() {
        return (
            <div>
                <ForumLoopItemsList forumList={this.state.forumList}></ForumLoopItemsList>
            </div>
            );
    }
}

export default ForumList;