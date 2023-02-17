package com.aaronsaiz.appproyectofaltas_grupo1.model

import java.io.Serializable
import java.sql.Time

data class HorarioCentro(
    val id: Int,
    val hora_inicio: Time,
    val hora_fin: Time,
    val turno: Char
): Serializable