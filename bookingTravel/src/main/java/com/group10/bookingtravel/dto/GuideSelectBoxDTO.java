package com.group10.bookingtravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class GuideSelectBoxDTO {

        private Long id;

        private String name;

        private String phone;

        private String address;

        private String info;

    public GuideSelectBoxDTO(Long id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.info = id.toString() + " - " + name + " - " + phone + " - "+ address;
    }
}
