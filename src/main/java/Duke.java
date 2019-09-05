import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke extends Application {

    protected static ArrayList<Task> taskList = new ArrayList<>();
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public static void main(String[] args) throws DukeException, FileNotFoundException {
        taskList = Storage.load();

        Scanner sc = new Scanner(System.in);
        String line = "    ____________________________________________________________\n";
        Ui ui = new Ui();
        Parser parser = new Parser();
        ui.greet();

        int x = 1; //switch
        do {        //to recycle the try catch block
            try {
                while (sc.hasNext()) {
                    String command = sc.next();

                    if (command.equals("bye")) {
                        ui.farewell();
                        x = 2; //break the do while loop
                        break;
                    } else if (command.equals("list")) {
                        String str = line + "     Here are the tasks in your list:\n";
                        for (int i = 0; i < taskList.size(); i++) {
                            String num = "" + (i + 1);
                            str += "     " + num + ".[" + taskList.get(i).getStatusIcon() + "] "
                                    + taskList.get(i).toString() + "\n";
                        }
                        System.out.print(str + line);

                    } else if (command.equals("done")) {
                        int taskNum = sc.nextInt();
                        taskList.get(taskNum - 1).markAsDone();
                        System.out.println(line + "     Nice! I've marked this task as done:\n"
                                + "     [" + taskList.get(taskNum - 1).getStatusIcon() + "]"
                                + taskList.get(taskNum - 1).toString() + "\n" + line);
                        Storage.saveTaskList(taskList); //save file

                    } else if (command.equals("todo")) {
                        String wordsAfter = sc.nextLine();
                        if (wordsAfter.isEmpty()) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        Task todoTask = new Todo(wordsAfter);
                        taskList.add(todoTask);
                        System.out.print(line + "     Got it. I've added this task: "
                                + "\n       " + todoTask.toString() + "\n");
                        System.out.print("     Now you have " + taskList.size() + " tasks in the list." + "\n" + line);
                        Storage.saveTaskList(taskList); //save file

                    } else if (command.equals("deadline")) {
                        String wordsAfter = sc.nextLine();
                        int pos = wordsAfter.indexOf("/");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
                        Task dlTask = new Deadline(wordsAfter.substring(0, pos), sdf.parse(wordsAfter.substring(pos + 3)));
                        taskList.add(dlTask);
                        System.out.print(line + "     Got it. I've added this task: "
                                + "\n       " + dlTask.toString() + "\n");
                        System.out.print("     Now you have " + taskList.size() + " tasks in the list." + "\n" + line);
                        Storage.saveTaskList(taskList); //save file

                    } else if (command.equals("event")) {
                        String wordsAfter = sc.nextLine();
                        int pos = wordsAfter.indexOf("/");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
                        Task eTask = new Event(wordsAfter.substring(0, pos), sdf.parse(wordsAfter.substring(pos + 3)));
                        taskList.add(eTask);
                        System.out.print(line + "     Got it. I've added this task: "
                                + "\n       " + eTask.toString() + "\n" + "     Now you have "
                                + taskList.size() + " tasks in the list." + "\n" + line);
                        Storage.saveTaskList(taskList); //save file

                    } else if (command.equals("delete")) {
                        int taskNum = sc.nextInt();
                        System.out.println(line + "     Noted. I've removed this task: ");
                        System.out.println("       " + taskList.get(taskNum - 1).toString());
                        taskList.remove(taskNum - 1);
                        System.out.print("     Now you have " + taskList.size() + "tasks in the list.\n" + line);
                        Storage.saveTaskList(taskList); //save file

                    } else if (command.equals("find")) {
                        String wordToFind = sc.nextLine();
                        ArrayList<Task> relatedTasks = new ArrayList<>();
                        for (Task task : taskList) {
                            if (task.getDescription().contains(wordToFind)) {
                                relatedTasks.add(task);
                            }
                        }
                        ui.printFindTasks(relatedTasks);

                    } else {
                        throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException | ParseException error) {
                System.out.print(line + "     " + error.getMessage() + "\n" + line);
            }
        } while (x == 1);
    }


    @Override
    public void start(Stage stage) {
        //Step 1. Formatting the window to look as expected.

        //The container for the content of the chat to scroll.
        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        //Step 2. Formatting the window to look as expected
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;

    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    private void handleUserInput() {
        VBox dialogContainer = new VBox();
        TextField userInput = new TextField();
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }

}

