package dose.util.gui.messagebox;

import dose.util.gui.ColourScheme;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

// todo: custom styling for tasks, including status, priority, deadline, tags etc
public class TaskMessageBox extends MessageBox {
    public TaskMessageBox(String text, ColourScheme colourScheme) {
        super(text);
        messageBox.setStyle("-fx-background-color: " + colourScheme.getDukeShadowColour());
        getMessage().setStyle("-fx-fill: " + colourScheme.getTextColour());
        DropShadow dropShadow = new DropShadow(5.0, Color.web(colourScheme.getDukeShadowColour()));
        messageBox.setEffect(dropShadow);
    }
}
