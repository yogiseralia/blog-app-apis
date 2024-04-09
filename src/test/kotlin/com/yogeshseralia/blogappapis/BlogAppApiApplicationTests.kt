package com.yogeshseralia.blogappapis

import com.yogeshseralia.blogappapis.repositories.UserRepo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BlogAppApiApplicationTests {

	@Autowired
	private lateinit var userRepo: UserRepo

	@Test
	fun contextLoads() {
	}

	@Test
	public fun repoTest() {
		println(userRepo.javaClass.packageName)
		println(userRepo.javaClass)
	}
}
