package com.example.appfaltasdataclass.data

import java.io.Serializable
import java.sql.Date

data class BajaPermiso(
    val id: Int,
    val id_profesor: Int,
    val fecha_inicio: Date,
    val fecha_fin: Date,
    val tipo: Char
): Serializable