package com.example.misterapp.domain.util

import com.example.misterapp.core.Order

sealed class PlayerOrder(val orderType: Order) {
    class Name(orderType: Order): PlayerOrder(orderType)
    class Birthday(orderType: Order): PlayerOrder(orderType)

    fun copy(orderType: Order): PlayerOrder {
        return when(this) {
            is Name -> Name(orderType)
            is Birthday -> Birthday(orderType)
        }
    }
}