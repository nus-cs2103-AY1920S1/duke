package duke.util;
import duke.util.exception.DukeException;
import duke.task.TaskList;
import java.util.Scanner;

public class Ui {
    private Scanner in;
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String welcomeMessage = "Hello from \n" + logo + "What can I do for you?";
    private String goodbyeMessage = "Bye. Hope to see you again soon!";

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    public void showMessage(UiMessage uiMessage) {
        System.out.println(uiMessage.getMessage());
    }

    public void showError(DukeException exception) {
        System.out.println(exception.getMessage());
    }

    public void showTasks(TaskList matchingTasks) {
        System.out.println("Here are the matching tasks in your list:");
        matchingTasks.printList();
    }
}
