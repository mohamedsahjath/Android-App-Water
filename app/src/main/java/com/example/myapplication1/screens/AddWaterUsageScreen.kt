package com.example.myapplication1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapplication1.R
import com.example.myapplication1.navigation.Screen
import com.example.myapplication1.ui.theme.MyApplication1Theme
import com.example.myapplication1.viewmodel.WaterUsageViewModel

@Composable
fun AddWaterUsageScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: WaterUsageViewModel = viewModel()
) {
    AddWaterUsageContent(
        date = viewModel.date,
        onDateChange = { viewModel.date = it },
        amount = viewModel.amount,
        onAmountChange = { viewModel.amount = it },
        onBackClick = { navController.popBackStack() },
        onDeleteClick = { /* Handle delete */ },
        onSaveClick = {
            viewModel.addUsage(viewModel.date, viewModel.amount, "")
            viewModel.resetFields()
            navController.navigate(Screen.UsageList.route) {
                popUpTo(Screen.UsageList.route) { inclusive = true }
            }
        },
        modifier = modifier
    )
}

@Composable
fun AddWaterUsageContent(
    date: String,
    onDateChange: (String) -> Unit,
    amount: String,
    onAmountChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val gradientColors = listOf(
        Color(0xFFFFFFFF),
        Color(0xFFAEE2FF),
        Color(0xFF81D4FA)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(gradientColors))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar with Back Button and Title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(48.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        // Placeholder for Back Icon (Arrow)
                        Box(
                            modifier = Modifier
                                .size(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(12.dp)
                                    .height(2.dp)
                                    .background(Color.Black)
                            )
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .offset(x = (-3).dp)
                                    .background(Color.Black, RoundedCornerShape(1.dp)) // This is a very rough arrow head
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(id = R.string.water_usage_title),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00B0FF),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Illustration Placeholder
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color(0xFFB3E5FC), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder for Water Drop
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            Color(0xFF007BFF),
                            RoundedCornerShape(topStart = 40.dp, bottomStart = 20.dp, bottomEnd = 20.dp)
                        )
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.quote_water_wise),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Input Fields
            WaterUsageInputField(
                label = stringResource(id = R.string.date_label),
                value = date,
                onValueChange = onDateChange,
                iconPlaceholder = {
                    Box(modifier = Modifier.size(24.dp).background(Color(0xFFEF5350), RoundedCornerShape(4.dp))) // Calendar placeholder
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            WaterUsageInputField(
                label = stringResource(id = R.string.water_used_label),
                value = amount,
                onValueChange = onAmountChange,
                iconPlaceholder = {
                    Box(modifier = Modifier.size(20.dp).background(Color(0xFF4FC3F7), CircleShape)) // Water drop placeholder
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            // Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = onDeleteClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.delete_button),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Button(
                    onClick = onSaveClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.save_button),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun WaterUsageInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    iconPlaceholder: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color(0xFF3394A1), RoundedCornerShape(4.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        iconPlaceholder()
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = label,
            color = Color.Black,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(12.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            textStyle = LocalTextStyle.current.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            singleLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddWaterUsageScreenPreview() {
    MyApplication1Theme {
        AddWaterUsageContent(
            date = "2023-10-27",
            onDateChange = {},
            amount = "50",
            onAmountChange = {},
            onBackClick = {},
            onDeleteClick = {},
            onSaveClick = {}
        )
    }
}
