package com.example.bookingsystem;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Controller class for managing the booking system UI and logic.
 * It handles adding, removing, and sorting bookings, and updates the booking list view.
 */
public class BookingSystemController {
    /** TextField for entering the name of the booking. */
    @FXML private TextField nameField, dateField, detailsField;

    /** ListView for displaying bookings. */
    @FXML private ListView<String> bookingListView;

    /** Logic handler for booking operations. */
    private BookingLogic bookingLogic;

    /** Observable list for binding bookings to the ListView. */
    private ObservableList<String> bookingList = FXCollections.observableArrayList();

    /** File path for storing booking data. */
    private static final String FILE_PATH = "bookings.txt";

    /**
     * Initializes the controller by setting up the booking logic and loading existing bookings.
     */
    public void initialize() {
        FileManager fileManager = new FileManager(FILE_PATH);
        bookingLogic = new BookingLogic(fileManager);
        updateBookingList();
    }

    /**
     * Adds a new booking using the input fields.
     * If any field is empty, shows an alert.
     */
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

    /**
     * Removes the selected booking from the list.
     * If no booking is selected, shows an alert.
     */
    @FXML
    private void removeBooking() {
        String selected = bookingListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String name = selected.split(",")[0];
            bookingLogic.removeBooking(name);
            updateBookingList();
        } else {
            showAlert("Please select a booking to remove.");
        }
    }

    /**
     * Sorts the bookings by date and updates the list.
     */
    @FXML
    private void sortBookings() {
        bookingLogic.sortByDate();
        updateBookingList();
    }

    /**
     * Updates the ListView with the current bookings from the logic.
     */
    private void updateBookingList() {
        bookingList.clear();
        for (Booking b : bookingLogic.getBookings()) {
            bookingList.add(b.getName() + ", " + b.getDate() + ", " + b.getDetails());
        }
        bookingListView.setItems(bookingList);
    }

    /**
     * Displays an informational alert with the given message.
     *
     * @param message The message to display.
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Booking System");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}