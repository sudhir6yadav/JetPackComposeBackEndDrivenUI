package com.hadi.superherolexicon.viewstate

import com.hadi.superherolexicon.data.model.Item
import com.hadi.superherolexicon.network.NetworkClient
import java.lang.Exception
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Repository {

    private val apiService = NetworkClient.service

    suspend fun getDashboardData(): HeroListViewState<List<Item>> {
        return withContext(Dispatchers.IO) {
            try {
                    HeroListViewState.Success(apiService.getDashboard())
            } catch (exception: Exception) {
                HeroListViewState.Error(exception)
            }
        }
    }


   /* suspend fun getDashboardData(): List<Item> {
        return apiService.getDashboard()
    }*/
}
