package com.treecutforms.models;

import java.io.Serializable;

/**
 * @author Gabriel Rodriguez
 * @version 1.0
 */

public class Form implements Serializable {

    private String beforeImage;
    private String afterImage;
    private String contrato;
    private String consecutivo;
    private String movil;
    private String ordenTrabajo;
    private String nroReporte;
    private String cuadrillero;
    private String operarioUno;
    private String operarioDos;
    private String operarioTres;
    private String operarioCuatro;
    private String fecha;
    private String horaSalida;
    private String horaInicio;
    private String horaFinal;
    private String horaLlegada;
    private String municipio;
    private String zona;
    private String direccion;
    private String barrio;
    private String circuito;
    private String tension;
    private String labor;
    private String kmInicial;
    private String kmFinal;
    private String especieArbol;
    private String tratamiento;
    private String clasePoda;
    private String alturaInicial;
    private String alturaFinal;
    private String baremo;
    private String pap;
    private String estadoFitosanario;
    private String diametroInicialX;
    private String diametroIncialY;
    private String diametroFinalX;
    private String diametroFinalY;
    private String plaqueta;
    private String coordenadaX;
    private String coordenadaY;
    private String operarioCinco;
    private String operarioSeis;

    public Form() {
    }

    public Form(String beforeImage, String afterImage, String contrato, String consecutivo, String movil,
                String ordenTrabajo, String nroReporte, String cuadrillero, String operarioUno,
                String operarioDos, String operarioTres, String operarioCuatro, String fecha,
                String horaSalida, String horaInicio, String horaFinal, String horaLlegada,
                String municipio, String zona, String direccion, String barrio, String circuito,
                String tension, String labor, String kmInicial, String kmFinal, String especieArbol,
                String tratamiento, String clasePoda, String alturaInicial, String alturaFinal, String baremo,
                String pap, String estadoFitosanario, String diametroInicialX, String diametroIncialY,
                String diametroFinalX, String diametroFinalY, String plaqueta, String coordenadaX,
                String coordenadaY, String operarioCinco, String operarioSeis) {

        this.beforeImage = beforeImage;
        this.afterImage = afterImage;
        this.contrato = contrato;
        this.consecutivo = consecutivo;
        this.movil = movil;
        this.ordenTrabajo = ordenTrabajo;
        this.nroReporte = nroReporte;
        this.cuadrillero = cuadrillero;
        this.operarioUno = operarioUno;
        this.operarioDos = operarioDos;
        this.operarioTres = operarioTres;
        this.operarioCuatro = operarioCuatro;
        this.fecha = fecha;
        this.horaSalida = horaSalida;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.horaLlegada = horaLlegada;
        this.municipio = municipio;
        this.zona = zona;
        this.direccion = direccion;
        this.barrio = barrio;
        this.circuito = circuito;
        this.tension = tension;
        this.labor = labor;
        this.kmInicial = kmInicial;
        this.kmFinal = kmFinal;
        this.especieArbol = especieArbol;
        this.tratamiento = tratamiento;
        this.clasePoda = clasePoda;
        this.alturaInicial = alturaInicial;
        this.alturaFinal = alturaFinal;
        this.baremo = baremo;
        this.pap = pap;
        this.estadoFitosanario = estadoFitosanario;
        this.diametroInicialX = diametroInicialX;
        this.diametroIncialY = diametroIncialY;
        this.diametroFinalX = diametroFinalX;
        this.diametroFinalY = diametroFinalY;
        this.plaqueta = plaqueta;
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
        this.operarioCinco = operarioCinco;
        this.operarioSeis = operarioSeis;
    }

    public String getBeforeImage() {
        return beforeImage;
    }

    public void setBeforeImage(String beforeImage) {
        this.beforeImage = beforeImage;
    }

    public String getAfterImage() {
        return afterImage;
    }

