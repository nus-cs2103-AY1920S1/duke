import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * Represents a duke chatbot
 */
public class Duke extends Application implements Serializable {
    // constants
    private static final String PRINT_INDENT = "";
    private static final String PRINT_HORIZONTAL_LINE = "____________________________________________________________";

    // main loop behavior constants
    private static final int STATE_CONTINUE = 1;
    private static final int STATE_EXIT = 2;

    // class members
    // data members
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Ui ui;
    private TaskList<Task> todoList;
    private Storage storage;
    private Parser parser;

    // jfx components
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public static void main(String[] args) {
        // new Duke(System.in, System.out, "data/duke.txt").run();
        Application.launch(Duke.class, args);
    }

    public Duke() {
        this.ui = Ui.newUi(new DukeInputStream(), new DukeOutputStream());

        this.todoList = new TaskList();
        this.storage = Storage.newStorage("data/duke.txt");
    }

    /**
     * Create a duke with provided parameters
     * @param inStream input stream
     * @param outStream output stream
     * @param saveLocation relative file save location
     */
    // takes in a input stream and output stream
    public Duke(InputStream inStream, OutputStream outStream, String saveLocation) {
        this.ui = Ui.newUi(inStream, outStream);

        this.todoList = new TaskList();
        this.storage = Storage.newStorage(saveLocation);
    }

    /**
     * Runs duke
     * @return 1 if exit with error, zero otherwise
     */
    public int run() {
        int returnCode = 0;
        try {
            greet();
            Object obj = storage.load();
            todoList = obj == null ? new TaskList() : (TaskList) obj;
            mainLoop();
        } catch (IOException e) {
            e.printStackTrace();
            returnCode = 1;
        } finally {
            ui.close();
        }
        return returnCode;
    }

    /**
     * Prints bye message
     * @throws IOException if stream fed to Duke is not valid
     */
    // prints out bye message to out stream
    private void bye() throws IOException {
        ui.write(String.format("%s Bye. Hope to see you again soon!\n", PRINT_INDENT));
        ui.flush();
    }

    /**
     * Main loop of the program
     * @throws IOException if stream fed to Duke is not valid
     */
    // main update loop where processinput is continuously called
    private void mainLoop() throws IOException {
        int code;
        do {
            code = processInput();
            if (code == STATE_EXIT) {
                bye();
            }
        } while (code == STATE_CONTINUE);
    }

    int processInput() throws IOException {
        String command = ui.readLine();
        return processInput(command);
    }

    /**
     * Main loop logic
     * @return CONTINUE if continue main loop, EXIT if exiting main loop
     * @throws IOException if stream fed to Duke is not valid
     */
    // main logic
    // returns EXIT if signalling to exit main loop
    // returns CONTINUE if to continue with loop
    int processInput(String command) throws IOException {
        if (command == null) {
            // EOF
            return STATE_EXIT;
        }
        try {
            String[] commandArgs = Parser.returnArgs(command);
            switch (commandArgs[0]){
            case "bye":
                return STATE_EXIT;
            case "help":
                help(commandArgs);
                break;
            case "find":
                find(commandArgs);
                break;
            case "list":
                listList(commandArgs);
                break;
            case "done":
                done(commandArgs);
                break;
            case "delete":
                delete(commandArgs);
                break;
            case "todo":
            case "deadline":
            case "event":
                addToList(commandArgs);
                break;
            default:
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            ui.write(String.format("%s %s\n", PRINT_INDENT, e.getMessage()));
            ui.flush();
        }
        return STATE_CONTINUE;
    }

    private void help(String[] commandArgs) throws IOException{
        ui.write("Here are the list of commands:\n");
        ui.write("  bye: close the application\n");
        ui.write("  help: bring up this help page\n");
        ui.write("  find: find a specific task with a keyword\n");
        ui.write("  list: displays the current task list\n");
        ui.write("  done: mark a task as done\n");
        ui.write("  delete: delete a task in the task list\n");
        ui.write("  todo: add a todo task\n");
        ui.write("  event: add an event task with an event time\n");
        ui.write("  deadline: add a deadline task with a deadline\n");
        ui.flush();
    }

    /**
     * Delete task with args
     * @param args args
     * @throws IOException if stream fed to Duke is not valid
     * @throws DukeException
     */
    private void find(String[] args) throws DukeException, IOException {
        if (args.length != 2){
            throw new DukeException("\u2639 OOPS!!! Done function needs exactly one argument.");
        }
        TaskList<Task> shortList = new TaskList<>(todoList);
        shortList.removeIf((x) -> !x.getName().matches(".*" + args[1] + ".*"));
        printList(shortList);
    }

    // input expected: "delete n"
    // deletes task n
    // throws duke exception if there is no task n
    private void delete(String[] args) throws IOException, DukeException {
        if (args.length != 2){
            throw new DukeException("\u2639 OOPS!!! Done function needs exactly one argument.");
        }
        if (!args[1].matches("\\d{1,9}")){
            throw new DukeException("\u2639 OOPS!!! Task number is too big.");
        }
        int thingToDo = Integer.parseInt(args[1]);
        Task removedTask;
        try {
            removedTask = todoList.remove(thingToDo - 1);
            ui.write(String.format("%s Noted. I've removed this task: \n", PRINT_INDENT));
            ui.write(String.format("%s   %s\n", PRINT_INDENT, removedTask));
            ui.write(String.format("%s  Now you have %d tasks in the list.\n", PRINT_INDENT, todoList.size()));
            ui.flush();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("\u2639 OOPS!!! There is no task %d.", thingToDo));
        }
        storage.save(todoList);
    }


