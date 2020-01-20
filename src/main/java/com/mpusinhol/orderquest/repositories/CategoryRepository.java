package com.mpusinhol.orderquest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpusinhol.orderquest.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
