package com.ven10.example.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class GitUser(
        @field:SerializedName("login") val username: String,
        @field:SerializedName("avatar_url") val avatarUrl: String,
        @field:SerializedName("type") val type: String,
        @field:SerializedName("html_url") val url: String
        ):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(avatarUrl)
        parcel.writeString(type)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GitUser> {
        override fun createFromParcel(parcel: Parcel): GitUser {
            return GitUser(parcel)
        }

        override fun newArray(size: Int): Array<GitUser?> {
            return arrayOfNulls(size)
        }
    }
}