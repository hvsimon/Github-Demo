package com.kiwi.github_demo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.kiwi.data.di.IoDispatcher
import com.kiwi.github_demo.data.api.GithubApi
import dagger.Reusable
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@Reusable
class GithubRepository @Inject constructor(
    private val githubApi: GithubApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    fun listUserPagingFlow(pageSize: Int) = Pager(
        PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false,
            initialLoadSize = pageSize,
        )
    ) {
        UsersPagingSource(githubApi)
    }.flow

    suspend fun getUserBy(username: String) = runCatching {
        withContext(ioDispatcher) {
            githubApi.getUserBy(username)
        }
    }
}
