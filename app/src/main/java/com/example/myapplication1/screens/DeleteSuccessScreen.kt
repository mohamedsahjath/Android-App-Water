package com.example.myapplication1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.R
import com.example.myapplication1.ui.theme.MyApplication1Theme

@Composable
fun DeleteSuccessScreen(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Gradient Top Background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFE1F5FE),
                            Color(0xFFB3E5FC),
                            Color.White
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header: "delete confirm" text


            // Icons Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClicked,
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_circle),
                        contentDescription = stringResource(R.string.back_button_content_description),
                        tint = Color.Unspecified,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Checkmark Icon
            Icon(
                painter = painterResource(id = R.drawable.ic_check_circle),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Successful Text
            Text(
                text = stringResource(R.string.successful_title),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Illustration
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                // Using a combination of existing icons to mimic the water drop/wave illustration
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_water_hand),
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = Color(0xFF0288D1)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(modifier = Modifier.width(150.dp).height(4.dp).background(Color(0xFF0288D1), androidx.compose.foundation.shape.RoundedCornerShape(2.dp)))
                    Spacer(modifier = Modifier.height(4.dp))
                    Box(modifier = Modifier.width(180.dp).height(4.dp).background(Color(0xFF01579B), androidx.compose.foundation.shape.RoundedCornerShape(2.dp)))
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteSuccessScreenPreview() {
    MyApplication1Theme {
        DeleteSuccessScreen()
    }
}
