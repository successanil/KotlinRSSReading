/*
 * Copyright (c) 2020. Relsell Global
 */

package com.relsellglobal.kotlinrssreading

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_root,RSSFragment()).commit();



    }
}
