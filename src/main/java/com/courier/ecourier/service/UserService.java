package com.courier.ecourier.service;

import com.courier.ecourier.dto.request.ReqToken;
import com.courier.ecourier.dto.request.ReqUser;
import com.courier.ecourier.dto.response.RespUser;
import com.courier.ecourier.dto.response.Response;

public interface UserService {
    Response<RespUser> login(ReqUser reqUser);
    Response logout(ReqToken reqToken);
}
