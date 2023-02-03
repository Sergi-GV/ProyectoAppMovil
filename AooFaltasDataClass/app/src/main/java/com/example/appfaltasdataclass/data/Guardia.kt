package com.example.appfaltasdataclass.data

import java.io.Serializable
import java.sql.Date

data class Guardia(
    val id: Int,
    val id_profesor_falta: Int,
    val id_profesor_hace_guardia: Int,
    val estado: Char,
    val fecha: Date,
    val horario: Int,
    val dia_semana: Int,
    val hora: String,
    val aviso: Int,
    val grupo: String,
    val aula: String,
    val observaciones: String
): Serializable