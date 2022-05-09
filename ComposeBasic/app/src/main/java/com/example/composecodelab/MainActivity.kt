package com.example.composecodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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
     * rememberSaveable을 통해 화면 회전, 프로세스 중단 등 다양한 상황에서도 상태 저장
     */
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnBoardingScreen {
            shouldShowOnboarding = false
        }
    } else {
        Greetings()
    }
}

@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
    // LazyColumn, LazyRow = RecyclerView와 동일
    /**
     * Lazy 레이아웃은 RecyclerView와 다르게 재활용하지 않음.
     * 컴포저블을 방출하는게 Views를 인스턴스화 하는 것 보다 상대적으로 비용이 적게 들기 때문.
     * 스크롤해서 새로운 UI가 보이게 되면 새 컴포저블을 방출하고 성능 유지
     * */
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        /**
         * names.forEach { name -> ...}
         */
        items(items = names) { name ->
            Greeting(name = name)
        }
    }

}

@Composable
fun Greeting(name: String) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name = name)
    }
}

/**
 * 카드 스타일을 위한 Composable
 */
@Composable
fun CardContent(name: String) {
    // Composable 함수의 상태를 기억하고 있는 변수
    var expanded by remember { mutableStateOf(false) }

    // Row에 'animateContentSize'를 적용함으로서 기존에 사용하던 extraPadding 및 coerceAtLeast 제거
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        // Column을 통해 Vertical로 배치
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello,")
            Text(
                text = name,
                style = MaterialTheme.typography.h4
                    .copy(
                        fontWeight = FontWeight.ExtraBold
                    )
            ) // style 적용 - copy()를 이용하여 좀 더 굵은 폰트로 설정

            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy.").repeat(4)
                )
            }
        }

        // OutlinedButton -> Material IconButton으로 교체
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded)
                    stringResource(id = R.string.show_less)
                else
                    stringResource(id = R.string.show_more)
            )
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

@Preview(showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    ComposeCodelabTheme {
        Greetings()
    }
}