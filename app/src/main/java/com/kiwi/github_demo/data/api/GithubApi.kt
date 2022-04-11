package com.kiwi.github_demo.data.api

import com.kiwi.github_demo.data.entites.User
import com.kiwi.github_demo.data.entites.UserDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("users")
    suspend fun listUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int,
    ): Response<List<User>>

    @GET("users/{username}")
    suspend fun getUserBy(
        @Path("username") username: String,
    ): UserDetails
}
