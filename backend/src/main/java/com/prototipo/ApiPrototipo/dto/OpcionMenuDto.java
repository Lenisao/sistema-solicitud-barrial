package com.prototipo.ApiPrototipo.dto;

public class OpcionMenuDto {
    private Short codigoOpcion;
    private Short codigoOpcionPadre;
    private String nombreOpcion;
    private String urlOpcion;
    private Boolean estadoOpcion;
    private String descripcionEstadoOpcion;

    public OpcionMenuDto(){}

    public OpcionMenuDto(Short codigoOpcion, Short codigoOpcionPadre, String nombreOpcion,
                         String urlOpcion, Boolean estadoOpcion, String descripcionEstadoOpcion)
    {
        this.codigoOpcion = codigoOpcion;
        this.codigoOpcionPadre = codigoOpcionPadre;
        this.nombreOpcion = nombreOpcion;
        this.urlOpcion = urlOpcion;
        this.estadoOpcion = estadoOpcion;
        this.descripcionEstadoOpcion = descripcionEstadoOpcion;
    }

    public Short getCodigoOpcion() {
        return codigoOpcion;
    }

    public void setCodigoOpcion(Short codigoOpcion) {
        this.codigoOpcion = codigoOpcion;
    }

    public Short getCodigoOpcionPadre() {
        return codigoOpcionPadre;
    }

    public void setCodigoOpcionPadre(Short codigoOpcionPadre) {
        this.codigoOpcionPadre = codigoOpcionPadre;
    }

    public String getNombreOpcion() {
        return nombreOpcion;
    }

    public void setNombreOpcion(String nombreOpcion) {
        this.nombreOpcion = nombreOpcion;
    }

    public String getUrlOpcion() {
        return urlOpcion;
    }

    public void setUrlOpcion(String urlOpcion) {
        this.urlOpcion = urlOpcion;
    }

    public Boolean getEstadoOpcion() {
        return estadoOpcion;
    }

    public void setEstadoOpcion(boolean estadoOpcion) {
        this.estadoOpcion = estadoOpcion;
    }

    public String getDescripcionEstadoOpcion() {
        return descripcionEstadoOpcion;
    }

    public void setDescripcionEstadoOpcion(String descripcionEstadoOpcion) {
        this.descripcionEstadoOpcion = descripcionEstadoOpcion;
    }

}
