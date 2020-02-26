package com.ravimhzn.starwars.presentation

import android.os.Bundle
import android.util.Log
import com.ravimhzn.starwars.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private val TAG = MainActivity::class.java.name

    @Inject
    lateinit var TEST: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, TEST);
    }
}
