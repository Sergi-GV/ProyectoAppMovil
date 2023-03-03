package es.proyectofaltas.appmovilfaltas.vistas

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import es.proyectofaltas.appmovilfaltas.R
import es.proyectofaltas.appmovilfaltas.data.AvisoGuardia
import es.proyectofaltas.appmovilfaltas.data.Horario
import es.proyectofaltas.appmovilfaltas.data.Profesor
import es.proyectofaltas.appmovilfaltas.databinding.FragmentHoraBinding
import es.proyectofaltas.appmovilfaltas.databinding.ItemHorasBinding
import es.proyectofaltas.appmovilfaltas.viewmodel.FaltasViewModel
import es.proyectofaltas.appmovilfaltas.vistas.adapter.AdapterHorarioProfesor
import java.time.LocalDate

class FragmentHora : Fragment() {
    private lateinit var binding: FragmentHoraBinding
    private val viewModel: FaltasViewModel by activityViewModels()
    private lateinit var navController: NavController
    private lateinit var aviso: AvisoGuardia
    private lateinit var adapterHorario: AdapterHorarioProfesor
    private var listaHorarios: MutableList<Horario> = mutableListOf()
    private var seleccionadosTodos = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            aviso = it.get("aviso") as AvisoGuardia
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHoraBinding.inflate(inflater, container, false)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        if(listaHorarios.isEmpty()) {
            binding.fHoraBtnConfirmar.isEnabled = false
        }

        viewModel.horarioLiveData.observe(viewLifecycleOwner) {
            val dia = LocalDate.parse(aviso.fecha_guardia).dayOfWeek.value
            val lista = it.filter { h -> h.dia_semana == dia }
            binding.listaHoras.apply {
                adapter = AdapterHorarioProfesor(lista, true){h -> onClickHorario(h) }
                val linearLayoutManager = LinearLayoutManager(context)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
                layoutManager = linearLayoutManager
            }

            adapterHorario = binding.listaHoras.adapter as AdapterHorarioProfesor

            binding.fHoraChkDia.setOnClickListener {
                seleccionadosTodos = (it as CheckBox).isChecked
                adapterHorario.cambiarColor(seleccionadosTodos)
                if(seleccionadosTodos) {
                    listaHorarios = lista.toMutableList()
                    binding.fHoraBtnConfirmar.isEnabled = listaHorarios.isNotEmpty()
                } else {
                    listaHorarios.removeAll(lista)
                }
                binding.fHoraBtnConfirmar.isEnabled = listaHorarios.isNotEmpty()
            }
        }

        binding.fHoraBtnConfirmar.setOnClickListener {
            for (h in listaHorarios) {
                aviso.confirmado = true
                aviso.anulado = false
                aviso.id_horario = h.id

                viewModel.crearAviso(aviso)
            }
            Toast.makeText(context, "Avisos creados correctamente.", Toast.LENGTH_SHORT).show()

            navController.navigate(FragmentHoraDirections.actionFragmentHoraToFragmentFalta())
        }
    }

    private fun onClickHorario(h: Horario): Boolean {
        if(!seleccionadosTodos) {
            if (listaHorarios.firstOrNull { ho -> ho.id == h.id } == null)
                listaHorarios.add(h)
            else {
                listaHorarios.remove(h)
            }
            binding.fHoraBtnConfirmar.isEnabled = listaHorarios.isNotEmpty()
        }
        return seleccionadosTodos
    }
}