package com.kiwi.github_demo.data.repository

import com.kiwi.data.di.IoDispatcher
import com.kiwi.github_demo.data.api.GithubApi
import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Reusable
class GithubRepository @Inject constructor(
    private val githubApi: GithubApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun listUsers() = runCatching {
        withContext(ioDispatcher) {
            githubApi.listUsers()
        }
    }
}
