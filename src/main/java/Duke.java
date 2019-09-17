

import java.io.*;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Duke extends Application{

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private Parse parse;

    public Duke() {
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public Storage getStorage() {
        return storage;
    }

    public Ui getUi() {
        return ui;
    }

    public Parse getParse() {
        return parse;
    }

    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList();
        parse = new Parse();
        ui = new Ui();
    }

    public static void main(String[] args) throws Exception {
        Duke duke = new Duke("M:\\test.txt");
        duke.storage.load(duke);
        duke.run();
        duke.storage.save(duke.taskList);
    }
    //run the main program
    public void run() {
        this.ui.displayWelcome();
        this.parse.scan(this);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/BBUdLh1.img?h=416&w=799&m=6&q=60&u=t&o=f&l=f"));

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
