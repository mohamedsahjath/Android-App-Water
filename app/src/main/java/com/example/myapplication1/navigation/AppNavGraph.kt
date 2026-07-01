package com.example.myapplication1.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication1.screens.*
import com.example.myapplication1.viewmodel.DonationViewModel
import com.example.myapplication1.viewmodel.ReportViewModel
import com.example.myapplication1.viewmodel.UserViewModel
import com.example.myapplication1.viewmodel.WaterUsageViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {
    val reportViewModel: ReportViewModel = viewModel()
    val donationViewModel: DonationViewModel = viewModel()
    val userViewModel: UserViewModel = viewModel()
    val waterUsageViewModel: WaterUsageViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }

        composable(Screen.Signup.route) {
            SignupScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.AddUsage.route) {
            AddWaterUsageScreen(
                navController = navController,
                viewModel = waterUsageViewModel
            )
        }

        composable(Screen.UsageList.route) {
            WaterUsageListScreen(
                viewModel = waterUsageViewModel,
                onBackClick = { 
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onEditClick = { navController.navigate(Screen.AddUsage.route) },
                onDeleteClick = { navController.navigate(Screen.DeleteConfirm.route) }
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }

        composable(Screen.Payment.route) {
            PaymentScreen(
                navController = navController,
                donationViewModel = donationViewModel
            )
        }

        composable(Screen.PaymentConfirm.route) {
            PaymentConfirmScreen(
                donationViewModel = donationViewModel,
                onBackClicked = { navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                } },
                onConfirmClicked = { navController.navigate(Screen.PaymentSuccess.route) }
            )
        }

        composable(Screen.PaymentSuccess.route) {
            PaymentSuccessScreen(
                onBackClicked = { navController.popBackStack(route = Screen.Home.route, inclusive = false) }
            )
        }

        composable(Screen.Awareness.route) {
            AwarenessScreen(navController)
        }

        composable(Screen.Report.route) {
            ViewReportScreen(
                navController = navController,
                reportViewModel = reportViewModel,
                userViewModel = userViewModel,
                onEditClicked = { navController.navigate(Screen.EditReport.route) },
                onDeleteClicked = { navController.navigate(Screen.DeleteConfirm.route) }
            )
        }

        composable(Screen.AddReport.route) {
            AddWaterProblemScreen(
                navController = navController,
                reportViewModel = reportViewModel,
                onSubmitClicked = { navController.navigate(Screen.ReportSuccess.route) }
            )
        }

        composable(Screen.ReportSuccess.route) {
            ReportSuccessScreen(
                onBackClicked = { navController.navigate(Screen.AddReport.route) },
                onEditClicked = { navController.navigate(Screen.EditReport.route) },
                onViewClicked = { navController.navigate(Screen.Report.route) }
            )
        }

        composable(Screen.EditReport.route) {
            EditReportScreen(
                reportViewModel = reportViewModel,
                onBackClick = { 
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onDeleteClicked = { navController.navigate(Screen.DeleteSummary.route) },
                onSubmitClicked = { navController.navigate(Screen.ReportSuccess.route) }
            )
        }

        composable(Screen.DeleteSummary.route) {
            DeleteReportScreen(
                reportViewModel = reportViewModel,
                userViewModel = userViewModel,
                onBack = { 
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onDeleteClick = { navController.navigate(Screen.DeleteConfirm.route) }
            )
        }

        composable(Screen.DeleteConfirm.route) {
            val prevRoute = navController.previousBackStackEntry?.destination?.route
            DeleteConfirmScreen(
                onBackClicked = { navController.popBackStack() },
                onYesClicked = {
                    if (prevRoute == Screen.UsageList.route) {
                        waterUsageViewModel.deleteAllUsage()
                        navController.popBackStack()
                    } else {
                        navController.navigate(Screen.DeleteSuccess.route)
                    }
                },
                onNoClicked = { navController.popBackStack() }
            )
        }

        composable(Screen.DeleteSuccess.route) {
            // Re-using PaymentSuccess UI or similar for Delete Success if it exists
            // Or if there is a DeleteSuccessScreen.kt
            DeleteSuccessScreen(
                onBackClicked = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Map.route) {
            MapScreen(navController)
        }
    }
}
