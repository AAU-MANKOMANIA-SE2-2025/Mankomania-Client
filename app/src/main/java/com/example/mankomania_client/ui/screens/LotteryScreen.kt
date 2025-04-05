package com.example.mankomania_client.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mankomania_client.ui.components.LotteryContent
import com.example.mankomania_client.viewmodel.LotteryViewModel

@Composable
fun LotteryScreen(
    playerId: String,  // Player-ID
    viewModel: LotteryViewModel = viewModel()
) {
    val currentAmount by viewModel.currentAmount.collectAsState()
    val notification by viewModel.notification.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LotteryContent(
        currentAmount = currentAmount,
        notification = notification,
        isLoading = isLoading,
        onPayClick = { amount ->
            viewModel.payToLottery(playerId, amount, "Lottery Payment")
        },
        onClaimClick = { viewModel.claimLottery(playerId) }
    )
}