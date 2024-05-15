package com.yogeshseralia.blogappapis.controllers

import com.yogeshseralia.blogappapis.payloads.ApiResponse
import com.yogeshseralia.blogappapis.payloads.CommentDto
import com.yogeshseralia.blogappapis.services.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class CommentController {

    @Autowired
    lateinit var commentService: CommentService

    @PostMapping("/user/{userId}/post/{postId}/comment")
    fun createComment(
        @RequestBody commentDto: CommentDto,
        @PathVariable userId: Int,
        @PathVariable postId: Int
    ): ResponseEntity<CommentDto> {
        return ResponseEntity(commentService.createComment(commentDto, postId, userId), HttpStatus.CREATED)
    }

    @DeleteMapping("/comment/{commentId}")
    fun createComment(@PathVariable commentId: Int): ResponseEntity<ApiResponse> {
        commentService.deleteComment(commentId)
        return ResponseEntity.ok(ApiResponse("Comment Deleted Successfully!!", true))
    }

    @GetMapping("/post/{postId}/comments")
    fun getCommentsByPostId(@PathVariable postId: Int): ResponseEntity<List<CommentDto>> {
        val commentDtos = commentService.getCommentsByPost(postId)
        return ResponseEntity.ok(commentDtos)
    }
}