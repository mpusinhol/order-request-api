package com.mpusinhol.orderquest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpusinhol.orderquest.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
