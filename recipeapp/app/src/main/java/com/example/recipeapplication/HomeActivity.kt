package com.example.recipeapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.recipeapplication.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: TryAdapter
    private lateinit var dataList:ArrayList<Recipe>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.  inflate(layoutInflater)
        setContentView(binding.root)

        //mkaing a function to set up the recycler view .......


        setUpRecyclerView()
        //this is for the search bar that when we click on the search bar we are directed to the next page where we can search.
        binding.search.setOnClickListener(){
            startActivity(Intent(this, SearchActivity::class.java))
        }
        binding.saladview.setOnClickListener {
            var myIntent = Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITTLE", "Salad")
            myIntent.putExtra("CATEGORY","Salad")
            startActivity(myIntent)
        }
        binding.MainCourse.setOnClickListener {
            var myIntent = Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITTLE", "Main Course")
            myIntent.putExtra("CATEGORY","Dish")
            startActivity(myIntent)
        }
        binding.shakes.setOnClickListener {
            var myIntent = Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITTLE", "Drinks")
            myIntent.putExtra("CATEGORY","Drinks")
            startActivity(myIntent)
        }
        binding.desertview.setOnClickListener {
            var myIntent = Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITTLE", "Desserts")
            myIntent.putExtra("CATEGORY","Desserts")
            startActivity(myIntent)
        }



    }

    private fun setUpRecyclerView() {
        dataList= ArrayList()
        binding.rv.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        var db=Room.databaseBuilder(this@HomeActivity,ApplicationDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject=db.getDao()
        var recipes = daoObject.getAll()
        for (i in recipes!!.indices){
            if (recipes[i]!!.category.contains("Popular")){
                dataList.add(recipes[i]!!)
            }

            rvAdapter= TryAdapter(dataList, this)
            binding.rv.adapter=rvAdapter


        }
    }
}