    /**
     * Do task with args
     * @param args args
     * @throws IOException if stream fed to Duke is not valid
     * @throws DukeException
     */
    // input expected: "done n"
    // marks task n as done
    // throws duke exception if there is no task n
    private void done(String[] args) throws IOException, DukeException {
        if (args.length != 2){
            throw new DukeException("\u2639 OOPS!!! Done function needs exactly one argument.");
        }
        if (!args[1].matches("\\d{1,9}")){
            throw new DukeException("\u2639 OOPS!!! Task number is too big.");
        }
        int thingToDo = Integer.parseInt(args[1]);
        try {
            todoList.get(thingToDo - 1).setState(Task.DONE);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("\u2639 OOPS!!! There is no task %d.", thingToDo));
        }
        ui.write(String.format("%s Nice! I've marked this task as done: \n", PRINT_INDENT));
        ui.write(String.format("%s   %s\n", PRINT_INDENT, todoList.get(thingToDo - 1)));
        ui.flush();
        storage.save(todoList);
    }

    /**
     * List to do list
     * @param args
     * @throws IOException if stream fed to Duke is not valid
     */
    // input expected: "list"
    // input not checked
    // prints out list of tasks to out stream
    private void listList(String[] args) throws IOException {
        printList(todoList);
    }

    private void printList(TaskList<Task> tl) throws IOException {
        int counter = 1;
        for (Task item : todoList) {
            ui.write(String.format("%s %d.%s\n", PRINT_INDENT, counter++, item));
        }
        if (counter == 1) {
            ui.write(String.format("%s Such empty, much wow\n", PRINT_INDENT));
        }
        ui.flush();
    }


    /**
     * Add a todo, deadline or event according to args
     * @param args args
     * @throws IOException if stream fed to Duke is not valid
     * @throws DukeException
     */
    private void addToList(String[] args) throws IOException, DukeException {
        switch (args[0]) {
        case "todo":
            if(args.length != 2){
                throw new DukeException("\u2639 OOPS!!! Todo must have exactly one argument.");
            }
            try {
                todoList.add(Task.parseTodo(args[1]));
            } catch (NoSuchElementException e) {
                throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            }
            break;
        case "deadline":
            try {
                todoList.add(Task.parseDeadline(args[1], args[2]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("\u2639 OOPS!!! A deadline must have a description and a date.");
            }
            break;
        case "event":
            try {
                todoList.add(Task.parseEvent(args[1], args[2]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("\u2639 OOPS!!! An event must have a description and a date.");
            }
            break;
        }
        ui.write(String.format("%s Got it. I've added this task: \n", PRINT_INDENT));
        ui.write(String.format("%s   %s\n", PRINT_INDENT, todoList.get(todoList.size() - 1)));
        ui.write(String.format("%s  Now you have %d tasks in the list.\n", PRINT_INDENT, todoList.size()));
        ui.flush();
        storage.save(todoList);
    }

    /**
     * Echo command.
     * @param command
     * @throws IOException if stream fed to Duke is not valid
     */
    private void echo(String command) throws IOException {
        ui.write(String.format("%s%s\n", PRINT_INDENT, command));
        ui.flush();
    }

    /**
     * Print greet message.
     * @throws IOException if stream fed to Duke is not valid
     */
    private void greet() throws IOException {
        ui.write(String.format("%s Hello! I'm Duke\n", PRINT_INDENT));
        ui.write(String.format("%s What can I do for you?\n", PRINT_INDENT));
        ui.flush();
    }

    @Override
    public void start(Stage stage) throws Exception {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        greet();
        Object obj = storage.load();
        todoList = obj == null ? new TaskList() : (TaskList) obj;
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        createLabelAndAdd(userInput.getText(), true, false);
        // createLabelAndAdd(getResponse(userInput.getText()), false, true);
        try {
            if(processInput() == STATE_EXIT){
                bye();
                System.exit(0);
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userInput.clear();
    }

    private void createLabelAndAdd(String text, boolean isUser, boolean isDuke){
        Label textbox = new Label(text);
        if(isUser){
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(textbox, new ImageView(user))
            );
        }
        if(isDuke){
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(textbox, new ImageView(duke))
            );
        }
    }

    private String getResponse(String input) {
        return input;
    }

    class DukeInputStream extends InputStream{
        int i = 0;

        @Override
        public synchronized void reset() throws IOException {
            // super.reset();
            i = 0;
        }

        @Override
        public int read() throws IOException {
            try {
                return userInput.getText().charAt(i++);
            }catch (StringIndexOutOfBoundsException e){
                return -1;
            }
        }

        @Override
        public int available() throws IOException {
            return userInput.getText().length();
        }
    }

    class DukeOutputStream extends OutputStream {
        String print = "";
        boolean prevValid = false;
        int prev = 0;

        @Override
        public void write(int i) throws IOException {
            if(prevValid){
                print += (char) ((i << 8) | (prev & 0xff));
            } else {
                prev = i;
            }
            prevValid = !prevValid;
        }

        @Override
        public void write(byte[] b) throws IOException {
            print.concat(new String(b, "UTF-16LE"));
        }

        @Override
        public void flush(){
            createLabelAndAdd(print, false, true);
            print = "";
        }
    }
}


class DukeException extends Exception {
    DukeException(String error) {
        super(error);
    }
}

class DukeError extends Error {

}