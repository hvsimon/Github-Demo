package com.kiwi.github_demo.userdetails

import com.kiwi.github_demo.BaseContract
import com.kiwi.github_demo.CoroutineContract
import com.kiwi.github_demo.data.entites.UserDetails

interface UserDetailsContract {

    interface View : BaseContract.View {
        fun showUserDetails(userDetails: UserDetails)
    }

    abstract class Presenter : CoroutineContract.Presenter<View>() {
        abstract fun loadUser(username: String)
    }
}
