/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.Service;

import com.restaurante.restaurante.Domain.Canton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Service
public class CantonService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
  
    public List<Canton> obtenerCantones() {
        String sql = "SELECT ID_CANTON, NOMBRE, ID_PROVINCIA FROM FIDE_CANTON_TB";
        
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Canton canton = new Canton();
            canton.setIdCanton(rs.getInt("id_canton"));
            canton.setNombre(rs.getString("nombre"));
            canton.setIdProvincia(rs.getInt("id_provincia"));
            return canton;
        });
    }
    
     public Canton obtenerCantonPorId(int id) {
        String sql = "SELECT ID_CANTON, NOMBRE, ID_PROVINCIA FROM FIDE_CANTON_TB WHERE ID_CANTON = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Canton.class), id);
    }

        
     
    public void insertarCanton( String nombre, Integer idProvincia) {
        String sql = "{call INSERTAR_CANTON_SP(?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, nombre);
            callableStatement.setInt(2, idProvincia.intValue());
            return callableStatement;
        });
    }
    
   
 
    public void actualizarCanton(Integer idCanton, String nombre, Integer idProvincia, Integer idEstado) {
        String sql = "{call ACTUALIZAR_CANTON_SP(?, ?, ?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idCanton.intValue());
            callableStatement.setString(2, nombre);
            callableStatement.setInt(1, idProvincia.intValue());
            callableStatement.setInt(3, idEstado.intValue());
            return callableStatement;
        });
    }
    
  
    public void eliminarCanton(Integer idCanton) {
        String sql = "{call ELIMINAR_CANTON_SP(?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idCanton.intValue());
            return callableStatement;
        });
    }

}
