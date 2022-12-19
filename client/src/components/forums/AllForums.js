import React, { Component } from 'react';
// import ForumList from './ForumList'
import ForumSpecificNameList from './ForumSpecificNameList';
import AddRemoveForum from './modify_forums/AddRemoveFourm'
class AllForums extends Component {
    state = {
        changed: 0
    }
    
    setIncreaseChanged = () =>{
        let val = this.state.changed + 1
        console.log("changed++ now: ", val)
        this.setState({changed: val})
    }

    render() {
        return (
            <div>
                <AddRemoveForum
                // changed={this.state.changed}
                setIncreaseChanged={this.setIncreaseChanged}
                >
                </AddRemoveForum>
                {/* <ForumList></ForumList> */}
                <ForumSpecificNameList 
                    changed={this.state.changed} 
                    // setIncreaseChanged={this.setIncreaseChanged}
                    >
                </ForumSpecificNameList>
            </div>
        );
    }
}

export default AllForums;