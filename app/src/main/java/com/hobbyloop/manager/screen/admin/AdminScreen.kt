package com.hobbyloop.manager.screen

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.*
import com.hobbyloop.manager.util.Constants.BASE_URL

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun AdminScreen(
    modifier: Modifier = Modifier
) {
    val webViewState = rememberWebViewState(
        url = BASE_URL
    )

    val webViewClient = AccompanistWebViewClient()
    val webChromeClient = AccompanistWebChromeClient()
    val webViewNavigator = rememberWebViewNavigator()

    WebView(
        state = webViewState,
        modifier = modifier,
        navigator = webViewNavigator,
        client = webViewClient,
        chromeClient = webChromeClient,
        onCreated = { webView ->
            with(webView) {
                settings.run {
                    javaScriptEnabled = true // JavaScript 허용
                    domStorageEnabled = true // Local Storage 허용
                    javaScriptCanOpenWindowsAutomatically = true // JavaScript로 새창 실행 허용
                }
            }
        }
    )

    BackHandler {
        if (webViewNavigator.canGoBack) {
            webViewNavigator.navigateBack()
        }
    }
}
