package com.group10.bookingtravel.service;

import com.group10.bookingtravel.entity.Orders;
import com.group10.bookingtravel.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    public List<Orders> getAllOrders(String fromDate, String toDate) throws ParseException {
        List<Orders> ordersList = new ArrayList<>();
        if(fromDate == null || fromDate.equals("null")){
            fromDate = "0/1/1";
        }
        Date date1 =new SimpleDateFormat("yyyy/MM/dd").parse(fromDate);
        if(toDate == null || toDate.equals("null")){
            toDate = "9999/1/1";
        }
        Date date2 =new SimpleDateFormat("yyyy/MM/dd").parse(toDate);
        if(ordersRepository.listAllOrders(date1,date2).isPresent()){
            return ordersRepository.listAllOrders(date1,date2).get();
        }
        return ordersList;
    }

    public Orders bookTour (Orders order){
        order.setCreatedDate(new Date());
        order.setStatus("1");
        return ordersRepository.save(order);
    }
}
