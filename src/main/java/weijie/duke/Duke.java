package weijie.duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import weijie.duke.commands.CommandHistory;
import weijie.duke.commands.CommandList;
import weijie.duke.commands.TaskCommandFactory;
import weijie.duke.db.FileSystemStorage;
import weijie.duke.db.ITaskStorage;
import weijie.duke.exceptions.DukeIllegalArgumentException;
import weijie.duke.exceptions.DukeIoException;
import weijie.duke.models.Task;
import weijie.duke.controllers.MainWindowController;
import weijie.duke.repos.IRepository;
import weijie.duke.repos.TaskRepo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class Duke extends Application {
    private String filePath;
    private TaskCommandFactory taskCommandFactory;
    private CommandHistory history;

    public Duke() {
        this.filePath = "data/duke.txt";
    }

    @Override
    public void start(Stage primaryStage) {

        try {
            initDependencies();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/MainWindow.fxml"));
            fxmlLoader.setControllerFactory(controllerFactory);

            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (DukeIoException | IOException | DukeIllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void initDependencies() throws DukeIoException, DukeIllegalArgumentException {
        ITaskStorage storage = new FileSystemStorage(filePath);
        IRepository<Task> repo = new TaskRepo(storage);
        history = new CommandHistory();

        taskCommandFactory = new TaskCommandFactory(CommandList.getCommandMap());
        taskCommandFactory.registerDependency(repo);
        taskCommandFactory.registerDependency(history);
    }

    private Callback<Class<?>, Object> controllerFactory = type -> {
        try {
            if (type.isAssignableFrom(MainWindowController.class)) {
                return type.getConstructor(TaskCommandFactory.class, CommandHistory.class)
                        .newInstance(taskCommandFactory, history);
            }
            return type.getConstructor().newInstance();

        } catch (IllegalAccessException | InstantiationException
                | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    };
}
