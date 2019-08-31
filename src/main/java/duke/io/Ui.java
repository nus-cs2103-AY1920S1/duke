package duke.io;

import java.util.Scanner;
import duke.task.*;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
        showLine();
        out("Hello! I'm Duke");
    }

    public String read() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

    public void out(String output) {
        System.out.println("\t" + output);
    }

    public void showLine() {
        out("=====================");
    }

    /**
     * Prints out list of tasks
     * @param taskList List of tasks
     */
    public void list(TaskList taskList) {
        if (taskList.size() == 0) {
            out("List is empty!");
        } else {
            out("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                out((i + 1) + "." + taskList.get(i));
            }
        }
    }
}
