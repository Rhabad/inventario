package com.inventario.models.Producto.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoStockDto {
    private Long id;
    private String nombreProducto;
    private int precio;
    private int precioOferta;
    //stock
    private Long idStock;
    private int stock;
    private int stockMinimo;
    private int stockMaximo;
}
