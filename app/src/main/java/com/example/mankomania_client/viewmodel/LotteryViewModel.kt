package com.example.mankomania_client.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mankomania_client.api.LotteryApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LotteryViewModel(
    private val api: LotteryApi
) : ViewModel() {

    private val _currentAmount = MutableStateFlow(0)
    val currentAmount: StateFlow<Int> = _currentAmount

    private val _notification = MutableStateFlow("")
    val notification: StateFlow<String> = _notification

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun refreshAmount() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) { api.getCurrentAmount() }
                if (response.isSuccessful) {
                    _currentAmount.value = response.body() ?: 0
                }
            } catch (e: Exception) {
                showTemporaryMessage("Error: ${e.message}")
            }
        }
    }

    fun payToLottery(playerId: String, amount: Int, reason: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = api.payToLottery(playerId, amount, reason)
                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        if (body.success) {
                            _currentAmount.value = body.newAmount
                        }
                    }
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun claimLottery(playerId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = withContext(Dispatchers.IO) { api.claimLottery(playerId) }
                if (response.isSuccessful) {
                    response.body()?.let {
                        _currentAmount.value = it.newAmount
                        showTemporaryMessage("${playerId} won ${it.wonAmount}!")
                    }
                }
            } catch (e: Exception) {
                showTemporaryMessage("Claim failed: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun showTemporaryMessage(msg: String) {
        _notification.value = msg
        viewModelScope.launch {
            kotlinx.coroutines.delay(3000)
            _notification.value = ""
        }
    }
}