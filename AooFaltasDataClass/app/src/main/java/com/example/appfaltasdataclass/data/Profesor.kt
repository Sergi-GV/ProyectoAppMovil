package com.example.appfaltasdataclass.data

import java.io.Serializable

data class Profesor(
    val id: Int,
    val dni: String,
    val nombre: String,
    val apellido1: String,
    val apellido2: String,
    val telefono: String,
    val baja: Boolean,
    val activo: Boolean,
    val codigo_departamento: String,
    val id_sustituye: Int?
): Serializable