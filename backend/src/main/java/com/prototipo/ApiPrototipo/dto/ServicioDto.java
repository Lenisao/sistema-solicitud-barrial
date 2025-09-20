package com.prototipo.ApiPrototipo.dto;

public class ServicioDto {
    private Short codigoServicio;
    private Short codigoTipoServicio;
    private String nombreServicio;
    private Boolean estadoServicio;
    private String descripcionEstadoServicio;

    public ServicioDto(){}

    public ServicioDto(Short codigoServicio, Short codigoTipoServicio, String nombreServicio, Boolean estadoServicio, String descripcionEstadoTipoServicio){
        this.codigoServicio = codigoServicio;
        this.codigoTipoServicio = codigoTipoServicio;
        this.nombreServicio = nombreServicio;
        this.estadoServicio = estadoServicio;
        this.descripcionEstadoServicio = descripcionEstadoServicio;
    }

    public Short getCodigoServicio()
    {
        return codigoServicio;
    }

    public void setCodigoServicio(Short codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public Short getCodigoTipoServicio() {
        return codigoTipoServicio;
    }

    public void setCodigoTipoServicio(Short codigoTipoServicio) {
        this.codigoTipoServicio = codigoTipoServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public Boolean getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(boolean estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    public String getDescripcionEstadoServicio() {
        return descripcionEstadoServicio;
    }

    public void setDescripcionEstadoServicio(String descripcionEstadoServicio) {
        this.descripcionEstadoServicio = descripcionEstadoServicio;
    }



}
