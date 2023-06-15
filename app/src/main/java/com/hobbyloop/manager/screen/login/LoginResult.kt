package com.hobbyloop.manager.screen.login

/**
 *   @Author Wonseok Oh
 *   @Date 2023/06/15
 *   @Company SKB
 *   @Email ows3090@sk.com
 */
sealed class LoginResult<out T> {
    data class Success<T>(val data: T) : LoginResult<T>()
    data class Failure(val throwable: Throwable? = null) : LoginResult<Nothing>()
    object Cancel: LoginResult<Nothing>()
}
