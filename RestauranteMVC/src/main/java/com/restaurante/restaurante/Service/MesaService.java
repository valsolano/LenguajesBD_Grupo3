/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.Service;

import com.restaurante.restaurante.Domain.Mesa;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service

public class MesaService {
    
    
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Mesa> obtenerMesas() {
        String sql = "SELECT ID_MESA, CAPACIDAD, ID_ESTADO FROM FIDE_MESAS_TB";
        
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Mesa mesa = new Mesa();
            mesa.setIdMesa(rs.getInt("id_mesa"));
            mesa.setCapacidad(rs.getInt("capacidad"));
            mesa.setIdEstado(rs.getInt("id_estado"));
            return mesa;
        });
    }

    public Mesa obtenerMesaPorId(int id) {
        String sql = "SELECT ID_MESA, CAPACIDAD, ID_ESTADO FROM FIDE_MESAS_TB WHERE ID_MESA = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Mesa.class), id);
    }

    public void insertarMesa(Integer capacidad) {
        String sql = "{call INSERTAR_MESAS_SP(?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, capacidad.intValue());
            return callableStatement;
        });
    }

    public void actualizarMesa( Integer idMesa, Integer capacidad, Integer idEstado) {
        String sql = "{call ACTUALIZAR_MESAS_SP(?, ?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idMesa.intValue());
            callableStatement.setInt(2, capacidad.intValue());
            callableStatement.setInt(3, idEstado.intValue());
            return callableStatement;
        });
    }
    
    public void eliminarMesa(Integer idMesa) {
        String sql = "{call ELIMINAR_MESAS_SP(?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idMesa.intValue());
          
            return callableStatement;
        });
    }
}
