package com.courier.ecourier.entity;


import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import reactor.util.annotation.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "courier")
@DynamicInsert
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String address;

    private byte[] image;

    private Date birthday;

    private int rating;

    private String phonenumb;

    private String email;

    @ColumnDefault("1")
    private Integer active;
    @CreationTimestamp
    private Date date_date;


}
