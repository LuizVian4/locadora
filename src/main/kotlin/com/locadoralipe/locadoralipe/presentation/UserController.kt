package com.locadoralipe.locadoralipe.presentation

import com.locadoralipe.locadoralipe.domain.user.User
import com.locadoralipe.locadoralipe.domain.user.UserBoundary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import javax.validation.ValidationException

@RestController
@RequestMapping("/user")
class UserController(
        @Autowired val getUsers: UserBoundary.IGetUsers,
        @Autowired val insertUser: UserBoundary.IInsertUser
) {

    @GetMapping()
    fun getUsers(): List<HashMap<String, Any>> {
        return getUsers.run().map {
            toJson(it)
        }
    }

    fun toJson(user: User): HashMap<String, Any> {
        return hashMapOf(
                "name" to user.name,
                "age" to user.age,
                "email" to user.email,
                "preferences" to user.preferences
        )

    }

    @PostMapping
    fun insertUser(
            @RequestBody body: HashMap<String, Any>
    ): ResponseEntity<HashMap<String, Any>> {
        return try {
            val name = body["name"] as String
            val age = body["age"] as Int
            val email = body["email"] as String
            val preferences = body["preferences"] as List<String>
            val id = insertUser.run(User(name, age, email, preferences))
            ResponseEntity(hashMapOf<String, Any>(
                    "id" to id
            ), HttpStatus.OK)
        } catch (e: Exception) {
            when (e) {
                is ValidationException -> {
                    ResponseEntity(HttpStatus.BAD_REQUEST)
                }
                else -> {
                    ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
                }
            }
        }
    }
}