package com.cocobranch.myrecycleviewdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    val fruitList = listOf<Fruit>(
        Fruit("Mango", "Name 1"),
        Fruit("Apple", "Name 2"),
        Fruit("Banana", "Name 3"),
        Fruit("Guava", "Name 4"),
        Fruit("Lemon", "Name 5"),
        Fruit("Pear", "Name 6"),
        Fruit("Orange", "Name 7")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.myRV)
        recyclerView.setBackgroundColor(Color.YELLOW)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecycleViewAdapter(fruitList) { selectedItem: Fruit ->
            listItemClicked(selectedItem)
        }
    }

    private fun listItemClicked(fruit: Fruit){
        Toast.makeText(
            this@MainActivity,
            "Supplier name is : ${fruit.supplier}",
            Toast.LENGTH_LONG
        ).show()
    }
}