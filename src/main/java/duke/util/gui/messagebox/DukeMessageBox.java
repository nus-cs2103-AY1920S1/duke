package duke.util.gui.messagebox;

import duke.util.gui.ColourScheme;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class DukeMessageBox extends MessageBox {
    public DukeMessageBox(String text, ColourScheme colourScheme) {
        super(text);
        messageBox.setStyle("-fx-background-color: " + colourScheme.getDukeMessageBoxColour());
        getMessage().setStyle("-fx-fill: " + colourScheme.getTextColour());
        DropShadow dropShadow = new DropShadow(5.0, Color.web(colourScheme.getDukeShadowColour()));
        messageBox.setEffect(dropShadow);
    }
}
