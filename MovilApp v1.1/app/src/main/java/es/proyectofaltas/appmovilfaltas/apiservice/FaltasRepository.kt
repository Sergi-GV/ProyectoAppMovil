package es.proyectofaltas.appmovilfaltas.apiservice

import es.proyectofaltas.appmovilfaltas.data.AvisoGuardia
import es.proyectofaltas.appmovilfaltas.data.Guardia
import es.proyectofaltas.appmovilfaltas.login.LoginActivity

class FaltasRepository {
    suspend fun loginProfesor(user: String, pwd: String) = FaltasApi.retrofitService.loginProfesor(user, pwd)
    suspend fun guardiasProfesor(id: Int) = FaltasApi.retrofitService.guardiasProfe(id, LoginActivity.Clave.KEY)
    suspend fun misGuardias(id: Int) = FaltasApi.retrofitService.misGuardias(id, LoginActivity.Clave.KEY)
    suspend fun horarioProfesor(id: Int) = FaltasApi.retrofitService.horarioProfesor(id, LoginActivity.Clave.KEY)
    suspend fun modificarGuardia(guardia: Guardia, id: Int) = FaltasApi.retrofitService.modificarGuardia(guardia, id, LoginActivity.Clave.KEY)
    suspend fun crearAviso(aviso: AvisoGuardia) = FaltasApi.retrofitService.crearAviso(aviso, LoginActivity.Clave.KEY)
    suspend fun anularGuardia(guardia: Guardia, id: Int) = FaltasApi.retrofitService.anularGuardia(guardia, id, LoginActivity.Clave.KEY)
}