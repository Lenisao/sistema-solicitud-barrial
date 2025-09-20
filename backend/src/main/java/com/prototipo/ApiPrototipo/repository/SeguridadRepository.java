package com.prototipo.ApiPrototipo.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prototipo.ApiPrototipo.dto.RespuestaDto;
import com.prototipo.ApiPrototipo.dto.RolDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SeguridadRepository {

    @Autowired
    private DataSource dataSource;

    private SimpleJdbcCall jdbcCall;

    public RespuestaDto login(String login, String clave) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("existeUsuario")
                .declareParameters(
                        new SqlParameter("loginUsuario", Types.VARCHAR),
                        new SqlParameter("claveUsuario", Types.VARCHAR),
                        new SqlOutParameter("co_msg", Types.SMALLINT),
                        new SqlOutParameter("ds_msg", Types.VARCHAR)
                )
                .returningResultSet("result", (rs, rowNum) -> {
                    Map<String, Object> row = new HashMap<>();
                    int cols = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= cols; i++) {
                        row.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
                    }
                    return row;
                });

        Map<String, Object> params = new HashMap<>();
        params.put("loginUsuario", login);
        params.put("claveUsuario", clave);


        Map<String, Object> out = jdbcCall.execute(params);

        Short codigoError = ((Number) out.get("co_msg")).shortValue();
        String mensajeError = (String) out.get("ds_msg");

        List<Map<String, Object>> result = (List<Map<String, Object>>) out.get("result");
        String body = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            body = mapper.writeValueAsString(result); // aquí puede lanzar JsonProcessingException
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        return new RespuestaDto(codigoError, mensajeError, body);
    }

    public RespuestaDto obtieneMenu(String login) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spUsuarioAccede")
                .declareParameters(
                        new SqlParameter("codigoUsuario", Types.VARCHAR),
                        new SqlOutParameter("co_msg", Types.SMALLINT),
                        new SqlOutParameter("ds_msg", Types.VARCHAR)
                )
                .returningResultSet("result", (rs, rowNum) -> {
                    Map<String, Object> row = new HashMap<>();
                    int cols = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= cols; i++) {
                        row.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
                    }
                    return row;
                });

        Map<String, Object> params = new HashMap<>();
        params.put("codigoUsuario", login);

        Map<String, Object> out = jdbcCall.execute(params);

        Short codigoError = ((Number) out.get("co_msg")).shortValue();
        String mensajeError = (String) out.get("ds_msg");

        List<Map<String, Object>> result = (List<Map<String, Object>>) out.get("result");
        String body = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            body = mapper.writeValueAsString(result); // aquí puede lanzar JsonProcessingException
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        return new RespuestaDto(codigoError, mensajeError, body);
    }

    public RespuestaDto cambiarClave(String login, String clave) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spCambioClave")
                .declareParameters(
                        new SqlParameter("loginUsuario", Types.VARCHAR),
                        new SqlParameter("claveUsuario", Types.VARCHAR),
                        new SqlOutParameter("co_msg", Types.SMALLINT),
                        new SqlOutParameter("ds_msg", Types.VARCHAR)
                )
                .returningResultSet("result", (rs, rowNum) -> {
                    Map<String, Object> row = new HashMap<>();
                    int cols = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= cols; i++) {
                        row.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
                    }
                    return row;
                });

        Map<String, Object> params = new HashMap<>();
        params.put("loginUsuario", login);
        params.put("claveUsuario", clave);


        Map<String, Object> out = jdbcCall.execute(params);

        Short codigoError = ((Number) out.get("co_msg")).shortValue();
        String mensajeError = (String) out.get("ds_msg");

        //List<Map<String, Object>> result = (List<Map<String, Object>>) out.get("result");
        String body = "";

        return new RespuestaDto(codigoError, mensajeError, body);
    }

    public RespuestaDto listarRoles() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spMantenimientoRoles")
                .declareParameters(
                        new SqlParameter("codigoRol", Types.SMALLINT),
                        new SqlParameter("nombreRol", Types.VARCHAR),
                        new SqlParameter("estadoRol", Types.BOOLEAN),
                        new SqlParameter("usuarioTransaccion", Types.VARCHAR),
                        new SqlParameter("equipoTransaccion", Types.VARCHAR),
                        new SqlParameter("opcion", Types.CHAR),
                        new SqlOutParameter("co_msg", Types.SMALLINT),
                        new SqlOutParameter("ds_msg", Types.VARCHAR)
                )
                .returningResultSet("result", (rs, rowNum) -> {
                    Map<String, Object> row = new HashMap<>();
                    int cols = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= cols; i++) {
                        row.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
                    }
                    return row;
                });

        Map<String, Object> params = new HashMap<>();
        params.put("codigoRol", 0);
        params.put("nombreRol", "");
        params.put("estadoRol", false);
        params.put("usuarioTransaccion", "");
        params.put("equipoTransaccion", "");
        params.put("opcion", "CO");

        Map<String, Object> out = jdbcCall.execute(params);

        Short codigoError = ((Number) out.get("co_msg")).shortValue();
        String mensajeError = (String) out.get("ds_msg");

        List<Map<String, Object>> result = (List<Map<String, Object>>) out.get("result");
        String body = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            body = mapper.writeValueAsString(result); // aquí puede lanzar JsonProcessingException
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        return new RespuestaDto(codigoError, mensajeError, body);
    }

    public RespuestaDto mantenimientoRol(Short codigoRol, String nombreRol, Boolean estadoRol) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spMantenimientoRoles")
                .declareParameters(
                        new SqlParameter("codigoRol", Types.SMALLINT),
                        new SqlParameter("nombreRol", Types.VARCHAR),
                        new SqlParameter("estadoRol", Types.BOOLEAN),
                        new SqlParameter("usuarioTransaccion", Types.VARCHAR),
                        new SqlParameter("equipoTransaccion", Types.VARCHAR),
                        new SqlParameter("opcion", Types.CHAR),
                        new SqlOutParameter("co_msg", Types.SMALLINT),
                        new SqlOutParameter("ds_msg", Types.VARCHAR)
                )
                .returningResultSet("result", (rs, rowNum) -> {
                    Map<String, Object> row = new HashMap<>();
                    int cols = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= cols; i++) {
                        row.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
                    }
                    return row;
                });

        String opcion = "";
        if(codigoRol==0)
            opcion ="IN";
        else
            opcion ="AC";

        Map<String, Object> params = new HashMap<>();
        params.put("codigoRol", codigoRol);
        params.put("nombreRol", nombreRol);
        params.put("estadoRol", estadoRol);
        params.put("usuarioTransaccion", "admin");
        params.put("equipoTransaccion", "servidor");
        params.put("opcion", opcion);

        Map<String, Object> out = jdbcCall.execute(params);

        Short codigoError = ((Number) out.get("co_msg")).shortValue();
        String mensajeError = (String) out.get("ds_msg");

        String body = "";

        return new RespuestaDto(codigoError, mensajeError, body);
    }

    public RespuestaDto obtenerRol() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spObtenerRoles")
                .declareParameters(
                        new SqlOutParameter("co_msg", Types.SMALLINT),
                        new SqlOutParameter("ds_msg", Types.VARCHAR)
                )
                .returningResultSet("result", (rs, rowNum) -> {
                    Map<String, Object> row = new HashMap<>();
                    int cols = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= cols; i++) {
                        row.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
                    }
                    return row;
                });

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> out = jdbcCall.execute(params);

        Short codigoError = ((Number) out.get("co_msg")).shortValue();
        String mensajeError = (String) out.get("ds_msg");

        List<Map<String, Object>> result = (List<Map<String, Object>>) out.get("result");
        String body = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            body = mapper.writeValueAsString(result); // aquí puede lanzar JsonProcessingException
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        return new RespuestaDto(codigoError, mensajeError, body);
    }

    public RespuestaDto listarOpcionMenu(Short codigoOpcionPadre) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spMantenimientoOpcionMenu")
                .declareParameters(
                        new SqlParameter("codigoOpcion", Types.SMALLINT),
                        new SqlParameter("codigoOpcionPadre", Types.SMALLINT),
                        new SqlParameter("nombreOpcion", Types.VARCHAR),
                        new SqlParameter("urlOpcion", Types.VARCHAR),
                        new SqlParameter("estadoOpcion", Types.BOOLEAN),
                        new SqlParameter("usuarioTransaccion", Types.VARCHAR),
                        new SqlParameter("equipoTransaccion", Types.VARCHAR),
                        new SqlParameter("opcion", Types.CHAR),
                        new SqlOutParameter("co_msg", Types.SMALLINT),
                        new SqlOutParameter("ds_msg", Types.VARCHAR)
                )
                .returningResultSet("result", (rs, rowNum) -> {
                    Map<String, Object> row = new HashMap<>();
                    int cols = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= cols; i++) {
                        row.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
                    }
                    return row;
                });

        Map<String, Object> params = new HashMap<>();
        params.put("codigoOpcion", 0);
        params.put("codigoOpcionPadre", codigoOpcionPadre);
        params.put("nombreOpcion", "");
        params.put("urlOpcion", "");
        params.put("estadoOpcion", false);
        params.put("usuarioTransaccion", "");
        params.put("equipoTransaccion", "");
        params.put("opcion", "CO");

        Map<String, Object> out = jdbcCall.execute(params);

        Short codigoError = ((Number) out.get("co_msg")).shortValue();
        String mensajeError = (String) out.get("ds_msg");

        List<Map<String, Object>> result = (List<Map<String, Object>>) out.get("result");
        String body = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            body = mapper.writeValueAsString(result); // aquí puede lanzar JsonProcessingException
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        return new RespuestaDto(codigoError, mensajeError, body);
    }

    public RespuestaDto mantenimientoOpcionMenu(Short codigoOpcion, Short codigoOpcionPadre, String nombreOpcion, String urlOpcion, Boolean estadoOpcion) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spMantenimientoOpcionMenu")
                .declareParameters(
                        new SqlParameter("codigoOpcion", Types.SMALLINT),
                        new SqlParameter("codigoOpcionPadre", Types.SMALLINT),
                        new SqlParameter("nombreOpcion", Types.VARCHAR),
                        new SqlParameter("urlOpcion", Types.VARCHAR),
                        new SqlParameter("estadoOpcion", Types.BOOLEAN),
                        new SqlParameter("usuarioTransaccion", Types.VARCHAR),
                        new SqlParameter("equipoTransaccion", Types.VARCHAR),
                        new SqlParameter("opcion", Types.CHAR),
                        new SqlOutParameter("co_msg", Types.SMALLINT),
                        new SqlOutParameter("ds_msg", Types.VARCHAR)
                )
                .returningResultSet("result", (rs, rowNum) -> {
                    Map<String, Object> row = new HashMap<>();
                    int cols = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= cols; i++) {
                        row.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
                    }
                    return row;
                });

        String opcion = "";
        if(codigoOpcion==0)
            opcion ="IN";
        else
            opcion ="AC";

        Map<String, Object> params = new HashMap<>();
        params.put("codigoOpcion", codigoOpcion);
        params.put("codigoOpcionPadre", codigoOpcionPadre);
        params.put("nombreOpcion", nombreOpcion);
        params.put("urlOpcion", urlOpcion);
        params.put("estadoOpcion", estadoOpcion);
        params.put("usuarioTransaccion", "admin");
        params.put("equipoTransaccion", "servidor");
        params.put("opcion", opcion);

        Map<String, Object> out = jdbcCall.execute(params);

        Short codigoError = ((Number) out.get("co_msg")).shortValue();
        String mensajeError = (String) out.get("ds_msg");

        String body = "";

        return new RespuestaDto(codigoError, mensajeError, body);
    }

    public RespuestaDto obtenerOpcionMenuPadre() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spObtenerOpcionMenuPadre")
                .declareParameters(
                        new SqlOutParameter("co_msg", Types.SMALLINT),
                        new SqlOutParameter("ds_msg", Types.VARCHAR)
                )
                .returningResultSet("result", (rs, rowNum) -> {
                    Map<String, Object> row = new HashMap<>();
                    int cols = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= cols; i++) {
                        row.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
                    }
                    return row;
                });

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> out = jdbcCall.execute(params);

        Short codigoError = ((Number) out.get("co_msg")).shortValue();
        String mensajeError = (String) out.get("ds_msg");

        List<Map<String, Object>> result = (List<Map<String, Object>>) out.get("result");
        String body = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            body = mapper.writeValueAsString(result); // aquí puede lanzar JsonProcessingException
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir a JSON: " + e.getMessage());
            body = "[]";
        }

        return new RespuestaDto(codigoError, mensajeError, body);
    }

    public RespuestaDto mantenimientoUsuario(Short codigoUsuario, String loginUsuario, String nombreUsuario, String numeroIdentificacion, String claveUsuario, Boolean estadoUsuario) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spMantenimientoUsuarios")
                .declareParameters(
                        new SqlParameter("codigoUsuario", Types.SMALLINT),
                        new SqlParameter("loginUsuario", Types.VARCHAR),
                        new SqlParameter("nombreUsuario", Types.VARCHAR),
                        new SqlParameter("numeroIdentificacion", Types.VARCHAR),
                        new SqlParameter("claveUsuario", Types.VARCHAR),
                        new SqlParameter("estadoUsuario", Types.BOOLEAN),
                        new SqlParameter("usuarioTransaccion", Types.VARCHAR),
                        new SqlParameter("equipoTransaccion", Types.VARCHAR),
                        new SqlParameter("opcion", Types.CHAR),
                        new SqlOutParameter("co_msg", Types.SMALLINT),
                        new SqlOutParameter("ds_msg", Types.VARCHAR)
                )
                .returningResultSet("result", (rs, rowNum) -> {
                    Map<String, Object> row = new HashMap<>();
                    int cols = rs.getMetaData().getColumnCount();
                    for (int i = 1; i <= cols; i++) {
                        row.put(rs.getMetaData().getColumnLabel(i), rs.getObject(i));
                    }
                    return row;
                });

        String opcion = "";

        if(codigoUsuario==0)
            opcion ="IN";
        else
            opcion ="AC";

        Map<String, Object> params = new HashMap<>();
        params.put("codigoUsuario", codigoUsuario);
        params.put("loginUsuario", loginUsuario);
        params.put("nombreUsuario", nombreUsuario);
        params.put("numeroIdentificacion", numeroIdentificacion);
        params.put("claveUsuario", claveUsuario);
        params.put("estadoUsuario", estadoUsuario);
        params.put("usuarioTransaccion", "admin");
        params.put("equipoTransaccion", "servidor");
        params.put("opcion", opcion);

        System.out.println("Params enviados al SP: " + params);

        Map<String, Object> out = jdbcCall.execute(params);

        Short codigoError = ((Number) out.get("co_msg")).shortValue();
        String mensajeError = (String) out.get("ds_msg");

        String body = "";

        return new RespuestaDto(codigoError,mensajeError,body);
    }

}


