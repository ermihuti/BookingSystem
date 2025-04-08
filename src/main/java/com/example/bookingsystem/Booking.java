package com.example.bookingsystem;

import java.util.UUID;

public class Booking {
    private String id;
    private String name;
    private String date;
    private String details;

    public Booking(String name, String date, String details) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.date = date;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}