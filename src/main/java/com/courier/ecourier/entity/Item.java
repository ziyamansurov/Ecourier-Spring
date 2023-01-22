package com.courier.ecourier.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@DynamicInsert
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    @OneToOne(cascade = CascadeType.REMOVE)
    private Item_options itemOptions;
    @ColumnDefault("1")
    private Integer active;

    @CreationTimestamp
    private Date date_date;



    


}
