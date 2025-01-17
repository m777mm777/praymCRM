package com.example.service;

import com.example.dto.request.CommentRequest;
import com.example.dto.request.SalaryRequest;
import com.example.dto.response.CommentResponse;
import com.example.dto.response.SalaryResponse;
import com.example.model.Comment;

import java.util.List;

public interface CommentService {

    Comment create(Comment comment);
    List<Comment> saveAll(List<Comment> comments);
}
