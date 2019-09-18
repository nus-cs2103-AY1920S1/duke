package core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import storage.JsonParser;
import tasklist.Task;
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
    private TaskList tasks;
    private JsonParser jsonParser;


    public Duke() throws IOException {
        jsonParser = new JsonParser("src/main/resources/data/SaveFile.json");
        tasks = jsonParser.readData();
    }

    public String getResponse(String input) throws IOException {
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        tasks = jsonParser.readData();
        parser.parseTasks(input, tasks,false);
        jsonParser.storeData(tasks);
        return outContent.toString();
    }

    public ObservableList<Task> getAllTasks() {
        return FXCollections.observableArrayList(tasks.getTasks());
    }
}
