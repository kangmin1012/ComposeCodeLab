package compose.state.codelab.ui.water

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import compose.state.codelab.ui.theme.ComposeStateCodelabTheme


@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
    Column(modifier = modifier) {
        StatefulCounter()
        val list = wellnessViewModel.tasks
        WellnessTaskList(
            list = list,
            onCloseTask = { task -> wellnessViewModel.remove(task) },
            onCheckedTask = { task, isChecked ->
                wellnessViewModel.changeTaskChecked(
                    task,
                    isChecked
                )
            }
        )
    }
}

/** 1 ~ 7 Step의 WaterCounter 코드랩 */
//@Composable
//fun WaterCounter(modifier: Modifier = Modifier) {
//    Column(modifier = modifier.padding(16.dp)) {
//        var count by remember { mutableStateOf(0) }
//
//        if (count > 0) {
//            var showTask by remember { mutableStateOf(true) }
//            if (showTask) {
//                WellnessTaskItem(
//                    taskName = "Have you taken your 15 minute walk today?",
//                    onClose = { showTask = false }
//                )
//            }
//            Text(text = "You've had $count glasses.")
//        }
//        Row(
//            modifier = Modifier.padding(top = 8.dp)
//        ) {
//            Button(
//                onClick = { count++ },
//                enabled = count < 10
//            ) {
//                Text(text = "Add one")
//            }
//
//            Button(
//                onClick = { count = 0 },
//                modifier = Modifier.padding(start = 8.dp)
//            ) {
//                Text(text = "Clear water count")
//            }
//        }
//    }
//}

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable { mutableStateOf(0) }
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text("Add one")
        }
    }
}

@Composable
fun StatelessCounter(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(
            onClick = onIncrement,
            modifier = Modifier.padding(top = 8.dp),
            enabled = count < 10
        ) {
            Text(text = "Add one")
        }
    }
}

@Composable
fun StatefulCounter(
    modifier: Modifier = Modifier
) {
    var count by rememberSaveable { mutableStateOf(0) }

    StatelessCounter(
        count = count,
        onIncrement = { count++ },
        modifier = modifier
    )
}

@Preview(name = "WaterCounter", showBackground = true)
@Composable
fun PreviewWaterCounter() {
    ComposeStateCodelabTheme {
        WaterCounter()
    }
}

@Preview(name = "WellnessScreen")
@Composable
fun PreviewWellnessScreen() {
    ComposeStateCodelabTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            WellnessScreen()
        }
    }
}