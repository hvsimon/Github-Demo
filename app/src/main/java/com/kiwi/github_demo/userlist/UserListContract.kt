package com.kiwi.github_demo.userlist

import androidx.paging.PagingData
import com.kiwi.github_demo.BaseContract
import com.kiwi.github_demo.CoroutineContract
import com.kiwi.github_demo.data.entites.User

interface UserListContract {

    interface View : BaseContract.View {
        fun addData(data: PagingData<User>)
    }

    abstract class Presenter : CoroutineContract.Presenter<View>()
}
