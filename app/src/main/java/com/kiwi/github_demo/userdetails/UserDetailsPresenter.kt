package com.kiwi.github_demo.userdetails

import com.kiwi.github_demo.data.repository.GithubRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class UserDetailsPresenter @AssistedInject constructor(
    @Assisted val view: UserDetailsContract.View,
    private val githubRepository: GithubRepository,
) : UserDetailsContract.Presenter() {

    override fun loadUser(username: String) {
        launch {
            githubRepository.getUserBy(username)
                .onSuccess { view.showUserDetails(it) }
                .onFailure { }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(view: UserDetailsContract.View): UserDetailsPresenter
    }
}
