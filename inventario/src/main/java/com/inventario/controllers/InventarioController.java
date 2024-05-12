package com.inventario.controllers;

import com.inventario.models.inventario.dto.InventarioDto;
import com.inventario.services.IInventarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InventarioController {

    private final IInventarioService inventarioService;

    public InventarioController(IInventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping("/inventarios")
    public ResponseEntity<List<InventarioDto>> findAllInventario() {
        return new ResponseEntity<>(inventarioService.findAllInventario(), HttpStatus.OK);
    }
}
