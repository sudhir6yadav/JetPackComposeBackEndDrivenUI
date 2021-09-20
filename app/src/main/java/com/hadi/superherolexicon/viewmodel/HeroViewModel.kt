package com.hadi.superherolexicon.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hadi.superherolexicon.data.model.Item
import com.hadi.superherolexicon.utils.Utils
import com.hadi.superherolexicon.viewstate.HeroListViewState
import com.hadi.superherolexicon.viewstate.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString

class HeroViewModel(application: Application) : AndroidViewModel(application) {


    //private val _dashboardItems = MutableStateFlow<HeroListViewState>(())
    //val dashboardItems= _dashboardItems.asStateFlow()
    //var dashboardItems = mutableStateOf(listOf(Item))
     //var dashboardItems: List<Item> by mutableStateOf(listOf())

    private val _dashboardItems = MutableLiveData<HeroListViewState<List<Item>>>()
    val dashboardItems: LiveData<HeroListViewState<List<Item>>> = _dashboardItems


    init {
        getAllHeroes()
    }

    private fun getAllHeroes() {
        _dashboardItems.postValue(HeroListViewState.Loading)

        viewModelScope.launch {
            _dashboardItems.postValue(Repository.getDashboardData())

            /*   val dashboardList=Repository.getDashboardData()
                when(dashboardList){
                    is HeroListViewState.Loading ->{ Log.d("loading"   ,"loading")}
                    is HeroListViewState.Success -> dashboardItems=dashboardList.data
                    is HeroListViewState.Error ->  Log.d("errorda"   ,"error data")
                }
*/

            Log.d("getAllHeroes", "getAllHeroes: " + dashboardItems)

        }
    }


}

