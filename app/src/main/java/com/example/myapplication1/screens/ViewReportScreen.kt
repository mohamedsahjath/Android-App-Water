package com.example.myapplication1.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication1.R
import com.example.myapplication1.navigation.Screen
import com.example.myapplication1.ui.theme.MyApplication1Theme
import com.example.myapplication1.viewmodel.ReportViewModel
import com.example.myapplication1.viewmodel.UserViewModel

@Composable
fun ViewReportScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    reportViewModel: ReportViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(),
    onEditClicked: () -> Unit = {},
    onDeleteClicked: () -> Unit = {}
) {
    val user = userViewModel.currentUser
    val fullName = if (user != null) "${user.firstName} ${user.lastName}" else "Loading..."

    ViewReportContent(
        fullName = fullName,
        locationAddress = reportViewModel.locationAddress,
        problemType = reportViewModel.problemType,
        description = reportViewModel.description,
        imageUri = reportViewModel.imageUri,
        onBackClicked = {
            navController.navigate("home") {
                popUpTo("home") { inclusive = true }
            }
        },
        onLocationClicked = { navController.navigate(Screen.Map.route) },
        onEditClicked = onEditClicked,
        onDeleteClicked = onDeleteClicked,
        modifier = modifier
    )
}

@Composable
fun ViewReportContent(
    fullName: String,
    locationAddress: String,
    problemType: String,
    description: String,
    imageUri: Uri?,
    onBackClicked: () -> Unit,
    onLocationClicked: () -> Unit,
    onEditClicked: () -> Unit,
    onDeleteClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color(0xFF81D4FA)
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
            // Top Bar
            Column(modifier = Modifier.fillMaxWidth()) { 
                Text(
                    text = stringResource(R.string.view_my_report_title),
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = onBackClicked) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_back_circle),
                            contentDescription = stringResource(R.string.back_button_content_description),
                            modifier = Modifier.size(52.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Large Profile Icon
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF004D73)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = fullName,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Report Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                color = Color.White,
                shape = RectangleShape,
                shadowElevation = 2.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Location
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(R.string.location_label))
                            }
                            append(locationAddress.ifEmpty { stringResource(R.string.location_sample) })
                        },
                        fontSize = 15.sp,
                        color = Color.Black,
                        lineHeight = 18.sp,
                        modifier = Modifier.clickable { onLocationClicked() }
                    )

                    // Problem Type
                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(R.string.problem_type_label_with_colon))
                            }
                            append(" ")
                            append(problemType.ifEmpty { stringResource(R.string.problem_type_sample) })
                        },
                        fontSize = 15.sp,
                        color = Color.Black
                    )

                    // Describe the Problem
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = stringResource(R.string.describe_problem_label_with_colon),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                        Text(
                            text = description.ifEmpty { stringResource(R.string.report_description_sample) },
                            fontSize = 14.sp,
                            color = Color.Black,
                            lineHeight = 16.sp
                        )
                    }

                    // Photo
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(
                            text = stringResource(R.string.photo_label),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                        if (imageUri != null) {
                            Text(
                                text = "Photo Selected",
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.water1),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(110.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onEditClicked,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = stringResource(R.string.edit_my_report_button),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

                Button(
                    onClick = onDeleteClicked,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007AFF)),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = stringResource(R.string.delete_report_button),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewReportScreenPreview() {
    MyApplication1Theme {
        ViewReportContent(
            fullName = "John Doe",
            locationAddress = "123 Main St",
            problemType = "Water Leak",
            description = "The pipe is leaking in the kitchen.",
            imageUri = null,
            onBackClicked = {},
            onLocationClicked = {},
            onEditClicked = {},
            onDeleteClicked = {}
        )
    }
}
