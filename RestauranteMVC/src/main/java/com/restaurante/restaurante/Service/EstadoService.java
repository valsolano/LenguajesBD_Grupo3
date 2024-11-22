/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restaurante.restaurante.Service;

import com.restaurante.restaurante.Domain.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Service

public class EstadoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Estado> obtenerEstados() {
        String sql = "SELECT ID_ESTADO, TIPO_ESTADO FROM FIDE_ESTADOS_TB";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Estado estado = new Estado();
            estado.setIdEstado(rs.getInt("id_estado"));
            estado.setTipoEstado(rs.getString("tipo_estado"));
            return estado;
        });
    }

    public Estado obtenerEstadoPorId(int id) {
        String sql = "SELECT ID_ESTADO, TIPO_ESTADO FROM FIDE_ESTADOS_TB WHERE ID_ESTADO = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Estado.class), id);
    }

    public void insertarEstado(String tipo_estado) {
        String sql = "{call INSERTAR_ESTADO_SP(?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, tipo_estado);

            return callableStatement;
        });
    }

    /*public void insertarEstado(String tipo_estado) {
    String sql = "{call INSERTAR_ESTADO_SP(?)}";

    jdbcTemplate.update(connection -> {

        CallableStatement callableStatement = connection.prepareCall(sql);
        callableStatement.setString(1, tipo_estado);  // Configura el valor del parÃ¡metro
        return callableStatement;
    });
}*/
    public void actualizarEstado(Integer idEstado, String tipo_estado) {
        String sql = "{call ACTUALIZAR_ESTADO_SP(?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idEstado);
            callableStatement.setString(2, tipo_estado);
            return callableStatement;
        });
    }

    public void eliminarEstado(Integer idEstado) {
        String sql = "{call ELIMINAR_ESTADO_SP(?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idEstado.intValue());
            return callableStatement;
        });
    }

}
