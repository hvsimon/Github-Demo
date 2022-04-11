package com.kiwi.github_demo

import androidx.annotation.CallSuper
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

interface CoroutineContract {

    abstract class Presenter<T : BaseContract.View> : BaseContract.Presenter<T>, CoroutineScope {

        override val coroutineContext: CoroutineContext =
            SupervisorJob() + Dispatchers.Main.immediate

        @CallSuper
        override fun stop() {
            coroutineContext.cancel()
        }
    }
}
