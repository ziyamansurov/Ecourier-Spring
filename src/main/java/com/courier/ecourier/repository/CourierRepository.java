package com.courier.ecourier.repository;

import com.courier.ecourier.entity.Courier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierRepository extends JpaRepository<Courier,Long> {

    List<Courier> findAllByActive(Integer active);


    Courier findCourierByIdAndActive(Long id,Integer active);
  //  Courier findCourierByIdAndActive(Long id);

}
