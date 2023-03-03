package es.proyectofaltas.appmovilfaltas.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.proyectofaltas.appmovilfaltas.apiservice.FaltasRepository
import es.proyectofaltas.appmovilfaltas.data.AvisoGuardia
import es.proyectofaltas.appmovilfaltas.data.Guardia
import es.proyectofaltas.appmovilfaltas.data.Horario
import es.proyectofaltas.appmovilfaltas.data.Profesor
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.EOFException

class FaltasViewModel : ViewModel() {
    val profesorLiveData = MutableLiveData<Profesor>()
    val guardiasLiveData = MutableLiveData<MutableList<Guardia>>()
    val misGuardiasLiveData = MutableLiveData<MutableList<Guardia>>()
    val horarioLiveData = MutableLiveData<List<Horario>>()
    private val repository = FaltasRepository()

    suspend fun loginProfesor(user: String, pwd: String) = repository.loginProfesor(user, pwd)
    suspend fun modificarGuardia(guardia: Guardia, id: Int) =
        repository.modificarGuardia(guardia, id)

    suspend fun anularGuardia(guardia: Guardia, id: Int) = repository.anularGuardia(guardia, id)

    fun guardarProfesor(profesor: Profesor) {
        profesorLiveData.postValue(profesor)
    }

    fun guardiasProfesor(id: Int) {
        viewModelScope.launch {
            var recibido = false
            do {
                try {
                    val guardias = repository.guardiasProfesor(id)
                    if (guardias.size > 0) {
                        guardiasLiveData.postValue(guardias)
                    } else {
                        guardiasLiveData.postValue(mutableListOf())
                    }
                    recibido = true
                } catch (_: HttpException) {

                }
            } while (!recibido)
        }
    }

    fun misGuardias(id: Int) {
        viewModelScope.launch {
            var recibido = false
            do {
                try {
                    val guardias = repository.misGuardias(id)
                    if (guardias.isNotEmpty()) {
                        misGuardiasLiveData.postValue(guardias)
                    } else {
                        misGuardiasLiveData.postValue(mutableListOf())
                    }
                    recibido = true
                } catch (_: HttpException) {
                }
            } while (!recibido)
        }
    }

    fun horarioProfesor(id: Int) {
        viewModelScope.launch {
            try {
                val horarios = repository.horarioProfesor(id)
                if (horarios.isNotEmpty()) {
                    horarioLiveData.postValue(horarios)
                }
            } catch (_: HttpException) {

            }
        }
    }

    fun modificarGuardia(guardia: Guardia) {
        viewModelScope.launch {
            modificarGuardia(guardia, guardia.id)
            guardiasProfesor(profesorLiveData.value!!.id)
        }
        guardiasLiveData.value!!.removeAll { it.id == guardia.id }
    }

    fun anularGuardia(guardia: Guardia) {
        viewModelScope.launch {
            anularGuardia(guardia, guardia.id)
            misGuardias(profesorLiveData.value!!.id)
        }
        misGuardiasLiveData.value!!.removeAll { it.id == guardia.id }
    }

    fun crearAviso(a: AvisoGuardia) {
        viewModelScope.launch {
            repository.crearAviso(a)
            misGuardias(profesorLiveData.value!!.id)
        }
        misGuardiasLiveData.value = misGuardiasLiveData.value
    }
}