package com.mysteriouscoder.brainbooster.ui.screens.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.mysteriouscoder.brainbooster.NewsButton
import com.mysteriouscoder.brainbooster.NewsTextButton
import com.mysteriouscoder.brainbooster.PreferenceManager
import com.mysteriouscoder.brainbooster.R
import com.mysteriouscoder.brainbooster.data.pages
import com.mysteriouscoder.brainbooster.ui.navigation.BottomNavigation
import com.mysteriouscoder.brainbooster.ui.screens.onboarding.components.OnBoardingPage
import com.mysteriouscoder.brainbooster.ui.screens.onboarding.components.PageIndicator
import kotlinx.coroutines.launch

//fun Boolean.toInt() = if (this) 1 else 0

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingScreen() {


    val pageState = rememberPagerState(initialPage = 0) { pages.size }

    val buttonState = remember {
        derivedStateOf {
            when (pageState.currentPage) {
                0 -> listOf("", "Next")
                1 -> listOf("Back", "Next")
                2 -> listOf("Back", "Get Started")
                else -> listOf("", "")
            }
        }
    }

//Shared Preferences logic
    val prefs = PreferenceManager(LocalContext.current)
//    var screenopen = prefs.get("screenopen")?.toLong() ?: 0
//    Log.d("screenopen", screenopen.toString())

    var screen by remember {
        mutableIntStateOf(0)
    }

    if (prefs.get("screenopen") == "1") {
        screen = 1

    }

    if (screen == 0) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "\tBrain Booster",
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.nunito_extrabold)),

                            )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    )
                )
            }) {paddingValues ->

            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(paddingValues )
            ) {


                HorizontalPager(state = pageState) { index ->
                    OnBoardingPage(page = pages[index])
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .navigationBarsPadding(),
//                horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    PageIndicator(
//                modifier = Modifier.width(PageIndicatorWidth),
                        modifier = Modifier.width(50.dp),
                        pageSize = pages.size,
                        selectedPage = pageState.currentPage,
                        selectedColor = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Row(
//                    modifier = Modifier.fillMaxWidth(),

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
                                    if (pageState.currentPage == 2) {
                                        screen = 1
                                        prefs.save("screenopen", "1")
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
        }
    } else if (screen == 1) {
        BottomNavigation()
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

