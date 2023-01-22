package com.courier.ecourier.repository;

import com.courier.ecourier.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {
    List<Users> findAllByActive(Integer active);
    Users findUsersByIdAndActive(Long id,Integer active);





}

