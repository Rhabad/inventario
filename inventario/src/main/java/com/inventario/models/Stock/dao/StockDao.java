package com.inventario.models.Stock.dao;

import com.inventario.models.Stock.dto.StockDto;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StockDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StockDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createStock(StockDto stockDto, Long productoId) {
        String sql = "INSERT INTO stock (stock, stock_minimo, stock_maximo, producto_id) " +
                "VALUES(:stock, :stock_minimo, :stock_maximo, :producto_id)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("stock", stockDto.getStock());
        params.addValue("stock_minimo", stockDto.getStockMinimo());
        params.addValue("stock_maximo", stockDto.getStockMaximo());
        params.addValue("producto_id", productoId);
        jdbcTemplate.update(sql, params);
    }
}
