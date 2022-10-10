import React, { Component } from 'react';
import { Link, useParams } from 'react-router-dom'
import ForumPosts from '../posts/ForumPosts';
import PostsShow from '../posts/PostsShow';
import AddPost from './../posts/AddPost'

    



const ForumLook = () => {
    const { forumId } = useParams();
    let param = 1;

    return (
        <div>
            <div>
                <PostsShow forumId={{forumId}}></PostsShow>
            </div>
            <div>
                <AddPost></AddPost>
            </div>
        </div>
    );
};

export default ForumLook;