package com.yogeshseralia.blogappapis.services

import org.springframework.web.multipart.MultipartFile
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

interface FileService {
    @Throws(IOException::class)
    fun uploadImage(path: String, multipartFile: MultipartFile): String

    @Throws(FileNotFoundException::class)
    fun getResource(path: String, fileName: String): InputStream
}