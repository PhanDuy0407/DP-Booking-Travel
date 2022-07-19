package com.group10.bookingtravel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "price")
@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tour_id")
    private Long tourId;

    @Column(name = "tour_price_id")
    private Long tourPriceId;

    @Column(name = "land_tour_price_id")
    private Long landTourPriceId;
}
