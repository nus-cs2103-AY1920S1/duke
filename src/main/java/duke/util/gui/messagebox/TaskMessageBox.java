package duke.util.gui.messagebox;

import duke.util.gui.ColourScheme;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

// todo: custom styling for tasks, including status, priority, deadline, tags etc
public class TaskMessageBox extends MessageBox {
    public TaskMessageBox(String text, ColourScheme colourScheme) {
        super(text);
        setStyle("-fx-background-color: " + colourScheme.getDukeShadowColour());
        getMessage().setStyle("-fx-fill: " + colourScheme.getTextColour());
        DropShadow dropShadow = new DropShadow(5.0, Color.web(colourScheme.getDukeShadowColour()));
        setEffect(dropShadow);
        setAlignment(Pos.TOP_LEFT);
    }
}
