package seedu.duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import seedu.duke.cli.Command;
import seedu.duke.cli.CommandException;
import seedu.duke.cli.Parser;
import seedu.duke.cli.commands.ByeCommand;
import seedu.duke.tasks.TaskList;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * The main class for the graphical Duke.
 */
public class DukeGui extends Application {
    private TextArea outputArea;
    private TextField commandLine;
    private Button enterButton;
    private TaskList taskList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        PrintStream redirectPrintStream = new PrintStream(new TextAreaOutputStream());
        System.setErr(redirectPrintStream);
        System.setOut(redirectPrintStream);

        System.out.println("Hello from Duke!");
        System.out.println("What can I do for you?\n");

        taskList = Serialisation.deserialise();
        boolean taskListWasNull = taskList == null;
        if (taskList == null) {
            taskList = new TaskList();
        }

        setupStage(primaryStage);

        if (taskListWasNull) {
            System.out.println("No saved list loaded.");
        } else {
            System.out.printf("Loaded tasks from %s.%n", Serialisation.DATA_FILE_PATH);
        }
    }

    @Override
    public void stop() throws Exception {
        Serialisation.serialise(taskList);
        System.out.printf("Saved tasks to %s.%n", Serialisation.DATA_FILE_PATH);
    }

    private boolean setupStage(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main.fxml"));

        Parent content;
        try {
            content = loader.load();
        } catch (IOException e) {
            System.err.println("Error while loading GUI");
            e.printStackTrace();
            Platform.exit();
            return false;
        }

        Scene scene = new Scene(content);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(200);

        // FIXME HACK HACK HACK
        // Should use FX Controller classes
        outputArea = (TextArea) content.lookup("#outputArea");
        commandLine = (TextField) content.lookup("#commandText");
        enterButton = (Button) content.lookup("#enterButton");
        
        commandLine.setOnAction(event -> executeCommand());
        enterButton.setOnAction(event -> executeCommand());

        primaryStage.show();

        return true;
    }

    private void executeCommand() {
        String cmd = commandLine.getText();
        commandLine.setText("");
        System.out.printf("> %s%n", cmd);
        try {
            Command c = Parser.parse(cmd);
            if (c == null) {
                System.out.println("Unknown command.");
            } else if (c instanceof ByeCommand) {
                System.out.println("Bye. Hope to see you again soon!");
                Platform.exit();
            } else {
                c.execute(taskList);
            }
        } catch (CommandException e) {
            System.out.println(e.getMessage());
        }
    }

    private class TextAreaOutputStream extends OutputStream {
        private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        private PrintStream origOut = System.out;

        @Override
        public void write(int i) throws IOException {
            buffer.write(i);
            if (i == ((int) '\n') && outputArea != null) {
                final String line = buffer.toString(StandardCharsets.UTF_8);
                buffer.reset();
                origOut.println(line);
                Platform.runLater(() -> {
                    outputArea.appendText(line);
                });
            }
        }
    }
}
