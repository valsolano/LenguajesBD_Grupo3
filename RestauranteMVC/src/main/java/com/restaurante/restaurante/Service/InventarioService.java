package com.restaurante.restaurante.Service;

import com.restaurante.restaurante.Domain.Inventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Service
public class InventarioService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Inventario> obtenerInventarios() {
        String sql = "SELECT ID_INVENTARIO, NOMBRE, DESCRIPCION, PRECIO_COMPRA, STOCK, FECHA_CADUCIDAD,ID_PROVEEDOR, ID_ESTADO FROM FIDE_INVENTARIO_TB";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Inventario inventario = new Inventario();
            inventario.setIdInventario(rs.getInt("id_inventario"));
            inventario.setNombre(rs.getString("nombre"));
            inventario.setDescripcion(rs.getString("descripcion"));
            inventario.setPrecioCompra(rs.getDouble("precio_compra"));
            inventario.setStock(rs.getInt("stock"));
            inventario.setFechaCaducidad(rs.getDate("fecha_caducidad"));
            inventario.setIdProveedor(rs.getInt("id_proveedor"));
            inventario.setIdEstado(rs.getInt("id_estado"));

            return inventario;
        });
    }

    public Inventario obtenerInventarioPorId(int id) {
        String sql = "SELECT ID_INVENTARIO, NOMBRE, DESCRIPCION, PRECIO_COMPRA, STOCK, FECHA_CADUCIDAD,ID_PROVEEDOR, ID_ESTADO FROM FIDE_INVENTARIO_TB WHERE ID_INVENTARIO = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Inventario.class), id);
    }

    public void insertarInventario(String nombre, String descripcion, double precioCompra, Integer stock, Date fechaCaducidad, Integer idProveedor) {
        String sql = "{call INSERTAR_INVENTARIO_SP(?, ?, ?, ?, ?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setString(1, nombre);
            callableStatement.setString(2, descripcion);
            callableStatement.setDouble(3, precioCompra);
            callableStatement.setInt(4, stock.intValue());
            callableStatement.setDate(5, new java.sql.Date(fechaCaducidad.getTime()));
            callableStatement.setInt(6, idProveedor.intValue());
            return callableStatement;
        });
    }

    public void actualizarInventario(Integer idInventario, String nombre, String descripcion,
            double precioCompra, Integer stock, Date fechaCaducidad, Integer idProveedor, Integer idEstado) {
        String sql = "{call ACTUALIZAR_INVENTARIO_SP(?, ?, ?, ?, ?, ?, ?, ?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idInventario.intValue());
            callableStatement.setString(2, nombre);
            callableStatement.setString(3, descripcion);
            callableStatement.setDouble(4, precioCompra);
            callableStatement.setInt(5, stock.intValue());
            callableStatement.setDate(6, new java.sql.Date(fechaCaducidad.getTime()));
            callableStatement.setInt(7, idProveedor.intValue());
            callableStatement.setInt(8, idEstado.intValue());
            return callableStatement;
        });
    }

    public void eliminarInventario(Integer idInventario) {
        String sql = "{call ELIMINAR_INVENTARIO_SP(?)}";

        jdbcTemplate.update(connection -> {
            var callableStatement = connection.prepareCall(sql);
            callableStatement.setInt(1, idInventario.intValue());
            return callableStatement;
        });
    }
}
