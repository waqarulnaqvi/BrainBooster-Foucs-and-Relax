package com.mysteriouscoder.physicalandmentalhealth.ui.Screens.Onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.mysteriouscoder.physicalandmentalhealth.R
import com.mysteriouscoder.physicalandmentalhealth.ui.Screens.Dimens.MediumPadding1
import com.mysteriouscoder.physicalandmentalhealth.ui.Screens.Dimens.MediumPadding2
import com.mysteriouscoder.physicalandmentalhealth.ui.Screens.OnBoarding.Page
import com.mysteriouscoder.physicalandmentalhealth.ui.Screens.OnBoarding.pages
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.PhysicalAndMentalHealthTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier=Modifier,
    page: Page =pages[0]
)
{
 Column(
     modifier=modifier
 ) {
   Image(modifier= Modifier
       .fillMaxWidth()
       .fillMaxHeight(fraction = 0.6f),
       painter = painterResource(id = page.image)
       , contentDescription = null,
       contentScale = ContentScale.Crop
   )
    Spacer(modifier = Modifier.height(MediumPadding1))
     Text(text = page.title,
         modifier = Modifier
         .padding(horizontal = MediumPadding2),
         style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
         color = colorResource(id = R.color.display_small)
     )
    Spacer(modifier = Modifier.height(10.dp))
     Text(text = page.description,
         modifier = Modifier
             .padding(horizontal = MediumPadding2),
         style = MaterialTheme.typography.bodyMedium,
         color = colorResource(id = R.color.text_medium))


 }
}

@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true)
@Composable
fun OnBoardingPagePreview()
{
 PhysicalAndMentalHealthTheme {
     OnBoardingPage( )
 }
}