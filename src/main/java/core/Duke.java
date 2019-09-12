package core;

import javafx.collections.ObservableList;
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


    public Duke() throws IOException {
        saveFile = new Storage("tasklist.txt");
        textui = new TextUi();
        tasks = new TaskList(saveFile.loadData());
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
        tasks.setTasks(saveFile.loadData());
        parser.parseTasks(input, tasks,false);
        saveFile.storeData(tasks.getTasks());
        return outContent.toString();
    }

    public ObservableList<Task> getAllTasks() {
        return tasks.getTasks();
    }
}
