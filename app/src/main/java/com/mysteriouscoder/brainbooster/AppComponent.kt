package com.mysteriouscoder.brainbooster

import android.os.Build
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.ColorUtils
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.mysteriouscoder.brainbooster.ui.theme.LightPurple
import com.mysteriouscoder.brainbooster.ui.theme.WhiteGray
import kotlinx.coroutines.delay


@Composable
fun OnImageCard(
    modifier: Modifier = Modifier,
    title: String = "3km Running",
    time: String = "10 min",
    image: Int = R.drawable.ic_deepsleep,
    idx: Int = 0,
    onClick: (idx: Int) -> Unit = {}
) {
    var isClicked by remember { mutableStateOf(false) }

    // Animate the rotation based on the click state
    val cardRotation by animateFloatAsState(
        targetValue = if (isClicked) -5f else 0f,
        label = "Rotation Animation"
    )

    // Reset the rotation to 0f after 1 second
    LaunchedEffect(isClicked) {
        if (isClicked) {
            delay(100L)
            isClicked = false
        }
    }

    Card(
//        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxWidth()
//            .width(260.dp)
            .height(150.dp)
            .padding(10.dp)
            .rotate(cardRotation)
            .clip(RoundedCornerShape(15.dp))// Apply the rotation animation
            .clickable {
                isClicked = true
                onClick(idx)
            },
        elevation = CardDefaults.cardElevation(5.dp)
    )
    {

        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = modifier.fillMaxWidth(),
//                modifier = modifier.size(400.dp),
                contentScale = ContentScale.Crop,

                )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.5f)
                            ),
                            startY = 100f
                        )
                    )
            )

            Column {

                Spacer(modifier = Modifier.weight(1f))


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp)
                        .padding(bottom = 10.dp)
//                        .width(220.dp)
                    ,
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start

                ) {


                    Text(
                        text = title.uppercase(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 6.dp),
                        fontSize = 14.sp,
//                        color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                        color = Color.White,
//                        color =MaterialTheme.colorScheme.onSecondary,
                        fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
                        overflow = TextOverflow.Ellipsis,//It makes sure when the title would be not fit then it put ... at the end
                        maxLines = 1 // Limit the text to a single line
                    )


                    Text(
                        text = "Duration: $time",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 6.dp),
//                        color = if (isSystemInDarkTheme()) Color.Black else Color.White,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
                        overflow = TextOverflow.Ellipsis,//It makes sure when the title would be not fit then it put ... at the end
                        maxLines = 1 // Limit the text to a single line
                    )

                }
            }
        }
    }
}


@Composable
fun Heading(
    modifier: Modifier = Modifier,
    title: String = "Binaural Beats FAQ",
    fontSize: Int = 22,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    Text(
        text = title,
        fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
        fontSize = fontSize.sp,
        modifier = modifier
            .then(modifier)
            .padding(start = 10.dp),
        textAlign = textAlign,
        color = color,
        overflow = TextOverflow.Ellipsis,//It makes sure when the title would be not fit then it put ... at the end
        maxLines = 1 // Limit the text to a single line
    )

}


