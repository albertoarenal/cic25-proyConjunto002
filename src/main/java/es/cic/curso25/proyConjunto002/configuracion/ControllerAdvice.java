package es.cic.curso25.proyConjunto002.configuracion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.cic.curso25.proyConjunto002.controller.CreacionSecurityException;
import es.cic.curso25.proyConjunto002.controller.ModificacionSecurityException;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ModificacionSecurityException.class)
    public void controlModificacion() {
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CreacionSecurityException.class)
    public void controlCreacion() {
    }
}
