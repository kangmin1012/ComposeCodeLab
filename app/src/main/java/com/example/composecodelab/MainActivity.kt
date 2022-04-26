package com.example.composecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
private fun MyApp(names: List<String> = listOf("World", "Compose")) {
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
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)) {

        Row(modifier = Modifier.padding(24.dp)) {
            // Column을 통해 Vertical로 배치
            Column(
                modifier = Modifier.weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello,")
                Text(text = name)
            }

            OutlinedButton(
                onClick = { expanded.value = !expanded.value }) {
                Text(text = if (expanded.value)"Show Less" else "Show More")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    ComposeCodelabTheme {
        MyApp()
    }
}