package com.locadoralipe.locadoralipe.domain.user

class UserBoundary {

    interface IRepository{
        fun getUsersMongo():List<User>
        fun insertUserMongo(user:User):String
    }

    interface IGetUsers{
        fun run():List<User>
    }

    interface IInsertUser{
        fun run(user: User):String
    }


}