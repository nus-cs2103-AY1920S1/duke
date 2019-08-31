package duke.ui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;


/**
 * User interface element for users to use to send commands to Duke.
 */

public class Input extends HBox {

    private TextField textField;
    private Button button;

    /**
     * Constructs the scene element.
     * @param eventHandler Action to be taken when the message in the text field is sent
     */
    public Input(EventHandler eventHandler) {
        //initialize instance of Button and TextField nodes
        textField = new TextField();
        button = new Button("SEND");

        // set position and dimension of the subnodes
        textField.setPrefWidth(315.0);
        textField.setAlignment(Pos.CENTER_LEFT);

        //textField.setPadding(new Insets(10,-10,10,10));
        //textField.set
        button.setPrefWidth(55.0);
        button.setAlignment(Pos.CENTER_RIGHT);

        // set button and textfield to do respond to user input
        button.setOnMouseClicked(eventHandler);
        textField.setOnAction(eventHandler);

        //add them as subnodes to this node
        getChildren().addAll(textField,new Rectangle(10,0), button);
        setPadding(new Insets(10,10,0,10));
    }

    /**
     * Returns the text in the text field
     * @return The text in the text field
     */
    public String getText() {
        return textField.getText();
    }

    /**
     * Clears the text in the text field
     */
    public void clearText() {
        textField.clear();
    }
}