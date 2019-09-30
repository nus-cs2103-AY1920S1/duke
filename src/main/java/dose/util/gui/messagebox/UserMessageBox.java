package dose.util.gui.messagebox;

import dose.util.gui.ColourScheme;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class UserMessageBox extends MessageBox {
    public UserMessageBox(String text, ColourScheme colourScheme) {
        super(text);
        messageBox.setStyle("-fx-background-color: " + colourScheme.getUserMessageBoxColour());
        getMessage().setStyle("-fx-fill: " + colourScheme.getTextColour());
        DropShadow dropShadow = new DropShadow(5.0, Color.web(colourScheme.getUserShadowColour()));
        messageBox.setEffect(dropShadow);
        //flip();
        messageBoxContainer.setAlignment(Pos.TOP_RIGHT);
    }

//    /**
//     * Flips the dialog box such that the ImageView is on the left and text on the right.
//     */
//    private void flip() {
//        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
//        Collections.reverse(tmp);
//        getChildren().setAll(tmp);
//        setAlignment(Pos.TOP_RIGHT);
//    }
}
