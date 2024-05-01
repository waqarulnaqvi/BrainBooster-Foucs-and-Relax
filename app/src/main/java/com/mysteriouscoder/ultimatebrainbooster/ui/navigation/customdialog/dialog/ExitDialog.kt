package com.mysteriouscoder.ultimatebrainbooster.ui.navigation.customdialog.dialog
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mysteriouscoder.ultimatebrainbooster.R
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.DialogGreen
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.DialogRed
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.Purple80
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.White


@Composable
fun ExitDialog(
    onDismiss: () -> Unit,

)
    {
        val context = LocalContext.current
        val activity = context as? ComponentActivity

        Dialog(
            onDismissRequest = { onDismiss() },
            properties = DialogProperties(
                dismissOnBackPress = true,
                usePlatformDefaultWidth = false,
                dismissOnClickOutside = false
            )
        ) {
            Box()
            {

                Card(
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(35.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(200.dp)
                        .padding(top = 10.dp)
                        .border(
                            3.dp, color = Purple80, shape = RoundedCornerShape(35.dp)
                        )
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(modifier =Modifier.height(10.dp))
                        Text(
                            text = "Are you sure you want to exit?",
                            fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
                            style = MaterialTheme.typography.headlineSmall,
                            textAlign = TextAlign.Center,
                            modifier=Modifier.fillMaxWidth()
                        )



                        Spacer(modifier = Modifier.weight(1f))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(30.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(
                                onClick = {
                                    activity?.finishAffinity()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = DialogRed,
                                    contentColor = White
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(10.dp),
                                shape = CircleShape
                            ) {
                                Text(
                                    text = "Yes",
                                    style = MaterialTheme.typography.headlineSmall,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                )
                            }

                            Button(
                                onClick = {
                                    onDismiss()
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = DialogGreen,
                                    contentColor = White
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(10.dp),
                                shape = CircleShape
                            ) {
                                Text(
                                    text = "No",
                                    style = MaterialTheme.typography.headlineSmall,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }

                        }
                    }

                }

                Image(painter = painterResource(id = R.drawable.close),
                    contentDescription = "Cancel Dialog",
//                    tint = Color.Red,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable {
                            onDismiss()
                        }
//                        .offset(2.dp)
                        .size(28.dp)

                )
            }
        }


    }

@Preview(showSystemUi = true)
@Composable
private fun ExitDialogPrev() {
    ExitDialog(
        onDismiss = {},

    )

}