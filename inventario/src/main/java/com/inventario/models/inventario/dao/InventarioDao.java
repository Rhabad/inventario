package com.inventario.models.inventario.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inventario.models.inventario.dto.InventarioDto;

@Repository
public class InventarioDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public InventarioDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createInventario(InventarioDto inventarioDto) {
        String sql = "INSERT INTO inventario (nombre_inventario) VALUES (:nombre_inventario)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("nombre_variable", inventarioDto.getNombreInventario());
        jdbcTemplate.update(sql, params);
    }

    public List<Map<String, Object>> findAllInventario() {
        String sql = "SELECT * FROM inventario";

        return jdbcTemplate.queryForList(sql, new MapSqlParameterSource());
    }

    public void updateInventario(InventarioDto inventarioDto) {
        String sql = "UPDATE inventario " +
                "SET " +
                "    nombre_inventario = :nombre_inventario " +
                "WHERE " +
                "    id_inventario = :id_inventario";

        jdbcTemplate.update(sql, new MapSqlParameterSource()
                .addValue("nombre_inventario", inventarioDto.getNombreInventario())
                .addValue("id_inventario", inventarioDto.getIdInventario())
        );
    }
}