    public void setAfterImage(String afterImage) {
        this.afterImage = afterImage;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getOrdenTrabajo() {
        return ordenTrabajo;
    }

    public void setOrdenTrabajo(String ordenTrabajo) {
        this.ordenTrabajo = ordenTrabajo;
    }

    public String getNroReporte() {
        return nroReporte;
    }

    public void setNroReporte(String nroReporte) {
        this.nroReporte = nroReporte;
    }

    public String getCuadrillero() {
        return cuadrillero;
    }

    public void setCuadrillero(String cuadrillero) {
        this.cuadrillero = cuadrillero;
    }

    public String getOperarioUno() {
        return operarioUno;
    }

    public void setOperarioUno(String operarioUno) {
        this.operarioUno = operarioUno;
    }

    public String getOperarioDos() {
        return operarioDos;
    }

    public void setOperarioDos(String operarioDos) {
        this.operarioDos = operarioDos;
    }

    public String getOperarioTres() {
        return operarioTres;
    }

    public void setOperarioTres(String operarioTres) {
        this.operarioTres = operarioTres;
    }

    public String getOperarioCuatro() {
        return operarioCuatro;
    }

    public void setOperarioCuatro(String operarioCuatro) {
        this.operarioCuatro = operarioCuatro;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCircuito() {
        return circuito;
    }

    public void setCircuito(String circuito) {
        this.circuito = circuito;
    }

    public String getTension() {
        return tension;
    }

    public void setTension(String tension) {
        this.tension = tension;
    }

    public String getLabor() {
        return labor;
    }

    public void setLabor(String labor) {
        this.labor = labor;
    }

    public String getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(String kmInicial) {
        this.kmInicial = kmInicial;
    }

    public String getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(String kmFinal) {
        this.kmFinal = kmFinal;
    }

    public String getEspecieArbol() {
        return especieArbol;
    }

    public void setEspecieArbol(String especieArbol) {
        this.especieArbol = especieArbol;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getClasePoda() {
        return clasePoda;
    }

    public void setClasePoda(String clasePoda) {
        this.clasePoda = clasePoda;
    }

    public String getAlturaInicial() {
        return alturaInicial;
    }

    public void setAlturaInicial(String alturaInicial) {
        this.alturaInicial = alturaInicial;
    }

    public String getAlturaFinal() {
        return alturaFinal;
    }

    public void setAlturaFinal(String alturaFinal) {
        this.alturaFinal = alturaFinal;
    }

    public String getBaremo() {
        return baremo;
    }

    public void setBaremo(String baremo) {
        this.baremo = baremo;
    }

    public String getPap() {
        return pap;
    }

    public void setPap(String pap) {
        this.pap = pap;
    }

    public String getEstadoFitosanario() {
        return estadoFitosanario;
    }

    public void setEstadoFitosanario(String estadoFitosanario) {
        this.estadoFitosanario = estadoFitosanario;
    }

    public String getDiametroInicialX() {
        return diametroInicialX;
    }

    public void setDiametroInicialX(String diametroInicialX) {
        this.diametroInicialX = diametroInicialX;
    }

    public String getDiametroIncialY() {
        return diametroIncialY;
    }

    public void setDiametroIncialY(String diametroIncialY) {
        this.diametroIncialY = diametroIncialY;
    }

    public String getDiametroFinalX() {
        return diametroFinalX;
    }

    public void setDiametroFinalX(String diametroFinalX) {
        this.diametroFinalX = diametroFinalX;
    }

    public String getDiametroFinalY() {
        return diametroFinalY;
    }

    public void setDiametroFinalY(String diametroFinalY) {
        this.diametroFinalY = diametroFinalY;
    }

    public String getPlaqueta() {
        return plaqueta;
    }

    public void setPlaqueta(String plaqueta) {
        this.plaqueta = plaqueta;
    }

    public String getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(String coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public String getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(String coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public String getOperarioCinco() {
        return operarioCinco;
    }

    public void setOperarioCinco(String operarioCinco) {
        this.operarioCinco = operarioCinco;
    }

    public String getOperarioSeis() {
        return operarioSeis;
    }

    public void setOperarioSeis(String operarioSeis) {
        this.operarioSeis = operarioSeis;
    }
}
