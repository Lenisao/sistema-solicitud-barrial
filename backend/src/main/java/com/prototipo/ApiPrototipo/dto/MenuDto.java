package com.prototipo.ApiPrototipo.dto;

public class MenuDto {
    private Short codigoRol;
    private String nombreRol;
    private Short codigoFuncion;
    private String nombreFuncion;
    private Short codigoTransaccion;
    private String nombreTransaccion;
    private String urlPagina;

    public MenuDto(){}

    public MenuDto(Short codigoRol, String nombreRol, Short codigoFuncion, String nombreFuncion,
                   Short codigoTransaccion, String nombreTransaccion, String urlPagina)
    {
        this.codigoRol = codigoRol;
        this.nombreRol = nombreRol;
        this.codigoFuncion = codigoFuncion;
        this.nombreFuncion = nombreFuncion;
        this.codigoTransaccion = codigoTransaccion;
        this.nombreTransaccion = nombreTransaccion;
        this.urlPagina = urlPagina;
    }

    public Short getCodigoRol() {
        return codigoRol;
    }

    public void setCodigoRol(Short codigoRol) {
        this.codigoRol = codigoRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Short getCodigoFuncion() {
        return codigoFuncion;
    }

    public void setCodigoFuncion(Short codigoFuncion) {
        this.codigoFuncion = codigoFuncion;
    }

    public String getNombreFuncion() {
        return nombreFuncion;
    }

    public void setNombreFuncion(String nombreFuncion) {
        this.nombreFuncion = nombreFuncion;
    }

    public Short getCodigoTransaccion() {
        return codigoTransaccion;
    }

    public void setCodigoTransaccion(Short codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    public String getNombreTransaccion() {
        return nombreTransaccion;
    }

    public void setNombreTransaccion(String nombreTransaccion) {
        this.nombreTransaccion = nombreTransaccion;
    }

    public String getUrlPagina() {
        return urlPagina;
    }

    public void setUrlPagina(String urlPagina) {
        this.urlPagina = urlPagina;
    }

}
