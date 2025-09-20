package com.prototipo.ApiPrototipo.dto;

public class RolDto {
    private Short codigoRol;
    private String nombreRol;
    private Boolean estadoRol;
    private String descripcionEstadoRol;

    public RolDto(){}

    public RolDto(Short codigoRol, String nombreRol, Boolean estadoRol, String descripcionEstadoRol)
    {
        this.codigoRol = codigoRol;
        this.nombreRol = nombreRol;
        this.estadoRol = estadoRol;
        this.descripcionEstadoRol = descripcionEstadoRol;
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

    public Boolean getEstadoRol() {
        return estadoRol;
    }

    public void setEstadoRol(boolean estadoRol) {
        this.estadoRol = estadoRol;
    }

    public String getDescripcionEstadoRol() {
        return descripcionEstadoRol;
    }

    public void setDescripcionEstadoRol(String descripcionEstadoRol) {
        this.descripcionEstadoRol = descripcionEstadoRol;
    }

}
