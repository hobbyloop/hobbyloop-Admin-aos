package com.hobbyloop.manager.screen.login

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hobbyloop.manager.R
import com.hobbyloop.manager.screen.HobbyLoopDestination
import com.hobbyloop.manager.ui.theme.Gray
import com.hobbyloop.manager.ui.theme.Green
import com.hobbyloop.manager.ui.theme.Orange
import com.hobbyloop.manager.ui.theme.Yellow
import com.hobbyloop.manager.ui.theme.pretendard
import com.hobbyloop.manager.util.toSp
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

@Composable
fun LoginScreen(
    navController: NavController = rememberNavController(),
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    LaunchedEffect(loginViewModel){
        loginViewModel.loginResult.collectLatest {
            when(it){
                is LoginResult.Success -> {
                    navController.navigate(HobbyLoopDestination.Home.route)
                }
                is LoginResult.Failure -> {
                    // TODO Toast or Dialog
                }
                else -> {
                    // TODO Cancel Log
                }
            }
        }
    }

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 24.dp)
                .padding(start = 24.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Gray)
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    loginViewModel.sendLoginResult(fetchKaKaoLogin(context))
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 232.dp)
                .padding(horizontal = 24.dp)
                .heightIn(min = 52.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Yellow)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_kakao),
                    contentDescription = null
                )

                Text(
                    text = stringResource(id = R.string.login_kakao),
                    modifier = Modifier.padding(start = 6.24.dp),
                    color = Color.Black,
                    fontSize = 14.dp.toSp(),
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 24.dp)
                .heightIn(min = 52.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Gray
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = null
                )

                Text(
                    text = stringResource(id = R.string.login_google),
                    modifier = Modifier.padding(start = 11.44.dp),
                    color = Color.Black,
                    fontSize = 14.dp.toSp(),
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .padding(horizontal = 24.dp)
                .heightIn(min = 52.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_naver),
                    contentDescription = null
                )

                Text(
                    text = stringResource(id = R.string.login_kakao),
                    modifier = Modifier.padding(start = 6.96.dp),
                    color = Color.White,
                    fontSize = 14.dp.toSp(),
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Text(
            text = stringResource(id = R.string.register_center),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp),
            color = Orange,
            fontSize = 16.dp.toSp(),
            fontFamily = pretendard,
            fontWeight = FontWeight.Bold
        )
    }
}

private suspend fun fetchKaKaoLogin(context: Context) = suspendCancellableCoroutine { continuation ->
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            token?.let {
                continuation.resume(LoginResult.Success(it.accessToken))
            } ?: run {
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    continuation.resume(LoginResult.Cancel)
                }

                UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                    token?.let {
                        continuation.resume(LoginResult.Success(it.accessToken))
                    } ?: run {
                        continuation.resume(LoginResult.Failure(error))
                    }
                }
            }
        }
    } else {
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            token?.let {
                continuation.resume(LoginResult.Success(it.accessToken))
            } ?: run {
                continuation.resume(LoginResult.Failure(error))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}
