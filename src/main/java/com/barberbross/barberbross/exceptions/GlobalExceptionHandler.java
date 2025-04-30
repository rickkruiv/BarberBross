package com.barberbross.barberbross.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.barberbross.barberbross.dto.ErroResposta;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler( RuntimeException.class ) 
    public ResponseEntity<ErroResposta> tratarRuntime( RuntimeException ex, HttpServletRequest request ) {

        ErroResposta erro = new ErroResposta(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            request.getRequestURI()
        );

        return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( erro );
    }
}
