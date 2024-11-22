/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.Service;

import com.restaurante.restaurante.Domain.Provincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Service
public class ProvinciaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Provincia> obtenerProvincias() {
        String sql = "SELECT ID_PROVINCIA, NOMBRE, ID_ESTADO FROM FIDE_PROVINCIA_TB";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Provincia provincia = new Provincia();
            provincia.setIdProvincia(rs.getInt("id_provincia"));
            provincia.setNombre(rs.getString("nombre"));
            provincia.setIdEstado(rs.getInt("id_estado"));

            return provincia;
        });
    }

    public Provincia obtenerProvinciaPorId(int id) {
        String sql = "SELECT ID_PROVINCIA, NOMBRE, ID_ESTADO FROM FIDE_PROVINCIA_TB WHERE ID_PROVINCIA = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Provincia.class), id);
    }

    public void insertarProvincia(String nombre) {
        String sql = "{call INSERTAR_PROVINCIA_SP(?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, nombre);
            return callableStatement;
        });
    }

    public void actualizarProvincia(Integer idProvincia, String nombre, Integer idEstado) {
        String sql = "{call ACTUALIZAR_PROVINCIA_SP(?, ?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idProvincia.intValue());
            callableStatement.setString(2, nombre);
            callableStatement.setInt(3, idEstado.intValue());
            return callableStatement;
        });
    }

    public void eliminarProvincia(Integer idProvincia) {
        String sql = "{call ELIMINAR_PROVINCIA_SP(?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idProvincia.intValue());
            return callableStatement;
        });
    }

}
