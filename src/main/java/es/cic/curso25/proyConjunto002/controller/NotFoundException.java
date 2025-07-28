package es.cic.curso25.proyConjunto002.controller;

public class NotFoundException extends RuntimeException {

    public NotFoundException() { 
        super("Registro no encontrado");
    }

    public NotFoundException(String mensaje){
        super(mensaje);
    }

    public NotFoundException(String mensaje, Throwable throwable){
        super(mensaje, throwable);
    }

}
