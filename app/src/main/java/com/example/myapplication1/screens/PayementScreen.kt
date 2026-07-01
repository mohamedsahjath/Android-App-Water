package com.example.myapplication1.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication1.navigation.Screen
import com.example.myapplication1.R
import com.example.myapplication1.ui.theme.LoginButtonBlue
import com.example.myapplication1.ui.theme.MyApplication1Theme
import com.example.myapplication1.viewmodel.DonationViewModel

@Composable
fun PaymentScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    donationViewModel: DonationViewModel = viewModel()
) {
    val context = LocalContext.current
    var amount by remember { mutableStateOf(donationViewModel.amount) }
    var selectedMethod by remember { mutableStateOf("Card") }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color(0xFFAEDFF7),
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = { navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    } },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_circle),
                        contentDescription = stringResource(id = R.string.back_button_content_description),
                        tint = Color.Unspecified
                    )
                }
            }

            Text(
                text = stringResource(id = R.string.donate_for_clean_water),
                color = Color(0xFF43A0AD),
                fontSize = 32.sp,
                lineHeight = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.water_splash),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.donation_description),
                color = Color.Black,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Donation Amount Input
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFF3399AA))
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.donation_amount_label),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = amount,
                    onValueChange = { amount = it },
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White.copy(alpha = 0.5f)
                    ),
                    textStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(id = R.string.select_payment_method),
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Payment Methods
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PaymentMethodItem(
                    name = "Card",
                    isSelected = selectedMethod == "Card",
                    onClick = { 
                        selectedMethod = "Card"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.visa.com"))
                        context.startActivity(intent)
                    }
                )
                PaymentMethodItem(
                    name = "PayPal",
                    isSelected = selectedMethod == "PayPal",
                    onClick = { 
                        selectedMethod = "PayPal"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.com/signin"))
                        context.startActivity(intent)
                    }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { 
                    if (amount.isNotEmpty()) {
                        donationViewModel.amount = amount
                        navController.navigate(Screen.PaymentConfirm.route) 
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = LoginButtonBlue),
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.donate_now_button),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun PaymentMethodItem(name: String, isSelected: Boolean, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .width(120.dp)
            .height(44.dp)
            .clickable { onClick() },
        color = if (isSelected) Color(0xFF3399AA) else Color.White,
        border = androidx.compose.foundation.BorderStroke(2.dp, Color(0xFF3399AA)),
        shape = RoundedCornerShape(4.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = name,
                color = if (isSelected) Color.White else Color(0xFF3399AA),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentScreenPreview() {
    MyApplication1Theme {
        PaymentScreen(navController = rememberNavController())
    }
}
