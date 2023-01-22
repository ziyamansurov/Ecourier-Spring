package com.courier.ecourier.dto.request;

import com.courier.ecourier.entity.Item_options;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqItem {
    private Long id;
    private String name;
    private String category;

    private Item_options itemOptions;
}
