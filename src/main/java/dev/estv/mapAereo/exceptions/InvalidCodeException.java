package dev.estv.mapAereo.exceptions;

public class InvalidCodeException extends RuntimeException{

    public InvalidCodeException() { super("O código do produto deve conter 6 dígitos numéricos."); }

    public InvalidCodeException(String message) { super(message); }
}