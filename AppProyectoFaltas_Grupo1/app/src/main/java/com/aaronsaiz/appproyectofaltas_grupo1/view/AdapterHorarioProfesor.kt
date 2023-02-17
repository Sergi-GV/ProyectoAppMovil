package com.aaronsaiz.appproyectofaltas_grupo1.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aaronsaiz.appproyectofaltas_grupo1.R
import com.aaronsaiz.appproyectofaltas_grupo1.databinding.ItemHorasBinding
import com.aaronsaiz.appproyectofaltas_grupo1.model.Horario
import com.aaronsaiz.appproyectofaltas_grupo1.model.Profesor

//Este adapter es para el horario del profesor mostrado en fragmentHorarios y fragmentHora, muestra el horario del día del profesor en un Recycler
//Pasamos al adapter una fecha para que saque los horarios del profesor y los muestre en el adapter
class AdapterHorarioProfesor(private val profe: Profesor,
                             private val onClickHorario: (Horario) -> Unit)
    : RecyclerView.Adapter<AdapterHorarioProfesor.HorarioProfesorViewHolder>()
{
private val horarioProfe=profe //Peticion a API de Horarios con este profesor como id
    class HorarioProfesorViewHolder(view: View)
        : RecyclerView.ViewHolder(view)
    {
        private val binding = ItemHorasBinding.bind(view)

        fun conversorHoras(hora: Int): String{
            when(hora){
                1->return "08:30-09:25"
                2->return "09:25-10:20"
                3->return "10:35-11:30"
                4->return "11:30-12:25"
                5->return "12:40-13:30"
                6->return "13:30-14:20"
                7->return "14:45-15:35"
                8->return "15:35-16:25"
                9->return "16:40-17:30"
                10->return "17:30-18:20"
                11->return "18:35-19:25"
                12->return "19:25-20:15"
            }
            return "ERROR"
        }

        fun bind(hora: Horario)
        {
            binding.itemHorasAula.text = hora.aula
            binding.itemHorasMateria.text=hora.materia
            binding.itemHorasHoras.text=conversorHoras(hora.hora)
            binding.itemHorasGrupo.text=hora.grupo



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorarioProfesorViewHolder
    {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_horas, parent, false)

        return HorarioProfesorViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: HorarioProfesorViewHolder, position: Int)
    {
        val profesor = horarioProfe[position]
        holder.bind(profesor, onClickHorario)
    }

    override fun getItemCount(): Int = horarioProfe.size

     fun conversorHoras(hora: Int): String{
        when(hora){
            1->return "08:30-09:25"
            2->return "09:25-10:20"
            3->return "10:35-11:30"
            4->return "11:30-12:25"
            5->return "12:40-13:30"
            6->return "13:30-14:20"
            7->return "14:45-15:35"
            8->return "15:35-16:25"
            9->return "16:40-17:30"
            10->return "17:30-18:20"
            11->return "18:35-19:25"
            12->return "19:25-20:15"
        }
    return "ERROR"
    }



}