package com.example.bookingsystem;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookingSystemController {
    @FXML private TextField nameField, dateField, detailsField;
    @FXML private ListView<String> bookingListView;

    private BookingLogic bookingLogic;
    private ObservableList<String> bookingList = FXCollections.observableArrayList();

    private static final String FILE_PATH = "bookings.txt";

    public void initialize() {
        FileManager fileManager = new FileManager(FILE_PATH);
        bookingLogic = new BookingLogic(fileManager);
        updateBookingList();
    }

    @FXML
    private void addBooking() {
        String name = nameField.getText();
        String date = dateField.getText();
        String details = detailsField.getText();

        if (!name.isEmpty() && !date.isEmpty() && !details.isEmpty()) {
            Booking newBooking = new Booking(name, date, details);
            bookingLogic.addBooking(newBooking);
            updateBookingList();
        } else {
            showAlert("Please fill in all fields.");
        }
    }
}
