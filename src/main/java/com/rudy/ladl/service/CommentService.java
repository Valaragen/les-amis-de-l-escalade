package com.rudy.ladl.service;

import com.rudy.ladl.core.site.Comment;
import com.rudy.ladl.core.site.Site;
import com.rudy.ladl.core.site.embeddable.CommentId;
import com.rudy.ladl.core.user.User;
import com.rudy.ladl.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public  class CommentService {

    private CommentRepository commentRepository;
    @Autowired
    public CommentService (CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public void addComment(Comment comment, Site site, User user) {
        CommentId commentId = new CommentId(site, user, new Date());
        comment.setId(commentId);
        commentRepository.save(comment);
    }

    public Comment findById(CommentId id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Boolean modifyComment(Comment comment, User user, Boolean isMember) {
        if(user == comment.getId().getUser() || isMember) {
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    public Boolean deleteComment(Comment comment, User user, Boolean isMember) {
        if(user == comment.getId().getUser() || isMember) {
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }
}
