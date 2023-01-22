package com.courier.ecourier.controller;

import com.courier.ecourier.dto.request.ReqUser;
import com.courier.ecourier.dto.response.RespUser;
import com.courier.ecourier.dto.response.Response;
import com.courier.ecourier.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    public Response<RespUser> login(@RequestBody ReqUser reqUser){

    return userService.login(reqUser);
    }
}
