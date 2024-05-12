package com.inventario.models.Producto.dao;

import com.inventario.models.Producto.dto.ProductoDto;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProductoDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createProducto(ProductoDto productoDto) {
        String sql = "INSERT INTO producto (nombre_producto, precio, precio_oferta) " +
                "VALUES(:nombre_producto, :precio, :precio_oferta)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nombre_producto", productoDto.getNombreProducto());
        params.addValue("precio", productoDto.getPrecio());
        params.addValue("precio_oferta", productoDto.getPrecioOferta());
        jdbcTemplate.update(sql, params);
    }

    public ProductoDto lastProducto() {
        String lastIdProducto = "SELECT * FROM producto ORDER BY id DESC LIMIT 1";

        MapSqlParameterSource params = new MapSqlParameterSource();
        ProductoDto lastProducto = jdbcTemplate.queryForObject(lastIdProducto, params,
                (rs, rowNum) -> {
                    ProductoDto p = ProductoDto.builder()
                            .id(rs.getLong("id"))
                            .nombreProducto(rs.getString("nombre_producto"))
                            .precio(rs.getInt("precio"))
                            .precioOferta(rs.getInt("precio_oferta"))
                            .build();
                    return p;
                });

        return lastProducto;
    }
}
