package com.inventario.services;

import com.inventario.models.Producto.dao.ProductoDao;
import com.inventario.models.Producto.dao.ProductoStockDao;
import com.inventario.models.Producto.dto.ProductoDto;
import com.inventario.models.Producto.dto.ProductoStockDto;
import com.inventario.models.Stock.dao.StockDao;
import com.inventario.models.Stock.dto.StockDto;
import com.inventario.models.inventario.dao.InventarioProductoDao;
import com.inventario.models.inventario.dto.InventarioProductoDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductoService {

    private final ProductoStockDao prodStockDao;
    private final ProductoDao productoDao;
    private final StockDao stockDao;
    private final InventarioProductoDao invProdDao;

    public ProductoService(
            ProductoStockDao prodStockDao,
            ProductoDao productoDao,
            StockDao stockDao,
            InventarioProductoDao invProdDao
    ) {
        this.prodStockDao = prodStockDao;
        this.productoDao = productoDao;
        this.stockDao = stockDao;
        this.invProdDao = invProdDao;
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


    @Transactional
    public void crearProductoStock(ProductoStockDto prodStockDto) {
        productoDao.createProducto(ProductoDto.builder()
                .nombreProducto(prodStockDto.getNombreProducto())
                .precio(prodStockDto.getPrecio())
                .precioOferta(prodStockDto.getPrecioOferta())
                .build());

        stockDao.createStock(StockDto.builder()
                        .stock(prodStockDto.getStock())
                        .stockMinimo(prodStockDto.getStockMinimo())
                        .stockMaximo(prodStockDto.getStockMaximo())
                        .build(),
                productoDao.lastProducto().getId());

        invProdDao.asignarProductoAInventario(InventarioProductoDto.builder()
                .inventarioId(prodStockDto.getIdInventario())
                .productoId(productoDao.lastProducto().getId())
                .build());
    }
}
