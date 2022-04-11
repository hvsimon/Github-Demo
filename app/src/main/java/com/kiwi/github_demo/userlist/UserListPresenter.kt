package com.kiwi.github_demo.userlist

import com.kiwi.github_demo.data.repository.GithubRepository
import javax.inject.Inject

class UserListPresenter @Inject constructor(
    githubRepository: GithubRepository,
) {

    val dataFlow = githubRepository.listUserPagingFlow(30)
}
