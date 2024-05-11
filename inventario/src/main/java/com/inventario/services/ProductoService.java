package com.inventario.services;

import com.inventario.models.Producto.dao.ProductoStockDao;
import com.inventario.models.Producto.dto.ProductoStockDto;
import org.springframework.stereotype.Service;

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

        List<ProductoStockDto> listProdStock;

        for (Map<String, Object> prod : productos) {

        }

        return null;
    }
}
