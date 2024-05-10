package com.yogeshseralia.blogappapis.controllers

import com.yogeshseralia.blogappapis.config.AppConstants.DEFAULT_PAGE_NUMBER
import com.yogeshseralia.blogappapis.config.AppConstants.DEFAULT_PAGE_SIZE
import com.yogeshseralia.blogappapis.config.AppConstants.TITLE
import com.yogeshseralia.blogappapis.config.AppConstants.TRUE
import com.yogeshseralia.blogappapis.payloads.ApiResponse
import com.yogeshseralia.blogappapis.payloads.PostDto
import com.yogeshseralia.blogappapis.payloads.PostResponse
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

    @GetMapping("/post/{postId}")
    fun getPostById(@PathVariable postId: Int): ResponseEntity<PostDto> {
        val postDto = service.getPostById(postId)
        return ResponseEntity.ok(postDto)
    }

    @DeleteMapping("/post/{postId}")
    fun deletePost(@PathVariable postId: Int): ApiResponse {
        service.deletePost(postId)
        return ApiResponse("Post successfully deleted!!", true)
    }

    @PutMapping("/post/{postId}")
    fun updatePost(@PathVariable postId: Int, @RequestBody postDto: PostDto): ResponseEntity<PostDto> {
        val updatedPost: PostDto = service.updatePost(postDto, postId)
        return ResponseEntity.ok(updatedPost)
    }

    @GetMapping("/posts")
    fun getPosts(@RequestParam("pageNumber", defaultValue = DEFAULT_PAGE_NUMBER, required = false) pageNumber: Int,
                 @RequestParam("pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) pageSize: Int,
                 @RequestParam("sortBy", defaultValue = TITLE, required = false) sortBy: String,
                 @RequestParam("isAscending", defaultValue = TRUE, required = false) isAscending: Boolean):
            ResponseEntity<PostResponse> {
        val postResponse = service.getPosts(pageNumber, pageSize, sortBy, isAscending)
        return ResponseEntity.ok(postResponse)
    }

    @GetMapping("/posts/search/{keyword}")
    fun searchPostsByTitle(@PathVariable("keyword") keywordInTitle: String): ResponseEntity<List<PostDto>> {
        val searchedPosts: List<PostDto> = service.searchPostsByContent(keywordInTitle)
        return ResponseEntity.ok(searchedPosts)
    }
}