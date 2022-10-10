import React, { Component } from 'react';
import ForumItems from './ForumItems';

class ForumLoopItemsList extends Component {
    render() {
        return (
            <div className="Container">
                <div className="row">
                    { 
                        this.props.forumList.map((item) => {
                            return(
                                <ForumItems key={item.id_forum} item={item}></ForumItems>
                            )
                        })
                    }
                </div>
            </div>

        );
    }
}

export default ForumLoopItemsList;