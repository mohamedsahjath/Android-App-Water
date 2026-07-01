package com.example.myapplication1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication1.R
import com.example.myapplication1.ui.theme.MyApplication1Theme
import com.example.myapplication1.viewmodel.ReportViewModel
import com.example.myapplication1.viewmodel.UserViewModel

@Composable
fun DeleteReportScreen(
    reportViewModel: ReportViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(),
    onBack: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
    DeleteReportContent(
        reportViewModel = reportViewModel,
        userViewModel = userViewModel,
        onBack = onBack,
        onDeleteClick = onDeleteClick
    )
}

@Composable
fun DeleteReportContent(
    modifier: Modifier = Modifier,
    reportViewModel: ReportViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(),
    onBack: () -> Unit = {},
    onDeleteClick: () -> Unit = {},
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
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_circle),
                        contentDescription = stringResource(id = R.string.back_button_content_description),
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )
                }

            }

            Spacer(modifier = Modifier.height(24.dp))

            Surface(
                shape = CircleShape,
                color = Color(0xFF01579B),
                modifier = Modifier.size(120.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Full Name Text
            Text(
                text = fullName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    ReportField(
                        label = stringResource(id = R.string.location_label),
                        value = reportViewModel.locationAddress.ifEmpty { stringResource(id = R.string.location_sample) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ReportField(
                        label = stringResource(id = R.string.problem_type_label_with_colon),
                        value = reportViewModel.problemType.ifEmpty { stringResource(id = R.string.problem_type_sample) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.describe_problem_label_with_colon),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = reportViewModel.description.ifEmpty { stringResource(id = R.string.report_description_sample) },
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.photo_label),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    if (reportViewModel.imageUri != null) {
                        Text(text = "Photo Selected", color = Color.Black, fontSize = 14.sp)
                    } else {
                        Image(
                            painter = ColorPainter(Color.Gray),
                            contentDescription = "Report Photo",
                            modifier = Modifier
                                .size(width = 150.dp, height = 100.dp)
                                .background(Color.LightGray),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onDeleteClick,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(50.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF))
            ) {
                Text(
                    text = stringResource(id = R.string.delete_report_button),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ReportField(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(label)
                }
                append(value)
            },
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyReportScreenPreview() {
    MyApplication1Theme {
        DeleteReportContent()
    }
}
