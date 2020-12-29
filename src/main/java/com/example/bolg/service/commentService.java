package com.example.bolg.service;

import com.example.bolg.po.Comment;

import java.util.List;

public interface commentService {

    List<Comment> listCommentByBlogId(Long blogId);
    Comment SaveComment(Comment comment);

}
