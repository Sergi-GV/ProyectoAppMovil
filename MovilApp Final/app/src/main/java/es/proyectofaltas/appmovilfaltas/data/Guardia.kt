package es.proyectofaltas.appmovilfaltas.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.sql.Date

data class Guardia(
    val id: Int,
    @SerializedName("prof_falta")
    val id_profesor_falta: Int,
    @SerializedName("prof_hace_guardia")
    var id_profesor_hace_guardia: Int?,
    val estado: Char,
    val fecha: String,
    val horario: Int,
    val dia_semana: Int,
    val hora: String,
    val aviso: Int,
    val grupo: String,
    val aula: String,
    val observaciones: String
): Serializable