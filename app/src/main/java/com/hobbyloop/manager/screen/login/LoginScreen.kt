package com.hobbyloop.manager.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hobbyloop.manager.R
import com.hobbyloop.manager.ui.theme.Gray
import com.hobbyloop.manager.ui.theme.Green
import com.hobbyloop.manager.ui.theme.Orange
import com.hobbyloop.manager.ui.theme.Yellow
import com.hobbyloop.manager.util.toSp

@Composable
fun LoginScreen() {
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
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 232.dp)
                .padding(horizontal = 24.dp)
                .heightIn(min = 52.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Yellow
            )
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
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen()
}