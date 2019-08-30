package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for Duke app.
 */
public class Duke extends Application {
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Duke when save file path is known.
     *
     * @param filePath file path
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFromSaveFile());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (IOException e) {
        }
    }

    /**
     * Constructor for Duke when save file path is unknown (will use the default location).
     *
     */
    public Duke() {
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadFromSaveFile());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (IOException e) {
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/MainWindow.fxml"));

        // Create the Pane and all Details
        VBox root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Duke");
        loader.<MainWindowController>getController().setDuke(this);
        primaryStage.show();
    }

    public List<ToDo> getTodos() {
        List<ToDo> todos = new ArrayList<>();
        for (Task t : tasks.getList()) {
            if (t instanceof ToDo) {
                todos.add((ToDo) t);
            }
        }
        return todos;
    }

    public List<Deadline> getDeadlines() {
        List<Deadline> deadlines = new ArrayList<>();
        for (Task t : tasks.getList()) {
            if (t instanceof Deadline) {
                deadlines.add((Deadline) t);
            }
        }
        return deadlines;
    }

    public List<Event> getEvent() {
        List<Event> events = new ArrayList<>();
        for (Task t : tasks.getList()) {
            if (t instanceof Event) {
                events.add((Event) t);
            }
        }
        return events;
    }

    public List<Task> getTasks() {
        return tasks.getList();
    }
}