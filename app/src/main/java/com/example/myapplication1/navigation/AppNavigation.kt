package com.example.myapplication1.navigation

sealed class Screen(val route:String){

    object Splash : Screen("splash")
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Home : Screen("home")

    object AddUsage : Screen("addUsage")
    object UsageList : Screen("usageList")

    object Profile : Screen("profile")
    object Payment : Screen("payment")
    object PaymentConfirm : Screen("paymentConfirm")
    object PaymentSuccess : Screen("paymentSuccess")
    object Awareness : Screen("awareness")
    object Report : Screen("report")
    object AddReport : Screen("addReport")
    object ReportSuccess : Screen("reportSuccess")
    object EditReport : Screen("editReport")
    object DeleteSummary : Screen("deleteSummary")
    object DeleteConfirm : Screen("deleteConfirm")
    object DeleteSuccess : Screen("deleteSuccess")
    object Map : Screen("map")
}