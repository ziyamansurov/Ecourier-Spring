package com.courier.ecourier.service;

import com.courier.ecourier.dto.request.ReqToken;
import com.courier.ecourier.dto.request.ReqUsers;
import com.courier.ecourier.dto.response.Response;
import com.courier.ecourier.entity.Users;

import java.util.List;

public interface UsersService {
    Response<List<Users>> getUserList(ReqToken reqToken);

    Response addUser(ReqUsers reqUsers);

    Response updateUsers(ReqUsers reqUsers);

    Response findUserById(Long id);
}
