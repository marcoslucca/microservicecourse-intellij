package br.com.fundatec.microservicecourse.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
    }
}
