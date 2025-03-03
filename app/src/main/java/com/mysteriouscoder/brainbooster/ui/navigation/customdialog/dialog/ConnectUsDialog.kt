package com.mysteriouscoder.brainbooster.ui.navigation.customdialog.dialog

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mysteriouscoder.brainbooster.R
import com.mysteriouscoder.brainbooster.ui.theme.InstagramPink
import com.mysteriouscoder.brainbooster.ui.theme.White
import com.mysteriouscoder.brainbooster.ui.theme.YoutubeRed

@Composable
fun ConnectUsDialog(
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val youtubeLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            // Handle the returned result here if needed
        }
    val instagramLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            // Handle the returned result here if needed
        }


    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = false
        )
    ) {
        Box {
            Card(
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(35.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(200.dp)
                    .padding(top = 10.dp)
                    .border(
                        3.dp, color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(35.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.scrim)
                        .padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(25.dp),
                ) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Connect with us on",
                        style = MaterialTheme.typography.headlineSmall,
                        fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        overflow = TextOverflow.Ellipsis,//It makes sure when the title would be not fit then it put ... at the end
                        maxLines = 1 // Limit the text to a single line
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(30.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                val instagramProfileUrl =
                                    "https://www.instagram.com/mysteriouscoder__?igsh=MTI5eGpxanE5ZGd5Mg=="
                                val uri = Uri.parse(instagramProfileUrl)
                                val instagramIntent = Intent(Intent.ACTION_VIEW, uri).apply {
                                    setPackage("com.instagram.android")
                                }
                                try {
                                    instagramLauncher.launch(instagramIntent)
                                } catch (e: ActivityNotFoundException) {
                                    // Instagram app isn't installed, open in web browser
                                    val instagramBrowserIntent = Intent(Intent.ACTION_VIEW, uri)
                                    instagramLauncher.launch(instagramBrowserIntent)
                                }
                            },
                            elevation = ButtonDefaults.elevatedButtonElevation(5.dp),//elevation give button a shadow
                            colors = ButtonDefaults.buttonColors(
                                containerColor = InstagramPink,
                                contentColor = White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(10.dp),
                            shape = CircleShape
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_instagram),
                                contentDescription = "instagram_link",
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Button(
                            onClick = {
                                val youtubeVideoUrl =
                                    "https://youtube.com/@Mysterious_Coder?si=ZkJkQ2kAMSv2Aqcg"
                                val uri = Uri.parse(youtubeVideoUrl)
                                val intent = Intent(Intent.ACTION_VIEW, uri)
                                val packageManager = context.packageManager
                                if (intent.resolveActivity(packageManager) != null) {
                                    youtubeLauncher.launch(intent)
                                } else {
                                    // If no app is available to handle the intent, open in a web browser
                                    val browserIntent = Intent(Intent.ACTION_VIEW, uri)
                                    youtubeLauncher.launch(browserIntent)
                                }
                            },
                            elevation = ButtonDefaults.elevatedButtonElevation(5.dp),//elevation give button a shadow
                            colors = ButtonDefaults.buttonColors(
                                containerColor = YoutubeRed,
                                contentColor = White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(10.dp),
                            shape = CircleShape
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_youtube),
                                contentDescription = "youtube_link",
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }
            }

//            Image(painter = painterResource(id = R.drawable.ic_close),
//                contentDescription = "Cancel Dialog",
//                modifier = Modifier
//                    .align(Alignment.TopEnd)
//                    .size(48.dp)
//                    .offset(12.dp)
//                    .offset(y= (-6).dp)
//                    .clickable {
//                        onDismiss()
//                    }
////                    .offset(2.dp)
//            )
            Box(
                modifier = Modifier
                    .size(48.dp) // Ensure the clickable area is 48dp x 48dp
                    .offset(12.dp, (-6).dp) // Apply the desired offset
                    .align(Alignment.TopEnd) // Align the Box to the top-end corner
                    .clickable {
                        onDismiss()
                    }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "Cancel Dialog",
                    modifier = Modifier.fillMaxSize() // Ensure the Image fills the entire Box
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ConnectUsDialogPrev() {
    ConnectUsDialog(
        onDismiss = {},
    )

}

//@Composable
//fun OpenYoutubePage() {
//    val context = LocalContext.current
//    val youtubeVideoUrl = "https://youtube.com/@Mysterious_Coder?si=ZkJkQ2kAMSv2Aqcg"
//    val uri = Uri.parse(youtubeVideoUrl)
//    val intent = remember { mutableStateOf(Intent(Intent.ACTION_VIEW, uri)) }
//
//    val launcher =
//        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
//            // Handle the returned result here if needed
//        }
//
//    Button(onClick = {
//        val packageManager = context.packageManager
//        if (intent.value.resolveActivity(packageManager) != null) {
//            launcher.launch(intent.value)
//        } else {
//            // If no app is available to handle the intent, open in a web browser
//            val browserIntent = Intent(Intent.ACTION_VIEW, uri)
//            launcher.launch(browserIntent)
//        }
//    }) {
//        Text("Open Youtube Page")
//    }
//}
//
//@Composable
//fun OpenInstagramPage() {
//    val context = LocalContext.current
//    val instagramProfileUrl =
//        "https://www.instagram.com/mysteriouscoder__?igsh=MTI5eGpxanE5ZGd5Mg=="
//    val uri = Uri.parse(instagramProfileUrl)
//    val instagramIntent = remember {
//        mutableStateOf(Intent(Intent.ACTION_VIEW, uri).apply {
//            setPackage("com.instagram.android")
//        })
//    }
//
//    val launcher =
//        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
//            // Handle the returned result here if needed
//        }
//
//    Button(onClick = {
//        try {
//            launcher.launch(instagramIntent.value)
//        } catch (e: ActivityNotFoundException) {
//            // Instagram app isn't installed, open in web browser
//            val instagramBrowserIntent = Intent(Intent.ACTION_VIEW, uri)
//            launcher.launch(instagramBrowserIntent)
//        }
//    }) {
//        Text("Open Instagram Page")
//    }
//}
//
//@Composable
//fun SendEmail() {
//    val context = LocalContext.current
//    val sendEmailIntent = remember {
//        mutableStateOf(Intent(Intent.ACTION_SENDTO).apply {
//            data = Uri.parse("mailto:mysteriouscoderr@gmail.com")
//            putExtra(Intent.EXTRA_SUBJECT, "Feedback")
//        })
//    }
//
//    val launcher =
//        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode != Activity.RESULT_OK) {
//                Toast.makeText(
//                    context,
//                    "No email client available. Please rate the app on the Play Store.",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//
//    Button(onClick = {
//        launcher.launch(sendEmailIntent.value)
//    }) {
//        Text(text = "Send Email")
//    }
//}