package com.example.composelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelayout.ui.theme.ComposeLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLayoutTheme {
            }
        }
    }
}

@Composable
fun LayoutsCodeLab() {
    /**
     * Scaffold - 최상위 머티리얼 구성요소 컴포저블
     * Scaffold를 이용하여 기본 머티리얼 디자인 레이아웃 구조로 UI를 구현할 수 있다.
     * */
    Scaffold(
        topBar = {
            /**
            Appbar 부분에 @Composable () -> Unit 유형 슬롯이 존재한다.
            즉, Appbar 부분을 내가 원하는 형태의 컴포저블로 꾸밀 수 있다는 뜻*/

            TopAppBar(/** 보통 TopAppBar와 함께 제공. */
                title = {
                    /** @Composable() -> Unit 유형의 람다 슬롯 */
                    Text(text = "LayoutCodelab")
                },
                actions = {
                    /** 우측 상단 앱바 부분의 아이콘 설정 영역 */
                    IconButton(
                        onClick = {
                            // todo : doSomething
                        }
                    ) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)

                        /**
                         * 머티리얼 아이콘 목록
                         * https://fonts.google.com/icons?selected=Material+Icons
                         *
                         * Dependency
                         * implementation "androidx.compose.material:material-icons-extended:$compose_version"
                         * */
                    }
                }
            )
        },
        bottomBar = {
            /**
             * Scaffold를 통한 BottomBar 구현
             * */
            BottomNavigation() {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(Icons.Filled.Settings, contentDescription = null)
                        Text(text = "sample1")
                    }
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.Search, contentDescription = null)
                }
            }
        }
    ) { innerPadding ->
        /** 쉬운 재사용 및 테스트 가능 코드를 위해 작은 단위로 구조화 */
        BodyContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(8.dp) // 상위 레이아웃과의 패딩값
            .clip(RoundedCornerShape(4.dp)) // 모서리 둥글게
            .background(MaterialTheme.colors.surface)
            .clickable {
                // todo :  Ignoring onClick
            }
            .padding(16.dp) // Row 안의 패딩값
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            // 프로필 이미지가 보여질 자리
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = "Alfred Sisley", fontWeight = FontWeight.Bold)

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview
@Composable
fun LayoutsCodeLabPreview() {
    ComposeLayoutTheme {
        LayoutsCodeLab()
    }
}

@Preview()
@Composable
fun PhotographerPreview() {
    ComposeLayoutTheme {
        PhotographerCard()
    }
}
