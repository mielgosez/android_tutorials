package com.cocobranch.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.cocobranch.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            button.setOnClickListener {
                if (!TextUtils.isEmpty(etName.text.toString())){
                    tvText.text = "Hello ${etName.text.toString()}"
                }else{
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter a Name",
                        Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun getStudent(): Student{
        return Student(1, "Marie", "m@gmail.com")
    }

}