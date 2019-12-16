package com.locadoralipe.locadoralipe.domain.user

import com.locadoralipe.locadoralipe.data.UserRepository

class GetUsers(val repository: UserBoundary.IRepository) : UserBoundary.IGetUsers {
    override fun run(): List<User> {
        return repository.getUsersMongo()
    }

}