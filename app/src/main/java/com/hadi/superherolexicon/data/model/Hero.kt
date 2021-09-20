package com.hadi.superherolexicon.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class Item(
    @SerializedName("viewType")
    val viewType: String?,

    @SerializedName("data")
    val data: List<Hero>?,

    @SerializedName("header")
    val header: Hero.Header?=null,

) {

    @Serializable
    data class Hero(

        @SerializedName("viewType")
        val viewType: String?="",

        @SerializedName("header")
        val header: Header?=null,

        @SerializedName("imageUrl")
        val imageUrl: String?="",

        @SerializedName("title")
        val title: String?=null,


        @SerializedName("appearance")
        val appearance: Appearance?=null,
        @SerializedName("biography")
        val biography: Biography?=null,
        @SerializedName("connections")
        val connections: Connections?=null,
        @SerializedName("id")
        val id: Int?=null,
        @SerializedName("images")
        val images: Images?=null,
        @SerializedName("name")
        val name: String?=null,
        @SerializedName("powerstats")
        val powerstats: Powerstats?=null,
        @SerializedName("slug")
        val slug: String?=null,
        @SerializedName("work")
        val work: Work?=null
    ) {
        @Serializable
        data class Appearance(
            @SerializedName("eyeColor")
            val eyeColor: String?,
            @SerializedName("gender")
            val gender: String?,
            @SerializedName("hairColor")
            val hairColor: String?,
            @SerializedName("height")
            val height: List<String?>?,
            @SerializedName("race")
            val race: String?,
            @SerializedName("weight")
            val weight: List<String?>?
        )

        @Serializable
        data class Biography(
            @SerializedName("aliases")
            val aliases: List<String>,
            @SerializedName("alignment")
            val alignment: String?,
            @SerializedName("alterEgos")
            val alterEgos: String?,
            @SerializedName("firstAppearance")
            val firstAppearance: String?,
            @SerializedName("fullName")
            val fullName: String?,
            @SerializedName("placeOfBirth")
            val placeOfBirth: String?,
            @SerializedName("publisher")
            val publisher: String?
        )

        @Serializable
        data class Connections(
            @SerializedName("groupAffiliation")
            val groupAffiliation: String?,
            @SerializedName("relatives")
            val relatives: String?
        )

        @Serializable
        data class Images(
            @SerializedName("lg")
            val lg: String?,
            @SerializedName("md")
            val md: String?,
            @SerializedName("sm")
            val sm: String?,
            @SerializedName("xs")
            val xs: String?
        )

        @Serializable
        data class Powerstats(
            @SerializedName("combat")
            val combat: Int?,
            @SerializedName("durability")
            val durability: Int?,
            @SerializedName("intelligence")
            val intelligence: Int?,
            @SerializedName("power")
            val power: Int?,
            @SerializedName("speed")
            val speed: Int?,
            @SerializedName("strength")
            val strength: Int?
        )

        @Serializable
        data class Work(
            @SerializedName("base")
            val base: String?,
            @SerializedName("occupation")
            val occupation: String?
        )

        @Serializable
        data class Header(
            @SerializedName("title")
            val title: String?,
            @SerializedName("occupation")
            val hasMore: Boolean?
        )
    }
}

enum class ItemViewType {
    horizontalScroll,
    VerticalScroll
}
