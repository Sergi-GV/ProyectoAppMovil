package es.proyectofaltas.appmovilfaltas.apiservice

import es.proyectofaltas.appmovilfaltas.data.AvisoGuardia
import es.proyectofaltas.appmovilfaltas.data.Guardia
import es.proyectofaltas.appmovilfaltas.data.Horario
import es.proyectofaltas.appmovilfaltas.data.Profesor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

class FaltasApiService {
    object RetrofitBuilder {
        fun build(): Retrofit =
            Retrofit.Builder()
                //.baseUrl("http://10.0.13.10:8080/")
                .baseUrl("http://192.168.56.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    interface FaltasApiService {
        @POST("login")
        suspend fun loginProfesor(@Header("user") user: String, @Header("pswd") pwd: String): List<Profesor>
        @POST("crear-aviso")
        suspend fun crearAviso(@Body aviso: AvisoGuardia, @Header("key") key: String)
        @GET("guardias-por-horario/{id}")
        suspend fun guardiasProfe(@Path("id") id: Int, @Header("key") key: String): MutableList<Guardia>
        @GET("guardias/{id}")
        suspend fun misGuardias(@Path("id") id: Int, @Header("key") key: String): MutableList<Guardia>
        @GET("horario-profesor/{id}")
        suspend fun horarioProfesor(@Path("id") id: Int, @Header("key") key: String): List<Horario>
        @PUT("modificar-guardia/{id}")
        suspend fun modificarGuardia(@Body guardia: Guardia, @Path("id")id: Int, @Header("key") key: String)
        @PUT("anular-guardia/{id}")
        suspend fun anularGuardia(@Body guardia: Guardia, @Path("id")id: Int, @Header("key") key: String)
    }

}

object FaltasApi {
    private val retrofit = FaltasApiService.RetrofitBuilder.build()
    val retrofitService: FaltasApiService.FaltasApiService by lazy { retrofit.create(
        FaltasApiService.FaltasApiService::class.java)}
}