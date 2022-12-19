import React from 'react';
import ForumInside from './ForumInside'
function ForumItems(props) {
    return (
        <div>
            <div key={props.item.id_forum} /*className="col-lg-6 border"*/ className="clsborder_forum">
                <h2>
                    Forum Name: {props.item.forum_name}
                </h2>
                <ForumInside key={props.item.id_forum} item={props.item}></ForumInside>
            </div>
        </div>
    );
}

export default ForumItems;