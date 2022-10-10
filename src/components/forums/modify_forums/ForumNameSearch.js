import React, { Component } from 'react';
import AddRemoveButtons from './AddRemoveButtons';

class ForumNameSearch extends Component {
    inputVal = React.createRef();
    makeChange = (url) => {
        this.props.setChosenForum(url, this.inputVal.current.value)
    }
    render() {
        return (
            <div>
                <input type="text" ref={this.inputVal}></input>
                <AddRemoveButtons inputVal={this.inputVal} makeChange={this.makeChange}></AddRemoveButtons>
            </div>
        );

    }
}

export default ForumNameSearch;