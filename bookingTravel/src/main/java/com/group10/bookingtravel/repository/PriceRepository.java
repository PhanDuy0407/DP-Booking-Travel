package com.group10.bookingtravel.repository;

import com.group10.bookingtravel.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price,Long> {
   @Query("select distinct new com.group10.bookingtravel.entity.Price(p.id,p.tourId,p.tourPriceId,p.landTourPriceId) from Price p, Tour t where t.id = p.tourId and t.id = ?1")
    public Optional<Price> getPriceById(Long id);

    @Query(value = "SELECT MAX(ID) FROM price",nativeQuery = true)
    public Long getMaxId();
}
