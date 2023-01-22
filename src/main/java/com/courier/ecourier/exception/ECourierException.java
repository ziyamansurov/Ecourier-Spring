package com.courier.ecourier.exception;

public class ECourierException extends RuntimeException{
    private Integer code;

public ECourierException(Integer code, String message){
    super(message);
    this.code=code;

}

public Integer getCode(){
    return code;
}
}
