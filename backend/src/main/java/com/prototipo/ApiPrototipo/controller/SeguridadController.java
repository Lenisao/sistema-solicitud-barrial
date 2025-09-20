package com.prototipo.ApiPrototipo.controller;

import com.prototipo.ApiPrototipo.dto.*;
import com.prototipo.ApiPrototipo.service.SeguridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seguridad")
public class SeguridadController {
    @Autowired
    private SeguridadService seguridadService;

    @PostMapping("/existeUsuario")
    public ResponseEntity<?> existeUsuario(@RequestBody UsuarioDto login) {
        UsuarioDto respuesta = seguridadService.login(login);

        if (respuesta == null) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/obtieneMenu")
    public ResponseEntity<?> obtieneMenu(@RequestBody UsuarioDto login) {
        List<MenuDto> respuesta = seguridadService.obtieneMenu(login);

        if (respuesta == null) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/cambiarClave")
    public ResponseEntity<?> cambiarClave(@RequestBody UsuarioDto login) {
        RespuestaDto respuesta = seguridadService.cambiarClave(login);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/listarRol")
    public ResponseEntity<?> listarRol() {
        List<RolDto> respuesta = seguridadService.listarRoles();

        if (respuesta == null) {
            return ResponseEntity.status(401).body("No existen datos de Rol");
        }

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/mantenimientoRol")
    public ResponseEntity<?> mantenimientoRol(@RequestBody RolDto rol) {
        RespuestaDto respuesta = seguridadService.mantenimientoRol(rol);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/obtenerRol")
    public ResponseEntity<?> obtenerRol() {
        List<RolDto> respuesta = seguridadService.obtenerRol();

        if (respuesta == null) {
            return ResponseEntity.status(401).body("No existen datos de Rol");
        }

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/listarOpcionMenu")
    public ResponseEntity<?> listarOpcionMenu(@RequestBody OpcionMenuDto opcionPadre) {
        List<OpcionMenuDto> respuesta = seguridadService.listarOpcionMenu(opcionPadre);

        if (respuesta == null) {
            return ResponseEntity.status(401).body("No existen datos de Opciones para Menú");
        }

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/mantenimientoOpcionMenu")
    public ResponseEntity<?> mantenimientoOpcionMenu(@RequestBody OpcionMenuDto opcionMenu) {
        RespuestaDto respuesta = seguridadService.mantenimientoOpcionMenu(opcionMenu);

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/obtenerOpcionMenuPadre")
    public ResponseEntity<?> obtenerOpcionMenuPadre() {
        List<OpcionMenuDto> respuesta = seguridadService.obtenerOpcionMenuPadre();

        if (respuesta == null) {
            return ResponseEntity.status(401).body("No existen datos de Opción Menú configurada");
        }

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/mantenimientoUsuario")
    public ResponseEntity<?> mantenimientoUsuario(@RequestBody UsuarioDto usuario) {
        var respuesta = seguridadService.mantenimientoUsuario(usuario);

        return ResponseEntity.ok(respuesta);
    }



}
