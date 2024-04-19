package com.mysteriouscoder.physicalandmentalhealth.ui.Screens.Onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mysteriouscoder.physicalandmentalhealth.ui.navigation.Bottom_Navigation
import com.mysteriouscoder.physicalandmentalhealth.ui.Screens.Dimens.MediumPadding2
import com.mysteriouscoder.physicalandmentalhealth.ui.Screens.NewsButton
import com.mysteriouscoder.physicalandmentalhealth.ui.Screens.NewsTextButton
import com.mysteriouscoder.physicalandmentalhealth.ui.Screens.OnBoarding.pages
import com.mysteriouscoder.physicalandmentalhealth.ui.Screens.Onboarding.components.OnBoardingEvent
import com.mysteriouscoder.physicalandmentalhealth.ui.Screens.Onboarding.components.OnBoardingPage
import com.mysteriouscoder.physicalandmentalhealth.ui.Screens.Onboarding.components.PageIndicator
import kotlinx.coroutines.launch


//@Preview(showSystemUi = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    event: (OnBoardingEvent) -> Unit,
) {
    var screen by remember {
        mutableIntStateOf(0)
    }
    if (screen == 0){
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            val pageState = rememberPagerState(initialPage = 0)
            {
                pages.size
            }

            val buttonState = remember {
                derivedStateOf {
                    when (pageState.currentPage) {
                        0 -> listOf("", "Next")
                        1 -> listOf("Back", "Next")
                        2 -> listOf("Back", "Next")
                        3 -> listOf("Back", "Next")
                        4 -> listOf("Back", "Get Started")
                        else -> listOf("", "")
                    }
                }
            }

            HorizontalPager(state = pageState) { index ->
                OnBoardingPage(page = pages[index])
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MediumPadding2)
                    .navigationBarsPadding(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PageIndicator(
//                modifier = Modifier.width(PageIndicatorWidth),
//                    modifier = Modifier.weight(0.3f),
                    pageSize = pages.size,
                    selectedPage = pageState.currentPage
                )
                Spacer(modifier=Modifier.weight(1f))

                Row(
//                    modifier = Modifier.weight(0.7f),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    val scope = rememberCoroutineScope()
                    if (buttonState.value[0].isNotEmpty()) {
                        NewsTextButton(
                            text = buttonState.value[0],
                            onClick = {
                                scope.launch {
                                    pageState.animateScrollToPage(page = pageState.currentPage - 1)

                                }
                            }
                        )
                    }
                    NewsButton(text = buttonState.value[1],
                        onClick = {
                            scope.launch {
                                if (pageState.currentPage == 4) {
                                   screen = 1
                                } else {
                                    scope.launch {
                                        pageState.animateScrollToPage(page = pageState.currentPage + 1)
                                    }
                                }
                            }
                        }
                    )

                }

            }
            Spacer(modifier = Modifier.weight(0.3f))


        }
    }else if(screen == 1){
        Bottom_Navigation()
    }
}

/*Clean architecture: clean architecture is to divide your project into 3 layer:
1)Presentation Layer
( views,viewModel,DI)

2)Data Layer
(DataSource,Api,Repository,Model,Mapper)

3)Domain Layer
(Repository,Model,UseCase)
 */