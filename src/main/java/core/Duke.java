package core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import storage.JsonParser;
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
    private String filepath = "../SaveFile.json";


    public Duke() throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(filepath);
            fileWriter.write("{}");
            fileWriter.close();
        }

        jsonParser = new JsonParser(filepath);
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
        parser.parseTasks(input, tasks,false);
        jsonParser.storeData(tasks);
        return outContent.toString();
    }

    public ObservableList<Task> getAllTasks() {
        return FXCollections.observableArrayList(tasks.getTasks());
    }
}
