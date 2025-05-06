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

    public void removeBooking(String id) {
        bookings.removeIf(b -> b.getId().equals(id));
        fileManager.saveBookings(bookings);
    }

    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    public void sortByDate() {
        bookings.sort((b1, b2) -> {
            LocalDate date1 = LocalDate.parse(b1.getDate(), FORMATTER);
            LocalDate date2 = LocalDate.parse(b2.getDate(), FORMATTER);
            return date1.compareTo(date2);
        });
        fileManager.saveBookings(bookings);
    }

    public void saveBookings(List<Booking> bookings) {
        fileManager.saveBookings(bookings);
    }

    public List<Booking> searchBooking(String name) {
        List<Booking> foundBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getName().equalsIgnoreCase(name)) {
                foundBookings.add(booking);
            }
        }
        return foundBookings;
    }
}