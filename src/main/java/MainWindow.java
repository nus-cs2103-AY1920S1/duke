import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import commands.DukeException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends BorderPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/surprisedpikachu.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/my_tweet.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    void setDuke(Duke d) {
        duke = d;
        String[] openingMessages = duke.getWelcomeMessage();
        DialogBox[] boxes = new DialogBox[openingMessages.length];
        for (int i = 0; i < openingMessages.length; i++) {
            boxes[i] = DialogBox.getDukeDialog(openingMessages[i], dukeImage);
        }
        dialogContainer.getChildren().addAll(boxes);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("clear")) {
            clearConsole();
            return;
        }
        if (input.equals("help")) {
            showHelpModal();
            userInput.clear();
            return;
        }
        if (input.equals("import")) {
            importNoOverWrite();
            userInput.clear();
            return;
        }
        if (input.equals("overwrite")) {
            importWithOverwrite();
            userInput.clear();
            return;
        }
        String[] response = duke.run(input);
        DialogBox[] a = produceDialogBoxes(response);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        dialogContainer.getChildren().addAll(a);
        dialogContainer.getChildren().add(DialogBox.getDukeDialogNoPic(makeLine()));
        userInput.clear();
    }

    String makeLine() {
        return "_____________________________________";
    }

    private DialogBox[] produceDialogBoxes(String[] arr) {
        DialogBox[] a = new DialogBox[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                a[i] = DialogBox.getDukeDialog(arr[i], dukeImage);
            } else {
                a[i] = DialogBox.getDukeDialogNoPic(arr[i]);
            }
        }
        return a;
    }


    private void showModal(String title, Pos pos, Node... items) {
        final Stage dialog = new Stage();
        dialog.setTitle(title);
        dialog.setMinWidth(300);
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(15);
        dialogVbox.setAlignment(pos);
        dialogVbox.setPadding(new Insets(20, 20, 20, 20));
        dialogVbox.getChildren().addAll(items);
        Scene dialogScene = new Scene(dialogVbox);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    @FXML
    private void showAboutModal() {
        Text dukeName = new Text("Duke v1.0");
        dukeName.setLineSpacing(1.0);
        Hyperlink link = new Hyperlink();
        Font futura = Font.font("Futura Hv BT", 14);
        link.setText("View the repository");
        link.setFont(futura);
        link.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URL("https://github.com/timothyleong97/duke").toURI());
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
        Text authorName = new Text("Timothy Leong, 2019");
        dukeName.setFont(futura);
        authorName.setFont(futura);
        showModal("About Duke", Pos.CENTER, dukeName, authorName, link);
    }

    @FXML
    private void showHelpModal() {
        Text header = new Text();
        header.setUnderline(true);
        header.setText("Commands");
        header.setFont(Font.font("System", 12));
        final TextFlow bye = makeCommand("bye", "Exits the application.");
        final TextFlow deadline = makeCommand("deadline <task> /by <dd/mm/yyyy hhmm>",
                "Creates a task with a deadline.");
        final TextFlow done = makeCommand("done <index number>", "Marks a task as done.");
        final TextFlow todo = makeCommand("todo <task name>", "Creates a to-do item.");
        final TextFlow delete = makeCommand("delete <range>",
                "e.g delete 1-4,6-9,12 will delete items 1 through 4, 6 through 9 and 12.");
        final TextFlow list = makeCommand("list", "Lists all items.");
        final TextFlow rmdone = makeCommand("rmdone", "Deletes all completed items.");
        final TextFlow clear = makeCommand("clear", "Clears the console.");
        final TextFlow help = makeCommand("help", "Shows this window.");
        final TextFlow importNoOver = makeCommand("import", "Opens file selector for importing w/o overwrite");
        final TextFlow importOver = makeCommand("overwrite", "Opens file selector for importing with overwrite");
        final TextFlow find = makeCommand("find <keyword>",
                "Returns a list of tasks that contain the keyword");
        final TextFlow event = makeCommand("event <name> /at <dd/mm/yyyy hhmm>",
                "Creates an event that starts at the given time.");
        final TextFlow deleteAll = makeCommand("deleteAll", "Delete all tasks.");
        Text glossary = new Text();
        glossary.setUnderline(true);
        glossary.setText("Glossary");
        glossary.setFont(Font.font("System", 12));
        TextFlow overwriteMeaning = makeCommand("Importing with overwrite",
                "Replace current tasks in Duke with tasks from the imported file");
        TextFlow noverwriteMeaning = makeCommand("Importing without overwrite",
                "Add tasks from file to currently existing tasks in Duke.");
        showModal("Help", Pos.TOP_LEFT, header, bye, clear, deadline, delete, deleteAll, done, event,
                find, help, importNoOver, list, importOver, rmdone, todo, glossary,
                overwriteMeaning, noverwriteMeaning);
    }

    private TextFlow makeCommand(String command, String desc) {
        TextFlow flow = new TextFlow();
        Font v = Font.font("Verdana");
        flow.setTextAlignment(TextAlignment.LEFT);
        Text c = new Text();
        c.setFont(v);
        c.setText(command);
        c.setStyle("-fx-font-weight: bold");
        Text d = new Text();
        d.setFont(v);
        d.setText(" - " + desc);
        flow.getChildren().addAll(c, d);
        return flow;
    }

    @FXML
    private void exit() {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog("Goodbye!", dukeImage));
        CompletableFuture.delayedExecutor(100, TimeUnit.MILLISECONDS).execute(() -> {
            Platform.exit();
        });
    }

    @FXML
    private void importNoOverWrite() {
        importFile(false);
    }

    @FXML
    private void importWithOverwrite() {
        importFile(true);
    }

    private void importFile(boolean overwrite) {
        File selected = getFile();
        if (selected != null) {
            try {
                if (overwrite) {
                    duke.loadTasksWithOverwrite(selected.getCanonicalPath());
                    String[] response = new String[]{"Overwrite from " + selected.getName() + " was successful!"};
                    DialogBox[] a = produceDialogBoxes(response);
                    dialogContainer.getChildren().addAll(a);
                    userInput.clear();
                } else {
                    duke.loadTasksWithNoOverwrite(selected.getCanonicalPath());
                    String[] response = new String[]{"Import from " + selected.getName() + " was successful!"};
                    DialogBox[] a = produceDialogBoxes(response);
                    dialogContainer.getChildren().addAll(a);
                    userInput.clear();
                }
            } catch (DukeException | IOException e) {
                String[] response = new String[]{"I could not read from " + selected.getName() + ". Sorry!"};
                DialogBox[] a = produceDialogBoxes(response);
                dialogContainer.getChildren().addAll(a);
                userInput.clear();
            }
        }

    }

    private File getFile() {
        final Stage dialog = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("duke.txt");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        return fileChooser.showOpenDialog(dialog);
    }


    @FXML
    private void deleteCompleted() {
        String[] response = duke.deleteCompletedTasks();
        DialogBox[] boxes = produceDialogBoxes(response);
        dialogContainer.getChildren().addAll(boxes);
    }

    @FXML
    private void clearConsole() {
        dialogContainer.getChildren().clear();
        userInput.clear();
    }

    @FXML
    private void startExpenses() {
        final Stage stage = new Stage();
        stage.setTitle("Expenses Tracker");
        try {
            (new ExpensesMain()).start(stage);
        } catch (IOException e) {
            System.err.println(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteAll() {
        String[] response = duke.deleteAll();
        DialogBox[] boxes = produceDialogBoxes(response);
        dialogContainer.getChildren().addAll(boxes);
    }
}