package com.group10.bookingtravel.repository;


import com.group10.bookingtravel.dto.TourDTO_User;
import com.group10.bookingtravel.dto.TourInfoDTO;
import com.group10.bookingtravel.entity.Place;
import com.group10.bookingtravel.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour,Long> {

    @Query("select distinct new com.group10.bookingtravel.dto.TourInfoDTO(t.id,t.code,t.name,t.shortDesc,t.startTime,t.period,t.startPlaceId,p1.name,t.endPlaceId,p2.name,t.mainImageUrl,t.guideId,t.placeOrderMax,t.status) from Tour t, Place p1, Place p2 where p1.id = t.startPlaceId and p2.id = t.endPlaceId and ((lower(t.code) like lower(concat('%',?1,'%')))or(?1 = '')) and ((lower(t.name) like lower(concat('%',?2,'%')))or(?2 = '' ))")
    List<TourInfoDTO> tourList(String codeTour,String nameTour);

    @Query(value = "SELECT MAX(ID) FROM tour",nativeQuery = true)
    public Integer getMaxId();

    // get tour list for frontend user
    @Query("select distinct new com.group10.bookingtravel.dto.TourInfoDTO" +
            "(t.id,t.code,t.name,t.shortDesc,t.startTime,t.period,t.startPlaceId,p1.name,t.endPlaceId,p2.name,t.mainImageUrl,t.guideId,t.placeOrderMax,t.status,tp.adult) " +
                    "from Tour t, Place p1, Place p2, Price p, TourPrice tp " +
                    "where p1.id = t.startPlaceId and p2.id = t.endPlaceId and t.id = p.tourId and p.tourPriceId = tp.id")
    List<TourInfoDTO> getTourList();
    @Query("select distinct new com.group10.bookingtravel.dto.TourInfoDTO" +
            "(t.id,t.code,t.name,t.shortDesc,t.startTime,t.period,t.startPlaceId,p1.name,t.endPlaceId,p2.name,t.mainImageUrl,t.guideId,t.placeOrderMax,t.status,tp.adult) " +
            "from Tour t, Place p1, Place p2, Price p, TourPrice tp " +
            "where p1.id = t.startPlaceId and p2.id = t.endPlaceId and t.id = p.tourId and p.tourPriceId = tp.id " +
            "and (t.startPlaceId = ?1 or ?1 is null) and (t.endPlaceId = ?2 or ?2 is null) " +
            "and (t.startTime = ?3 or ?3 is null) " +
            "and (t.period >= ?4 or ?4 is null) and (t.period <= ?5 or ?5 is null) " +
            "and (tp.adult >= ?6 or ?6 is null) and (tp.adult <=?7 or ?7 is null) ")
    List<TourInfoDTO> getTourListFilter(Long startPlaceId, Long endPlaceId, Date startTime, Integer fromPeriod, Integer toPeriod, Integer fromPrice, Integer toPrice);
    @Query("select distinct new com.group10.bookingtravel.dto.TourDTO_User" +
            "(t.id,t.code,t.name,t.shortDesc,t.startTime,t.period,t.startPlaceId,p1.name,t.endPlaceId,p2.name,t.mainImageUrl,t.guideId,g.name,t.placeOrderMax,t.status,p.id) " +
            "from Place p1, Place p2, Tour t, Guide g, Price p " +
            "where p1.id = t.startPlaceId and p2.id = t.endPlaceId and t.guideId = g.id and t.id = p.tourId " +
            "and t.id = ?1")
    TourDTO_User getTourById(Long id);
    @Query("select distinct new com.group10.bookingtravel.dto.TourDTO_User" +
            "(t.id,t.code,t.name,t.shortDesc,t.startTime,t.period,t.startPlaceId,p1.name,t.endPlaceId,p2.name,t.mainImageUrl,t.placeOrderMax,tp.adult,d.discount,d.startDate,d.endDate) " +
            "from Tour t, Place p1, Place p2, Price p, TourPrice tp, Discount d " +
            "where p1.id = t.startPlaceId and p2.id = t.endPlaceId and t.id = p.tourId and p.tourPriceId = tp.id and p.id = d.priceId")
    List<TourDTO_User> getToursDiscount();

}
