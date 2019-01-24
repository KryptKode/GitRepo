package com.ven10.example.model

import android.os.Parcel
import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "repos")
data class GitRepo(
    @PrimaryKey @field:SerializedName("id") val id: Long,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("full_name") val fullName: String?,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("html_url") val url: String?,
    @field:SerializedName("stargazers_count") val stars: Int,
    @field:SerializedName("forks_count") val forks: Int,
    @field:SerializedName("created_at") val dateCreated: String?,
    @field:SerializedName("updated_at") val dateUpdated: String?,
    @field:SerializedName("watchers_count") val watchers: Int,
    @field:SerializedName("language") val language: String?,
    @field:SerializedName("owner") val user: GitUser?
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readParcelable(GitUser::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(fullName)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeInt(stars)
        parcel.writeInt(forks)
        parcel.writeString(dateCreated)
        parcel.writeString(dateUpdated)
        parcel.writeInt(watchers)
        parcel.writeString(language)
        parcel.writeParcelable(user, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GitRepo> {
        override fun createFromParcel(parcel: Parcel): GitRepo {
            return GitRepo(parcel)
        }

        override fun newArray(size: Int): Array<GitRepo?> {
            return arrayOfNulls(size)
        }

        val ITEM_CALLBACK = object : DiffUtil.ItemCallback<GitRepo>() {
            override fun areItemsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean {
                return oldItem.id.equals(newItem.id)
            }

            override fun areContentsTheSame(oldItem: GitRepo, newItem: GitRepo): Boolean {
                return oldItem.equals(newItem)
            }

        }
    }


}
