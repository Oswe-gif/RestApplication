package com.example.rest.controller;

import com.example.rest.data.MySQL;
import com.example.rest.repository.DbService;
import org.springframework.web.bind.annotation.*;

@RestController
public class DogDaycareController {

    private DbService service = new DbService(new MySQL());

    public DogDaycareController() {

    }

    @PostMapping("/clients")
    public String registerClient(@RequestParam String id, @RequestParam String name, @RequestParam String address, @RequestParam String petName) {
        ClientDTO client = ClientDTO.builder().id(id).name(name).address(address).petName(petName).build();
        return service.addClient(client);
    }

    @PostMapping("/bookings")
    public String registerBooking(@RequestParam String id, @RequestParam String date) {
        BookingDTO booking = BookingDTO.builder().id(id).date(date).build();
        return service.addBooking(booking);
    }

    @GetMapping("/pets")
    public String getPetsDayAgenda(@RequestParam String date) {
        return service.getPetsByDate(date).toString();
    }

    @GetMapping("/bookings")
    public String getClientBookingHistory(@RequestParam String id) {
        return service.getClientBookingHistory(id).toString();
    }

}