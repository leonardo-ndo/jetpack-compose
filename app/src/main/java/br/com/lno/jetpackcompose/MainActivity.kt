package br.com.lno.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                HomeContent()
            }
        }
    }
}

@Composable
fun HomeContent() {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar {
                Text(text = stringResource(R.string.app_name))
            }
        },
        floatingActionButton = { FloatingActionButton(scaffoldState) },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Box(modifier = Modifier.padding(5.dp)) {
            Text(
                fontSize = 16.sp,
                text = stringResource(id = R.string.app_description)
            )
        }
    }
}

@Composable
fun FloatingActionButton(scaffoldState: ScaffoldState) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    return FloatingActionButton(
        onClick = {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = context.getString(R.string.fab_click_message),
                    actionLabel = context.getString(R.string.dismiss)
                )
            }
        }
    ) {
        Icon(Icons.Filled.Add, stringResource(id = R.string.add))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        HomeContent()
    }
}