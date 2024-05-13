package com.inventario.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.inventario.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.inventario.models.inventario.dao.InventarioDao;
import com.inventario.models.inventario.dto.InventarioDto;
import com.inventario.services.IInventarioService;

@Service
public class InventarioService implements IInventarioService {

    private final InventarioDao inventarioDao;

    public InventarioService(InventarioDao inventarioDao) {
        this.inventarioDao = inventarioDao;
    }

    @Override
    public List<InventarioDto> findAllInventario() {
        List<Map<String, Object>> inventarioLista = inventarioDao.findAllInventario();

        List<InventarioDto> listaInventarioDto = new ArrayList<>();

        for (Map<String, Object> inventario : inventarioLista) {
            listaInventarioDto.add(InventarioDto.builder()
                    .idInventario((Long) inventario.get("id_inventario"))
                    .nombreInventario((String) inventario.get("nombre_inventario"))
                    .build());
        }
        return listaInventarioDto;
    }

    @Override
    public void createInventario(InventarioDto inventarioDto) {
        inventarioDao.createInventario(inventarioDto);
    }

    @Override
    public void updateInventario(InventarioDto inventarioDto) {
        if (inventarioDto.getIdInventario() != null) {
            inventarioDao.updateInventario(inventarioDto);
        } else {
            throw new ResourceNotFoundException("Inventario", "id_inventario", inventarioDto.getIdInventario());
        }

    }

    @Override
    public void deleteInventario(Long id) {
        if (id != null) {
            inventarioDao.deleteInventario(id);
        } else {
            throw new ResourceNotFoundException("Inventario", "id_inventario", id);
        }
    }
}
