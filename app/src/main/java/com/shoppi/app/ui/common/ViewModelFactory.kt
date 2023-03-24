package com.shoppi.app.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shoppi.app.AssetLoader
import com.shoppi.app.network.ApiClient
import com.shoppi.app.repository.CategoryRemoteDataSource
import com.shoppi.app.repository.CategoryRepository
import com.shoppi.app.repository.HomeAssetDataSource
import com.shoppi.app.repository.HomeRepository
import com.shoppi.app.ui.category.CategoryViewModel
import com.shoppi.app.ui.home.HomeViewModel

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
            HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CategoryViewModel::class.java)){
            val repository = CategoryRepository(CategoryRemoteDataSource(ApiClient.create()))
            CategoryViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}