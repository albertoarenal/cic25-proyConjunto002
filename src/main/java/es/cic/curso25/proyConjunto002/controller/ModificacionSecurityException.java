package es.cic.curso25.proyConjunto002.controller;

public class ModificacionSecurityException extends RuntimeException {

    public ModificacionSecurityException() {
        super("No se puede actualizar en el método GET");
    }

    public ModificacionSecurityException(String mensaje) {
        super(mensaje);
    }

    public ModificacionSecurityException(String mensaje, Throwable throwable) {
        super(mensaje, throwable);
    }

}
