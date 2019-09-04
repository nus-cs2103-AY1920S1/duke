package duke.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import duke.tasklist.TaskList;
import duke.task.Task;
import duke.exception.DukeException;

public class UI {

    private TaskList taskList;
    private BufferedReader reader;

    public UI(TaskList list) {
        this.taskList = list;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readLine() throws IOException {
        return reader.readLine();
    }

    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void printBlank() {
        System.out.println();
    }

    public void printTask(Task task) {
        System.out.println("       " + task);
    }

    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        printLine();
        printBlank();
    }

    public void printError(DukeException error) {
        printLine();
        System.out.println(error);
        printLine();
        printBlank();
    }

    public void printTaskAdd(Task task) {
        printLine();
        System.out.println("     Got it. I've added this task:");
        printTask(task);
        if (taskList.size() == 1) {
            System.out.println("     Now you have " + taskList.size() + " task in the list");
        } else {
            System.out.println("     Now you have " + taskList.size() + " tasks in the list");
        }
        printLine();
        printBlank();
    }

    public void printTaskDelete(Task task) {
        printLine();
        System.out.println("     Noted. I have removed this task:");
        printTask(task);
        if (taskList.size() == 1) {
            System.out.println("     Now you have " + taskList.size() + " task in the list");
        } else {
            System.out.println("     Now you have " + taskList.size() + " tasks in the list");
        }
        printLine();
        printBlank();
    }

    public void printTaskDone(Task task) {
        printLine();
        System.out.println("     Nice! I've marked this task as done:");
        printTask(task);
        printLine();
        printBlank();
    }

    public void printList() {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println("     " + i + ". " + taskList.get(i - 1));
        }
        printLine();
        printBlank();
    }
    public void printBye() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
        printBlank();
    }

}
