package com.example.service;

import com.example.dto.request.CommentRequest;
import com.example.dto.response.CommentResponse;
import com.example.mapper.CommentMapper;
import com.example.model.Comment;
import com.example.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> saveAll (List<Comment> comments) {
        return commentRepository.saveAll(comments);
    }
}
