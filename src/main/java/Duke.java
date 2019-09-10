import duke.utils.AException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;

/**
 * Main driver class for Duke.
 */
public class Duke extends Application{
    
    private Ui ui;
    private Storage storage;
    private TaskList tasks;


    /**
     * Creates a Duke object that takes in a filepath to
     * the file where the list of previous tasks is stored.
     * @param filePath path to where the last saved task list is stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
//        tasks = new TaskList(new LinkedList<Task>());
            tasks = new TaskList(storage.printFileContents());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //write to file when there is a change in state

    /**
     * Runs the logic for Duke.
     */
    public String run(String input) {
        try {
//        tasks = new TaskList(new LinkedList<Task>());
            tasks = new TaskList(storage.printFileContents());
        } catch (IOException e) {
            System.out.println("File not found");
        }
//        Scanner scan = new Scanner(System.in);
        
//        ui.showWelcome();

        //LinkedList used to store all the String given by the user;
        // LinkedList<Task> li = new LinkedList<Task>();

        //read the existing task from the file and create the Duke.tasks to put
        //into the list. 

        // try{
        //     storage.printFileContents(li);
        // } catch(FileNotFoundException e){
        //     //System.out.println("File not found");
        // }

//        while (true) {
//            ui.printnewline();
//            String echo = scan.nextLine();
            String echo = input;
            Parser split = new Parser(echo);
            String error = "";

            String firstWord = split.getType();

            if (firstWord.equals("bye")) {
                // if bye, then end the program
//                ui.printline();
                return "\tBye. Hope to see you again soon!";
//                System.out.println("\tBye. Hope to see you again soon!");
//                ui.printline();
                
                
//                break;

            } else if (firstWord.equals("list")) {
                //if list, print the list of Duke.tasks
                return tasks.printList();


            } else if (firstWord.equals("done")) {
                //if done, change the task status and tell them what they have completed
                int taskNum = Integer.parseInt(split.getDesc().get(0));
                //scan.nextLine();
                //System.out.println(taskNum);
                
                int taskNumb = taskNum - 1;
                
                if (taskNumb >= tasks.size()) {
                    error = "taskDoNotExist";
                } else if (tasks.getTask(taskNumb).getIsDone()) {
                    //System.out.println("im here");
                    error = "taskAlreadyCompleted";
                } else {
                    Task change = tasks.getTask(taskNumb);
                    change.completed();
                    saving((tasks.getList()));
                    return ui.printDone(change);
                }

            } else if (firstWord.equals("delete")) {
                int taskNum = Integer.parseInt(split.getDesc().get(0));
                int taskNumb = taskNum - 1;

                if (taskNumb >= tasks.size()) {
                    error = "taskDoNotExist";
                } else {

                    tasks.remove(taskNumb);
                    saving((tasks.getList()));
                    return ui.printDelete(tasks.getTask(taskNumb), tasks.size() - 1);
                }

            } else if (firstWord.equals("find")) {
                String searchWord = split.getDesc().get(0);
                LinkedList<Task> listWord = new LinkedList<>();
                TaskList containsWord = new TaskList(listWord);
                for (int i = 0; i < tasks.size(); i++) {
                    Task taskInList = tasks.getTask(i);
                    if (taskInList.getDescription().contains(searchWord)) {
                        containsWord.add(taskInList);
                    }
                }
                return containsWord.printList();
            } else {
                String actual =  "";
                Task newTask = null;
                LinkedList<String> copy = split.getDesc();

                if (firstWord.equals("todo")) {
                    actual =  String.join(" ", copy);
                    if (actual.isEmpty()) {
                        error = "emptyToDo";
                    }
                    newTask =  new ToDo(actual);
                } else if (firstWord.equals("deadline")) {
                    String help = String.join(" ", copy);

                    String task = "";
                    String time = "";

                    if (help.isEmpty()) {
                        error = "emptyDeadline";
                    } else {
                        int slashInt = help.indexOf("/by");
                        //System.out.println(slashInt);
                        //time = help.substring();
                        if (slashInt == -1) {
                            error = "emptyBy";
                        } else {
                            time = help.substring(slashInt + 4);
                            task = help.substring(0, slashInt - 1);

                            if (task.equals(" ")) {
                                error = "emptyDeadline";
                            } else {
                                //System.out.println(task);
                                newTask = new Deadline(task, storage.dateTimeConversion(time));
                                System.out.println(time);
                            }
                        }

                    }
 
                } else if (firstWord.equals("event")) {
                    String help = String.join(" ", copy);

                    String task = "";
                    String time = "";

                    if (help.isEmpty()) {
                        error = "emptyEvent";
                    } else {
                        int slashInt = help.indexOf("/at");
                        //System.out.println(slashInt);
                        //time = help.substring();
                        if (slashInt == -1) {
                            error = "emptyAt";
                        } else {
                            time = help.substring(slashInt + 4);
                            task = help.substring(0, slashInt - 1);
                            if (task.equals(" ")) {
                                error = "emptyEvent";
                            } else {
                                //System.out.println(task);
                                newTask = new Event(task, storage.dateTimeConversion(time));
                            }
                        }

                    }
                }

                //handle all errors
                if (!error.isEmpty() || newTask == null) {
                    
                    AException ee =  new AException();
//                    ui.printline();
                    String emsg = "";
                    if (error.equals("emptyToDo")) {
                        emsg =  ee.emptyToDoException();
                    } else if (error.equals("emptyDeadline")) {
                        emsg =  ee.emptyDeadlineException();
                    } else if (error.equals("emptyBy")) {
                        emsg = ee.emptyByException();
                    } else if (error.equals("emptyEvent")) {
                        emsg = ee.emptyEventException();
                    } else if (error.equals("emptyAt")) {
                        emsg = ee.emptyAtException();
                    } else {
                        emsg = ee.dontUnderstand();
                    }

//                    ui.printline();
                    error = "";
                    return emsg;
                } else {
//                    return "here";
                    tasks.add(newTask);
                    saving((tasks.getList()));
                    return ui.takeInput(newTask, tasks.size());
                }

            }

            if (!error.isEmpty()) {
                AException ee2 =  new AException();
                ui.printline();
                if (error.equals("taskDoNotExist")) {
                    return ee2.exceedListSize();
                } else if (error.equals("taskAlreadyCompleted")) {
                    return ee2.taskAlreadyCompleted();
                }
//                ui.printline();
            }

            return "";

//            try {
//                storage.writeFile(tasks.getList());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void saving(LinkedList<Task> li){
        try {
            storage.writeFile(li);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    
//    public static void main(String[] args) {
//        new Duke("duke.txt");
//    }

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("images/DaDuke.png"));

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
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(run(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText.getText(), user),
                DialogBox.getDukeDialog(dukeText.getText(), duke)

        );
        userInput.clear();
    }

    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        sendButton.setOnMouseClicked((event) -> {
            String userWord = userInput.getText();
            if (!userWord.isEmpty()) {
                handleUserInput();
            }
        });

        userInput.setOnAction((event) -> {
            String userWord = userInput.getText();
            if(!userWord.isEmpty()) {
                handleUserInput();
            }
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.setScene(scene);
        stage.show();
    }
}
