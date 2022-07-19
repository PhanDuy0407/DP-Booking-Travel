package com.group10.bookingtravel.repository;

import com.group10.bookingtravel.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends JpaRepository<Discount,Long>{

    public Optional<Discount> findByPriceId(Long id);
}
