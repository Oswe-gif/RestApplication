package com.example.rest.controller;

import lombok.*;
@Getter
@Setter
@Builder
public class BookingDTO {

    private String id;
    private String date;

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }

}
