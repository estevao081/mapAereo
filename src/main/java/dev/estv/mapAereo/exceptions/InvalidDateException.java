package dev.estv.mapAereo.exceptions;

public class InvalidDateException extends RuntimeException {

    public InvalidDateException() { super("Data inválida."); }

    public InvalidDateException(String message) { super(message); }
}
