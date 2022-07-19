package com.group10.bookingtravel.repository;

import com.group10.bookingtravel.entity.LandTourPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LandTourPriceRepository extends JpaRepository<LandTourPrice,Long> {

    @Query("select distinct new com.group10.bookingtravel.entity.LandTourPrice(l.id,l.adult,l.children,l.kid,l.baby,l.surcharge) from LandTourPrice l where l.id = ?1")
    public Optional<LandTourPrice> getLandTourPriceById(Long id);

    @Query(value = "SELECT MAX(ID) FROM land_tour_price",nativeQuery = true)
    public Integer getMaxId();

    // for frontend user
    @Query("select distinct new com.group10.bookingtravel.entity.LandTourPrice" +
            "(l.id,l.adult,l.children,l.kid,l.baby,l.surcharge) " +
            "from LandTourPrice l, Price p " +
            "where l.id = p.landTourPriceId and p.tourId = ?1")
    public Optional<LandTourPrice> getLandTourPriceByTourId(Long tourId);
}
