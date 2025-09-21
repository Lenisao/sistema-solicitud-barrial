package com.prototipo.ApiPrototipo.service;

import com.prototipo.ApiPrototipo.dto.*;
import com.prototipo.ApiPrototipo.repository.SeguridadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

@Service
public class SeguridadService {
    @Autowired
    private SeguridadRepository seguridadRepository;

    public UsuarioDto login(UsuarioDto request) {
        RespuestaDto resultado = seguridadRepository.login(request.getLoginUsuario(), request.getClaveUsuario());

        if (resultado.getCodigoError() != 0) {
            return null; // o lanzar excepción personalizada
        }

        String body = resultado.getBody();
        List<UsuarioDto> listaUsuarios = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            listaUsuarios = mapper.readValue(body, new TypeReference<List<UsuarioDto>>() {
            });
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        if (listaUsuarios == null || listaUsuarios.isEmpty()) {
            return null;
        }

        return listaUsuarios.get(0);
    }

    public List<MenuDto> obtieneMenu(UsuarioDto request) {
        RespuestaDto resultado = seguridadRepository.obtieneMenu(request.getLoginUsuario());

        if (resultado.getCodigoError() != 0) {
            return null; // o lanzar excepción personalizada
        }

        String body = resultado.getBody();
        List<MenuDto> listaMenu = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            listaMenu = mapper.readValue(body, new TypeReference<List<MenuDto>>() {
            });
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        if (listaMenu == null || listaMenu.isEmpty()) {
            return null;
        }

        return listaMenu;
    }

    public RespuestaDto cambiarClave(UsuarioDto request) {
        RespuestaDto resultado = seguridadRepository.cambiarClave(request.getLoginUsuario(), request.getClaveUsuario());

        if (resultado.getCodigoError() != 0) {
            return null; // o lanzar excepción personalizada
        }

        return resultado;
    }

    public List<RolDto> listarRoles() {
        RespuestaDto resultado = seguridadRepository.listarRoles();

        if (resultado.getCodigoError() != 0) {
            return null; // o lanzar excepción personalizada
        }

        String body = resultado.getBody();
        List<RolDto> listaRol = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            listaRol = mapper.readValue(body, new TypeReference<List<RolDto>>() {
            });
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        if (listaRol == null || listaRol.isEmpty()) {
            return null;
        }

        return listaRol;
    }

    public RespuestaDto mantenimientoRol(RolDto request) {
        RespuestaDto resultado = seguridadRepository.mantenimientoRol(request.getCodigoRol(), request.getNombreRol(),
                request.getEstadoRol());

        if (resultado.getCodigoError() != 0) {
            return null; // o lanzar excepción personalizada
        }

        return resultado;
    }

    public List<RolDto> obtenerRol() {
        RespuestaDto resultado = seguridadRepository.obtenerRol();

        if (resultado.getCodigoError() != 0) {
            return null; // o lanzar excepción personalizada
        }

        String body = resultado.getBody();
        List<RolDto> listaRol = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            listaRol = mapper.readValue(body, new TypeReference<List<RolDto>>() {
            });
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        if (listaRol == null || listaRol.isEmpty()) {
            return null;
        }

        return listaRol;
    }

    public List<OpcionMenuDto> listarOpcionMenu(OpcionMenuDto request) {
        Short codigoPadre = request.getCodigoOpcionPadre();
        RespuestaDto resultado = seguridadRepository.listarOpcionMenu(codigoPadre);

        if (resultado.getCodigoError() != 0) {
            return null; // o lanzar excepción personalizada
        }

        String body = resultado.getBody();
        List<OpcionMenuDto> listaOpcionMenu = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            listaOpcionMenu = mapper.readValue(body, new TypeReference<List<OpcionMenuDto>>() {
            });
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        if (listaOpcionMenu == null || listaOpcionMenu.isEmpty()) {
            return null;
        }

        return listaOpcionMenu;
    }

    public RespuestaDto mantenimientoOpcionMenu(OpcionMenuDto request) {
        RespuestaDto resultado = seguridadRepository.mantenimientoOpcionMenu(request.getCodigoOpcion(),
                request.getCodigoOpcionPadre(),
                request.getNombreOpcion(), request.getUrlOpcion(), request.getEstadoOpcion());

        if (resultado.getCodigoError() != 0) {
            return null; // o lanzar excepción personalizada
        }

        return resultado;
    }

    public List<OpcionMenuDto> obtenerOpcionMenuPadre() {
        RespuestaDto resultado = seguridadRepository.obtenerOpcionMenuPadre();

        if (resultado.getCodigoError() != 0) {
            return null; // o lanzar excepción personalizada
        }

        String body = resultado.getBody();
        List<OpcionMenuDto> listaOpcionMenuPadre = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            listaOpcionMenuPadre = mapper.readValue(body, new TypeReference<List<OpcionMenuDto>>() {
            });
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        if (listaOpcionMenuPadre == null || listaOpcionMenuPadre.isEmpty()) {
            return null;
        }

        return listaOpcionMenuPadre;
    }

    public RespuestaDto mantenimientoUsuario(UsuarioDto request) {
        RespuestaDto resultado = seguridadRepository.mantenimientoUsuario(
                request.getCodigoUsuario(),
                request.getLoginUsuario(),
                request.getNombreUsuario(),
                request.getNumeroIdentificacion(), // primero la identificación
                request.getClaveUsuario(), // después la clave
                request.getEstadoUsuario());

        if (resultado.getCodigoError() != 0) {
            return null; // o lanzar excepción personalizada
        }

        return resultado;
    }

}
