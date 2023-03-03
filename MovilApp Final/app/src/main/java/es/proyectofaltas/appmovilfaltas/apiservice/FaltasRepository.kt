package es.proyectofaltas.appmovilfaltas.apiservice

import es.proyectofaltas.appmovilfaltas.data.AvisoGuardia
import es.proyectofaltas.appmovilfaltas.data.Guardia
import es.proyectofaltas.appmovilfaltas.vistas.HolderActivity

class FaltasRepository {
    suspend fun loginProfesor(user: String, pwd: String) = FaltasApi.retrofitService.loginProfesor(user, pwd)
    suspend fun guardiasProfesor(id: Int) = FaltasApi.retrofitService.guardiasProfe(id, HolderActivity.Clave.KEY!!)
    suspend fun misGuardias(id: Int) = FaltasApi.retrofitService.misGuardias(id, HolderActivity.Clave.KEY!!)
    suspend fun horarioProfesor(id: Int) = FaltasApi.retrofitService.horarioProfesor(id, HolderActivity.Clave.KEY!!)
    suspend fun modificarGuardia(guardia: Guardia, id: Int) = FaltasApi.retrofitService.modificarGuardia(guardia, id, HolderActivity.Clave.KEY!!)
    suspend fun crearAviso(aviso: AvisoGuardia) = FaltasApi.retrofitService.crearAviso(aviso, HolderActivity.Clave.KEY!!)
    suspend fun anularGuardia(guardia: Guardia, id: Int) = FaltasApi.retrofitService.anularGuardia(guardia, id, HolderActivity.Clave.KEY!!)
}