@Composable
fun FAQComponent(
    modifier: Modifier = Modifier,
    question: String = "What is Binaural Beats?",
    answer: String = "Binaural beats are a form of soundwave therapy. The idea is that when exposed to two different frequencies at the same time through stereo headphones, the brain perceives a beat at the frequency equal to the difference between the two frequencies.",
    color: Color = LightPurple,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 20.dp,
) {
//    val taskColor= listOf(
//        LightPurple,
////        LightBlue,
////        LightGreen,
//        LightOrange
//    ).random()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp),
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
                            MaterialTheme.colorScheme.onSurface
                        ),
                        shape = CircleShape
                    )

            )

            HorizontalDivider(
                modifier = Modifier.width(6.dp),
                color = MaterialTheme.colorScheme.onSurface
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .weight(0.9f)
                )
                {

                    //     Canvas(modifier = Modifier.matchParentSize()) {
                    //                        val clipPath = Path().apply {
                    //                            val cutSize = cutCornerSize.toPx() // Common cut size for all corners
                    //
                    //                            // Top-left corner
                    //                            moveTo(cutSize, 0f)
                    //                            // Top-right corner
                    //                            lineTo(size.width - cutSize, 0f)
                    //                            lineTo(size.width, cutSize)
                    //                            // Bottom-right corner
                    //                            lineTo(size.width, size.height - cutSize)
                    //                            lineTo(size.width - cutSize, size.height)
                    //                            // Bottom-left corner
                    //                            lineTo(cutSize, size.height)
                    //                            lineTo(0f, size.height - cutSize)
                    //                            // Close the path to complete the shape
                    //                            lineTo(0f, cutSize)
                    //                            close()
                    //                        }
                    //
                    //
                    //                        // Clipping the path for the cut corners
                    //                        clipPath(clipPath) {
                    //                            // Draw background rectangle
                    //                            drawRoundRect(
                    //                                color = color,
                    //                                size = size,
                    //                                cornerRadius = CornerRadius(cornerRadius.toPx())
                    //                            )
                    //
                    //                            // Apply the gradient-like effect to each corner
                    //
                    //                            // Top-left corner
                    //                            drawRoundRect(
                    //                                color = Color(
                    //                                    ColorUtils.blendARGB(color.toArgb(), 0x000000, 0.2f)
                    //                                ),
                    //                                topLeft = Offset(-100f, -100f),
                    //                                size = androidx.compose.ui.geometry.Size(
                    //                                    cutCornerSize.toPx() + 100f,
                    //                                    cutCornerSize.toPx() + 100f
                    //                                ),
                    //                                cornerRadius = CornerRadius(cornerRadius.toPx())
                    //                            )
                    //
                    ////                             Top-right corner
                    //                            drawRoundRect(
                    //                                color = Color(
                    //                                    ColorUtils.blendARGB(color.toArgb(), 0x000000, 0.2f)
                    //                                ),
                    //                                topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                    //                                size = androidx.compose.ui.geometry.Size(
                    //                                    cutCornerSize.toPx() + 100f,
                    //                                    cutCornerSize.toPx() + 100f
                    //                                ),
                    //                                cornerRadius = CornerRadius(cornerRadius.toPx())
                    //                            )
                    //
                    //                            // Bottom-right corner
                    //                            drawRoundRect(
                    //                                color = Color(
                    //                                    ColorUtils.blendARGB(color.toArgb(), 0x000000, 0.2f)
                    //                                ),
                    //                                topLeft = Offset(size.width - cutCornerSize.toPx(), size.height - cutCornerSize.toPx()),
                    //                                size = androidx.compose.ui.geometry.Size(
                    //                                    cutCornerSize.toPx() + 100f,
                    //                                    cutCornerSize.toPx() + 100f
                    //                                ),
                    //                                cornerRadius = CornerRadius(cornerRadius.toPx())
                    //                            )
                    //
                    //                            // Bottom-left corner
                    //                            drawRoundRect(
                    //                                color = Color(
                    //                                    ColorUtils.blendARGB(color.toArgb(), 0x000000, 0.2f)
                    //                                ),
                    //                                topLeft = Offset(-100f, size.height - cutCornerSize.toPx()),
                    //                                size = androidx.compose.ui.geometry.Size(
                    //                                    cutCornerSize.toPx() + 100f,
                    //                                    cutCornerSize.toPx() + 100f
                    //                                ),
                    //                                cornerRadius = CornerRadius(cornerRadius.toPx())
                    //                            )
                    //                        }
                    //                    }

                    Canvas(modifier = Modifier.matchParentSize()) {
                        val clipPath = Path().apply {
                            lineTo(size.width - cutCornerSize.toPx(), 0f)
                            lineTo(size.width, cutCornerSize.toPx())
                            lineTo(size.width, size.height)
                            lineTo(0f, size.height)
                            close()
                        }

                        clipPath(clipPath) {
                            drawRoundRect(
                                color = color,
                                size = size,
                                cornerRadius = CornerRadius(cornerRadius.toPx())
                            )
                            drawRoundRect(
                                color = Color(
                                    ColorUtils.blendARGB(color.toArgb(), 0x000000, 0.2f)
                                ),
                                topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                                size = androidx.compose.ui.geometry.Size(
                                    cutCornerSize.toPx() + 100f,
                                    cutCornerSize.toPx() + 100f
                                ),
                                cornerRadius = CornerRadius(cornerRadius.toPx())
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(end = 22.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)

                    ) {
                        Text(
                            modifier = Modifier
                                .padding(
                                    top = 12.dp,
                                    start = 12.dp
                                ),
                            text = "Q: $question",
                            fontFamily = FontFamily(
                                Font(R.font.nunito_bold)
                            ),
                            color = Color.Black,
//                            color=MaterialTheme.colorScheme.primary
                        )



                        Text(
                            modifier = Modifier
                                .padding(
                                    start = 12.dp
                                ),
                            color = Color.Black.copy(alpha = 0.6f),
                            text = "Ans: $answer",
//                            fontStyle = FontStyle.Italic,
                            fontFamily = FontFamily(
                                Font(R.font.nunito_bold)
                            ),
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                }
                HorizontalDivider(
                    modifier = Modifier
                        .width(6.dp)
                        .weight(0.1f),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }

    }
}

@Composable
fun NewsButton(
    text: String = "Track Progress",
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        elevation = ButtonDefaults.elevatedButtonElevation(5.dp),//elevation give button a shadow
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = MaterialTheme.shapes.extraLarge
//        shape = RoundedCornerShape(size = 6.dp)
    )
    {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.SemiBold,

                ),
            overflow = TextOverflow.Ellipsis,//It makes sure when the title would be not fit then it put ... at the end
            maxLines = 1 // Limit the text to a single line
        )
    }

}

