package com.locadoralipe.locadoralipe.data

import com.locadoralipe.locadoralipe.domain.user.User
import com.locadoralipe.locadoralipe.domain.user.UserBoundary
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class UserRepository : UserBoundary.IRepository {

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    override fun getUsersMongo(): List<User> {
        val responses = mongoTemplate.find(Query.query(Criteria()), MongoDataBase.mongoUser::class.java, "user")
        return responses.map {
            User(
                    it.name,
                    it.age,
                    it.email,
                    it.preferences
            )
        }
    }

    override fun insertUserMongo(user: User): String {
        val response = mongoTemplate.insert(
                MongoDataBase.mongoUser(
                        user.name,
                        user.age,
                        user.email,
                        user.preferences
                )
        )
        return response.id.toHexString()
    }
}