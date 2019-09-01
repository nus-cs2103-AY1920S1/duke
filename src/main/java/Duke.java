import java.io.*;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a Duke class which is used to run a taskList computer program
 * that allows users to store their various task (Deadline, Event, Things to do)
 * and store them in a text file. The program also allows them to delete and update
 * their list once they have completed it.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class
     * Takes in a filePath and instantiate a UI, Storage and TaskList object.
     *
     * @param filePath an String file location
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("D:\\NUS Computer Science\\CS2103T\\duke\\src\\main\\java\\duke.txt");
        tasks = new TaskList();
    }

    /**
     * Runs the entire program. Kick starts the entire computer duke Program
     *
     * @throws IOException throws exception if user input invalid
     */
    public String getResponse(String inputInstruction) throws IOException {
        String outputContent;
        String inputCommand = Parser.getInputCommand(inputInstruction);
            if (inputCommand.equals("bye")) {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                System.setOut(new PrintStream(buffer));
                System.out.println("___________________________________");
                System.out.println("Bye. Hope to see you again soon!!");
                System.out.println("___________________________________");
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                outputContent = buffer.toString();
                buffer.reset();
            } else {
                outputContent = ui.executeInstructions(inputInstruction, inputCommand, storage, tasks);
            }
            return outputContent;
        }

    public String reformatInput (String input) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        System.out.println("___________________________________");
        System.out.println(input);
        System.out.println("___________________________________");
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String outputContent = buffer.toString();
        buffer.reset();
        return outputContent;
    }

    public String getStartMessage() {
        return ui.initiate();
    }
}
