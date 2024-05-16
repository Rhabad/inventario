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

    @DeleteMapping("/producto/{id}")
    public ResponseEntity<String> deleteProductoStockInventario(@PathVariable Long id) {
        proService.deleteProductoStock(id);
        return new ResponseEntity<>("se elimino la wea", HttpStatus.OK);
    }

    @PutMapping("/producto")
    public ResponseEntity<String> updateProductoStock(@RequestBody ProductoStockDto productoStockDto) {
        proService.updateProductoStock(productoStockDto);

        return new ResponseEntity<>("se actualzo la wea", HttpStatus.OK);
    }
}
