package com.yogeshseralia.blogappapis.controllers

import com.yogeshseralia.blogappapis.payloads.PostDto
import com.yogeshseralia.blogappapis.services.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PostController {

    @Autowired
    lateinit var service: PostService

    // create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    fun createPost(@RequestBody postDto: PostDto,
                   @PathVariable userId: Int,
                   @PathVariable categoryId: Int): ResponseEntity<PostDto> {
        val createdPost = service.createPost(postDto, userId, categoryId)
        return ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED)
    }

    //get by user
    @GetMapping("/user/{userId}/posts")
    fun getPostByUserId(@PathVariable userId: Int): ResponseEntity<List<PostDto>> {
        val postDtoList = service.getPostsByUserId(userId)
        return ResponseEntity.ok(postDtoList)
    }

    //get by category
    @GetMapping("/category/{categoryId}/posts")
    fun getPostByCategoryId(@PathVariable categoryId: Int): ResponseEntity<List<PostDto>> {
        val postDtoList = service.getPostsByCategoryId(categoryId)
        return ResponseEntity.ok(postDtoList)
    }

    @GetMapping("/posts")
    fun getAllPosts(): ResponseEntity<List<PostDto>> {
        val postDtoList = service.getAllPosts()
        return ResponseEntity.ok(postDtoList)
    }

    @GetMapping("/post/{postId}")
    fun getPostById(@PathVariable postId: Int): ResponseEntity<PostDto> {
        val postDto = service.getPostById(postId)
        return ResponseEntity.ok(postDto)
    }
}