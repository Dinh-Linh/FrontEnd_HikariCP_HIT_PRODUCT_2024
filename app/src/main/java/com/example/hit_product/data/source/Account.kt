package com.example.hit_product.data.source

data class Account(
    val userID: String,
    val username : String,
    val dateOfBirth : String,
    val dateOfClub1 : String,
    val dateOfClub2 : String
){
    companion object{
        val list = mutableListOf(
            Account("001", "username1", "2004", "2010", "2010"),
            Account("002", "username2", "2004", "2010", "2010"),
            Account("003", "username3", "2004", "2010", "2010"),
            Account("004", "username4", "2004", "2010", "2010"),
            Account("005", "username5", "2004", "2010", "2010")
        )
    }
}
