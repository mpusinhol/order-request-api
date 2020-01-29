package com.mpusinhol.orderquest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpusinhol.orderquest.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
