package com.example.bookingsystem;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingLogic {
    private List<Booking> bookings = new ArrayList<>();
    private final FileManager fileManager;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public BookingLogic(FileManager fileManager) {
        this.fileManager = fileManager;
        this.bookings = fileManager.loadBookings();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        fileManager.saveBookings(bookings);
    }
}