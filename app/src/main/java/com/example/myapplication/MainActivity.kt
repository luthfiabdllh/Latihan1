package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)};

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnPlus.setOnClickListener{
            val hasilAmbiltext = binding.txtNumber.text
            val angka = hasilAmbiltext.toString().toInt()
            binding.txtNumber.text = (angka+1).toString()
        }
        binding.btnMinus.setOnClickListener{
            val hasilAmbiltext = binding.txtNumber.text
            val angka = hasilAmbiltext.toString().toInt()
            binding.txtNumber.text = (angka-1).toString()
        }
        binding.btnToast.setOnClickListener({
            val text = binding.txtNumber.text.toString()
            Toast.makeText(this, "text yang muncul adalah" + text,
                Toast.LENGTH_SHORT).show()
        })
    }
}