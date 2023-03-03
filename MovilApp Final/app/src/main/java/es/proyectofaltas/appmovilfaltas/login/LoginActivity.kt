package es.proyectofaltas.appmovilfaltas.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import es.proyectofaltas.appmovilfaltas.databinding.ActivityLoginBinding
import es.proyectofaltas.appmovilfaltas.viewmodel.FaltasViewModel
import es.proyectofaltas.appmovilfaltas.vistas.HolderActivity
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.math.BigInteger
import java.net.SocketTimeoutException
import java.security.MessageDigest

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val faltasViewModel: FaltasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { login() }
        this.title = "Iniciar Sesión"
    }

    private fun login() {
        val user = binding.username.text.toString()
        val pwd = encriptarMD5(binding.password.text.toString())

        lifecycleScope.launch {
            try {
                val profesor = faltasViewModel.loginProfesor(user, pwd)[0]

                val intent = Intent(applicationContext, HolderActivity::class.java)
                intent.putExtra("profesor", profesor)
                startActivity(intent)
            } catch (e: SocketTimeoutException) {
                Toast.makeText(
                    this@LoginActivity,
                    "Error en la conexión",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: HttpException) {
                Toast.makeText(
                    this@LoginActivity,
                    "Usuario o Contraseña inválido",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun encriptarMD5(contra: String): String {
        val md = MessageDigest.getInstance("MD5")
        val messageDigest = md.digest(contra.toByteArray())
        val no = BigInteger(1, messageDigest)
        var hashtext = no.toString(16)
        while (hashtext.length < 32) {
            hashtext = "0$hashtext"
        }
        return hashtext
    }
}