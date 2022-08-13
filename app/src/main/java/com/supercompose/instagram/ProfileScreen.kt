package com.supercompose.instagram

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.supercompose.R
import com.tutorial.supercompose.instagram.*
import com.tutorial.supercompose.instagram.entity.ImageWithText

@Composable
fun ProfileScreen() {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(8.dp))
        TopBar(name = "nickname_official", modifier = Modifier.padding(10.dp))
        Spacer(modifier = Modifier.height(4.dp))
        ProfileSection(modifier = Modifier)
        Spacer(modifier = Modifier.height(20.dp))
        ButtonSection()
        Spacer(modifier = Modifier.height(10.dp))
        PostTabView(
            imageWithTexts = listOf(
                ImageWithText(painterResource(R.drawable.ic_grid), "Posts"),
                ImageWithText(painterResource(R.drawable.ic_reels), "Reels"),
                ImageWithText(painterResource(R.drawable.ic_ig_tv), "IG tv"),
                ImageWithText(painterResource(R.drawable.ic_profile), "Posts"),
            )
        ) {
            selectedTabIndex = it
        }
        when (selectedTabIndex) {
            0 -> PostSection(
                posts = listOf(
                    painterResource(R.drawable.instagram_test_image1),
                    painterResource(R.drawable.instagram_test_image2),
                    painterResource(R.drawable.instagram_test_image3),
                    painterResource(R.drawable.instagram_test_image4),
                    painterResource(R.drawable.instagram_test_image5),
                    painterResource(R.drawable.instagram_test_image6),
                    painterResource(R.drawable.instagram_test_image7),
                    painterResource(R.drawable.instagram_test_image8),
                    painterResource(R.drawable.instagram_test_image9)
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
