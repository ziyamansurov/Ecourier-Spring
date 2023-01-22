package com.courier.ecourier.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
public class Response<T> {

    @JsonProperty("response")
    private T t;
    private RespStatus status;
}
