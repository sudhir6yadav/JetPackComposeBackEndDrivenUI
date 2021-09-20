package com.hadi.superherolexicon.viewstate

/*import androidx.compose.Composable
import androidx.compose.onCommit
import androidx.compose.remember
import androidx.compose.runtime.Composable
import androidx.compose.state*/
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Based on https://medium.com/swlh/android-mvi-with-jetpack-compose-b0890f5156ac
 * Adapted for 0.1.0-dev04
 */
@Composable
fun <T> observe(data: LiveData<T>): T? {

    /*var result by stat
    var result by state { data.value }
    val observer = remember { Observer<T> { result = it } }

    onCommit(data) {
        data.observeForever(observer)
        onDispose { data.removeObserver(observer) }
    }*/

    return null
}
