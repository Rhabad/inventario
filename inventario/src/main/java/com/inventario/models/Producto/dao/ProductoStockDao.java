package com.inventario.models.Producto.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductoStockDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProductoStockDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findAllProductoStock() {
        String sql = "SELECT p.*, s.stock, s.stock_minimo, s.stock_maximo\n" +
                "FROM producto p\n" +
                "    LEFT JOIN stock s ON p.id = s.producto_id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.queryForList(sql, params);
    }

    public void deleteProductoStock(Long id) {
        String sql = "DELETE FROM producto WHERE id = :id";

        jdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("id", id)
        );
    }
}
