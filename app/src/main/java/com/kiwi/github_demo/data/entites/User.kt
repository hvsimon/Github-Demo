package com.kiwi.github_demo.data.entites

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("login") val login: String,
    @SerialName("id") val id: String,
    @SerialName("avatar_url") val avatarUrl: String,
    @SerialName("site_admin") val siteAdmin: Boolean,
)
