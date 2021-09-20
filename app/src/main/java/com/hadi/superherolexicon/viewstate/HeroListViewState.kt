package com.hadi.superherolexicon.viewstate


sealed class HeroListViewState<out T> {

   // object Empty : HeroListViewState()
    //object Loading : HeroListViewState()
    //data class Success(val data: List<Item>) : HeroListViewState()
    //data class Error(val exception: Throwable) : HeroListViewState()

    object Empty : HeroListViewState<Nothing>()
    object Loading : HeroListViewState<Nothing>()
    data class Success<T>(val data: T) : HeroListViewState<T>()
    data class Error(val exception: Throwable) : HeroListViewState<Nothing>()
}