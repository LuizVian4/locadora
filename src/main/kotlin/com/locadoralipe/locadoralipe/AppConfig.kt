package com.locadoralipe.locadoralipe

import com.locadoralipe.locadoralipe.data.UserRepository
import com.locadoralipe.locadoralipe.domain.user.GetUsers
import com.locadoralipe.locadoralipe.domain.user.InsertUser
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.MongoTemplate


@Configuration
@ServletComponentScan
class AppConfig :AbstractMongoClientConfiguration() {
    @Autowired
    lateinit var userRepository: UserRepository

    @Bean
    fun getUsers()= GetUsers(userRepository)

    @Bean
    fun insertUser()= InsertUser(userRepository)

    override fun mongoClient(): MongoClient {
        //return MongoClients.create("mongodb://localhost:27017")
        return MongoClients.create("mongodb+srv://luizviana:7MSSxrRyoa5aqpN5@locadoralipe-xhcmw.gcp.mongodb.net/test?retryWrites=true&w=majority")
    }

    override fun getDatabaseName(): String {
        return "locadoralipe"
    }

    @Bean
    override fun mongoTemplate(): MongoTemplate {
        return MongoTemplate(mongoClient(), databaseName)
    }


}