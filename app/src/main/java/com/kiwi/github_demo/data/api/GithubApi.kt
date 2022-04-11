package com.kiwi.github_demo.data.api

import com.kiwi.github_demo.data.entites.User
import retrofit2.http.GET

interface GithubApi {

    @GET("users")
    suspend fun listUsers(): List<User>
}
