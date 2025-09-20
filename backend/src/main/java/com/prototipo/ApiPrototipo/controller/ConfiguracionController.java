package com.prototipo.ApiPrototipo.controller;

import com.prototipo.ApiPrototipo.dto.*;
import com.prototipo.ApiPrototipo.service.ConfiguracionService;
import com.prototipo.ApiPrototipo.service.SeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/configuracion")
public class ConfiguracionController {

    @Autowired
    private ConfiguracionService configuracionService;

    @GetMapping("/listarTipoServicio")
    public ResponseEntity<?> listarTipoServicio() {
        List<TipoServicioDto> respuesta = configuracionService.listarTipoServicio();

        if (respuesta == null) {
            return ResponseEntity.status(401).body("No existen datos de Tipo de Servicio");
        }

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/mantenimientoTipoServicio")
    public ResponseEntity<?> mantenimientoTipoServicio(@RequestBody TipoServicioDto tipoServicio) {
        RespuestaDto respuesta = configuracionService.mantenimientoTipoServicio(tipoServicio);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/obtenerTipoServicio")
    public ResponseEntity<?> obtenerTipoServicio() {
        List<TipoServicioDto> respuesta = configuracionService.obtenerTipoServicio();

        if (respuesta == null) {
            return ResponseEntity.status(401).body("No existen datos de Tipo de Servicio");
        }

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/listarServicioPorTipo")
    public ResponseEntity<?> listarServicioPorTipo(@RequestBody TipoServicioDto tipoServicio) {
        List<ServicioDto> respuesta = configuracionService.listarServicioPorTipo(tipoServicio);

        if (respuesta == null) {
            return ResponseEntity.status(401).body("No existen datos de Servicio");
        }

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/mantenimientoServicio")
    public ResponseEntity<?> mantenimientoServicio(@RequestBody ServicioDto servicio) {
        RespuestaDto respuesta = configuracionService.mantenimientoServicio(servicio);

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/obtenerServicioPorTipo")
    public ResponseEntity<?> obtenerServicioPorTipo(@RequestBody TipoServicioDto tipoServicio) {
        List<ServicioDto> respuesta = configuracionService.obtenerServicioPorTipo(tipoServicio);

        if (respuesta == null) {
            return ResponseEntity.status(401).body("No existen datos de Servicio");
        }

        return ResponseEntity.ok(respuesta);
    }

}
