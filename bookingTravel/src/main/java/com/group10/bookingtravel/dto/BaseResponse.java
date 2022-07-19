package com.group10.bookingtravel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse {
    public boolean success;
    public String message;
    public Object data;

    public BaseResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
