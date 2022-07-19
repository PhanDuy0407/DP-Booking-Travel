package com.group10.bookingtravel.repository;

import com.group10.bookingtravel.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    @Query("select distinct new com.group10.bookingtravel.entity.Orders(o.id,o.userId,o.fullname,o.email,o.phoneNumber,o.address,o.adultCount,o.childrenCount,o.kidCount,o.babyCount,o.createdDate,o.status,o.priceId,o.sumPrice) from Orders o where o.priceId = ?1")
    public Optional<List<Orders>> listOrdersByPriceId(Long id);

    @Query("select distinct new com.group10.bookingtravel.entity.Orders(o.id,o.userId,o.fullname,o.email,o.phoneNumber,o.address,o.adultCount,o.childrenCount,o.kidCount,o.babyCount,o.createdDate,o.status,o.priceId,o.sumPrice) from Orders o where ((o.createdDate >= ?1) or(?1 = '')) and ((o.createdDate <= ?2) or(?2 = ''))")
    public Optional<List<Orders>> listAllOrders(Date fromDate, Date toDate);


}
