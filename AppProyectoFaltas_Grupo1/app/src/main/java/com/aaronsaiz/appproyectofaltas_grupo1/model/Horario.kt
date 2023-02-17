package com.aaronsaiz.appproyectofaltas_grupo1.model

import java.io.Serializable

data class Horario(
    val id: Int,
    val id_profesor: Int,
    val dia_semana: Int,
    val hora: Int,
    val aula: String,
    val grupo: String,
    val materia: String,
    val genera_guardia: Boolean
): Serializable