import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Duke {
    public static final String LINE = "    ____________________________________________________________\n";
    // private static ArrayList<Task> tasks;
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;
    private static TaskList tasks;


    public static void main(String[] args) {
        storage = new Storage("/Users/liuzechu/Desktop/CS2103/project_duke/duke/data/duke.txt");
        ui = new Ui();
        parser = new Parser();

        startDuke();
        ui.greet();
        ui.getUserInput(parser);
    }


    private static void exitDuke() {
        String toSave = tasks.convertTasksToString();
        try {
            storage.writeToFile(toSave);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FILE NOT SAVED PROPERLY");
        }
    }

    private static void startDuke() {
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND");
            tasks = new TaskList(new ArrayList<>());
        }
    }


}