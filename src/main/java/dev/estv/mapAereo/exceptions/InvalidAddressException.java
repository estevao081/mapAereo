package dev.estv.mapAereo.exceptions;

public class InvalidAddressException extends RuntimeException{

    public InvalidAddressException() { super("Informe um endereço válido."); }

    public InvalidAddressException(String message) { super(message); }
}
