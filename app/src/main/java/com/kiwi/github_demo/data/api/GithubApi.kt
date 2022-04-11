package com.kiwi.github_demo.data.api

import com.kiwi.github_demo.data.entites.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubApi {

    @Headers("accept:application/vnd.github.v3+json")
    @GET("users")
    suspend fun listUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int,
    ): Response<List<User>>
}
