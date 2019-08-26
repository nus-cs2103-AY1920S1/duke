import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Scanner;


/**
 * Models a personal assistant chatbot for tracking tasks.
 * Allows the user to add, remove, and mark as done different kinds of tasks,
 * and stores them in a local text file.
 */
public class Duke {
    public static void main(String[] args) {
        TaskList taskList;
        Scanner sc;
        Boolean exit = false;

        // Retrieve existing tasks
        try {
            Storage storage = new Storage("tasks.txt");
            taskList = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            Ui.showLoadingError();
            taskList = new TaskList();
        }

        Ui.showWelcomeMessage();

        String nextAction = Parser.parseInput(Ui.getNextLine(), taskList);
        while (!nextAction.equals("exit")) {
            nextAction = Parser.parseInput(Ui.getNextLine(), taskList);
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
