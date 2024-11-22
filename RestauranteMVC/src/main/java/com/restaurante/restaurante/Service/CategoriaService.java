package com.restaurante.restaurante.Service;

import com.restaurante.restaurante.Domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Service

public class CategoriaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Categoria> obtenerCategorias() {
        String sql = "SELECT ID_CATEGORIA, NOMBRE, DESCRIPCION, ID_ESTADO FROM FIDE_CATEGORIA_TB";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(rs.getInt("id_categoria"));
            categoria.setNombre(rs.getString("nombre"));
            categoria.setDescripcion(rs.getString("descripcion"));
            categoria.setIdEstado(rs.getInt("id_estado"));

            return categoria;
        });
    }

    public Categoria obtenerCategoriaPorId(int id) {
        String sql = "SELECT ID_CATEGORIA, NOMBRE, DESCRIPCION, ID_ESTADO FROM FIDE_CATEGORIA_TB WHERE ID_CATEGORIA= ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Categoria.class), id);
    }

    public void insertarCategoria(String nombre, String descripcion) {
        String sql = "{call INSERTAR_CATEGORIA_SP(?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, nombre);
            callableStatement.setString(2, descripcion);
            return callableStatement;
        });
    }

    public void actualizarCategoria(Integer idCategoria, String nombre, String descripcion, Integer idEstado) {
        String sql = "{call ACTUALIZAR_CATEGORIA_SP(?, ?, ?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idCategoria.intValue());
            callableStatement.setString(2, nombre);
            callableStatement.setString(3, descripcion);
            callableStatement.setInt(4, idEstado.intValue());
            return callableStatement;
        });
    }

    public void eliminarCategoria(Integer idCategoria) {
        String sql = "{call ELIMINAR_CATEGORIA_SP(?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idCategoria.intValue());
            return callableStatement;
        });
    }

}
