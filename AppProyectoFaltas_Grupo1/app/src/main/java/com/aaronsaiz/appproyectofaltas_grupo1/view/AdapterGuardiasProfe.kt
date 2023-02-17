package com.aaronsaiz.appproyectofaltas_grupo1.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aaronsaiz.appproyectofaltas_grupo1.R
import com.aaronsaiz.appproyectofaltas_grupo1.databinding.ItemGuardiaBinding
import com.aaronsaiz.appproyectofaltas_grupo1.model.Guardia

//Este adapter funciona con el recycler de guardias disponibles de HorariosFragment
class AdapterGuardiasProfe(private val listaGuardias: List<Guardia>,
                      val onClickGuardiaProfe: (Guardia) -> Unit)
    : RecyclerView.Adapter<AdapterGuardiasProfe.GuardiasProfeViewHolder>()
{

    class GuardiasProfeViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {
        private val binding = ItemGuardiaBinding.bind(view)


        fun bind(guardia: Guardia, onClickGuardia: (Guardia) -> Unit) {
            binding.itemGuardiaAula.text=guardia.aula
            binding.itemGuardiaFecha.text=guardia.fecha.toString()
            binding.itemGuardiaHoras.text=guardia.hora
            binding.itemGuardiaGrupo.text=guardia.grupo
            binding.itemGuardiaProfesor.text=guardia.id_profesor_falta //pedir a API profesor con el id indicado

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuardiasProfeViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_guardia, parent, false)

        return GuardiasProfeViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: GuardiasProfeViewHolder, position: Int) {
        val guardia = listaGuardias[position]
        holder.bind(guardia, onClickGuardiaProfe)
    }

    override fun getItemCount(): Int = listaGuardias.size
}

