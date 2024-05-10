package com.yogeshseralia.blogappapis.services.impl

import com.yogeshseralia.blogappapis.entities.Post
import com.yogeshseralia.blogappapis.exceptions.ResourceNotFoundException
import com.yogeshseralia.blogappapis.mappers.mapToPostDto
import com.yogeshseralia.blogappapis.payloads.PostDto
import com.yogeshseralia.blogappapis.payloads.PostResponse
import com.yogeshseralia.blogappapis.repositories.CategoryRepo
import com.yogeshseralia.blogappapis.repositories.PostRepo
import com.yogeshseralia.blogappapis.repositories.UserRepo
import com.yogeshseralia.blogappapis.services.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostServiceImpl : PostService {

    @Autowired
    lateinit var postRepo: PostRepo

    @Autowired
    lateinit var userRepo: UserRepo

    @Autowired
    lateinit var categoryRepo: CategoryRepo

    override fun createPost(postDto: PostDto, userId: Int, categoryId: Int): PostDto {
        val user = userRepo.findById(userId).orElseThrow { ResourceNotFoundException("User", "userId", userId) }
        val category = categoryRepo.findById(categoryId).orElseThrow { ResourceNotFoundException("Category", "categoryId", categoryId) }
        val post = postRepo.save(
                Post(
                        title = postDto.title.orEmpty(),
                        content = postDto.content.orEmpty(),
                        imageName = "default.png",
                        addDate = Date(),
                        user = user,
                        category = category
                )
        )
        return post.mapToPostDto()
    }

    override fun updatePost(postDto: PostDto, postId: Int): PostDto {
        val post = postRepo.findById(postId).orElseThrow { ResourceNotFoundException("Post", "postId", postId) }
        return postRepo.save(
                post.apply {
                    title = postDto.title.orEmpty()
                    content = postDto.content.orEmpty()
                    imageName = postDto.imageName.orEmpty()
                }
        ).mapToPostDto()
    }

    override fun deletePost(postId: Int) {
        val post = postRepo.findById(postId).orElseThrow { ResourceNotFoundException("Post", "postId", postId) }
        postRepo.delete(post)
    }

    override fun getPostById(postId: Int): PostDto {
        val post = postRepo.findById(postId).orElseThrow { ResourceNotFoundException("Post", "postId", postId) }
        return post.mapToPostDto()
    }

    override fun getPostsByUserId(userId: Int): List<PostDto> {
        val user = userRepo.findById(userId).orElseThrow { ResourceNotFoundException("User", "userId", userId) }
        val postDtoList = arrayListOf<PostDto>()
        postRepo.findByUser(user).map { postDtoList.add(it.mapToPostDto()) }
        return postDtoList
    }

    override fun getPostsByCategoryId(categoryId: Int): List<PostDto> {
        val category = categoryRepo.findById(categoryId).orElseThrow { ResourceNotFoundException("Category", "categoryId", categoryId) }
        val postDtoList = arrayListOf<PostDto>()
        postRepo.findByCategory(category).map { postDtoList.add(it.mapToPostDto()) }
        return postDtoList
    }

    override fun getAllPosts(): List<PostDto> {
        val postList = postRepo.findAll()
        val postDtoList = arrayListOf<PostDto>()
        postList.map { postDtoList.add(it.mapToPostDto()) }
        return postDtoList
    }

    override fun getPosts(pageNumber: Int, pageSize: Int, sortBy: String, isAscending: Boolean): PostResponse {
        val sort: Sort = Sort.by(sortBy).apply {
            if (isAscending) ascending()
            else descending()
        }
        val request = PageRequest.of(pageNumber, pageSize, sort)

        val postDtoList = arrayListOf<PostDto>()

        val pagedPosts: Page<Post> = postRepo.findAll(request)
        pagedPosts.content.map { postDtoList.add(it.mapToPostDto()) }

        return PostResponse().apply {
            content = postDtoList
            this.pageSize = pagedPosts.pageable.pageSize
            this.pageNumber = pagedPosts.pageable.pageNumber
            totalPages = pagedPosts.totalPages
            totalElements = pagedPosts.totalElements
            lastPage = pagedPosts.isLast
        }
    }

    override fun searchPosts(title: String): List<PostDto> {
        val postList: List<Post> = postRepo.findByTitleContaining(title)
        val postDtoList: ArrayList<PostDto> = arrayListOf()
        postList.map { postDtoList.add(it.mapToPostDto()) }
        return postDtoList
    }

    override fun searchPostsByContent(title: String): List<PostDto> {
        val postList: List<Post> = postRepo.searchByContent("%$title%")
        val postDtoList: ArrayList<PostDto> = arrayListOf()
        postList.map { postDtoList.add(it.mapToPostDto()) }
        return postDtoList
    }
}