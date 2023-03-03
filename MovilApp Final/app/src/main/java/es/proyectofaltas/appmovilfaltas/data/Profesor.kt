package es.proyectofaltas.appmovilfaltas.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Profesor(
    @SerializedName("id")
    val id: Int,
    @SerializedName("apikey")
    var key: String?,
    @SerializedName("dni")
    val dni: String,
    @SerializedName("user")
    val usuario: String,
    @SerializedName("password")
    val contra: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("ape1")
    val apellido1: String,
    @SerializedName("ape2")
    val apellido2: String,
    @SerializedName("tfno")
    val telefono: String,
    @SerializedName("baja")
    val baja: Boolean,
    @SerializedName("activo")
    val activo: Boolean,
    @SerializedName("dept_cod")
    val codigo_departamento: String,
    @SerializedName("id_sustituye")
    val id_sustituye: Int?
): Serializable