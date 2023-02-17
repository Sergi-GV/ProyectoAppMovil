package com.aaronsaiz.appproyectofaltas_grupo1.model

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