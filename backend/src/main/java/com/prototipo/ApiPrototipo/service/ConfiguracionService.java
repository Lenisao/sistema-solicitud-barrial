package com.prototipo.ApiPrototipo.service;

import com.prototipo.ApiPrototipo.dto.*;
import com.prototipo.ApiPrototipo.repository.ConfiguracionRepository;
import com.prototipo.ApiPrototipo.repository.SeguridadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

@Service
public class ConfiguracionService {

    @Autowired
    private ConfiguracionRepository configuracionRepository;

    public List<TipoServicioDto> listarTipoServicio() {
        RespuestaDto resultado = configuracionRepository.listarTipoServicio();

        if (resultado.getCodigoError()!=0) {
            return null; // o lanzar excepción personalizada
        }

        String body = resultado.getBody();

        List<TipoServicioDto> listaTipoServicio = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            listaTipoServicio = mapper.readValue(body,new TypeReference<List<TipoServicioDto>>() {});
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        if (listaTipoServicio == null || listaTipoServicio.isEmpty()) {
            return null;
        }

        return listaTipoServicio;
    }

    public RespuestaDto mantenimientoTipoServicio(TipoServicioDto request) {
        RespuestaDto resultado = configuracionRepository.mantenimientoTipoServicio(request.getCodigoTipoServicio(), request.getNombreTipoServicio(),request.getEstadoTipoServicio());

        if (resultado.getCodigoError()!=0) {
            return null; // o lanzar excepción personalizada
        }

        return resultado;
    }

    public List<TipoServicioDto> obtenerTipoServicio() {
        RespuestaDto resultado = configuracionRepository.obtenerTipoServicio();

        if (resultado.getCodigoError()!=0) {
            return null; // o lanzar excepción personalizada
        }

        String body = resultado.getBody();
        List<TipoServicioDto> listaTipoServicio = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            listaTipoServicio = mapper.readValue(body,new TypeReference<List<TipoServicioDto>>() {});
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        if (listaTipoServicio == null || listaTipoServicio.isEmpty()) {
            return null;
        }

        return listaTipoServicio;
    }

    public List<ServicioDto> listarServicioPorTipo(TipoServicioDto tipoServicioDto) {
        RespuestaDto resultado = configuracionRepository.listarServicioPorTipo(tipoServicioDto.getCodigoTipoServicio());

        if (resultado.getCodigoError()!=0) {
            return null; // o lanzar excepción personalizada
        }

        String body = resultado.getBody();

        List<ServicioDto> listaServicio = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            listaServicio = mapper.readValue(body,new TypeReference<List<ServicioDto>>() {});
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        if (listaServicio == null || listaServicio.isEmpty()) {
            return null;
        }

        return listaServicio;
    }

    public RespuestaDto mantenimientoServicio(ServicioDto request) {
        RespuestaDto resultado = configuracionRepository.mantenimientoServicio(request.getCodigoServicio(), request.getCodigoTipoServicio(), request.getNombreServicio(),request.getEstadoServicio());

        if (resultado.getCodigoError()!=0) {
            return null; // o lanzar excepción personalizada
        }

        return resultado;
    }

    public List<ServicioDto> obtenerServicioPorTipo(TipoServicioDto tipoServicioDto) {
        RespuestaDto resultado = configuracionRepository.obtenerServicioPorTipo(tipoServicioDto.getCodigoTipoServicio());

        if (resultado.getCodigoError()!=0) {
            return null; // o lanzar excepción personalizada
        }

        String body = resultado.getBody();

        List<ServicioDto> listaServicio = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            listaServicio = mapper.readValue(body,new TypeReference<List<ServicioDto>>() {});
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        if (listaServicio == null || listaServicio.isEmpty()) {
            return null;
        }

        return listaServicio;
    }

}
