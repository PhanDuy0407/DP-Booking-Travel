package com.group10.bookingtravel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "adult_count")
    private Integer adultCount;

    @Column(name = "children_count")
    private Integer childrenCount;

    @Column(name = "kid_count")
    private Integer kidCount;

    @Column(name = "baby_count")
    private Integer babyCount;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "status")
    private String status;

    @Column(name = "price_id")
    private Long priceId;

    @Column(name = "sum_price")
    private Integer sumPrice;
}