@Composable
fun NewsTextButton(
    text: String = "Hello",
    onClick: () -> Unit
) {
    TextButton(onClick = onClick)
    {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = WhiteGray
        )
    }

}

@Composable
fun TotalTimeReport(
    heading: String = "Soothing Music",
    totaltime: Int = 0

) {
    val seconds by rememberSaveable {
        mutableIntStateOf(totaltime % 60)
    }
    val minutes by rememberSaveable {
        mutableIntStateOf(totaltime / 60 % 60)
    }
    val hour by rememberSaveable {
        mutableIntStateOf(minutes / 60)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    )
    {

        Box(
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(10.dp)

        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = heading,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 6.dp),
//                        color = Color.White
                        color = MaterialTheme.colorScheme.background,
                        overflow = TextOverflow.Ellipsis,//It makes sure when the title would be not fit then it put ... at the end
                        maxLines = 1 // Limit the text to a single line,
                    )
                    Text(
                        text = "⏰",
                        style = MaterialTheme.typography.headlineSmall,
                    )
//                    GIFLoading()
                }

                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {


                    GIFLoading(
                        gif = R.drawable.ic_clockgif,
                        modifier = Modifier
                            .height(75.dp)
                            .width(100.dp)
                            .clip(CircleShape)
//                            .border(2.dp, Color.White, CircleShape)
                            .border(2.dp, MaterialTheme.colorScheme.background, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 16.sp,
//                                        color = Color.White
                                        color = MaterialTheme.colorScheme.background
                                    )
                                ) {
                                    append("$hour ")
                                }
                                append("h")

                            },

                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
//                            color = Color.White,
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis,//It makes sure when the title would be not fit then it put ... at the end
                            maxLines = 1 // Limit the text to a single line
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 16.sp,
//                                        color = Color.White
                                        color = MaterialTheme.colorScheme.background
                                    )
                                ) {
                                    append("  $minutes ")
                                }
                                append("m")

                            },
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
//                            color = Color.White,
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis,//It makes sure when the title would be not fit then it put ... at the end
                            maxLines = 1 // Limit the text to a single line
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 16.sp,
//                                        color = Color.White
                                        color = MaterialTheme.colorScheme.background
                                    )
                                ) {
                                    append("  $seconds ")
                                }
                                append("s")
                            },
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
//                            color = Color.White,
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis,//It makes sure when the title would be not fit then it put ... at the end
                            maxLines = 1 // Limit the text to a single line
                        )

                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }

}

@Composable
fun GIFLoading(
    modifier: Modifier = Modifier,
    gif: Int = R.drawable.ic_musicgif,
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = gif)
            .apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
    )
    Image(
        painter = painter,
        contentDescription = "GIF Loading...",
        modifier = modifier
            .width(50.dp)
            .height(50.dp),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun BgWhiteMoreAppIcons(
    modifier: Modifier = Modifier,
    image: Int = R.drawable.ic_winged_bird_icon,
    border: Boolean = true
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "GIF Loading...",
        modifier = modifier
            .width(50.dp)
            .height(50.dp)
            .clip(CircleShape)
            .border(
                0.5.dp,
                if (border) if (isSystemInDarkTheme()) Color.Gray else Color.Black else Color.Transparent,
                CircleShape
            ),
        contentScale = ContentScale.FillBounds
    )
}


@Composable
fun MoreAppIcons(
    image: Int,
    modifier: Modifier = Modifier,
) {

    Image(
        painter = painterResource(id = image),
        contentDescription = "MoreAppIcons...",
        modifier = modifier
            .width(56.dp)
            .height(56.dp),
        contentScale = ContentScale.FillBounds
    )
}

//@Composable
//fun FinishAffinity() {
//    val context = LocalContext.current
//    val activity = remember(context) { context as? ComponentActivity }
//
//    activity?.finishAffinity()
//
//}