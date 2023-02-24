package es.proyectofaltas.appmovilfaltas.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import es.proyectofaltas.appmovilfaltas.data.Profesor
import es.proyectofaltas.appmovilfaltas.databinding.ActivityLoginBinding
import es.proyectofaltas.appmovilfaltas.login.LoginActivity.Clave.KEY
import es.proyectofaltas.appmovilfaltas.viewmodel.FaltasViewModel
import es.proyectofaltas.appmovilfaltas.vistas.HolderActivity
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val faltasViewModel: FaltasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { login() }
        this.title = "Pantalla de Inicio"
    }

    private fun login() {
        val user = binding.username.text.toString()
        val pwd = binding.password.text.toString()

        lifecycleScope.launch {
            try {
                val profesor = faltasViewModel.loginProfesor(user, pwd)[0]

                KEY = "${profesor.usuario} ${profesor.contra}"
                val intent = Intent(applicationContext, HolderActivity::class.java)
                intent.putExtra("profesor", profesor)
                startActivity(intent)
            } catch (e: HttpException) {
                Toast.makeText(
                    this@LoginActivity,
                    "Usuario o Contraseña inválido",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    object Clave {
        lateinit var KEY: String
    }
}