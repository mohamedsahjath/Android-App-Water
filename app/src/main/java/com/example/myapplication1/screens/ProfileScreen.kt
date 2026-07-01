package com.example.myapplication1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication1.R
import com.example.myapplication1.ui.theme.*
import com.example.myapplication1.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel = viewModel()
) {
    val user = userViewModel.currentUser
    val fullName = if (user != null) "${user.firstName} ${user.lastName}" else "Loading..."

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        LoginBackgroundLight,
                        LoginBackgroundDark
                    )
                )
            )
    ) {
        // Top label "My Profile"
        Text(
            text = stringResource(id = R.string.my_profile),
            modifier = Modifier
                .fillMaxWidth()
                .padding(70.dp),
            color = Color.Black,
            fontSize = 36.sp,
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            // Back Button
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_back_circle),
                    contentDescription = stringResource(id = R.string.back_button_content_description),
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { 
                            navController.navigate("home") {
                                popUpTo("home") { inclusive = true }
                            }
                        }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Profile Image (Dark blue circle with person icon)
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .background(color = Color(0xFF004D7A), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = stringResource(id = R.string.profile_image_content_description),
                    modifier = Modifier.fillMaxSize(0.6f),
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Full Name Text
            Text(
                text = fullName,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.weight(1f))

            // Log out Button
            Button(
                onClick = { 
                    userViewModel.logout()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .height(65.dp),
                shape = RoundedCornerShape(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LoginButtonBlue)
            ) {
                Text(
                    text = stringResource(id = R.string.log_out),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MyApplication1Theme {
        ProfileScreen(navController = rememberNavController())
    }
}
