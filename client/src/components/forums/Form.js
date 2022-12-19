import React, { Component } from 'react';
import ForumSpecificNameList from './ForumSpecificNameList';

class Form extends Component {
    inputVal = React.createRef();
    showTheForum = () =>{
        this.props.setChosenForum(this.inputVal.current.value)
        }
    render() {
        return (
            <div>
                <input type="text" ref={this.inputVal}/>
                <button onClick={this.showTheForum} >Search</button>
                {console.log(this.props.chosenForum)}
                <ForumSpecificNameList chosenForum={this.props.chosenForum}></ForumSpecificNameList>
            </div>
        );
    }
}
        
export default Form;