package com.hadi.superherolexicon.view

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hadi.superherolexicon.components.BannerItem
import com.hadi.superherolexicon.components.HeroListItem
import com.hadi.superherolexicon.components.showCategoryElement
import com.hadi.superherolexicon.data.model.Item
import com.hadi.superherolexicon.ui.theme.Grotesk
import com.hadi.superherolexicon.ui.theme.accentColor
import com.hadi.superherolexicon.viewmodel.HeroViewModel

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun HeroListView(heroList: List<Item>, viewModel: HeroViewModel, onClick: (Item.Hero) -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var search by remember {
        mutableStateOf("")
    }
    Log.d("HeroListView.viewd", "HeroListView: inside data")
    Column(Modifier.padding(top=30.dp)) {

    topBar()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 5.dp)
    ) {

        item {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 11.dp, end = 12.dp, top = 12.dp)
                    .border(width = 2.dp, color = accentColor, shape = RoundedCornerShape(6.dp))
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {

                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(start = 8.dp, end = 8.dp),
                        tint = Color.Black

                    )

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {


                        BasicTextField(
                            value = search,
                            onValueChange = {
                                search = it
                            },
                            maxLines = 1,
                            singleLine = true,
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontFamily = Grotesk,
                                fontSize = 18.sp
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    keyboardController?.hide()
                                }
                            )
                        )

                        if (search.isEmpty()) {

                            Text(
                                text = "Search items..",
                                color = Color.Black.copy(0.5F),
                                fontSize = 18.sp,
                                textAlign = TextAlign.Start
                            )

                        }

                    }
                }
            }
        }



        heroList.forEachIndexed { index, item ->
            item {
                item.header?.let {
                    Log.d("headerCo", "header: ")
                    header(it!!.title, it!!.hasMore)
                }
            }
            when (item.viewType) {
                "VerticalScroll" -> {

                        items(item.data!!.windowed(2, 2, true)) { sublist ->

                            Row(Modifier.fillMaxWidth()) {
                                sublist.forEach { item ->
                                    Column(modifier = Modifier.fillParentMaxWidth(0.5f)) {
                                        HeroListItem(item, onClick)
                                    }
                                }
                            }

                    }
                }
                "horizontalScroll" -> {
                    item {

                        showHorizontalItem(
                            item.data!!, search, onClick
                        )
                    }
                }
            }
        }

    }
    }
}

@Composable
fun header(title: String?, hasMore: Boolean?)
{
    Log.d("headerCo", "header: "+title+" "+hasMore)
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, top = 10.dp, end = 10.dp)) {

        Box(modifier = Modifier.fillMaxWidth()) {
            title?.let {
                Text(
                    text = title,
                    color = Color.Black,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            hasMore?.let {
                Text(
                    text = "View All",
                    color = Color.Red,
                    fontSize = 18.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier.align(Alignment.CenterEnd)

                )
            }
        }
    }
}

@Composable
fun topBar(){
    TopAppBar(
        // in below line we are
        // adding title to our top bar.

        title = {
            // inside title we are
            // adding text to our toolbar.
            Text(
                text = "Catalog",
                // below line is use
                // to give text color.
                color = Color.White
            )
        },
        navigationIcon = {
            // navigation icon is use
            // for drawer icon.
            IconButton(onClick = { }) {
                // below line is use to
                // specify navigation icon.
                //Icon(painter = rememberImagePainter(request = R.drawable.ic_eye), contentDescription ="" )
                Icon(Icons.Filled.Menu, "menu")
            }
        },
        // below line is use to give background color
        backgroundColor = Color(0xFF996376),

        // content color is use to give
        // color to our content in our toolbar.
        contentColor = Color.White,

        // below line is use to give
        // elevation to our toolbar.
        elevation = 12.dp
    )
}


@ExperimentalFoundationApi
@Composable
fun showVerticalItem(heroList: List<Item.Hero>, search: String, onClick: (Item.Hero) -> Unit) {

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(top = 12.dp, start = 4.dp, end = 4.dp)
    ) {


        val heroes =
            heroList.filter { it.name!!.contains(search, ignoreCase = true) }
        items(heroes.size) { index ->
            HeroListItem(heroes[index], onClick)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun showHorizontalItem(heroList: List<Item.Hero>, search: String, onClick: (Item.Hero) -> Unit) {
    LazyRow(
        //cells = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 4.dp, end = 4.dp, bottom = 5.dp)
    ) {
        val heroes =heroList
        items(heroes.size) { index ->
            //Text(text = heroes[index].title!!, style = TextStyle(fontSize = 80.sp))



            if (heroes.get(index).viewType=="bannerElement") {
                BannerItem(heroes[index], onClick)
            }
            else if (heroes.get(index).viewType=="categoryElement") {
                showCategoryElement(heroes[index], onClick)
            }
            else
            {
                HeroListItem(heroes[index], onClick)
            }
        }

    }
}

val books = (1..10).map { "Book $it" }.toList()
val wishlisted = (1..50).map { "Wishlisted Book $it" }

@Composable
fun NestedScrollScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        // My Books section
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("My Books", style = MaterialTheme.typography.h4)
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(books) { item ->
                        Box(
                            modifier = Modifier
                                .padding(4.dp)
                                .height(120.dp)
                                .width(90.dp)
                                //.background(color = Color.Gray, shape = RoundedCornerShape(8.dp))
                        ) {
                            Text(item, modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
            }

        }
        item {
            Text("Whishlisted Books", style = MaterialTheme.typography.h4)
        }
        // Turning the list in a list of lists of two elements each
        items(wishlisted.windowed(2, 2, true)) { sublist ->
            Row(Modifier.fillMaxWidth()) {
                sublist.forEach { item ->

                    Text(
                        item, modifier = Modifier
                            .height(120.dp)
                            .padding(4.dp)
                            //.background(Color.Yellow)
                            .fillParentMaxWidth(.5f)
                    )
                }
            }
        }
    }
}