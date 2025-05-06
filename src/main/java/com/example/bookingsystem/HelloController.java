package com.example.bookingsystem;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class HelloController {
    private BookingLogic bookingLogic;
    private List<Booking> allBookings = new ArrayList<>();
    private List<Booking> searchResults = new ArrayList<>();
    private boolean isSorted = false;

    @FXML
    private ListView<String> bookingListView;
    @FXML
    private TextField nameField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField detailsField;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button sortButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button clearSearchButton;
    @FXML
    private Label statusLabel;

    public void initialize() {
        FileManager fileManager = new FileManager("bookings.txt");
        bookingLogic = new BookingLogic(fileManager);
        allBookings = bookingLogic.getBookings();
        updateBookingList(allBookings);
        clearSearchButton.setVisible(false);
    }

    @FXML
    private void addBooking() {
        String name = nameField.getText();
        LocalDate selectedDate = datePicker.getValue();
        String date = (selectedDate != null)
                ? selectedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                : datePicker.getEditor().getText();
        String details = detailsField.getText();

        if (!name.isEmpty() && !date.isEmpty()) {
            Booking newBooking = new Booking(name, date, details);
            bookingLogic.addBooking(newBooking);
            allBookings.add(newBooking);

            if (!searchResults.isEmpty()) {
                updateBookingList(searchResults);
            } else {
                updateBookingList(allBookings);
            }

            statusLabel.setText("Booking added.");
            nameField.clear();
            datePicker.setValue(null);
            datePicker.getEditor().clear();
            detailsField.clear();
        } else {
            statusLabel.setText("Please enter name and date.");
        }
    }

    @FXML
    private void removeBooking() {
        String selected = bookingListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            String id = selected.split(" - ")[0];
            bookingLogic.removeBooking(id);
            allBookings.removeIf(b -> b.getId().equals(id));
            searchResults.removeIf(b -> b.getId().equals(id));
            updateBookingList(!searchResults.isEmpty() ? searchResults : allBookings);
            statusLabel.setText("Booking removed.");
        } else {
            statusLabel.setText("Please select a booking to remove.");
        }
    }

    @FXML
    private void sortBookings() {
        List<Booking> target = searchResults.isEmpty() ? allBookings : searchResults;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        target.sort((b1, b2) -> {
            LocalDate d1 = LocalDate.parse(b1.getDate(), formatter);
            LocalDate d2 = LocalDate.parse(b2.getDate(), formatter);
            return d1.compareTo(d2);
        });

        if (searchResults.isEmpty()) {
            isSorted = true;
            bookingLogic.saveBookings(allBookings);
        }

        updateBookingList(target);
        statusLabel.setText("Sorted by date.");
    }

    @FXML
    private void searchBooking() {
        String name = nameField.getText();
        searchResults = bookingLogic.searchBooking(name);

        if (!searchResults.isEmpty()) {
            if (isSorted) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                searchResults.sort((b1, b2) -> {
                    LocalDate d1 = LocalDate.parse(b1.getDate(), formatter);
                    LocalDate d2 = LocalDate.parse(b2.getDate(), formatter);
                    return d1.compareTo(d2);
                });
            }
            updateBookingList(searchResults);
            clearSearchButton.setVisible(true);
            statusLabel.setText("Found " + searchResults.size() + " result(s).");
        } else {
            statusLabel.setText("No bookings found.");
        }
    }

    @FXML
    private void clearSearch() {
        searchResults.clear();
        clearSearchButton.setVisible(false);
        updateBookingList(allBookings);
        statusLabel.setText("Search cleared.");
    }

    private void updateBookingList(List<Booking> list) {
        bookingListView.getItems().clear();
        for (Booking b : list) {
            bookingListView.getItems().add(b.getId() + " - " + b.getName() + " - " + b.getDate() + " - " + b.getDetails());
        }
    }
}