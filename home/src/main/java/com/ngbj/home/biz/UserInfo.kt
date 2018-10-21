package com.ngbj.home.biz

import java.util.*

/**
 * Created by zack on 2018/10/17
 */
data class UserInfo(val id: String,
                    val password: String,
                    val user_name: String,
                    val user_age: Int,
                    val user_sex: Int,
                    val user_avatar: String,
                    val phone_number:String,
                    val user_token: String,
                    val auth_type: Array<Int>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserInfo

        if (id != other.id) return false
        if (password != other.password) return false
        if (user_name != other.user_name) return false
        if (user_age != other.user_age) return false
        if (user_sex != other.user_sex) return false
        if (user_avatar != other.user_avatar) return false
        if (phone_number != other.phone_number) return false
        if (user_token != other.user_token) return false
        if (!Arrays.equals(auth_type, other.auth_type)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + user_name.hashCode()
        result = 31 * result + user_age
        result = 31 * result + user_sex
        result = 31 * result + user_avatar.hashCode()
        result = 31 * result + phone_number.hashCode()
        result = 31 * result + user_token.hashCode()
        result = 31 * result + Arrays.hashCode(auth_type)
        return result
    }
}