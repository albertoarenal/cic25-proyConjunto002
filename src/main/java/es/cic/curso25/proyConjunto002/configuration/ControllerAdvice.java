package es.cic.curso25.proyConjunto002.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import es.cic.curso25.proyConjunto002.controller.CreacionSecurityException;
import es.cic.curso25.proyConjunto002.controller.ModificacionSecurityException;
import es.cic.curso25.proyConjunto002.controller.NotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    //Creación - Status code 400
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CreacionSecurityException.class)
    public void controlCreacion(){

    }

    //Lectura - Status code 404
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void controlLectura(){

    }

    //Modificación - Status code 400
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ModificacionSecurityException.class)
    public void controlModificacion(){

    }


}
