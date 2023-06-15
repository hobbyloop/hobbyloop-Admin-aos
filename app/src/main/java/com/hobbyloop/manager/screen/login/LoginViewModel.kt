package com.hobbyloop.manager.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *   @Author Wonseok Oh
 *   @Date 2023/06/15
 *   @Company SKB
 *   @Email ows3090@sk.com
 */
@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel(){
    private val _loginResult = MutableSharedFlow<LoginResult<String>>()
    val loginResult = _loginResult.asSharedFlow()

    fun sendLoginResult(loginResult: LoginResult<String>){
        viewModelScope.launch {
            _loginResult.emit(loginResult)
        }
    }
}