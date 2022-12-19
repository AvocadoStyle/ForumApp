import React, { Component } from 'react';

class RemoveOrAdd extends Component {
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

    componentDidUpdate(props, state){ 
        if((this.props.forumSearchValue !== props.forumSearchValue) ||
         (this.props.apiRequest !== props.apiRequest)){
            let url = this.props.apiRequest + this.props.forumSearchValue
            let request_header = this.header()
            if(url.includes("delete")){
                request_header.method = 'DELETE';
                fetch(url, request_header)
            }            
            if(url.includes("add")){
                request_header.method = 'POST';
                fetch(url, request_header)
            }
        }
}

    render() {
        return (
            <div>
                {/* <ForumLoopItemsList forumList={this.state.forumList}></ForumLoopItemsList> */}
                {/* <ForumSpecificNameList></ForumSpecificNameList> */}
            </div>
        )
    }
}
export default RemoveOrAdd;