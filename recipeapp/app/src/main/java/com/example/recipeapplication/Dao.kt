package com.example.recipeapplication
import androidx.room.*
import androidx.room.Dao
import androidx.room.Query
import org.jetbrains.annotations.Nullable

@Dao
interface Dao {

    @Query("SELECT * FROM recipe")
    fun getAll():List<Recipe>?


}