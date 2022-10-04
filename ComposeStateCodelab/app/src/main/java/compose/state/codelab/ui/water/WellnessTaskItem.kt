package compose.state.codelab.ui.water

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import compose.state.codelab.ui.data.model.WellnessTask
import compose.state.codelab.ui.theme.ComposeStateCodelabTheme

/** Codelab 1 ~9 까지 사용 */
//@Composable
//fun WellnessTaskItem(
//    taskName: String,
//    onClose: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    Row(
//        modifier = modifier,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            modifier = Modifier
//                .weight(1f)
//                .padding(start = 16.dp),
//            text = taskName
//        )
//        IconButton(onClick = onClose) {
//            Icon(
//                Icons.Filled.Close,
//                contentDescription = "Close"
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewWellnessTaskItem() {
//    ComposeStateCodelabTheme {
//        WellnessTaskItem(taskName = "This is a task", onClose = { })
//    }
//}

/** Codelab 10에서 사용 */
@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = taskName,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )

        IconButton(onClick = onClose) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWellnessTaskItem() {
    ComposeStateCodelabTheme {
        WellnessTaskItem(
            taskName = "This is a task",
            checked = false,
            onCheckedChange = {},
            onClose = {}
        )
    }
}