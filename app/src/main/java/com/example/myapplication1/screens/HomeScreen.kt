package com.example.myapplication1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication1.R
import com.example.myapplication1.ui.theme.MyApplication1Theme

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFB3E5FC),
                        Color.White
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(
                painter = painterResource(id = R.drawable.aquacare_logo),
                contentDescription = "AquaCare Logo",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 8.dp),
                contentScale = ContentScale.Fit
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Bell Icon Placeholder
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🔔", fontSize = 32.sp)
                }

                // Profile Icon Placeholder
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color.Transparent)
                        .clickable { navController.navigate("profile") },
                    contentAlignment = Alignment.Center
                ) {
                    Text("👤", fontSize = 32.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            HomeButton(
                icon = "🚰",
                text = stringResource(R.string.report_water_issue),
                onClick = { navController.navigate("addReport") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            HomeButton(
                icon = "📊",
                text = stringResource(R.string.track_water_usage),
                onClick = { navController.navigate("usageList") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            HomeButton(
                icon = "💧",
                text = stringResource(R.string.donate),
                onClick = { navController.navigate("payment") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            HomeButton(
                icon = "📚",
                text = stringResource(R.string.awareness_tips),
                onClick = { navController.navigate("awareness") }
            )
        }
    }
}

@Composable
fun HomeButton(
    icon: String,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = icon, fontSize = 24.sp)
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MyApplication1Theme {
        HomeScreen(navController = rememberNavController())
    }
}
