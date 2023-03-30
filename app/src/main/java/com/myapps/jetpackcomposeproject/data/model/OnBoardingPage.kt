package com.myapps.jetpackcomposeproject.data.model

import com.myapps.jetpackcomposeproject.R

sealed class OnBoardingPage (
    val img: Int,
    val title: String,
    val desc: String
    ) {
    object FirstPage :OnBoardingPage(
        img = R.drawable.tourist,
        title = "FirstPage",
        desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )
    object SecondPage :OnBoardingPage(
        img = R.drawable.travel,
        title = "SecondPage",
        desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )
    object ThirdPage :OnBoardingPage(
        img = R.drawable.traveller,
        title = "ThirdPage",
        desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )
}