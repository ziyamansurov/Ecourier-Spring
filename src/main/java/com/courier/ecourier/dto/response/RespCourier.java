package com.courier.ecourier.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Builder
public class RespCourier {

    private Long id;
    private String name;
    private String surname;
    private String address;

    private byte[] image;

    private String birthday;

    private int rating;

    private String phonenumb;

    private String email;




}
