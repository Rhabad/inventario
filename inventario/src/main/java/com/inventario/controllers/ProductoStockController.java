package com.inventario.controllers;

import com.inventario.models.Producto.dto.ProductoStockDto;
import com.inventario.services.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductoStockController {

    IProductoService proService;

    public ProductoStockController(IProductoService proService) {
        this.proService = proService;
    }

    @GetMapping("/productos")
    public ResponseEntity<?> getProductos() {
        return new ResponseEntity<>(proService.findAllProductos(), HttpStatus.OK);
    }

    @PostMapping("/producto")
    public ResponseEntity<String> crearProducto(
            @RequestBody ProductoStockDto prodStockDto
    ) {
        proService.crearProductoStock(prodStockDto);
        return new ResponseEntity<>("Se creo la wea", HttpStatus.OK);
    }
}
