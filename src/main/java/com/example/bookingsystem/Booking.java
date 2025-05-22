package com.example.bookingsystem;

import java.util.UUID;

/**
 * Represents a booking with a unique ID, name, date, and details.
 */
public class Booking {
    private String id;
    private String name;
    private String date;
    private String details;

    /**
     * Constructs a new Booking with the specified name, date, and details.
     * Generates a unique ID automatically.
     *
     * @param name    the name of the person or booking
     * @param date    the date of the booking (format: dd.MM.yyyy)
     * @param details additional information about the booking
     */
    public Booking(String name, String date, String details) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.date = date;
        this.details = details;
    }

    /**
     * Returns the unique ID of the booking.
     *
     * @return the booking ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name associated with the booking.
     *
     * @return the booking name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the date of the booking.
     *
     * @return the booking date
     */
    public String getDate() {
        return date;
    }

    /**
     * Returns additional details of the booking.
     *
     * @return the booking details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the name for the booking.
     *
     * @param name the new name for the booking
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the date for the booking.
     *
     * @param date the new date (format: dd.MM.yyyy)
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Sets the details for the booking.
     *
     * @param details the new details
     */
    public void setDetails(String details) {
        this.details = details;
    }
}