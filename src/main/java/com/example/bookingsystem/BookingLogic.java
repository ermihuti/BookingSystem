package com.example.bookingsystem;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles the main logic for managing bookings, including adding, removing,
 * searching, and sorting. Uses a FileManager to persist data.
 */
public class BookingLogic {
    private List<Booking> bookings = new ArrayList<>();
    private final FileManager fileManager;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Constructs a BookingLogic instance with the provided FileManager.
     * Loads existing bookings from file.
     *
     * @param fileManager the FileManager responsible for loading and saving bookings
     */
    public BookingLogic(FileManager fileManager) {
        this.fileManager = fileManager;
        this.bookings = fileManager.loadBookings();
    }

    /**
     * Adds a new booking and saves the updated list to file.
     *
     * @param booking the booking to add
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
        fileManager.saveBookings(bookings);
    }

    /**
     * Removes a booking by its ID and saves the updated list to file.
     *
     * @param id the ID of the booking to remove
     */
    public void removeBooking(String id) {
        bookings.removeIf(b -> b.getId().equals(id));
        fileManager.saveBookings(bookings);
    }

    /**
     * Returns a copy of all current bookings.
     *
     * @return list of all bookings
     */
    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    /**
     * Sorts the bookings by date in ascending order and saves the result.
     */
    public void sortByDate() {
        bookings.sort((b1, b2) -> {
            LocalDate date1 = LocalDate.parse(b1.getDate(), FORMATTER);
            LocalDate date2 = LocalDate.parse(b2.getDate(), FORMATTER);
            return date1.compareTo(date2);
        });
        fileManager.saveBookings(bookings);
    }

    /**
     * Saves a given list of bookings to the file.
     *
     * @param bookings the list of bookings to save
     */
    public void saveBookings(List<Booking> bookings) {
        fileManager.saveBookings(bookings);
    }

    /**
     * Searches for bookings by name (case-insensitive).
     *
     * @param name the name to search for
     * @return list of bookings that match the name
     */
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