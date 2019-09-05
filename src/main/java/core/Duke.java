package core;

import ui.TextUi;
import storage.Storage;
import tasklist.TaskList;
import parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

/**
 * Main class of the program.
 * starts the application and receives use input.
 */
public class Duke{
    private Storage saveFile;
    private TextUi textui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image ui = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    public Duke() throws IOException {
        saveFile = new Storage("tasklist.txt");
        textui = new TextUi();
    }

    public Duke(String filepath) throws IOException {
        saveFile = new Storage(filepath);
        textui = new TextUi();
    }

    /**
     * Runs the program until termination.
     * @throws IOException in case file doesn't load
     */
    public void run() throws IOException {
        textui.printIntroduction();
        Scanner input = new Scanner(System.in);
        String user = input.nextLine();
        TaskList scheduler;
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        while (!user.equals("bye")) {
            scheduler = new TaskList(saveFile.loadData());
            parser.parse(user, scheduler,false);
            saveFile.storeData(scheduler.getTaskList());
            user = input.nextLine();
        }

        textui.printGoodByeMsg();

    }

    public String getResponse(String input) throws IOException {
        //textui.printIntroduction();
        TaskList scheduler;
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        scheduler = new TaskList(saveFile.loadData());
        parser.parse(input, scheduler,false);
        saveFile.storeData(scheduler.getTaskList());
        return outContent.toString();
    }



    public static void main(String[] args) throws IOException {
        new Duke("tasklist.txt").run();
    }
}
