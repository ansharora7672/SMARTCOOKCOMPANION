package com.example.recipeapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.recipeapplication.databinding.ActivityHomeBinding
import com.example.recipeapplication.databinding.ActivitySearchBinding
import java.util.logging.Filter

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var rvAdapter: SearchAdapter
    private lateinit var dataList:ArrayList<Recipe>
    private lateinit var recipes:List<Recipe?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchbar2.requestFocus()
        var db= Room.databaseBuilder(this@SearchActivity,ApplicationDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject=db.getDao()
        recipes = daoObject.getAll()!!

        setUpRecyclerView()
        binding.gobackhome.setOnClickListener { finish() }
        binding.searchbar2.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString()!=""){
                    filterData(s.toString())
                }
                else{
                    setUpRecyclerView()
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun filterData(filterText: String) {
        var filterData=ArrayList<Recipe>()
        for(i in recipes.indices){
            if (recipes[i]!!.tittle.lowercase().contains(filterText.lowercase())){
                filterData.add(recipes[i]!!)

            }
            //here i will add some additional feature of chatgpt api which would give the recipe of the dish if it is not in the database....
            //as of now this feature is kept as additional featrue
            rvAdapter.filterList(filterData)
        }

    }

    private fun setUpRecyclerView() {
        dataList= ArrayList()
        binding.rvSearch.layoutManager= LinearLayoutManager(this)

        for (i in recipes!!.indices){
            if (recipes[i]!!.category.contains("Popular")){
                dataList.add(recipes[i]!!)
            }

            rvAdapter= SearchAdapter(dataList, this)
            binding.rvSearch.adapter=rvAdapter


        }
    }
}