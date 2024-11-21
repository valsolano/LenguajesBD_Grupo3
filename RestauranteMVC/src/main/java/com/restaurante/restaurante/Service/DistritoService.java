/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.Service;

import com.restaurante.restaurante.Domain.Distrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Service
public class DistritoService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
     public List<Distrito> obtenerDistritos() {
        String sql = "SELECT ID_DISTRITO, NOMBRE, ID_CANTON, ID_ESTADO FROM FIDE_DISTRITO_TB";
        
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Distrito distrito = new Distrito();
            distrito.setIdDistrito(rs.getInt("id_distrito"));
            distrito.setNombre(rs.getString("nombre"));
            distrito.setIdCanton(rs.getInt("id_canton"));
            distrito.setIdEstado(rs.getInt("id_estado"));
            return distrito;
        });
    }
    
      public Distrito obtenerDistritoPorId(int id) {
        String sql = "SELECT ID_DISTRITO, NOMBRE, ID_CANTON, ID_ESTADO FROM FIDE_DISTRITO_TB WHERE ID_DISTRITO = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Distrito.class), id);
    }

    public void insertarDistrito( String nombre,Integer idCanton) {
        String sql = "{call INSERTAR_DISTRITO_SP(?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, nombre);
            callableStatement.setInt(2, idCanton.intValue());
            return callableStatement;
        });
    }

    public void actualizarDistrito(Integer idDistrito,String nombre,Integer idCanton, Integer idEstado) {
        String sql = "{call ACTUALIZAR_DISTRITO_SP(?, ?, ?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idDistrito.intValue());
            callableStatement.setString(2, nombre);
            callableStatement.setInt(3, idCanton.intValue());
            callableStatement.setInt(4, idEstado.intValue());
            return callableStatement;
        });
    }

    public void eliminarDistrito(Integer idDistrito) {
        String sql = "{call ELIMINAR_DISTRITO_SP(?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idDistrito.intValue());
            return callableStatement;
        });
    }
    
}
