package core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import notes.Note;
import Task.JsonParser;
import tasklist.Task;
import tasklist.TaskList;
import parser.Parser;

import java.io.*;

/**
 * Main class of the program.
 * starts the application and receives use input.
 */
public class Duke {
    private TaskList tasks;
    private JsonParser jsonParser;
    private static String FILEPATH = "../SaveFile.json";


    public Duke() throws IOException {
        jsonParser = new JsonParser(FILEPATH);
        tasks = jsonParser.readData();
    }

    /**
     * Main link between the ui and the core program.
     * @param input user input
     * @return returns collection of print statements to be displayed by chatbot
     * @throws IOException when there is a loading error
     */
    public String getResponse(String input) throws IOException {
        Parser parser = new Parser();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        tasks = jsonParser.readData();
        parser.parseTasks(input, tasks);
        jsonParser.storeData(tasks);
        return outContent.toString();
    }

    public ObservableList<Task> getUiTasks() {
        return FXCollections.observableArrayList(tasks.getUiTaskList());
    }
    public ObservableList<Note> getNotes(){ return FXCollections.observableArrayList(tasks.getUiNoteList());}
}
