package com.inventario.services;

import com.inventario.models.Producto.dto.ProductoStockDto;

import java.util.List;

public interface IProductoService {

    List<ProductoStockDto> findAllProductos();

    void crearProductoStock(ProductoStockDto prodStockDto);

    void deleteProductoStock(Long id);
}
