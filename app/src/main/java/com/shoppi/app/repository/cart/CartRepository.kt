package com.shoppi.app.repository.cart

import com.shoppi.app.model.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository(
    private val localDataSource: CartItemLocalDataSource
) {

    suspend fun getCartItems(): List<CartItem> {
        return withContext(Dispatchers.IO) {

        }
    }
}