package duke.util;
import duke.exception.DukeException;
import duke.task.TaskList;
import java.util.Scanner;

public class Ui {
    private Scanner in;
    private String loadingErrorMessage = "Oh no! Could not load tasks from file :(";
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

    public void showLoadingError() {
        System.out.println(loadingErrorMessage);
    }

    public void showWelcome() {
        System.out.println(welcomeMessage);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showLine() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    public void showError(DukeException exception) {
        System.out.println(exception.getMessage());
    }

    public void showGoodbye() {
        System.out.println(goodbyeMessage);
    }

    public void showTasks(TaskList matchingTasks) {
        System.out.println("Here are the matching tasks in your list:");
        matchingTasks.printList();
    }
}
