package es.proyectofaltas.appmovilfaltas.vistas

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import es.proyectofaltas.appmovilfaltas.data.AvisoGuardia
import es.proyectofaltas.appmovilfaltas.data.Profesor
import es.proyectofaltas.appmovilfaltas.databinding.FragmentFaltaBinding
import es.proyectofaltas.appmovilfaltas.viewmodel.FaltasViewModel
import java.time.LocalDate
import java.time.LocalDateTime

class FragmentFalta : Fragment() {
    private lateinit var binding: FragmentFaltaBinding
    private lateinit var navController: NavController
    private val viewModel: FaltasViewModel by activityViewModels()
    private lateinit var profesor: Profesor
    @RequiresApi(Build.VERSION_CODES.O)
    private var fecha: LocalDate = LocalDate.now()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFaltaBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calendarioFalta.updateDate(fecha.year, fecha.monthValue - 1, fecha.dayOfMonth)

        binding.calendarioFalta.setOnDateChangedListener { _, y, m, d ->
            fecha = LocalDate.of(y, arreglarMes(m), d)
        }

        binding.fFaltaBtnContinuar.setOnClickListener {
            val aviso = AvisoGuardia()
            aviso.fecha_guardia = fecha.toString()
            aviso.motivo = binding.fFaltaMotivo.text.toString()
            aviso.observaciones = binding.fFaltaObservaciones.text.toString()
            aviso.id_profesor = profesor.id
            aviso.fecha_hora_aviso = LocalDateTime.now().toString()

            navController.navigate(FragmentFaltaDirections.actionFragmentFaltaToFragmentHora(aviso))
        }

        navController = view.findNavController()
        viewModel.profesorLiveData.observe(viewLifecycleOwner) {
            profesor = it
        }
    }

    private fun arreglarMes(mes: Int): Int {
        return when(mes) {
            0 -> 1
            1 -> 2
            2 -> 3
            3 -> 4
            4 -> 5
            5 -> 6
            6 -> 7
            7 -> 8
            8 -> 9
            9 -> 10
            10 -> 11
            else -> 12
        }
    }
}