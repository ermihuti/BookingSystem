package com.example.bookingsystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The FileManager class is responsible for handling file operations related to bookings.
 * It can load bookings from a file and save bookings to a file.
 */
public class FileManager {
    private String filePath;

    /**
     * Constructs a FileManager object with the specified file path.
     *
     * @param filePath The path to the file used for storing and loading bookings.
     */
    public FileManager(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Loads the bookings from a file.
     * Each line in the file represents a booking, and bookings are separated by commas.
     * The file should contain lines in the format: Name, Date, Details.
     *
     * @return A list of Booking objects representing the bookings read from the file.
     */
    public List<Booking> loadBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    bookings.add(new Booking(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return bookings;
    }

    /**
     * Saves a list of bookings to a file.
     * Each booking is written on a new line in the format: Name, Date, Details.
     *
     * @param bookings The list of Booking objects to be saved to the file.
     */
    public void saveBookings(List<Booking> bookings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Booking booking : bookings) {
                writer.write(booking.getName() + "," + booking.getDate() + "," + booking.getDetails());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}