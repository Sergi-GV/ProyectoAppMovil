package es.proyectofaltas.appmovilfaltas.vistas.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.proyectofaltas.appmovilfaltas.R
import es.proyectofaltas.appmovilfaltas.data.Horario
import es.proyectofaltas.appmovilfaltas.databinding.ItemHorasBinding

//Este adapter es para el horario del profesor mostrado en fragmentHorarios y fragmentHora, muestra el horario del día del profesor en un Recycler
class AdapterHorarioProfesor(
    private val horario: List<Horario>,
    private val seleccionable: Boolean = false,
    private val onClickHorario: (Horario) -> Boolean
) : RecyclerView.Adapter<AdapterHorarioProfesor.HorarioProfesorViewHolder>() {
    var cambiarColor = false

    fun cambiarColor(cambiar: Boolean) {
        cambiarColor = cambiar
        notifyDataSetChanged()
    }

    class HorarioProfesorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemHorasBinding.bind(view)
        private var color = false

        fun conversorHoras(hora: Int): String {
            when (hora) {
                1 -> return "08:30-09:25"
                2 -> return "09:25-10:20"
                3 -> return "10:35-11:30"
                4 -> return "11:30-12:25"
                5 -> return "12:40-13:30"
                6 -> return "13:30-14:20"
                7 -> return "14:45-15:35"
                8 -> return "15:35-16:25"
                9 -> return "16:40-17:30"
                10 -> return "17:30-18:20"
                11 -> return "18:35-19:25"
                12 -> return "19:25-20:15"
            }
            return "ERROR"
        }

        fun conversorDia(dia: Int): String {
            when (dia) {
                1 -> return "Lunes"
                2 -> return "Martes"
                3 -> return "Miércoles"
                4 -> return "Jueves"
                else -> return "Viernes"
            }
        }

        @SuppressLint("ResourceAsColor")
        fun bind(hora: Horario, seleccionable: Boolean, onClickHorario: (Horario) -> Boolean) {
            binding.itemHorasAula.text = "Aula: \n${hora.aula}"
            binding.itemHorasDia.text = "Día: \n${conversorDia(hora.dia_semana)}"
            binding.itemHorasMateria.text = "Materia: \n${hora.materia}"
            binding.itemHorasHoras.text = "Hora: \n${conversorHoras(hora.hora)}"
            binding.itemHorasGrupo.text = "Grupo: \n${hora.grupo}"
            binding.root.setOnClickListener {
                val seleccionadosTodos = onClickHorario(hora)
                if (seleccionable && !seleccionadosTodos)
                    color = if (color) {
                        it.setBackgroundColor(Color.WHITE)
                        false
                    } else {
                        it.setBackgroundColor(Color.parseColor("#CDF89C"))
                        true
                    }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorarioProfesorViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.item_horas, parent, false)

        return HorarioProfesorViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: HorarioProfesorViewHolder, position: Int) {
        val h = horario[position]
        holder.bind(h, seleccionable, onClickHorario)
        if(cambiarColor) {
            holder.itemView.setBackgroundColor(Color.parseColor("#CDF89C"))
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }
    }

    override fun getItemCount(): Int = horario.size
}