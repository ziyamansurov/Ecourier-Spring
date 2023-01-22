package com.courier.ecourier.util;

import com.courier.ecourier.dto.request.ReqToken;
import com.courier.ecourier.entity.User;
import com.courier.ecourier.enums.EnumDataStatus;
import com.courier.ecourier.exception.ECourierException;
import com.courier.ecourier.exception.ExceptionConstants;
import com.courier.ecourier.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Utility {
    private final UserRepository userRepository;
    public  void checkToken(ReqToken reqToken){
        Long userId = reqToken.getUserId();
        String token = reqToken.getToken();

        if(userId==null || token==null){
            throw new ECourierException(ExceptionConstants.INVALID_REQUEST_DATA,"User id or token is empty");
        }
        User user = userRepository.findUserByIdAndTokenAndActive(userId,token, EnumDataStatus.ACTIVE.getValue());
        if(user==null){
            throw  new ECourierException(ExceptionConstants.INVALID_TOKEN,"Token is invalid");
        }
    }


}
