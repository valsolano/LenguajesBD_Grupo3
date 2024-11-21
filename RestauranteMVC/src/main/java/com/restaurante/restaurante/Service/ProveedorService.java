/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.restaurante.restaurante.Domain.Proveedor;

@Service
public class ProveedorService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Proveedor> obtenerProveedores() {
        String sql = "SELECT ID_PROVEEDOR, NOMBRE, TELEFONO, EMAIL, ID_DIRECCION, ID_ESTADO FROM FIDE_PROVEEDORES_TB";
        
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Proveedor proveedor = new Proveedor();
            proveedor.setIdProveedor(rs.getInt("id_proveedor"));
            proveedor.setNombre(rs.getString("nombre"));
            proveedor.setTelefono(rs.getString("telefono"));
            proveedor.setEmail(rs.getString("email"));
            proveedor.setIdDireccion(rs.getInt("id_direccion"));
            proveedor.setIdEstado(rs.getInt("id_estado"));
            return proveedor;
        });
    }

    public Proveedor obtenerProveedorPorId(int id) {
        String sql = "SELECT ID_PROVEEDOR, NOMBRE, TELEFONO, EMAIL, ID_DIRECCION, ID_ESTADO FROM FIDE_PROVEEDORES_TB WHERE ID_PROVEEDOR = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Proveedor.class), id);
    }

    public void insertarProveedor(String nombre,String telefono, String email, Integer idDireccion) {
        String sql = "{call INSERTAR_PROVEEDOR_SP(?, ?, ?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, nombre);
            callableStatement.setString(2, telefono);
            callableStatement.setString(3, email);
            callableStatement.setInt(4, idDireccion.intValue());
            return callableStatement;
        });
    }

    public void actualizarProveedor( Integer idProveedor,String nombre,String telefono, String email, Integer idDireccion, Integer idEstado) {
        String sql = "{call ACTUALIZAR_PROVEEDOR_SP(?, ?, ?, ?, ?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idProveedor.intValue());
            callableStatement.setString(2, nombre);
            callableStatement.setString(3, telefono);
            callableStatement.setString(4, email);
            callableStatement.setInt(5, idDireccion.intValue());
            callableStatement.setInt(6, idEstado.intValue());
            return callableStatement;
        });
    }
    
    public void eliminarProveedor(Integer idProveedor) {
        String sql = "{call ELIMINAR_PROVEEDOR_SP(?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idProveedor.intValue());
          
            return callableStatement;
        });
    }
}
