import React, { Component } from 'react';

class AddRemoveButtons extends Component {
    addForum = ()=>{
        this.props.makeChange(`http://localhost:8080/ReserveSeatWebService2/webresources/reservation/forums?add_forum_name=`)
    }
    
    removeForum = ()=>{
        this.props.makeChange(`http://localhost:8080/ReserveSeatWebService2/webresources/reservation/forums?delete_forum_name=`)
    }

    render() {
        return (
            <div>
                <button onClick={this.addForum}>Add</button>
                <button onClick={this.removeForum}>Remove</button>
            </div>
        );
    }
}

export default AddRemoveButtons;