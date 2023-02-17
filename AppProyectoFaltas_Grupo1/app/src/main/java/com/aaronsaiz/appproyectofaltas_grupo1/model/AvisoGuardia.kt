package com.aaronsaiz.appproyectofaltas_grupo1.model

import java.io.Serializable
import java.sql.Date
import java.sql.Time

data class AvisoGuardia(
    val id: Int,
    val id_profesor: Int,
    val id_horario: Int,
    val motivo: String,
    val observaciones: String,
    val confirmado: Boolean,
    val anulado: Boolean,
    val fecha_aviso: Date,
    val hora_aviso: Time
): Serializable