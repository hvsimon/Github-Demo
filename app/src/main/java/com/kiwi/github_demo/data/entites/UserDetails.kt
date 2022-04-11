package com.kiwi.github_demo.data.entites

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetails(
    @SerialName("login") val login: String,
    @SerialName("id") val id: Int,
    @SerialName("avatar_url") val avatarUrl: String,
    @SerialName("site_admin") val siteAdmin: Boolean,
    @SerialName("name") val name: String?,
    @SerialName("bio") val bio: String?,
    @SerialName("location") val location: String?,
    @SerialName("blog") val blog: String?,
)
