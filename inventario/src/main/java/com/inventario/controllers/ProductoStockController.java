package com.inventario.controllers;

import com.inventario.services.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductoStockController {

    ProductoService proService;

    public ProductoStockController(ProductoService proService) {
        this.proService = proService;
    }

    @GetMapping("/productos")
    public ResponseEntity<?> getProductos() {
        return new ResponseEntity<>(proService.findAllProductos(), HttpStatus.OK);
    }
}
