/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.Service;
import com.restaurante.restaurante.Domain.Restaurante;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Service

public class RestauranteService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Restaurante> obtenerRestaurantes() {
        String sql = "SELECT ID_RESTAURANTE, NOMBRE, TELEFONO, CORREO, DIRECCION, ID_ESTADO FROM FIDE_RESTAURANTES_TB";
        
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Restaurante restaurante = new Restaurante();
            restaurante.setIdRestaurante(rs.getInt("id_restaurante"));
            restaurante.setNombre(rs.getString("nombre"));
            restaurante.setTelefono(rs.getString("telefono"));
            restaurante.setCorreo(rs.getString("correo"));
            restaurante.setDireccion(rs.getString("direccion"));
            restaurante.setIdEstado(rs.getInt("id_estado"));
            return restaurante;
        });
    }

    public Restaurante obtenerRestaurantePorId(int id) {
        String sql = "SELECT ID_RESTAURANTE, NOMBRE, TELEFONO, CORREO, DIRECCION, ID_ESTADO FROM FIDE_RESTAURANTES_TB WHERE ID_RESTAURANTE = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Restaurante.class), id);
    }

    public void insertarRestaurante(String nombre,String telefono, String correo, String direccion) {
        String sql = "{call INSERTAR_RESTAURANTE_SP(?, ?, ?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, nombre);
            callableStatement.setString(2, telefono);
            callableStatement.setString(3, correo);
            callableStatement.setString(4, direccion);
            return callableStatement;
        });
    }

    public void actualizarRestaurante( Integer idRestaurante,String nombre,String telefono, String correo, String direccion, Integer idEstado) {
        String sql = "{call ACTUALIZAR_RESTAURANTE_SP(?, ?, ?, ?, ?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idRestaurante.intValue());
            callableStatement.setString(2, nombre);
            callableStatement.setString(3, telefono);
            callableStatement.setString(4, correo);
            callableStatement.setString(5, direccion);
            callableStatement.setInt(6, idEstado.intValue());
            return callableStatement;
        });
    }
    
    public void eliminarRestaurante(Integer idRestaurante) {
        String sql = "{call ELIMINAR_RESTAURANTE_SP(?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idRestaurante.intValue());
          
            return callableStatement;
        });
    }
    
    
}
