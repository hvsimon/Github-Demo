package com.kiwi.github_demo.data

import com.kiwi.github_demo.BuildConfig
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()

        builder.header("accept", "application/vnd.github.v3+json")
        if (BuildConfig.GITHUB_TOKEN.isNotEmpty()) {
            builder.header("Authorization", "Token ${BuildConfig.GITHUB_TOKEN}")
        }

        val request = builder.method(original.method, original.body).build()
        return chain.proceed(request)
    }
}
