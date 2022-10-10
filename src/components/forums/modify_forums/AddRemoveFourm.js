import React, { Component } from 'react';
import ForumNameSearch from './ForumNameSearch';
import RemoveOrAdd from './RemoveOrAdd';

class AddRemoveFourm extends Component {
    state = {
        apiRequest: "add or remove",
        forumSearchValue: "search"
    }

    setChosenForum=(request, forum_name)=>{
        // console.log("forum name: ", forum_name)
        // console.log("url: ", (request+forum_name))
        this.props.setIncreaseChanged()
        this.setState({apiRequest: request,
            forumSearchValue: forum_name})
        console.log("did it!")
        // this.setState({forumSearchValue: forum_name})
    }
    render() {
        return (
            <div>
                <ForumNameSearch setChosenForum={this.setChosenForum}></ForumNameSearch>
                <RemoveOrAdd apiRequest={this.state.apiRequest} forumSearchValue={this.state.forumSearchValue}></RemoveOrAdd>
            </div>
        );
    }
}

export default AddRemoveFourm;