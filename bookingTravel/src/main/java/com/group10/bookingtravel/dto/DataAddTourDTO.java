package com.group10.bookingtravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataAddTourDTO {
    private String code;
    private String name;
    private String startTime;
    private String period;
    private Long startPlaceId;
    private Long endPlaceId;
    private String mainImageUrl;
    private String shortDesc;

    private String tourPriceAdult;
    private String tourPriceChildren;
    private String tourPriceKid;
    private String tourPriceBaby;
    private String tourPriceSurcharge;

    private String landtourPriceAdult;
    private String landtourPriceChildren;
    private String landtourPriceKid;
    private String landtourPriceBaby;
    private String landtourPriceSurcharge;

    private List<TourScheduleDTO> tourScheduleDTOList;

    private String discountStartDate;
    private String discountEndDate;
    private String discount;

    private Long guideId;
}
