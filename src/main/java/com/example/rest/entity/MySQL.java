package com.example.rest.entity;

import com.example.rest.repository.DbHandler;
import com.example.rest.controller.dto.BookingDTO;
import com.example.rest.controller.dto.ClientDTO;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;

public class MySQL implements DbHandler {

    private String url = "jdbc:db2://b0aebb68-94fa-46ec-a1fc-1c999edb6187.c3n41cmd0nqnrk39u98g.databases.appdomain.cloud:31249/bludb:user=nvj39390;password=l8VuWk0Fn0HUBc1Z;sslConnection=true;";
    Connection con;
    Statement stmt;
    ResultSet rs;

    @Override
    public void insertClient(ClientDTO client) {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO customer VALUES (" + client.getId() + "," + client.getName()+ "," + client.getAddress() + "," + client.getPetName() + ");");
            stmt.close();
            con.commit();
            con.close();
        } catch (SQLException e) {
            System.out.println("El ID ingresado esta repetido");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertBooking(BookingDTO booking) {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO booking (Customer_document, Booking_date) VALUES (" + booking.getId() + "," + booking.getDate() + ");");
            stmt.close();
            con.commit();
            con.close();
        } catch (SQLException e) {
            System.out.println("ID cliente inexistente");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<String> findPetsByDate(String date) {

        ArrayList<String> pets = new ArrayList<String>();
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT c.Pet_name FROM customer c, booking b WHERE c.Customer_document = b.Customer_document and b.Booking_date = " + date + ";");
            while (rs.next()) {
                pets.add(rs.getString("Pet_name"));
            }
            rs.close();
            stmt.close();
            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return pets;

    }

    @Override
    public List<BookingDTO> ObtainClientBookingHistory(String id) {
        List<BookingDTO> bookings = new ArrayList<>();
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT Customer_document, Booking_date FROM booking where Customer_document=" + id + ";");
            while (rs.next()) {
                bookings.add(BookingDTO.builder().id(rs.getString("Customer_document")).date(rs.getString("Booking_date")).build());
            }
            rs.close();
            stmt.close();
            con.commit();
            con.close();
        } catch (SQLException e) {
            System.out.println("ID cliente inexistente");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return bookings;
    }

    @Override
    public int obtainBookingAmount(BookingDTO booking) {
        int amount = 0;
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            con = DriverManager.getConnection(url);
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT count(Customer_document) as number FROM booking WHERE Booking_date = " + booking.getDate() + ";");
            while (rs.next()) {
                amount = rs.getInt("number");
            }
            rs.close();
            stmt.close();
            con.commit();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return amount;
    }
}
