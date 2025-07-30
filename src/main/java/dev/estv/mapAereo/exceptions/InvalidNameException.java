package dev.estv.mapAereo.exceptions;

public class InvalidNameException extends RuntimeException {

    public InvalidNameException() { super("O nome do produto deve conter apenas letras."); }

    public InvalidNameException(String message) { super(message); }
}
