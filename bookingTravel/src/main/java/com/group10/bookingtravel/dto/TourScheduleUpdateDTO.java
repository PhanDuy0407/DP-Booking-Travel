package com.group10.bookingtravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourScheduleUpdateDTO {

    private Integer id;

    private String alias;

    private String time;

    private String location;

    private Integer tourId;

    private String detail;
}
