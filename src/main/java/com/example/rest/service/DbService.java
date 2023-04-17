package com.example.rest.service;

import com.example.rest.controller.dto.BookingDTO;
import com.example.rest.controller.dto.ClientDTO;
import com.example.rest.repository.DbHandler;

import java.util.ArrayList;
import java.util.List;

public class DbService {
    private DbHandler handler;
    public DbService(DbHandler handler){
        this.handler = handler;
    }
    public String addClient(ClientDTO client){
        handler.insertClient(client);
        return "cliente añadido";
    }
    public String addBooking(BookingDTO booking){
        if (handler.obtainBookingAmount(booking)<20){
            handler.insertBooking(booking);
            return "reserva añadida";
        } else {
            return "guarderia llena, intente mañana";
        }

    }
    public ArrayList<String> getPetsByDate(String date){
        return handler.findPetsByDate(date);
    }
    public List<BookingDTO> getClientBookingHistory(String id){
        return handler.ObtainClientBookingHistory(id);
    }
    //pruebaaquibjdwnjedjndejn
}