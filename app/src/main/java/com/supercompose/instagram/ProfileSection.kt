package com.supercompose.instagram

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.supercompose.R
import com.tutorial.supercompose.instagram.StatSection

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            RoundImage(
                image = painterResource(id = R.drawable.earth_avatar),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3F)
            )
            Spacer(modifier = Modifier.width(16.dp))
            StatSection(modifier = modifier.weight(7F))
        }
        Spacer(modifier = Modifier.height(2.dp))
        ProfileDescription(
            displayName = "Earth",
            description = "A culture of wanderlust",
            url = "earth.co/wallpapers",
            followedBy = listOf("nickname", "micheal_23"),
            otherCount = 3
        )
    }
}

@Composable
fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1F, matchHeightConstraintsFirst = true)
            .border(width = 1.dp, color = Color.Gray, shape = CircleShape)
            .padding(3.dp)
            .clip(CircleShape)
    )
}
