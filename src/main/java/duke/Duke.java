package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for Duke app.
 */
public class Duke extends Application {
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    protected Storage storage;
    protected TaskList tasks;

    @Override
    public void start(Stage primaryStage) throws Exception {
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadFromSaveFile());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (IOException e) {
        }

        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/view/MainWindow.fxml"));

        primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWING, new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent window) {
                MainWindowController controller = loader.getController();
                controller.setDuke(Duke.this);
            }
        });

        // Create the Pane and all Details
        VBox root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Duke");
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