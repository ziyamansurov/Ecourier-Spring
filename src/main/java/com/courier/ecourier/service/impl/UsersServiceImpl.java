package com.courier.ecourier.service.impl;

import com.courier.ecourier.dto.request.ReqToken;
import com.courier.ecourier.dto.request.ReqUsers;
import com.courier.ecourier.dto.response.RespStatus;
import com.courier.ecourier.dto.response.Response;
import com.courier.ecourier.entity.Users;
import com.courier.ecourier.enums.EnumDataStatus;
import com.courier.ecourier.exception.ECourierException;
import com.courier.ecourier.exception.ExceptionConstants;
import com.courier.ecourier.repository.UsersRepository;
import com.courier.ecourier.service.UsersService;

import com.courier.ecourier.util.Utility;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

@RequiredArgsConstructor

public class UsersServiceImpl implements UsersService {
   private final UsersRepository usersRepository;
    private final Utility utility;

    @Override
    public Response<List<Users>> getUserList(ReqToken reqToken) {

        Response response=new Response();
        try{
            utility.checkToken(reqToken);
            List<Users> usersList= usersRepository.findAllByActive(EnumDataStatus.ACTIVE.getValue());
            response.setT(usersList);
            response.setStatus(RespStatus.getSuccessMessage());




        }catch (Exception e){
            e.printStackTrace();
        }




//        return userRepostirory.findAllByActive(1);
        return response;
    }

    @Override
    public Response addUser(ReqUsers reqUsers) {
        Response response=new Response();
        try {


            String name = reqUsers.getName();
            String surname = reqUsers.getSurname();
            String username=reqUsers.getUsername();
            String password=reqUsers.getPassword();
            String email=reqUsers.getEmail();
            String address=reqUsers.getUser_address();
            Date dob=reqUsers.getBirthday();
            ReqToken reqToken=reqUsers.getReqToken();
            if(name==null||
                    surname==null||
                    username==null||
                    password==null||
                    reqToken==null){
                throw new ECourierException(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid Request Data");
            }
            utility.checkToken(reqToken);
            Users user=new Users();
            user.setName(name);
            user.setSurname(surname);
            user.setUser_address(address);
            user.setEmail(email);
            user.setBirthday(dob);
            System.out.println(user.getName()+" "+user.getSurname());
            usersRepository.save(user);
            response.setStatus(RespStatus.getSuccessMessage());
        }
        catch (ECourierException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        }
        catch (Exception e){
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Internal Exception"));
            e.printStackTrace();
        }



        return response;
    }

    @Override
    public Response updateUsers(ReqUsers reqUsers) {
        Response response=new Response();
        try {

            Long id = reqUsers.getUsersId();
            String name = reqUsers.getName();
            String surname = reqUsers.getSurname();
            String username = reqUsers.getUsername();
            String password = reqUsers.getPassword();
            String email = reqUsers.getEmail();
            String address = reqUsers.getUser_address();
            Date dob = reqUsers.getBirthday();
            ReqToken reqToken = reqUsers.getReqToken();
            if(name==null||
                    surname==null||
                    username==null||
                    password==null||
                    reqToken==null){
                throw new ECourierException(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid Request Data");
            }
            utility.checkToken(reqToken);
            Users user=usersRepository.findUsersByIdAndActive(id,EnumDataStatus.ACTIVE.getValue());
            System.out.println(user.getName());
            if(user==null){
                throw new ECourierException(ExceptionConstants.USER_NOT_FOUND,"User Not Found by This id");
            }
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setUser_address(address);
            user.setBirthday(dob);
            usersRepository.save(user);
            response.setStatus(RespStatus.getSuccessMessage());

        }
        catch (ECourierException ex){
            response.setStatus(new RespStatus(ex.getCode(),ex.getMessage()));
        }
        catch (Exception ex){
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Invalid Request Data"));

        }


        return response;
    }

    @Override
    public Response findUserById(Long id) {
        Response response=new Response();
        try{
            Long userId=id;
            if(userId==null){
                throw new ECourierException(ExceptionConstants.USER_NOT_FOUND,"User Not Found");

            }
            Users user=usersRepository.findUsersByIdAndActive(id,EnumDataStatus.ACTIVE.getValue());
            response.setT(user);
            response.setStatus(RespStatus.getSuccessMessage());

        }
        catch (ECourierException ex){
            response.setStatus(new RespStatus(ex.getCode(),ex.getMessage()));
        }
        catch (Exception ex){
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION,"Invalid Request Data"));

        }
        return response;
    }
//    @Override
//    public Response<List<RespUser>> getUserList() {
//
//        return userRepostirory.findAllByActive(1);
//    }
}
