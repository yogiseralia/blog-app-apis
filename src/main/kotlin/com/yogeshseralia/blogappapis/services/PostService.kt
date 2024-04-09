package com.yogeshseralia.blogappapis.services

import com.yogeshseralia.blogappapis.payloads.PostDto

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
}