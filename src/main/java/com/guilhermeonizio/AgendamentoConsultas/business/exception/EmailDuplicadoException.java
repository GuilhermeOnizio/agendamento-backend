package com.guilhermeonizio.AgendamentoConsultas.business.exception;

public class EmailDuplicadoException extends RuntimeException {
    public EmailDuplicadoException(String message) {
        super(message);
    }
}