package com.inventario.controllers;

import com.inventario.models.inventario.dto.InventarioDto;
import com.inventario.services.IInventarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/inventario")
    public ResponseEntity<String> createInventario(@RequestBody InventarioDto inventarioDto) {
        inventarioService.createInventario(inventarioDto);
        return new ResponseEntity<>("Se creo la wea", HttpStatus.CREATED);
    }

    @PutMapping("/inventario")
    public ResponseEntity<String> updateInventario(@RequestBody InventarioDto inventarioDto) {
        inventarioService.updateInventario(inventarioDto);
        return new ResponseEntity<>("Se actualizo la wea", HttpStatus.OK);
    }

    @DeleteMapping("/inventario/{id}")
    public ResponseEntity<String> deleteInventario(@PathVariable Long id) {
        inventarioService.deleteInventario(id);
        return new ResponseEntity<>("Se elimino la wea", HttpStatus.OK);
    }
}
