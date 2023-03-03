package es.proyectofaltas.appmovilfaltas.vistas.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import es.proyectofaltas.appmovilfaltas.R
import es.proyectofaltas.appmovilfaltas.data.Guardia
import es.proyectofaltas.appmovilfaltas.databinding.ItemGuardiasBinding
import es.proyectofaltas.appmovilfaltas.databinding.ItemMisGuardiasBinding

//Este adapter funciona con el recycler de guardias disponibles de HorariosFragment
class AdapterGuardiasProfe(
    private val listaGuardias: List<Guardia>,
    private val misGuardias: Boolean = false,
    private val onClickGuardiaProfe: (Guardia) -> Unit
) : RecyclerView.Adapter<AdapterGuardiasProfe.GuardiasProfeViewHolder>() {

    class GuardiasProfeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bind(guardia: Guardia, onClickGuardia: (Guardia) -> Unit, misGuardias: Boolean) {
            if(!misGuardias) {
                val binding = ItemGuardiasBinding.bind(view)

                binding.itemGuardiaAula.text = "Aula: " + guardia.aula
                binding.itemGuardiaFecha.text = "Fecha: " + guardia.fecha
                binding.itemGuardiaHoras.text = "Hora: " + guardia.hora
                binding.itemGuardiaGrupo.text = "Grupo: " + guardia.grupo
                binding.itemGuardiaProfesor.text = "Prof. Baja: " + guardia.id_profesor_falta
                binding.itemGuardiaCheckConfirmar.setOnClickListener {
                    onClickGuardia(guardia)
                    //(it as CheckBox).isChecked = false
                }
            } else {
                val binding = ItemMisGuardiasBinding.bind(view)

                binding.itemGuardiaAula.text = "Aula: " + guardia.aula
                binding.itemGuardiaFecha.text = "Fecha: " + guardia.fecha
                binding.itemGuardiaHoras.text = "Hora: " + guardia.hora
                binding.itemGuardiaGrupo.text = "Grupo: " + guardia.grupo
                binding.itemGuardiaProfesor.text = "Prof. Baja: " + guardia.id_profesor_falta
                binding.root.setOnClickListener { onClickGuardia(guardia) }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuardiasProfeViewHolder {
        val item = if (misGuardias) R.layout.item_mis_guardias else R.layout.item_guardias
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(item, parent, false)

        return GuardiasProfeViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: GuardiasProfeViewHolder, position: Int) {
        val guardia = listaGuardias[position]
        holder.bind(guardia, onClickGuardiaProfe, misGuardias)
    }

    override fun getItemCount(): Int = listaGuardias.size
}

