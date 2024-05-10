package com.yogeshseralia.blogappapis.services

import com.yogeshseralia.blogappapis.payloads.PostDto
import com.yogeshseralia.blogappapis.payloads.PostResponse

interface PostService {
    //create
    fun createPost(postDto: PostDto, userId: Int, categoryId: Int): PostDto

    //update
    fun updatePost(postDto: PostDto, postId: Int): PostDto

    //delete
    fun deletePost(postId: Int)

    //getbyid
    fun getPostById(postId: Int): PostDto

    //getbyUserId
    fun getPostsByUserId(userId: Int): List<PostDto>

    //getByCategoryId
    fun getPostsByCategoryId(categoryId: Int): List<PostDto>

    //getAll
    fun getAllPosts(): List<PostDto>

    //get posts by page
    fun getPosts(pageNumber: Int, pageSize: Int, sortBy: String, isAscending: Boolean): PostResponse

    fun searchPosts(title: String): List<PostDto>

    fun searchPostsByContent(title: String): List<PostDto>
}