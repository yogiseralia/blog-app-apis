package com.yogeshseralia.blogappapis.controllers

import com.yogeshseralia.blogappapis.config.AppConstants.DEFAULT_PAGE_NUMBER
import com.yogeshseralia.blogappapis.config.AppConstants.DEFAULT_PAGE_SIZE
import com.yogeshseralia.blogappapis.config.AppConstants.TITLE
import com.yogeshseralia.blogappapis.config.AppConstants.TRUE
import com.yogeshseralia.blogappapis.payloads.ApiResponse
import com.yogeshseralia.blogappapis.payloads.PostDto
import com.yogeshseralia.blogappapis.payloads.PostResponse
import com.yogeshseralia.blogappapis.services.FileService
import com.yogeshseralia.blogappapis.services.PostService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

@RestController
@RequestMapping("/api")
class PostController {

    @Autowired
    lateinit var postService: PostService

    @Autowired
    lateinit var fileService: FileService

    @Value("\${project.image}")
    lateinit var imageUploadPath: String

    // create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    fun createPost(@RequestBody postDto: PostDto,
                   @PathVariable userId: Int,
                   @PathVariable categoryId: Int): ResponseEntity<PostDto> {
        val createdPost = postService.createPost(postDto, userId, categoryId)
        return ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED)
    }

    //get by user
    @GetMapping("/user/{userId}/posts")
    fun getPostByUserId(@PathVariable userId: Int): ResponseEntity<List<PostDto>> {
        val postDtoList = postService.getPostsByUserId(userId)
        return ResponseEntity.ok(postDtoList)
    }

    //get by category
    @GetMapping("/category/{categoryId}/posts")
    fun getPostByCategoryId(@PathVariable categoryId: Int): ResponseEntity<List<PostDto>> {
        val postDtoList = postService.getPostsByCategoryId(categoryId)
        return ResponseEntity.ok(postDtoList)
    }

    @GetMapping("/post/{postId}")
    fun getPostById(@PathVariable postId: Int): ResponseEntity<PostDto> {
        val postDto = postService.getPostById(postId)
        return ResponseEntity.ok(postDto)
    }

    @DeleteMapping("/post/{postId}")
    fun deletePost(@PathVariable postId: Int): ApiResponse {
        postService.deletePost(postId)
        return ApiResponse("Post successfully deleted!!", true)
    }

    @PutMapping("/post/{postId}")
    fun updatePost(@PathVariable postId: Int, @RequestBody postDto: PostDto): ResponseEntity<PostDto> {
        val updatedPost: PostDto = postService.updatePost(postDto, postId)
        return ResponseEntity.ok(updatedPost)
    }

    @GetMapping("/posts")
    fun getPosts(@RequestParam("pageNumber", defaultValue = DEFAULT_PAGE_NUMBER, required = false) pageNumber: Int,
                 @RequestParam("pageSize", defaultValue = DEFAULT_PAGE_SIZE, required = false) pageSize: Int,
                 @RequestParam("sortBy", defaultValue = TITLE, required = false) sortBy: String,
                 @RequestParam("isAscending", defaultValue = TRUE, required = false) isAscending: Boolean):
            ResponseEntity<PostResponse> {
        val postResponse = postService.getPosts(pageNumber, pageSize, sortBy, isAscending)
        return ResponseEntity.ok(postResponse)
    }

    @GetMapping("/posts/search/{keyword}")
    fun searchPostsByTitle(@PathVariable("keyword") keywordInTitle: String): ResponseEntity<List<PostDto>> {
        val searchedPosts: List<PostDto> = postService.searchPostsByContent(keywordInTitle)
        return ResponseEntity.ok(searchedPosts)
    }

    @Throws(IOException::class)
    @PostMapping("/post/image/upload/{postId}")
    fun uploadImage(@PathVariable("postId") postId: Int, @RequestParam("image") file: MultipartFile): ResponseEntity<PostDto> {
        val postById = postService.getPostById(postId)
        val fileName = fileService.uploadImage(path = imageUploadPath, file)
        val postDto = postById.apply {
            imageName = fileName
        }
        val updatedPost = postService.updatePost(postDto, postId)
        return ResponseEntity.ok(updatedPost)
    }

    @Throws(FileNotFoundException::class)
    @GetMapping("/post/image/{imageName}", produces = [MediaType.IMAGE_JPEG_VALUE])
    fun downloadImage(@PathVariable("imageName") imageName: String, response: HttpServletResponse) {
        val inputStream: InputStream = fileService.getResource(imageUploadPath, imageName)
        response.contentType = MediaType.IMAGE_JPEG_VALUE
        StreamUtils.copy(inputStream, response.outputStream)
    }
}