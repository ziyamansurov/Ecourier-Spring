package com.courier.ecourier.service.impl;

import com.courier.ecourier.dto.request.ReqToken;
import com.courier.ecourier.dto.request.ReqUser;
import com.courier.ecourier.dto.response.RespStatus;
import com.courier.ecourier.dto.response.RespUser;
import com.courier.ecourier.dto.response.Response;
import com.courier.ecourier.entity.User;
import com.courier.ecourier.enums.EnumDataStatus;
import com.courier.ecourier.exception.ECourierException;
import com.courier.ecourier.exception.ExceptionConstants;
import com.courier.ecourier.repository.UserRepository;
import com.courier.ecourier.service.UserService;
import com.courier.ecourier.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Utility utility;
    @Override
    public Response<RespUser> login(ReqUser reqUser) {
        Response<RespUser> response = new Response<>();
        RespUser respUser = new RespUser();
        String username = reqUser.getUsername();
        String password = reqUser.getPassword();
        User user=null;
        try {

            if (username == null || password == null) {
                throw new ECourierException(ExceptionConstants.EMPTY_USER, "Username or password null");
            }
            user=userRepository.findUserByUsernameAndPasswordAndActive(username,password, EnumDataStatus.ACTIVE.getValue());
            if(user==null){
                throw new ECourierException(ExceptionConstants.INVALID_USER,"There is not any user by this credentials");
            }
            String token=user.getToken();
            if(token!=null){
                throw new ECourierException(ExceptionConstants.USER_ALREADY_LOGGED,"There is already an open session by this user");
            }
            token= UUID.randomUUID().toString();
            user.setToken(token);
            userRepository.save(user);
            respUser.setUserId(user.getId());
            respUser.setUsername(user.getUsername());
            respUser.setFullName(user.getFullName());
            respUser.setToken(user.getToken());
            response.setT(respUser);
            response.setStatus(RespStatus.getSuccessMessage());
        }catch (ECourierException ex) {
            if(user!=null){
                respUser.setUserId(user.getId());
                respUser.setToken(user.getToken());
                response.setT(respUser);
            }
            response.setStatus(new RespStatus(ex.getCode(),ex.getMessage()));

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @Override
    public Response logout(ReqToken reqToken) {
        Response response = new Response();
        Long userId=reqToken.getUserId();
        String token= reqToken.getToken();
        try {
            if(userId==null||token==null){
                throw new ECourierException(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid Request Data");
            }
            utility.checkToken(reqToken);
            User user=userRepository.findUserByIdAndTokenAndActive(userId,token,EnumDataStatus.ACTIVE.getValue());
            user.setToken(null);
            userRepository.save(user);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (ECourierException ex) {
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));
        } catch (Exception ex) {
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "Internal Exception"));
            ex.printStackTrace();
        }
        return response;
    }
    }

