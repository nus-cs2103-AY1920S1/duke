package duke.util.gui.messagebox;

import duke.util.gui.ColourScheme;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class UserMessageBox extends MessageBox {
    public UserMessageBox(String text, ColourScheme colourScheme) {
        super(text);
        setStyle("-fx-background-color: " + colourScheme.getUserMessageBoxColour());
        getMessage().setStyle("-fx-fill: " + colourScheme.getTextColour());
        DropShadow dropShadow = new DropShadow(5.0, Color.web(colourScheme.getUserShadowColour()));
        setEffect(dropShadow);
        setAlignment(Pos.TOP_RIGHT);
    }
}
