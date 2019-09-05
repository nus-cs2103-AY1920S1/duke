package weijie.duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import weijie.duke.commands.CommandList;
import weijie.duke.commands.TaskCommandFactory;
import weijie.duke.db.Storage;
import weijie.duke.exceptions.DukeIoException;
import weijie.duke.models.Task;
import weijie.duke.controllers.MainWindowController;
import weijie.duke.repos.IRepository;
import weijie.duke.repos.TaskRepo;
import weijie.duke.views.Ui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


public class Duke extends Application {
    private String filePath;
    private TaskCommandFactory taskCommandFactory;

    public Duke() {
        this.filePath = "data/duke.txt";
    }

    @Override
    public void start(Stage primaryStage) {
        initDependencies();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/MainWindow.fxml"));
            fxmlLoader.setControllerFactory(controllerFactory);

            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initDependencies() {
        Ui ui = new Ui();

        try {
            Storage storage = new Storage(filePath);
            IRepository<Task> repo = new TaskRepo(storage);

            taskCommandFactory = new TaskCommandFactory(CommandList.getCommandMap());
            taskCommandFactory.registerDependency(repo);

        } catch (DukeIoException e) {
            ui.printError(e);
            ui.printExit();
        }
    }

    private Callback<Class<?>, Object> controllerFactory = type -> {
        try {
            if (type.isAssignableFrom(MainWindowController.class)) {
                return type.getConstructor(TaskCommandFactory.class).newInstance(taskCommandFactory);
            }
            return type.getConstructor().newInstance();

        } catch (IllegalAccessException | InstantiationException
                | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    };
}
