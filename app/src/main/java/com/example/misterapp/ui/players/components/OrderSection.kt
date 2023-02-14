package com.example.misterapp.ui.players.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.misterapp.core.Order
import com.example.misterapp.domain.util.PlayerOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    playerOrder: PlayerOrder = PlayerOrder.Name(Order.ASC),
    onOrderChange: (PlayerOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Name",
                selected = playerOrder is PlayerOrder.Name,
                onSelect = { onOrderChange(PlayerOrder.Name(playerOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = playerOrder is PlayerOrder.Birthday,
                onSelect = { onOrderChange(PlayerOrder.Birthday(playerOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = playerOrder.orderType == Order.ASC,
                onSelect = {
                    onOrderChange(playerOrder.copy(Order.ASC))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = playerOrder.orderType == Order.DESC,
                onSelect = {
                    onOrderChange(playerOrder.copy(Order.DESC))
                }
            )
        }
    }
}