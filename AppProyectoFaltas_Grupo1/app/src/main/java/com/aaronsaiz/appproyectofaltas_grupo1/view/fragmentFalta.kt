package com.aaronsaiz.appproyectofaltas_grupo1.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.aaronsaiz.appproyectofaltas_grupo1.R
import com.aaronsaiz.appproyectofaltas_grupo1.databinding.ActivityHolderBinding
import com.aaronsaiz.appproyectofaltas_grupo1.databinding.FragmentFaltaBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragmentFalta.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragmentFalta : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentFaltaBinding
    private lateinit var navController: NavController

 /*   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }*/


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View

    {

        binding = FragmentFaltaBinding.inflate(inflater, container, false)


        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        binding.fFaltaBtnContinuar.setOnClickListener(
            View.OnClickListener {
                val accion=fragmentFaltaDirections.actionFragmentFaltaToFragmentHora()
                navController.navigate(accion)
            }
        )
    }




}