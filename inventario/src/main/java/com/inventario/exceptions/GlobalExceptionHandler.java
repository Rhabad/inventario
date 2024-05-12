package com.inventario.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // manejando excepciones de sql
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorDetalle> manejarSqlException(SQLException ex, WebRequest webRequest) {
        ErrorDetalle errorDetalle = ErrorDetalle.builder()
                .marcaDeTiempo(new Date())
                .mensaje(ex.getMessage())
                .detalle(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetalle, HttpStatus.BAD_REQUEST);
    }

    // manejando recurso no encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetalle> manejandoResourceNotFound(
            ResourceNotFoundException ex, WebRequest webRequest
    ) {
        ErrorDetalle errorDetalle = ErrorDetalle.builder()
                .marcaDeTiempo(new Date())
                .mensaje(ex.getMessage())
                .detalle(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetalle, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDetalle> manejandoNullPointerException(
            NullPointerException ex, WebRequest webRequest
    ) {
        ErrorDetalle errorDetalle = ErrorDetalle.builder()
                .marcaDeTiempo(new Date())
                .mensaje(ex.getMessage())
                .detalle(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(errorDetalle, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
