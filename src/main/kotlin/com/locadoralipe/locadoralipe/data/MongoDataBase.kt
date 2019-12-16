package com.locadoralipe.locadoralipe.data

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.index.Indexed



class MongoDataBase {

    @Document(collection = "user")
    data class mongoUser(
            val name:String,
            val age:Int,
            @Indexed(unique = true) val email: String,
            val preferences : List<String>
    ){
        @Id
        lateinit var id: ObjectId
    }
}