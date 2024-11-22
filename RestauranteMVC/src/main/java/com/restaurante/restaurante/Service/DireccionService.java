/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.Service;

import com.restaurante.restaurante.Domain.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Service
public class DireccionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Direccion> obtenerDirecciones() {
        String sql = "SELECT ID_DIRECCION, ID_PROVINCIA, ID_CANTON, ID_DISTRITO, DIRECCION,ID_ESTADO FROM FIDE_DIRECCIONES_TB";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Direccion direccion = new Direccion();
            direccion.setIdDireccion(rs.getInt("id_direccion"));
            direccion.setIdProvincia(rs.getInt("id_provincia"));
            direccion.setIdCanton(rs.getInt("id_canton"));
            direccion.setIdDistrito(rs.getInt("id_distrito"));
            direccion.setDireccion(rs.getString("direccion"));
            direccion.setIdEstado(rs.getInt("id_estado"));
            return direccion;
        });
    }

    public Direccion obtenerDireccionPorId(int id) {
        String sql = "SELECT ID_DIRECCION, ID_PROVINCIA, ID_CANTON, ID_DISTRITO,DIRECCION, ID_ESTADO FROM FIDE_DIRECCIONES_TB WHERE ID_DIRECCION = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Direccion.class), id);
    }

    public void insertarDireccion(Integer idProvincia, Integer idCanton, Integer idDistrito, String Direccion) {
        String sql = "{call INSERTAR_DIRECCION_SP(?, ?, ?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idProvincia.intValue());
            callableStatement.setInt(2, idCanton.intValue());
            callableStatement.setInt(3, idDistrito.intValue());
            callableStatement.setString(4, Direccion);
            return callableStatement;
        });
    }

    public void actualizarDireccion(Integer idDireccion, Integer idProvincia, Integer idCanton, Integer idDistrito, Integer idEstado, String Direccion) {
        String sql = "{call ACTUALIZAR_DIRECCION_SP(?, ?, ?, ?, ?,?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idDireccion.intValue());
            callableStatement.setInt(2, idProvincia.intValue());
            callableStatement.setInt(3, idCanton.intValue());
            callableStatement.setInt(4, idDistrito.intValue());
            callableStatement.setInt(5, idEstado.intValue());
            callableStatement.setString(6, Direccion);

            return callableStatement;
        });
    }

    public void eliminarDireccion(Integer idDireccion) {
        String sql = "{call ELIMINAR_DIRECCION_SP(?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idDireccion.intValue());
            return callableStatement;
        });
    }

}
