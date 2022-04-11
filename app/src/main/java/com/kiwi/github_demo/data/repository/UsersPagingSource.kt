package com.kiwi.github_demo.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kiwi.github_demo.data.api.GithubApi
import com.kiwi.github_demo.data.entites.User
import com.kiwi.github_demo.utils.LinkHeaderParser.extractLinks
import com.kiwi.github_demo.utils.LinkHeaderParser.nextSinceValue

class UsersPagingSource(
    private val githubApi: GithubApi,
) : PagingSource<Int, User>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, User> {

        return try {
            val response = githubApi.listUsers(
                since = params.key ?: 0,
                perPage = params.loadSize,
            )

            // For example
            // link:  /api.github.com/users?since=135&per_page=100>; rel="next",  /api.github.com/users{?since}>; rel="first"
            val linkHeader = response.headers()["link"]!!
            val next = linkHeader.extractLinks().nextSinceValue()
            val data = response.body()!!

            LoadResult.Page(
                data = data,
                prevKey = null, // Only paging forward.
                nextKey = next
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? = null
}
