package duke;

import java.io.IOException;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DateBox extends VBox {
    @FXML
    private Label dateLabel;

    private DateBox(Date date) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class
                    .getResource("/view/DateBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dateLabel.setText("Conversation started: " + date.toString());
    }

    public static DateBox getDateBox(Date date) {
        return new DateBox(date);
    }
}
