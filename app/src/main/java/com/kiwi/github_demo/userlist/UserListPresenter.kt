package com.kiwi.github_demo.userlist

import com.kiwi.github_demo.data.repository.GithubRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserListPresenter @AssistedInject constructor(
    @Assisted val view: UserListContract.View,
    githubRepository: GithubRepository,
) : UserListContract.Presenter() {

    private val dataFlow = githubRepository.listUserPagingFlow(30)

    init {
        launch {
            dataFlow.collectLatest { view.addData(it) }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(view: UserListContract.View): UserListPresenter
    }
}
