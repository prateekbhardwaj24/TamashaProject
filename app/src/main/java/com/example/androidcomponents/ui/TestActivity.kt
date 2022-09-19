package com.example.androidcomponents.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.androidcomponents.R
import com.example.androidcomponents.viewmodel.TestViewModel

class TestActivity : AppCompatActivity() {
    private lateinit var viewModel:TestViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        viewModel = ViewModelProvider(this)[TestViewModel::class.java]

        viewModel.inceaseData1(viewModel.data1++)
    }
}