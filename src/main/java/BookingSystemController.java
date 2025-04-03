import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.ArrayList;

public class BookingSystemController {

    @FXML
    private ComboBox<String> slotComboBox;
    @FXML
    private ListView<String> slotListView;

    private ArrayList<String> availableSlots;

    public void initialize() {
        // Initialize available slots
        availableSlots = new ArrayList<>();
        availableSlots.add("10:00 AM");
        availableSlots.add("12:00 PM");
        availableSlots.add("2:00 PM");
        availableSlots.add("4:00 PM");

        // Populate ComboBox and ListView
        slotComboBox.getItems().addAll(availableSlots);
        slotListView.getItems().addAll(availableSlots);
    }

    @FXML
    private void handleBookSlot() {
        String selectedSlot = slotComboBox.getSelectionModel().getSelectedItem();

        if (selectedSlot != null) {
            // Remove the selected slot from available slots
            availableSlots.remove(selectedSlot);
            slotListView.getItems().remove(selectedSlot);

            // Show confirmation message
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Booking Successful");
            alert.setHeaderText(null);
            alert.setContentText("You have successfully booked the slot: " + selectedSlot);
            alert.showAndWait();
        } else {
            // Show warning if no slot is selected
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Slot Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a time slot.");
            alert.showAndWait();
        }
    }
}