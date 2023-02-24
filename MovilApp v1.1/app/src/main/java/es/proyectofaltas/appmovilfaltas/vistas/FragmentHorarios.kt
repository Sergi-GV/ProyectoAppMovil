package es.proyectofaltas.appmovilfaltas.vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import es.proyectofaltas.appmovilfaltas.databinding.FragmentHorariosBinding
import es.proyectofaltas.appmovilfaltas.viewmodel.FaltasViewModel
import es.proyectofaltas.appmovilfaltas.vistas.HolderActivity.ConfirmarGuardia.onClickAnularGuardia
import es.proyectofaltas.appmovilfaltas.vistas.HolderActivity.ConfirmarGuardia.onClickGuardia
import es.proyectofaltas.appmovilfaltas.vistas.adapter.AdapterGuardiasProfe
import es.proyectofaltas.appmovilfaltas.vistas.adapter.AdapterHorarioProfesor

class FragmentHorarios : Fragment() {
    private lateinit var binding: FragmentHorariosBinding
    private val viewModel: FaltasViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHorariosBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.horarioLiveData.observe(viewLifecycleOwner) {
            binding.listaHorarios.apply {
                adapter = AdapterHorarioProfesor(it){ onClickHorario() }
                val linearLayoutManager = LinearLayoutManager(context)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
                layoutManager = linearLayoutManager
            }
        }

        viewModel.misGuardiasLiveData.observe(viewLifecycleOwner) {
            binding.listaGuardias.apply {
                adapter = AdapterGuardiasProfe(it, true) { guardia -> onClickAnularGuardia(guardia, context, viewModel) }
                val linearLayoutManager = LinearLayoutManager(context)
                linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
                layoutManager = linearLayoutManager
            }
        }
    }

    private fun onClickHorario() = true
}