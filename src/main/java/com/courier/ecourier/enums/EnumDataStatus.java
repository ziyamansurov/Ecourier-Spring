package com.courier.ecourier.enums;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EnumDataStatus {
    ACTIVE(1),INACTIVE(0);

    private int value;

    public Integer getValue(){return value;}





}
