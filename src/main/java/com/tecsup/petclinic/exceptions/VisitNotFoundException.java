package com.tecsup.petclinic.exceptions;

/**
 * Excepción lanzada cuando una visita no es encontrada.
 */
public class VisitNotFoundException extends Exception {

    public VisitNotFoundException(String message) {
        super(message);
    }
}
