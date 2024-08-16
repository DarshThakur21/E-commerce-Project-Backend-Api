package com.Spring.Learning.e_comproj.repo;

import com.Spring.Learning.e_comproj.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Products ,Integer> {

    @Query("    SELECT p from Products  p where "+
            "LOWER(p.name) LIKE LOWER(CONCAT('%',:keyword,'%'))"

    )
    List<Products> searchkey(String keyword);
}
