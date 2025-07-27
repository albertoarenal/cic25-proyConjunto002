package es.cic.curso25.proyConjunto002.controller;

public class CreacionSecurityException extends RuntimeException{

    public CreacionSecurityException(){
        super("Se ha intentado crear en vez de actualizar en el endpoint PUT");
    }

     public CreacionSecurityException(String menaje){
        super(menaje);
    }

     public CreacionSecurityException(String mensaje, Throwable throwable){
        super(mensaje, throwable);
    
    }


}
