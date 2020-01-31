package com.mpusinhol.orderquest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpusinhol.orderquest.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
