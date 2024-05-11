package com.inventario.models.Producto.dao;

import com.inventario.models.Producto.dto.ProductoStockDto;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public class ProductoStockDao {
    NamedParameterJdbcTemplate jdbcTemplate;
    MapSqlParameterSource params;

    public ProductoStockDao(NamedParameterJdbcTemplate jdbcTemplate, MapSqlParameterSource params) {
        this.jdbcTemplate = jdbcTemplate;
        this.params = params;
    }


    public List<Map<String, Object>> findAllProductoStock() {
        String sql = "SELECT * FROM producto";
        return jdbcTemplate.queryForList(sql, params);
    }

    @Transactional
    public void createProductoStock(ProductoStockDto prodStock) {

        try {
            String sql = "INSERT INTO producto (nombre_producto, precio, precio_oferta) " +
                    "VALUES(:nombre_producto, :precio, :precio_oferta)";
            params.addValue("nombre_producto", prodStock.getNombreProducto());
            params.addValue("precio", prodStock.getPrecio());
            params.addValue("precio_oferta", prodStock.getPrecioOferta());
            jdbcTemplate.update(sql, params);
            params.getValues().clear();


            String lastIdProducto = "SELECT *\n" +
                    "FROM producto\n" +
                    "ORDER BY id DESC\n" +
                    "LIMIT 1;";
            ProductoStockDto productoId = jdbcTemplate.queryForObject(lastIdProducto, params,
                    (rs, rowNum) -> {
                        ProductoStockDto p = ProductoStockDto.builder()
                                .id(rs.getLong("id"))
                                .nombreProducto(rs.getString("nombre_producto"))
                                .precio(rs.getInt("precio"))
                                .precioOferta(rs.getInt("precio_oferta"))
                                .build();
                        return p;
                    });
            params.getValues().clear();


            String sql2 = "INSERT INTO stock (stock, stock_minimo, stock_maximo, producto_id) " +
                    "VALUES(:stock, :stock_minimo, :stock_maximo, producto_id)";
            params.addValue("stock", prodStock.getStock());
            params.addValue("stock_minimo", prodStock.getStockMinimo());
            params.addValue("stock_maximo", prodStock.getStockMaximo());
            params.addValue("producto_id", productoId.getId());
            jdbcTemplate.update(sql2, params);

            params.getValues().clear();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("error al realizar la transaccion", e);
        }
    }

    public void deleteProductoStock(Long id) {
        String sql = "";
    }
}
