package com.treecutforms.network;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Usuario on 18/07/2016.
 */
public interface ApiService {

    @POST("login")
    Call<GenericResponse> doLogin(@Body LoginRequest loginRequest);

    @Multipart
    @POST("store/data")
    Call<ImageResponse> doStoreImage(@Part("beforeDocument") String beforeImage,
                                     @Part("afterDocument") String afterImage,
                                     @Part("contrato") String contrato,
                                     @Part("consecutivo") String consecutivo,
                                     @Part("movil") String movil,
                                     @Part("ordenTrabajo") String ordenTrabajo,
                                     @Part("nroReporte") String nroReporte,
                                     @Part("cuadrillero") String cuadrillero,
                                     @Part("operarioUno") String operarioUno,
                                     @Part("operarioDos") String operarioDos,
                                     @Part("operarioTres") String operarioTres,
                                     @Part("operarioCuatro") String operarioCuatro,
                                     @Part("fecha") String fecha,
                                     @Part("horaSalida") String horaSalida,
                                     @Part("horaInicio") String horaInicio,
                                     @Part("horaFinal") String horaFinal,
                                     @Part("horaLlegada") String horaLlegada,
                                     @Part("municipio") String municipio,
                                     @Part("zona") String zona,
                                     @Part("direccion") String direccion,
                                     @Part("barrio") String barrio,
                                     @Part("circuito") String circuito,
                                     @Part("tension") String tension,
                                     @Part("labor") String labor,
                                     @Part("kmInicial") String kmInicial,
                                     @Part("kmFinal") String kmFinal,
                                     @Part("especieArbol") String especieArbol,
                                     @Part("tratamiento") String tratamiento,
                                     @Part("clasePoda") String clasePoda,
                                     @Part("alturaInicial") String alturaInicial,
                                     @Part("alturaFinal") String alturaFinal,
                                     @Part("baremo") String baremo,
                                     @Part("pap") String pap,
                                     @Part("estadoFitosanario") String estadoFitosanario,
                                     @Part("diametroInicialX") String diametroInicialX,
                                     @Part("diametroInicialY") String diametroIncialY,
                                     @Part("diametroFinalX") String diametroFinalX,
                                     @Part("diametroFinalY") String diametroFinalY,
                                     @Part("plaqueta") String plaqueta,
                                     @Part("coordenadaX") String coordenadaX,
                                     @Part("coordenadaY") String coordenadaY,
                                     @Part("operarioCinco") String operarioCinco,
                                     @Part("operarioSeis") String operarioSeis,
                                     @Part("presentDocument") String presentDocument,
                                     @Part MultipartBody.Part afterImageDocument,
                                     @Part MultipartBody.Part beforeImageDocument,
                                     @Part MultipartBody.Part presentImageDocument
                                     );
}
