package com.mysteriouscoder.ultimatebrainbooster

import android.media.MediaPlayer
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.LightBlue
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.LightGray
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.LightGreen
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.LightOrange
import com.mysteriouscoder.ultimatebrainbooster.ui.theme.LightPurple


@Composable
fun OnImageCard(

    title: String = "3km Running",
    time: String = "10 min",
    image: Int = R.drawable.relaxationandstressrelief,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .width(260.dp)
            .height(150.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)


    )
    {

        val context = LocalContext.current
        Box(modifier = Modifier
            .height(200.dp)
            .clickable {
            }
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = modifier.size(400.dp),
                contentScale = ContentScale.Crop,
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 200f
                        )
                    )
            )

            Column {

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp)
                        .padding(bottom = 10.dp)
                        .width(220.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center

                ) {


                    Text(
                        text = title.uppercase(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
                    )
                    Spacer(modifier = Modifier.weight(1f))


                    Text(
                        text = time,
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
                    )

                    Spacer(modifier = Modifier.width(20.dp))



                }
            }
        }
    }
}


@Composable
fun Heading(
    title: String = "Binaural Beats FAQ",
    modifier: Modifier = Modifier
) {
    Text(text = title,
        fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
        fontSize=22.sp,
        modifier = modifier.padding(start=20.dp)
    )
    
}



@Preview(showSystemUi = true)
@Composable
fun FAQComponent(
     question: String = "What is Binaural Beats?",
    answer: String = "Binaural beats are a form of soundwave therapy. The idea is that when exposed to two different frequencies at the same time through stereo headphones, the brain perceives a beat at the frequency equal to the difference between the two frequencies.",
      color:Color= LightPurple,
     modifier: Modifier= Modifier
     ) {
//    val taskColor= listOf(
//        LightPurple,
////        LightBlue,
////        LightGreen,
//        LightOrange
//    ).random()

    Row(
        modifier= modifier.fillMaxWidth().padding(start = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(
                modifier = Modifier
                    .size(16.dp)
//                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(
                            3.dp,
                            Color.Black
                        ),
                        shape = CircleShape
                    )

            )

            Divider(
                modifier = Modifier.width(6.dp),
                color = Color.Black
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(color)
                        .weight(0.9f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)

                ) {
                    Text(
                        modifier = Modifier
                            .padding(
                                top = 12.dp,
                                start = 12.dp
                            ),
                        text = "Q: $question",
                        fontFamily = FontFamily(Font(R.font.nunito_bold))
                    )


                    Text(
                        modifier = Modifier
                            .padding(
                                start = 12.dp
                            ),
                        color = Color.Gray,
                        text = "Ans: $answer",
                        fontFamily = FontFamily(Font(R.font.nunito_bold))
                    )
                    Spacer(modifier =Modifier.height(8.dp))
                }


                Divider(
                    modifier = Modifier
                        .width(6.dp)
                        .weight(0.1f),
                    color = Color.Black
                )
            }

        }

        }
    }
