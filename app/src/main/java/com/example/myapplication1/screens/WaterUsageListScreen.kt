package com.example.myapplication1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication1.R
import com.example.myapplication1.database.WaterUsage
import com.example.myapplication1.ui.theme.MyApplication1Theme
import com.example.myapplication1.viewmodel.WaterUsageViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun WaterUsageListScreen(
    modifier: Modifier = Modifier,
    viewModel: WaterUsageViewModel = viewModel(),
    onBackClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    val usageList by viewModel.usageList.collectAsState()

    WaterUsageListContent(
        usageList = usageList,
        onBackClick = onBackClick,
        onEditClick = {
            if (usageList.isNotEmpty()) {
                val firstUsage = usageList[0]
                viewModel.date = firstUsage.date
                viewModel.amount = firstUsage.amount
            }
            onEditClick()
        },
        onDeleteClick = onDeleteClick,
        modifier = modifier
    )
}

@Composable
fun WaterUsageListContent(
    usageList: List<WaterUsage>,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE3F2FD),
                        Color(0xFFB3E5FC),
                        Color(0xFF81D4FA)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.usage_list_title),
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                textAlign = TextAlign.Center
            )

            IconButton(
                onClick = onBackClick,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_circle),
                    contentDescription = stringResource(id = R.string.back_button_content_description),
                    tint = Color.Unspecified,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Illustration
            WaterIntakeIllustration(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Table
            UsageTable(
                usageList = usageList,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.weight(1f))

            // Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Button(
                    onClick = onEditClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.edit_button),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onDeleteClick,
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.delete_button),
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun WaterIntakeIllustration(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF5C6BC0), Color(0xFF3949AB))
                ),
                shape = RoundedCornerShape(4.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        // Illustration from AddWaterUsageScreen
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
    }
}

@Composable
fun UsageTable(
    usageList: List<WaterUsage>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(2.dp, Color.Black)
            .height(IntrinsicSize.Min)
    ) {
        Column {
            // Table Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.date_header),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(id = R.string.liters_header),
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            // Horizontal Divider below header
            HorizontalDivider(thickness = 4.dp, color = Color.Black)

            // Table Rows
            usageList.forEach { usage ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = usage.date,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 32.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = usage.amount,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 32.dp),
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
        }

        // Vertical Divider (Continuous)
        VerticalDivider(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxHeight(),
            thickness = 4.dp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WaterUsageListScreenPreview() {
    MyApplication1Theme {
        WaterUsageListContent(
            usageList = listOf(
                WaterUsage(id = 1, date = "2026-01-22", amount = "4 l", description = ""),
                WaterUsage(id = 2, date = "2026-01-23", amount = "6 l", description = "")
            ),
            onBackClick = {},
            onEditClick = {},
            onDeleteClick = {}
        )
    }
}
