package com.studing.infra;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class HttpMessageNotReadableException  extends RuntimeException {

    public HttpMessageNotReadableException(String message){
        super(message);
    }
}
