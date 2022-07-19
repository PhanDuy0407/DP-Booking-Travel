package com.group10.bookingtravel.controller;

import com.group10.bookingtravel.entity.Orders;
import com.group10.bookingtravel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class OrdersController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders-all")
    public List<Orders> getAllOrders(@RequestParam(name = "fromDate" , required = false) String fromDate,
                                     @RequestParam(name = "toDate", required = false) String toDate) throws ParseException {
        return orderService.getAllOrders(fromDate,toDate);
    }

    @PostMapping("/order")
    public ResponseEntity<Orders> bookTour(@RequestBody Orders order){
        return new ResponseEntity<>(orderService.bookTour(order), HttpStatus.CREATED);
    }
}
