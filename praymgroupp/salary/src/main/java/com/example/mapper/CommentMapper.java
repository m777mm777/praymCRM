package com.example.mapper;

import com.example.dto.request.CommentRequest;
import com.example.dto.response.CommentResponse;
import com.example.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentMapper {

    public Comment toComment(CommentRequest request) {
        if (request == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setId(request.getId());
        comment.setText(request.getText());

        return comment;
    }

    public CommentResponse toResponse(Comment comment) {
        if (comment == null) {
            return null;
        }

        CommentResponse.CommentResponseBuilder commentResponseBuilder = CommentResponse.builder();
        commentResponseBuilder.id(comment.getId());
        commentResponseBuilder.text(comment.getText());


        return commentResponseBuilder.build();
    }

    public List<CommentResponse> toResponseCollection(List<Comment> comments) {
        if (comments == null) {
            return null;
        }
        List<CommentResponse> list = new ArrayList<CommentResponse>(comments.size());

        for (Comment comment : comments) {
            list.add(toResponse(comment));
        }

        return list;
    }

    public List<Comment> toCommentCollection(List<CommentRequest> requests) {
        if (requests == null) {
            return null;
        }
        List<Comment> list = new ArrayList<Comment>(requests.size());

        for (CommentRequest request : requests) {
            list.add(toComment(request));
        }

        return list;
    }
}
