package com.yogeshseralia.blogappapis.services.impl

import com.yogeshseralia.blogappapis.entities.Comment
import com.yogeshseralia.blogappapis.entities.Post
import com.yogeshseralia.blogappapis.exceptions.ResourceNotFoundException
import com.yogeshseralia.blogappapis.mappers.mapToCommentDto
import com.yogeshseralia.blogappapis.payloads.CommentDto
import com.yogeshseralia.blogappapis.repositories.CommentRepo
import com.yogeshseralia.blogappapis.repositories.PostRepo
import com.yogeshseralia.blogappapis.repositories.UserRepo
import com.yogeshseralia.blogappapis.services.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Date

@Service
class CommentServiceImpl : CommentService {

    @Autowired
    lateinit var commentRepo: CommentRepo

    @Autowired
    lateinit var postRepo: PostRepo

    @Autowired
    lateinit var userRepo: UserRepo

    override fun createComment(commentDto: CommentDto, postId: Int, userId: Int): CommentDto {

        val post: Post = postRepo.findById(postId).orElseThrow { ResourceNotFoundException("Post", "postId", postId) }
        val user = userRepo.findById(userId).orElseThrow { ResourceNotFoundException("User", "userId", userId) }
        return commentRepo.save(
            Comment(
                comment = commentDto.comment,
                addDate = Date(),
                post = post,
                user = user
            )
        ).mapToCommentDto()
    }

    override fun deleteComment(commentId: Int) {
        val comment =
            commentRepo.findById(commentId).orElseThrow { ResourceNotFoundException("Comment", "commentId", commentId) }
        commentRepo.delete(comment)
    }

    override fun getCommentsByPost(postId: Int): List<CommentDto> {
        val post = postRepo.findById(postId).orElseThrow { ResourceNotFoundException("Post", "postId", postId) }
        val commentDtos = arrayListOf<CommentDto>()
        commentRepo.findByPost(post).map { commentDtos.add(it.mapToCommentDto()) }
        return commentDtos
    }
}