package es.proyectofaltas.appmovilfaltas.data

import java.io.Serializable

data class Perfil(
    val id: Int,
    val nombre_perfil: Perfiles
): Serializable

enum class Perfiles {
    PROFESOR,
    JEFATURA_ESTUDIOS,
    DIRECTOR,
    ADMINISTRADOR
}