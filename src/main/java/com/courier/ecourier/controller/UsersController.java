package com.courier.ecourier.controller;


import com.courier.ecourier.dto.request.ReqToken;
import com.courier.ecourier.dto.request.ReqUser;
import com.courier.ecourier.dto.request.ReqUsers;
import com.courier.ecourier.dto.response.RespUser;
import com.courier.ecourier.dto.response.Response;
import com.courier.ecourier.entity.Users;
import com.courier.ecourier.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;

//    @GetMapping("/GetUserList")
//    public String getUserList(){
//
//        return "salam";
//    }


    @RequestMapping(value = "/GetUserList",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<Users>> getUserList(@RequestBody ReqToken reqToken){

        return usersService.getUserList(reqToken);
    }
    @RequestMapping(value = "/AddUser",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addUser(@RequestBody ReqUsers reqUsers){


   return usersService.addUser(reqUsers);
    }
    @PutMapping("/UpdateUser")
    public Response updateUser(@RequestBody ReqUsers reqUsers){

        return usersService.updateUsers(reqUsers);
    }

    @GetMapping("/FindById{id}")
    public Response findUserById(@RequestParam Long id){

        return usersService.findUserById(id);
    }



}
