import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import tasks.Task;
import java.io.IOException;
import java.util.Scanner;

/**
 * Entry point of this project Duke. Duke is a Task manager that aims
 * to serve as an efficient way to manage our day to day tasks. It supports multiple types of task
 * such as todo reminders, tasks with a deadline and even an event.
 * The Duke task manager has many iterations, it is
 * continuously evolving and becoming smarter to cater to it users' needs.
 *
 * @author TuanDingWei
 */
public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Construct a Duke object, the Task manager bot.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("/Users/TuanDingWei/Desktop/NUS_Academia" +
                "/CS2103/Individual_project/Duke/local/Tasks.txt");
        tasks = new TaskList(storage.load());
        ui.welcome();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        String filePath = "/Users/TuanDingWei/Desktop/NUS_Academia"
                + "/CS2103/Individual_project/Duke/local/Tasks.txt";

        Scanner sc = new Scanner(System.in);
        String input = "dummy";
        while (!input.equals("bye")) {
            input = sc.nextLine();
            System.out.println(execution(input));
        }
    }

    @Override
    public void start(Stage stage) {
        Duke duke = new Duke();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Jarvis");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Contains most of the operations of the Task Manager bot.
     */
    protected String getResponse(String input) {
        return execution(input);
    }

    /**
     * Contains the bulk of the execution in responds to the user's input.
     * @param input contains the user's input
     * @return Replies from the Duke bot.
     */
    private String execution(String input) {
        String check;
        int taskCount;
        TaskManager manager = new TaskManager();

        try {
            Parser parser = new Parser(input.toLowerCase());
            String userCommand = parser.getUserCommand();
            String taskDescription = parser.getTaskDescription();
            if (userCommand.equals("hi")) {
                return ui.greeting();
            } else if (userCommand.equals("list")) {
                return ui.showListOfTask(tasks);
            } else if (userCommand.equals("done")) {
                int target = Integer.parseInt(taskDescription);
                Task completedTask;
                if (tasks.size() >= target && target > 0) {
                    completedTask = tasks.get(target - 1);
                } else {
                    throw new IndexDoesNotExistException(taskDescription + " is out of the list.");
                }
                completedTask.markAsDone();
                storage.updateLocalFile(tasks.get());
                return ui.doneAnnouncement(completedTask);
            } else if (userCommand.equals("find")) {
                if (taskDescription.equals("dummy") || taskDescription.equals("")) {
                    throw new EmptyKeywordException("You can't get me to search for nothing.\n" +
                            "Try again! :)");
                }
                return tasks.keywordSearch(taskDescription);
            } else if (userCommand.equals("delete")) {
                int target = Integer.parseInt(taskDescription);
                Task taskToDelete;
                if (tasks.size() >= target && target > 0) {
                    taskToDelete = tasks.get((target - 1));
                    tasks.removeTask((target - 1));
                } else {
                    throw new IndexDoesNotExistException(taskDescription + " is out of the list.");
                }
                storage.updateLocalFile(tasks.get());
                taskCount = tasks.size();
                return ui.deleteAnnouncement(taskToDelete, taskCount);
            } else if (userCommand.equals("clear")) {
                storage.clear();
                tasks.clear();
                assert tasks.size() == 0;
                return ui.announceCleared();
            } else if (!userCommand.equals("bye")) {
                return manager.createTask(parser, storage, ui, tasks);
            } else if (userCommand.equals("bye")) {
                return ui.sayYourGoodBye();
            }
        } catch (DukeException ex) {
            return ("OOPS!!! " + ex.getMessage() + "\n");
        }
        return "Sorry, I don't understand what you are trying to tell me.";
    }
}
