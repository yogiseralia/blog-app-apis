package com.yogeshseralia.blogappapis.services.impl

import com.yogeshseralia.blogappapis.services.FileService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@Service
class FileServiceImpl : FileService {
    override fun uploadImage(path: String, file: MultipartFile): String {
        val name = file.originalFilename.orEmpty()
        val randomID = UUID.randomUUID().toString()
        val fileName = randomID + name.substring(name.lastIndexOf("."))
        val filePath = path + File.separator + fileName

        val fileObject = File(path)
        if (fileObject.exists().not()) fileObject.mkdirs()

        Files.copy(file.inputStream, Paths.get(filePath))

        return fileName
    }

    override fun getResource(path: String, fileName: String): InputStream {
        val filePath = path + File.separator + fileName
        val fileInputStream = FileInputStream(filePath)
        return fileInputStream
    }
}