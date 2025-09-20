package com.prototipo.ApiPrototipo.dto;

public class RespuestaDto {
    private short codigoError;
    private String mensajeError;
    private String body;

    public RespuestaDto(){}

    public RespuestaDto(short codigoError,String mensajeError, String body )
    {
        this.codigoError = codigoError;
        this.mensajeError = mensajeError;
        this.body = body;
    }

    public Short getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(Short codigoError) {
        this.codigoError = codigoError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
