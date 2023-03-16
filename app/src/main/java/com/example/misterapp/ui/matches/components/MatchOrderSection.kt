package com.example.misterapp.ui.matches.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.misterapp.core.Order
import com.example.misterapp.domain.util.MatchOrder
import com.example.misterapp.domain.util.PlayerOrder
import com.example.misterapp.ui.players.components.DefaultRadioButton

@Composable
fun MatchOrderSection(
    modifier: Modifier = Modifier,
    matchOrder: MatchOrder = MatchOrder.Date(Order.ASC),
    onOrderChange: (MatchOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Rival",
                selected = matchOrder is MatchOrder.Opponent,
                onSelect = { onOrderChange(MatchOrder.Opponent(matchOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Fecha",
                selected = matchOrder is MatchOrder.Date,
                onSelect = { onOrderChange(MatchOrder.Date(matchOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascendente",
                selected = matchOrder.orderType == Order.ASC,
                onSelect = {
                    onOrderChange(matchOrder.copy(Order.ASC))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descendente",
                selected = matchOrder.orderType == Order.DESC,
                onSelect = {
                    onOrderChange(matchOrder.copy(Order.DESC))
                }
            )
        }
    }
}