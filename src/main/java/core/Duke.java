package core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import storage.JsonParser;
import tasklist.Task;
import ui.TextUi;
import storage.Storage;
import tasklist.TaskList;
import parser.Parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Main class of the program.
 * starts the application and receives use input.
 */
public class Duke{
    private Storage saveFile;
    private TextUi textui;
    private TaskList tasks;
    private JsonParser jsonParser;


    public Duke() throws IOException {
        //saveFile = new Storage("tasklist.txt");
        textui = new TextUi();
        //tasks = new TaskList(saveFile.loadData());
        jsonParser = new JsonParser("SaveFile.json");
        tasks = jsonParser.readData();
    }

    public Duke(String filepath) throws IOException {
        saveFile = new Storage(filepath);
        textui = new TextUi();
        tasks = new TaskList(saveFile.loadData());
    }

    public String getResponse(String input) throws IOException {
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        //tasks.setTasks(saveFile.loadData());
        tasks = jsonParser.readData();
        parser.parseTasks(input, tasks,false);
        //saveFile.storeData(tasks.getTasks());
        /**
         * testing json
         */
        jsonParser.storeData(tasks);
        return outContent.toString();
    }

    public ObservableList<Task> getAllTasks() {
        return FXCollections.observableArrayList(tasks.getTasks());
    }
}
