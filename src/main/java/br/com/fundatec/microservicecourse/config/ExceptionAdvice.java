package br.com.fundatec.microservicecourse.config;

import br.com.fundatec.microservicecourse.controller.response.ApiError;
import br.com.fundatec.microservicecourse.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiError onException(NotFoundException exception) {
        return new ApiError("NOT_FOUND", extractMessage(exception));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiError onException(Exception exception) {
        return new ApiError("INTERNAL_SERVER_ERROR", extractMessage(exception));
    }

    private String extractMessage(Throwable exception) {
        if (exception.getCause() == null && exception.getMessage() != null) {
            return exception.getMessage();
        } else if (exception.getCause() == null && exception.getMessage() == null) {
            return "";
        } else {
            return exception.getCause().getLocalizedMessage();
        }
    }
}
