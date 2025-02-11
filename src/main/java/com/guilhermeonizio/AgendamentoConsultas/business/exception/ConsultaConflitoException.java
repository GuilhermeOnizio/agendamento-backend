package com.guilhermeonizio.AgendamentoConsultas.business.exception;

public class ConsultaConflitoException extends RuntimeException {
    public ConsultaConflitoException(String message) {
        super(message);
    }
}
