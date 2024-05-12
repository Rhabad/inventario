package com.inventario.models.Stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StockDto {
    private Long idStock;
    private int stock;
    private int stockMinimo;
    private int stockMaximo;
}
