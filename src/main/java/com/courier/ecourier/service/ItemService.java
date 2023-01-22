package com.courier.ecourier.service;

import com.courier.ecourier.dto.request.ReqItem;
import com.courier.ecourier.dto.response.RespItem;
import com.courier.ecourier.dto.response.Response;

import java.util.List;

public interface ItemService {

    Response<List<RespItem>> getItemList();

    Response addItem(ReqItem item);

    Response findItemById(Long id);
    Response deleteItemById(Long id);
}
