import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/** Controller for MainWindow. Provides the layout for the other controls. */
public class MainWindow extends AnchorPane {
    private static String suntec = "THIS IS DA                                                  \r\n                                                         ttt:::t                                                  \r\n                                                         t:::::t                                                  \r\n                                                         t:::::t                                                  \r\n    ssssssssss   uuuuuu    uuuuuunnnn  nnnnnnnn    ttttttt:::::ttttttt        eeeeeeeeeeee        cccccccccccccccc\r\n  ss::::::::::s  u::::u    u::::un:::nn::::::::nn  t:::::::::::::::::t      ee::::::::::::ee    cc:::::::::::::::c\r\nss:::::::::::::s u::::u    u::::un::::::::::::::nn t:::::::::::::::::t     e::::::eeeee:::::ee c:::::::::::::::::c\r\ns::::::ssss:::::su::::u    u::::unn:::::::::::::::ntttttt:::::::tttttt    e::::::e     e:::::ec:::::::cccccc:::::c\r\n s:::::s  ssssss u::::u    u::::u  n:::::nnnn:::::n      t:::::t          e:::::::eeeee::::::ec::::::c     ccccccc\r\n   s::::::s      u::::u    u::::u  n::::n    n::::n      t:::::t          e:::::::::::::::::e c:::::c             \r\n      s::::::s   u::::u    u::::u  n::::n    n::::n      t:::::t          e::::::eeeeeeeeeee  c:::::c             \r\nssssss   s:::::s u:::::uuuu:::::u  n::::n    n::::n      t:::::t    tttttte:::::::e           c::::::c     ccccccc\r\ns:::::ssss::::::su:::::::::::::::uun::::n    n::::n      t::::::tttt:::::te::::::::e          c:::::::cccccc:::::c\r\ns::::::::::::::s  u:::::::::::::::un::::n    n::::n      tt::::::::::::::t e::::::::eeeeeeee   c:::::::::::::::::c\r\n s:::::::::::ss    uu::::::::uu:::un::::n    n::::n        tt:::::::::::tt  ee:::::::::::::e    cc:::::::::::::::c\r\n  sssssssssss        uuuuuuuu  uuuunnnnnn    nnnnnn          ttttttttttt      eeeeeeeeeeeeee      cccccccccccccccc";
    public static String greeting = "\n\nCommands available:\n" +
        "1. help -- shows this again.\n" +
        "2. list -- lists all tasks.\n" +
        "3. todo <name> -- adds a task with a specified name.\n" +
        "4. Deadline <name> /by dd/mm/yyyy hhmm -- adds a deadline with the specified name and date.\n" +
        "5. Event <name> /at dd/mm/yyyy hhmm -- adds an event with the specified name and date.\n" +
        "6. done <number> -- marks item with corresponding number from the list command as done.\n" +
        "7. delete all -- deletes all tasks.\n" +
        "8. delete done -- deletes all tasks marked as done.\n" +
        "9. delete <number> -- deletes tasks numbered <number> shown in the list command.\n" +
        "10. find <term> -- searches for tasks with <term> in their title.\n";

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(
        this.getClass().getResourceAsStream("/images/user.jpg")
    );
    private Image dukeImage = new Image(
        this.getClass().getResourceAsStream("/images/duke.jpg")
    );

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren()
            .addAll(DialogBox.getDukeDialog(suntec+greeting, dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
    * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and
    * then appends them to the dialog container. Clears the user input after processing.
    */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            response = Parser.handleCommand(input);
        } catch (DukeException e) {
            response = e.toString();
        }

        userInput.clear();
        dialogContainer.getChildren()
            .addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
            );
    }
}
