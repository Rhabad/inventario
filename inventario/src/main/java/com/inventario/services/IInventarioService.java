package com.inventario.services;

import java.util.List;

import com.inventario.models.inventario.dto.InventarioDto;

public interface IInventarioService {
    List<InventarioDto> findAllInventario();

    void createInventario(InventarioDto inventarioDto);

    void updateInventario(InventarioDto inventarioDto);
}
