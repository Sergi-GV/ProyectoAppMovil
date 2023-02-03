package com.example.appfaltasdataclass.data

import java.io.Serializable

data class HorarioGuardia(
    val id: Int,
    val id_profesor: Int,
    val dia_semana: Int,
    val hora_guardia: Int,
    val realizadas: Int
): Serializable