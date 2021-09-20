package com.hadi.superherolexicon.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.ViewModelProvider
import com.hadi.superherolexicon.ui.theme.SuperheroLexiconTheme
import com.hadi.superherolexicon.view.HeroListView
import com.hadi.superherolexicon.viewmodel.HeroViewModel
import com.hadi.superherolexicon.viewmodel.ViewModelFactory
import com.hadi.superherolexicon.viewstate.HeroListViewState

class HeroesActivity : ComponentActivity() {


    private lateinit var viewModel: HeroViewModel

    @ExperimentalComposeUiApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupViewModel()

        setContent {
            SuperheroLexiconTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {


                    /*HeroListView(
                        heroList = viewModel.dashboardItems,
                        viewModel = viewModel
                    ) { hero ->

                        Log.d("item.herto",""+hero.id);

                        val intent = Intent(this, HeroDetailsActivity::class.java)
                        intent.putExtra("HERO_ID", hero.id)
                        startActivity(intent)
                    }
*/
                    when (val result = viewModel.dashboardItems.observeAsState().value) {

                        HeroListViewState.Loading -> Text(text = "Loading")
                        is HeroListViewState.Error -> Text(text = "Error found: ${result.exception}")
                        is HeroListViewState.Success -> {
                            Log.d("data.success", "onCreate: "+result)
                            Log.d("data.success.data", "onCreate: "+result.data)

                            val data = result.data.distinctBy { it.data?.get(0) }
                            data.forEach { item ->
                                Log.d("DISTINCT_PUBLISHERS", item.viewType+"-"+item.data!!.size)
                            }
                            HeroListView(
                                heroList = result.data,
                                viewModel = viewModel
                            ) { hero ->

                                Log.d("item.herto",""+hero.id);

                                val intent = Intent(this, HeroDetailsActivity::class.java)
                                intent.putExtra("HERO_ID", hero.id)
                                startActivity(intent)
                            }

                        }
                        HeroListViewState.Empty -> Text("No results found!")
                 }

                }
            }
        }
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory(application)
        viewModel = ViewModelProvider(this, factory).get(HeroViewModel::class.java)
    }
}
