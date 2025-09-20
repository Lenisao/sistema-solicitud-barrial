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
public class ConfiguracionRepository {

    @Autowired
    private DataSource dataSource;

    private SimpleJdbcCall jdbcCall;

    public RespuestaDto listarTipoServicio() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spMantenimientoTipoServicio")
                .declareParameters(
                        new SqlParameter("codigoTipoServicio", Types.SMALLINT),
                        new SqlParameter("nombreTipoServicio", Types.VARCHAR),
                        new SqlParameter("estadoTipoServicio", Types.BOOLEAN),
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
        params.put("codigoTipoServicio", 0);
        params.put("nombreTipoServicio", "");
        params.put("estadoTipoServicio", false);
        params.put("usuarioTransaccion", "");
        params.put("equipoTransaccion", "");
        params.put("opcion", "CO");
       //System.out.println("Params enviados al SP: " + params);

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

    public RespuestaDto mantenimientoTipoServicio(Short codigoTipoServicio, String nombreTipoServicio, Boolean estadoTipoServicio) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spMantenimientoTipoServicio")
                .declareParameters(
                        new SqlParameter("codigoTipoServicio", Types.SMALLINT),
                        new SqlParameter("nombreTipoServicio", Types.VARCHAR),
                        new SqlParameter("estadoTipoServicio", Types.BOOLEAN),
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
        if(codigoTipoServicio==0)
            opcion ="IN";
        else
            opcion ="AC";

        Map<String, Object> params = new HashMap<>();
        params.put("codigoTipoServicio", codigoTipoServicio);
        params.put("nombreTipoServicio", nombreTipoServicio);
        params.put("estadoTipoServicio", estadoTipoServicio);
        params.put("usuarioTransaccion", "admin");
        params.put("equipoTransaccion", "servidor");
        params.put("opcion", opcion);

        Map<String, Object> out = jdbcCall.execute(params);

        Short codigoError = ((Number) out.get("co_msg")).shortValue();
        String mensajeError = (String) out.get("ds_msg");

        String body = "";

        return new RespuestaDto(codigoError, mensajeError, body);
    }

    public RespuestaDto obtenerTipoServicio() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spObtenerTipoServicio")
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

    public RespuestaDto listarServicioPorTipo(Short codigoTipoServicio) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spMantenimientoServicio")
                .declareParameters(
                        new SqlParameter("codigoServicio", Types.SMALLINT),
                        new SqlParameter("codigoTipoServicio", Types.SMALLINT),
                        new SqlParameter("nombreServicio", Types.VARCHAR),
                        new SqlParameter("estadoServicio", Types.BOOLEAN),
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
        params.put("codigoServicio", 0);
        params.put("codigoTipoServicio", codigoTipoServicio);
        params.put("nombreServicio", "");
        params.put("estadoServicio", false);
        params.put("usuarioTransaccion", "");
        params.put("equipoTransaccion", "");
        params.put("opcion", "CO");

        //System.out.println("Params enviados al SP: " + params);

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

    public RespuestaDto mantenimientoServicio(Short codigoServicio, Short codigoTipoServicio, String nombreServicio, Boolean estadoServicio) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spMantenimientoServicio")
                .declareParameters(
                        new SqlParameter("codigoServicio", Types.SMALLINT),
                        new SqlParameter("codigoTipoServicio", Types.SMALLINT),
                        new SqlParameter("nombreServicio", Types.VARCHAR),
                        new SqlParameter("estadoServicio", Types.BOOLEAN),
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
        if(codigoServicio==0)
            opcion ="IN";
        else
            opcion ="AC";

        Map<String, Object> params = new HashMap<>();
        params.put("codigoServicio", codigoServicio);
        params.put("codigoTipoServicio", codigoTipoServicio);
        params.put("nombreServicio", nombreServicio);
        params.put("estadoServicio", estadoServicio);
        params.put("usuarioTransaccion", "admin");
        params.put("equipoTransaccion", "servidor");
        params.put("opcion", opcion);

        Map<String, Object> out = jdbcCall.execute(params);

        Short codigoError = ((Number) out.get("co_msg")).shortValue();
        String mensajeError = (String) out.get("ds_msg");

        String body = "";

        return new RespuestaDto(codigoError, mensajeError, body);
    }

    public RespuestaDto obtenerServicioPorTipo(Short codigoTipoServicio) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("spObtenerServicioxTipo")
                .declareParameters(
                        new SqlParameter("codigoServicio", Types.SMALLINT),
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
        params.put("codigoTipoServicio", codigoTipoServicio);

        //System.out.println("Params enviados al SP: " + params);

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

}
