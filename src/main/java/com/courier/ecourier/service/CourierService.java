package com.courier.ecourier.service;

import com.courier.ecourier.dto.request.ReqCourier;
import com.courier.ecourier.dto.response.RespCourier;
import com.courier.ecourier.dto.response.RespStatus;
import com.courier.ecourier.dto.response.Response;

import java.util.List;

public interface CourierService {
    Response<List<RespCourier>> getCourierList();


    Response<RespCourier> getCourierById(Long courierid);

    Response addCourier(ReqCourier reqCourier);

    Response updateCourier(ReqCourier reqCourier);
}
