package com.locadoralipe.locadoralipe.domain.user

class InsertUser(val repository : UserBoundary.IRepository):UserBoundary.IInsertUser {
    override fun run(user: User): String {
        return repository.insertUserMongo(user)
    }
}