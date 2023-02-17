package com.aaronsaiz.appproyectofaltas_grupo1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.aaronsaiz.appproyectofaltas_grupo1.R
import com.aaronsaiz.appproyectofaltas_grupo1.databinding.ActivityHolderBinding

class HolderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHolderBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.layoutFragmentHolder) as NavHostFragment
        navController = navHostFragment.navController

        opcionesMenu()
    }



    private fun opcionesMenu() {
        binding.viewBottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.Horarios -> navController.navigate(R.id.fragmentHorarios)
                R.id.CrearFalta -> navController.navigate(R.id.fragmentFalta)
                R.id.Guardias->navController.navigate(R.id.fragmentGuardiasProfe)

            }
            true
        }
    }
}