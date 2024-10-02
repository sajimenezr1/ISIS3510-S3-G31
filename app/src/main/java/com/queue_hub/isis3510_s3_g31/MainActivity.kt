package com.queue_hub.isis3510_s3_g31

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.queue_hub.isis3510_s3_g31.navigation.AppNavigation
import com.queue_hub.isis3510_s3_g31.ui.theme.ISIS3510S3G31Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ISIS3510S3G31Theme {
                AppNavigation()
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ISIS3510S3G31Theme {

    }
}