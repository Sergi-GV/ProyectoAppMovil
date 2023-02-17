package com.aaronsaiz.appproyectofaltas_grupo1.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aaronsaiz.appproyectofaltas_grupo1.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent: Intent= Intent(applicationContext, HolderActivity::class.java)
startActivity(intent)
    }
}