package com.example.misterapp.ui.team.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.misterapp.R
import com.example.misterapp.domain.model.TeamModel

@Composable
fun TeamContent(
    team: TeamModel
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = { }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.team),
                    modifier = Modifier.size(72.dp).clip(CircleShape).background(Color.LightGray).padding(8.dp),
                    tint = Color.Unspecified,
                    contentDescription = "")
                Text(
                    text = "Plantilla",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold)
            }

        }
    }
}