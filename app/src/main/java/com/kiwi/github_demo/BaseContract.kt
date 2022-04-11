package com.kiwi.github_demo

interface BaseContract {

    interface View

    interface Presenter<T : View> {
        fun stop()
    }
}
