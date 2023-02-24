package es.proyectofaltas.appmovilfaltas.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.sql.Date
import java.sql.Time
import java.time.LocalDateTime

data class AvisoGuardia(
    @SerializedName("id_aviso")
    var id: Int = -1,
    @SerializedName("profesor")
    var id_profesor: Int = -1,
    @SerializedName("horario")
    var id_horario: Int = -1,
    var motivo: String? = null,
    var observaciones: String? = null,
    var confirmado: Boolean? = null,
    var anulado: Boolean? = null,
    var fecha_guardia: String? = null,
    var fecha_hora_aviso: String? = null
): Serializable