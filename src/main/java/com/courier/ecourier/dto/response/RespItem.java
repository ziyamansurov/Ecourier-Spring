package com.courier.ecourier.dto.response;

import com.courier.ecourier.entity.Item_options;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
@Data
@Builder
public class RespItem {

    private Long id;
    private String name;
    private String category;

    private Item_options itemOptions;

}
