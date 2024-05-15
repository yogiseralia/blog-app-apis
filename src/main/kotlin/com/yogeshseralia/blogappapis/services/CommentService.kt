package com.yogeshseralia.blogappapis.services

import com.yogeshseralia.blogappapis.payloads.CommentDto

interface CommentService {
    fun createComment(commentDto: CommentDto, postId: Int, userId: Int): CommentDto
    fun deleteComment(commentId: Int)

    fun getCommentsByPost(postId: Int): List<CommentDto>
}