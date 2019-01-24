package com.ven10.example.db.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.ven10.example.model.GitUser

class GitUserConverter{

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromStringToGitUser(json: String): GitUser {
            return Gson().fromJson(json, GitUser::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromGitUserToString(gitUser: GitUser): String {
            return Gson().toJson(gitUser)
        }
    }

}
