package br.com.deliverit.Util;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Component("ApiErrorGrp")
public class ApiError extends IllegalArgumentException {

    public ApiError() {
        super();
    }

    public ApiError(String s) {
        super(s);
    }

    public ApiError(String message, Throwable cause) {
        super(message, cause);
    }
}


