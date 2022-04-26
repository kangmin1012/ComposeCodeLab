package com.example.composecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composecodelab.ui.theme.ComposeCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCodelabTheme {
                MyApp()
            }
        }
    }
}

@Composable
private fun MyApp() {
    /** hoisting target 값
     * by 키워드를 이용하면 .value를 사용하지 않아도 됨.
     */
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnBoardingScreen {
            shouldShowOnboarding = false
        }
    } else {
        Greetings()
    }
}

@Composable
private fun Greetings(names: List<String> = listOf("World", "Compose")) {
    Surface(color = Color.LightGray, modifier = Modifier.padding(vertical = 8.dp)) {
        Column() {
            names.forEach { name ->
                Greeting(name = name)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    // Composable 함수의 상태를 기억하고 있는 변수
    val expanded = remember { mutableStateOf(false) }

    // expanded의 상태에 따라 값이 바뀌는 변수
    val extraPadding = if (expanded.value) 48.dp else 0.dp

    // Surface의 color값에 따라 안에 있는 TextView의 적절한 색상을 자동으로 적용
    Surface(
        color = MaterialTheme.colors.primary,
        // Modifier를 통해 다양한 옵션을 줄 수 있다.
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
    ) {

        Row(modifier = Modifier.padding(24.dp)) {
            // Column을 통해 Vertical로 배치
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello,")
                Text(text = name)
            }

            OutlinedButton(
                onClick = { expanded.value = !expanded.value }) {
                Text(text = if (expanded.value) "Show Less" else "Show More")
            }
        }
    }
}

/**
 * State Hoisting 이란?
 * Stateful한 Composable을 Sateless하게 변환하는 프로세스
 * (UI 재사용 및 테스트 용이를 위함)
 *
 * 여러 함수에서 읽거나 수정하는 Composble의 상태에 대해서는 공통 상위 항목에 배치.
 * */

@Composable
fun OnBoardingScreen(clickEvent: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = { clickEvent() }
            ) {
                Text(text = "Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingPreview() {
    ComposeCodelabTheme {
        OnBoardingScreen {}
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeCodelabTheme {
        MyApp()
    }
}