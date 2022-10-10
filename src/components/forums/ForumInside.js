import React from 'react';
import {
    Link
  } from "react-router-dom";

  function ForumInside(props) {
    return (
        <div>
            <Link to={`/forums/${props.item.id_forum}`}><button /*className="col-sm-4"*/>get in forum</button></Link>
        </div>

);  
}

export default ForumInside;