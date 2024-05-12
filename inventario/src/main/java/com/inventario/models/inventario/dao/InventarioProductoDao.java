package com.inventario.models.inventario.dao;

import com.inventario.models.inventario.dto.InventarioProductoDto;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InventarioProductoDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public InventarioProductoDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void asignarProductoAInventario(InventarioProductoDto invProd) {
        // agregar al inventario
        String inventarioProducto = "INSERT INTO\n" +
                "    inventario_producto (inventario_id, producto_id)\n" +
                "VALUES (:inventario_id, :producto_id)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("inventario_id", invProd.getInventarioId());
        params.addValue("producto_id", invProd.getProductoId());
        jdbcTemplate.update(inventarioProducto, params);
    }

}
