/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.Service;

import com.restaurante.restaurante.Domain.Cargo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author valer
 */
@Service
public class CargoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cargo> obtenerCargos() {
        String sql = "SELECT ID_CARGO, NOMBRE, DESCRIPCION, ID_ESTADO FROM FIDE_CARGOS_TB";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Cargo cargo = new Cargo();
            cargo.setIdCargo(rs.getInt("id_cargo"));
            cargo.setNombre(rs.getString("nombre"));
            cargo.setDescripcion(rs.getString("descripcion"));
            cargo.setIdEstado(rs.getInt("id_estado"));

            return cargo;
        });
    }

    public Cargo obtenerCargoPorId(int id) {
        String sql = "SELECT ID_CARGO, NOMBRE, DESCRIPCION, ID_ESTADO FROM FIDE_CARGOS_TB WHERE ID_CARGO = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Cargo.class), id);
    }

    public void insertarCargo(String nombre, String descripcion) {
        String sql = "{call INSERTAR_CARGO_SP(?,?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, nombre);
            callableStatement.setString(2, descripcion);

            return callableStatement;
        });
    }

    public void actualizarCargo(Integer idCargo, String nombre, String descripcion, Integer idEstado) {
        String sql = "{call ACTUALIZAR_CARGO_SP(?, ?, ?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idCargo);
            callableStatement.setString(2, nombre);
            callableStatement.setString(3, descripcion);
            callableStatement.setInt(4, idEstado);

            return callableStatement;
        });
    }

    public void eliminarCargo(Integer idCargo) {
        String sql = "{call ELIMINAR_CARGO_SP(?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idCargo);
            return callableStatement;
        });
    }
}
