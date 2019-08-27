import java.io.FileNotFoundException;
import java.util.Scanner;

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
