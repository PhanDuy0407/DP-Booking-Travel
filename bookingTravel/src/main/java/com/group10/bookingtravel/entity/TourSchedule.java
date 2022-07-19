package com.group10.bookingtravel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tour_schedule")
@Entity
public class TourSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tour_id")
    private Long tourId;

    @Column(name = "alias")
    private String alias;

    @Column(name = "time")
    private Date time;

    @Column(name = "location")
    private String location;

    @Column(name = "detail")
    private String detail;

}
