package com.hadi.superherolexicon.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.hadi.superherolexicon.data.model.Item
import com.hadi.superherolexicon.ui.theme.background
import com.hadi.superherolexicon.ui.theme.card

@Composable
fun HeroListItem(hero: Item.Hero, onClick: (Item.Hero) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(card)
            .clickable(onClick = { onClick(hero) }),
    ) {


        Box(
            modifier = Modifier
                .height(200.dp)
        ) {


            Image(
                painter = rememberImagePainter(
                    data = hero.images?.md,
                   /* builder = {
                        crossfade(true)
                    }*/
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            ) {

            }
            Box(
                modifier =
                Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = hero.name ?: "-",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.White
                )
            }
        }

    }
}


@Composable
fun BannerItem(hero: Item.Hero, onClick: (Item.Hero) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(card)
            .clickable(onClick = { onClick(hero) }),
    ) {


        Box(
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
        ) {

            Image(
                painter = rememberImagePainter(
                    data = hero.imageUrl,
                    builder = {
                        crossfade(true)
                    }

                ),
                modifier = Modifier.size(120.dp),
                contentDescription = null,
                contentScale = ContentScale.Crop,

            )

            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                contentAlignment = Alignment.BottomStart
            ) {
                Box(
                    modifier =
                    Modifier
                        .fillMaxWidth().height(40.dp)
                        .background(Color.Black.copy(alpha = 0.4f)),

                ) {
                    Text(
                        text = hero.title ?: "-",
                        style = MaterialTheme.typography.overline,
                        color = Color.White,


                        )
                }
            }
        }

    }
}

@Composable
fun showCategoryElement(hero: Item.Hero, onClick: (Item.Hero) -> Unit) {

    Column(
        Modifier
            .width(120.dp)
            .height(170.dp)
            .padding(5.dp)) {


    Column {
        //Spacer(modifier = LayoutHeight(5.dp)) // added to support space for header
        Modifier
            .fillMaxWidth()


        loadImage(url = hero.imageUrl!!,onClick,hero)
        ///Spacer(modifier = LayoutHeight(5.dp))
        Column(Modifier.padding(start = 8.dp)) {
        categoryInfo(

                title = hero.title,
                subTitle = hero.title


        )
        }
    }
    }
}

@Composable
fun loadImage(url: String, onClick: (Item.Hero) -> Unit, hero: Item.Hero) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(card)
            //.border(width = 1.dp,color = Color.Black)
            .clickable(onClick = { onClick(hero) }),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(card)
                .background(Color(0xFFEC407A)),
            contentAlignment = Alignment.Center

        ) {

            Image(
                painter = rememberImagePainter(
                    data = url,
                    builder = {
                        crossfade(true)
                    }
                ),

                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}

    @Composable
     fun categoryInfo(title: String?, subTitle: String?) {

           title?.let {

               Text(
                   text = it,
                   style = MaterialTheme.typography.subtitle2
               )

           }
           subTitle?.let {

               Text(
                   text = it,
                   style = MaterialTheme.typography.overline

               )

           }

    }

