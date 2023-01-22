package com.courier.ecourier.repository;

import com.courier.ecourier.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsernameAndPasswordAndActive(String username,String password,Integer active);

    User findUserByIdAndTokenAndActive(Long id,String token,Integer active);

}
