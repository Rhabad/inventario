package com.inventario.models.Stock.dao;

import com.inventario.models.Producto.dto.ProductoStockDto;
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

    public void updateStock(ProductoStockDto productoStockDto) {
        String sql = "UPDATE stock " +
                "SET " +
                "    stock = :stock, " +
                "    stock_minimo = :stock_minimo, " +
                "    stock_maximo = :stock_maximo " +
                "WHERE " +
                "    producto_id = :producto_id";
        jdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("stock", productoStockDto.getStock())
                .addValue("stock_minimo", productoStockDto.getStockMinimo())
                .addValue("stock_maximo", productoStockDto.getStockMaximo())
                .addValue("producto_id", productoStockDto.getId())
        );
    }
}
