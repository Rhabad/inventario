package com.inventario.services;

import com.inventario.models.Producto.dao.ProductoStockDao;
import com.inventario.models.Producto.dto.ProductoStockDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductoService {

    ProductoStockDao prodStockDao;

    public ProductoService(ProductoStockDao prodStockDao) {
        this.prodStockDao = prodStockDao;
    }

    public List<ProductoStockDto> findAllProductos() {
        List<Map<String, Object>> productos = prodStockDao.findAllProductoStock();

        List<ProductoStockDto> listProdStock = new ArrayList<>();

        for (Map<String, Object> prod : productos) {
            listProdStock.add(ProductoStockDto.builder()
                    .id((Long) prod.get("id"))
                    .nombreProducto((String) prod.get("nombre_producto"))
                    .precio((int) prod.get("precio"))
                    .precioOferta((int) prod.get("precio_oferta"))
                    .stock((int) prod.get("stock"))
                    .stockMinimo((int) prod.get("stock_minimo"))
                    .stockMaximo((int) prod.get("stock_maximo"))
                    .build());
        }

        return listProdStock;
    }
}
