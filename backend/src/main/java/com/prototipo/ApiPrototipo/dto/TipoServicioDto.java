package com.prototipo.ApiPrototipo.dto;

public class TipoServicioDto {
    private Short codigoTipoServicio;
    private String nombreTipoServicio;
    private Boolean estadoTipoServicio;
    private String descripcionEstadoTipoServicio;

    public TipoServicioDto(){}

    public TipoServicioDto(Short codigoTipoServicio, String nombreTipoServicio, Boolean estadoTipoServicio, String descripcionEstadoTipoServicio){
        this.codigoTipoServicio = codigoTipoServicio;
        this.nombreTipoServicio = nombreTipoServicio;
        this.estadoTipoServicio = estadoTipoServicio;
        this.descripcionEstadoTipoServicio = descripcionEstadoTipoServicio;
    }

    public Short getCodigoTipoServicio() {
        return codigoTipoServicio;
    }

    public void setCodigoTipoServicio(Short codigoTipoServicio) {
        this.codigoTipoServicio = codigoTipoServicio;
    }

    public String getNombreTipoServicio() {
        return nombreTipoServicio;
    }

    public void setNombreTipoServicio(String nombreTipoServicio) {
        this.nombreTipoServicio = nombreTipoServicio;
    }

    public Boolean getEstadoTipoServicio() {
        return estadoTipoServicio;
    }

    public void setEstadoTipoServicio(boolean estadoTipoServicio) {
        this.estadoTipoServicio = estadoTipoServicio;
    }

    public String getDescripcionEstadoTipoServicio() {
        return descripcionEstadoTipoServicio;
    }

    public void setDescripcionEstadoTipoServicio(String descripcionEstadoTipoServicio) {
        this.descripcionEstadoTipoServicio = descripcionEstadoTipoServicio;
    }



}
