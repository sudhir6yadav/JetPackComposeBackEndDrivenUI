package com.hadi.superherolexicon.viewstate

import com.hadi.superherolexicon.data.model.Item


sealed class HeroDetailsViewState {

    object Empty : HeroDetailsViewState()
    object Loading : HeroDetailsViewState()
    data class Success(val data: Item.Hero) : HeroDetailsViewState()
    data class Error(val exception: Throwable) : HeroDetailsViewState()
}