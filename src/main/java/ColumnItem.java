import expenses.ItemInfo;
import expenses.TrackingService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ColumnItem extends HBox {
    @FXML
    private Label name;
    @FXML
    private Label amnt;
    @FXML
    private Button delete;
    @FXML
    private Button edit;

    private boolean isCredit;
    private int index;
    private TrackingService service = TrackingService.getTrackingService();

    ColumnItem(String title, String amnt, boolean isCredit, int index) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/Item.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.isCredit = isCredit;
            this.index = index;
        } catch (IOException e) {
            e.printStackTrace();
        }
        name.setText(title);
        this.amnt.setText(amnt);
        delete.setOnAction(e -> service.updateDeletionSubject(new ItemInfo(isCredit,index)));
        edit.setOnAction(e -> service.updateEditSubject(new ItemInfo(isCredit, index)));
    }

}
