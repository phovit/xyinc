package br.com.xyinc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PoiNotFoundException extends RuntimeException {


    public PoiNotFoundException() {

        super();
    }


    public PoiNotFoundException(String message) {

        super(message);
    }
}
