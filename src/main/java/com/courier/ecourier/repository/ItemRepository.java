package com.courier.ecourier.repository;

import com.courier.ecourier.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findItemByActive(Integer active);
    Item findItemByIdAndActive(Long id,Integer active);

}
