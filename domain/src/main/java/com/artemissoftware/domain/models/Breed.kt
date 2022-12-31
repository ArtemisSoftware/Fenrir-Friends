package com.artemissoftware.domain.models

data class Breed(
    val id: Int,
    val name: String,
    val url: String,
    val group: String,
    val origin: String
){

    companion object{

        val mockBreeds = listOf(
            Breed(
                id = 1 ,
                name = "Affenpinscher",
                url = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg",
                origin = "Germany, France",
                group = "Killer",
            ),
            Breed(
                id = 2,
                name = "Saint Bernard",
                url = "https://cdn2.thedogapi.com/images/_Qf9nfRzL.png",
                origin = "USA, France",
                group = "Toy",
            ),
        )
    }

}
