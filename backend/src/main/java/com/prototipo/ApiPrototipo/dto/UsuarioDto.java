package com.prototipo.ApiPrototipo.dto;

public class UsuarioDto {
    private Short codigoUsuario;
    private String loginUsuario;
    private String nombreUsuario;
    private String numeroIdentificacion;
    private String claveUsuario;
    private Boolean estadoUsuario;
    private String descripcionEstadoUsuario;

    public UsuarioDto() {
    }

    public UsuarioDto(Short codigoUsuario, String loginUsuario, String nombreUsuario,
            String numeroIdentificacion, String claveUsuario, Boolean estadoUsuario, String descripcionEstadoUsuario) {
        this.codigoUsuario = codigoUsuario;
        this.loginUsuario = loginUsuario;
        this.nombreUsuario = nombreUsuario;
        this.numeroIdentificacion = numeroIdentificacion;
        this.claveUsuario = claveUsuario;
        this.estadoUsuario = estadoUsuario;
        this.descripcionEstadoUsuario = descripcionEstadoUsuario;

    }

    public Short getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Short codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public Boolean getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(boolean estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getDescripcionEstadoUsuario() {
        return descripcionEstadoUsuario;
    }

    public void setDescripcionEstadoUsuario(String descripcionEstadoUsuario) {
        this.descripcionEstadoUsuario = descripcionEstadoUsuario;
    }

}
