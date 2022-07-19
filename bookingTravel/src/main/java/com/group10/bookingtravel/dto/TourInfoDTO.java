package com.group10.bookingtravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourInfoDTO {
    private Long id;
    private String code;
    private String name;
    private String shortDesc;
    private Date startTime;
    private Integer period;
    private Long startPlaceId;
    private String startPlaceName;
    private Long endPlaceId;
    private String endPlaceName;
    private String mainImageUrl;
    private Long guideId;
    private Integer placeOrderMax;
    private Integer status;
    private Integer price;

    public TourInfoDTO(Long id, String code, String name, String shortDesc, Date startTime, Integer period, Long startPlaceId, String startPlaceName, Long endPlaceId, String endPlaceName, String mainImageUrl, Long guideId, Integer placeOrderMax, Integer status) throws ParseException {
        this.id = id;
        this.code = code;
        this.name = name;
        this.shortDesc = shortDesc;
        this.startTime = startTime;
        this.period = period;
        this.startPlaceId = startPlaceId;
        this.startPlaceName = startPlaceName;
        this.endPlaceId = endPlaceId;
        this.endPlaceName = endPlaceName;
        this.mainImageUrl = mainImageUrl;
        this.guideId = guideId;
        this.placeOrderMax = placeOrderMax;
        this.status = status;

    }
}
