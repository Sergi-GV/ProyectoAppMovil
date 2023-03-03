package es.proyectofaltas.appmovilfaltas.vistas

import android.app.AlertDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

import es.proyectofaltas.appmovilfaltas.R
import es.proyectofaltas.appmovilfaltas.data.Guardia
import es.proyectofaltas.appmovilfaltas.data.Profesor
import es.proyectofaltas.appmovilfaltas.databinding.ActivityHolderBinding
import es.proyectofaltas.appmovilfaltas.viewmodel.FaltasViewModel
import es.proyectofaltas.appmovilfaltas.vistas.HolderActivity.Clave.KEY
import java.io.EOFException

class HolderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHolderBinding
    private lateinit var navController: NavController
    private lateinit var profesor: Profesor
    private val faltasViewModel: FaltasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.layoutFragmentHolder) as NavHostFragment
        navController = navHostFragment.navController

        profesor = intent.getSerializableExtra("profesor") as Profesor
        KEY = profesor.key
        faltasViewModel.guardarProfesor(profesor)

        faltasViewModel.profesorLiveData.observe(this) {
            faltasViewModel.guardiasProfesor(it.id)
            faltasViewModel.horarioProfesor(it.id)
            faltasViewModel.misGuardias(it.id)
        }

        opcionesMenu()
    }

    private fun cambiarTitulo(titulo: String) {
        this.title = titulo
    }

    private fun opcionesMenu() {
        with(binding.viewBottomNavigation) {
            selectedItemId = R.id.Guardias
            cambiarTitulo("Guardias Disponibles")
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.Guardias -> {
                        navController.navigate(R.id.fragmentGuardiasProfe)
                        cambiarTitulo("Guardias Disponibles")
                    }
                    R.id.CrearFalta -> {
                        navController.navigate(R.id.fragmentFalta)
                        cambiarTitulo("Nueva Falta")
                    }
                    R.id.Horarios -> {
                        navController.navigate(R.id.fragmentHorarios)
                        cambiarTitulo("Tu Horario")
                    }
                }
                true
            }
        }
    }

    object ConfirmarGuardia {

        fun onClickGuardia(guardia: Guardia, context: Context, viewModel: FaltasViewModel) {
            val builder = AlertDialog.Builder(context)
            with(builder) {
                setTitle("Guardia a ${guardia.hora} hora en el aula ${guardia.aula}")
                setMessage("¿Desea confirmar que realizará la guardia?")
                setPositiveButton("SÍ") { _, _ ->
                    val id = viewModel.profesorLiveData.value!!.id
                    guardia.id_profesor_hace_guardia = id

                    viewModel.modificarGuardia(guardia)

                    Toast.makeText(
                        context,
                        "La guardia ha sido confirmada correctamente.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                setNegativeButton("No") { _, _ ->
                    Toast.makeText(
                        context,
                        "La guardia no se ha confirmado.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            val dialog = builder.create()
            dialog.show()
        }

        fun onClickAnularGuardia(guardia: Guardia, context: Context, viewModel: FaltasViewModel) {
            val builder = AlertDialog.Builder(context)
            with(builder) {
                setTitle("Guardia a ${guardia.hora} hora en el aula ${guardia.aula}")
                setMessage("¿Desea anular esta guardia?")
                setPositiveButton("SÍ") { _, _ ->
                    viewModel.anularGuardia(guardia)

                    Toast.makeText(
                        context,
                        "La guardia ha sido anulada correctamente.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                setNegativeButton("No") { _, _ ->
                    Toast.makeText(
                        context,
                        "La guardia no se ha anulado.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            val dialog = builder.create()
            dialog.show()
        }
    }

    object Clave {
        var KEY: String? = null
    }
}