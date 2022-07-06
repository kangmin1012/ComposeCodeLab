package com.example.composelayoutlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.composelayoutlab.ui.theme.ComposeLayoutLabTheme

class ConstraintSampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLayoutLabTheme {
            }
        }
    }
}

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        val (button, text) = createRefs() // ConstraintLayout에서 사용하기 위한 참조

        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button) { // 위에서 선언한 button 참조와 제약
                top.linkTo(parent.top, margin = 16.dp) // top_top = parent
            }
        ) {
            Text("Button")
        }

        Text("Text", Modifier.constrainAs(text) { // 위에서 선언한 text 참조와 제약
            top.linkTo(button.bottom, margin = 16.dp) // top_bottom = button
            centerHorizontallyTo(parent) // start_start = parent, end_end = parent
        })
    }
}

@Composable
fun ConstraintLayoutContent2() {
    ConstraintLayout {
        val (button1, button2, text) = createRefs()

        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button 1")
        }

        Text("Text", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, margin = 16.dp)
            centerAround(button1.end) // button1의 끝쪽을 기준으로 정가운데 위치
        })

        val barrier = createEndBarrier(button1, text)

        Button(
            onClick = { },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text(text = "Button2")
        }
    }
}

@Composable
fun LargeConstraintLayout() {
    ConstraintLayout {
        val text = createRef()

        val guideline = createGuidelineFromStart(fraction = 0.5f)

        Text(
            text = "This is a very very  very very very very very very long long long long text", // 매우 긴 텍스트는 화면 밖으로 나갈 수 있다.
            modifier = Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end) // horizontal or vertical로 사용
                width = Dimension.preferredWrapContent // 할당된 공간에서 알아서 줄바꿈 처리가능하도록 설정
            }
        )
    }
}

@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraints(16.dp) // 디바이스 세로 모드
        } else {
            decoupledConstraints(32.dp) // 디바이스 가로 모드
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { },
                modifier = Modifier.layoutId("button")
            ) {
                Text(text = "Button")
            }

            Text(text = "Text", modifier = Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }

        constrain(text) {
            top.linkTo(button.bottom, margin = margin)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConstraintLayoutContent() {
    ComposeLayoutLabTheme {
        ConstraintLayoutContent()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConstraintLayoutContent2() {
    ComposeLayoutLabTheme {
        ConstraintLayoutContent2()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLargeConstraintLayoutContent() {
    ComposeLayoutLabTheme {
        LargeConstraintLayout()
    }
}

@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p, widthDp = 360, heightDp = 720)
@Composable
fun PreviewDecoupledConstraintLayout() {
    ComposeLayoutLabTheme {
        DecoupledConstraintLayout()
    }
}