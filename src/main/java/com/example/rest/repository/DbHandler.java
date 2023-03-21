package com.example.rest.repository;

import com.example.rest.controller.BookingDTO;
import com.example.rest.controller.ClientDTO;

import java.util.ArrayList;
import java.util.List;

public interface DbHandler {
    void insertClient(ClientDTO client);
    void insertBooking(BookingDTO booking);
    ArrayList<String> findPetsByDate(String date);
    List<BookingDTO> ObtainClientBookingHistory(String id);
    int obtainBookingAmount(BookingDTO booking);
}
