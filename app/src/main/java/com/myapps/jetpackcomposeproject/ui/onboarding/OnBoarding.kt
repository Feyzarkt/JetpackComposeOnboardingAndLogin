package com.myapps.jetpackcomposeproject.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import com.myapps.jetpackcomposeproject.R
import com.myapps.jetpackcomposeproject.data.model.OnBoardingPage
import com.myapps.jetpackcomposeproject.data.model.Screen
import com.myapps.jetpackcomposeproject.ui.theme.Blue
import com.myapps.jetpackcomposeproject.ui.theme.Orange
import com.myapps.jetpackcomposeproject.ui.theme.Purple
import com.myapps.jetpackcomposeproject.ui.theme.RoundedCardShape
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun OnBoarding(navController: NavController) {
    val pages = listOf(
        OnBoardingPage.FirstPage,
        OnBoardingPage.SecondPage,
        OnBoardingPage.ThirdPage
    )

    val pagerState = rememberPagerState(pageCount = 3)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(90.dp, Alignment.Bottom)
    ) {
        HorizontalPager(state = pagerState) { position ->
            PagerImg(onBoardingPage = pages[position])
        }
        val background = when (pagerState.currentPage) {
            0 -> Orange
            1 -> Blue
            else -> Purple
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(370.dp),
            backgroundColor = background,
            shape = RoundedCardShape.large
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPagerIndicator(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 30.dp, end = 50.dp),
                    pagerState = pagerState
                )
                HorizontalPager(state = pagerState) { position ->
                    PagerDesc(onBoardingPage = pages[position])
                }
                SkipAndNextButtons(pagerState, navController)
            }
        }
    }
}

@Composable
fun PagerImg(onBoardingPage: OnBoardingPage) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = onBoardingPage.img),
            contentDescription = "PagerImg"
        )
    }

}

@Composable
fun PagerDesc(onBoardingPage: OnBoardingPage) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = onBoardingPage.title,
            modifier = Modifier.padding(top = 30.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = onBoardingPage.desc,
            modifier = Modifier.padding(40.dp)
        )
    }

}

@ExperimentalPagerApi
@Composable
fun SkipAndNextButtons(pagerState: PagerState, navController: NavController, viewModel: OnboardingViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()
    val user = viewModel.auth.currentUser

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(170.dp, Alignment.CenterHorizontally)
    ) {
        if (pagerState.currentPage != 2) {
            Button(
                colors =  ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.purple_700)),
                onClick = {
                    navController.popBackStack()
                    if (user == null) navController.navigate(Screen.Login.route)
                    else navController.navigate(Screen.Home.route)
                },
                shape = RoundedCornerShape(27.dp)
            ) {
                Text(
                    text = "Skip"
                )
            }

            IconButton(onClick = {
                scope.launch {
                    if(pagerState.currentPage != 2) pagerState.scrollToPage(pagerState.currentPage + 1)
                }
            }) {
                Icon(
                    imageVector = Icons.Rounded.ArrowForward,
                    contentDescription = stringResource(
                        id = R.string.icon_content,
                    ),
                )
            }
        }
        else {
            Button(
                colors =  ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.purple_700)),
                onClick = {
                    navController.popBackStack()
                    if (user == null) navController.navigate(Screen.Login.route)
                    else navController.navigate(Screen.Home.route)
                },
                shape = RoundedCornerShape(27.dp)
            ) {
                Text(
                    text = "Finish"
                )
            }
        }
    }
}

