package com.inventario.models.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventarioProductoDto {
    private Long id;
    private Long inventarioId;
    private Long productoId;
}
