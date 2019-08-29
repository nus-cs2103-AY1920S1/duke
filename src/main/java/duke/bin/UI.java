package duke.bin;

import duke.bin.common.Constants;
import duke.bin.common.DukeException;
import duke.bin.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private Scanner sc;

    public UI () {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        display("What can I do for you?", "Hello! I'm Duke");
    }

    public void showError(DukeException e) {
        display(e.getMessage());
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void display(String string) {
        System.out.println(Constants.HORIZONTAL_LINE + Constants.INDENTATION + string + "\n" + Constants.HORIZONTAL_LINE);
    }

    public void display(String string, String startPhrase) {
        System.out.println(Constants.HORIZONTAL_LINE + Constants.INDENTATION + startPhrase);
        System.out.println(Constants.INDENTATION + string + "\n" + Constants.HORIZONTAL_LINE);
    }

    public void display(String string, String startPhrase, String endingPhrase) {
        System.out.println(Constants.HORIZONTAL_LINE + Constants.INDENTATION + startPhrase);
        System.out.println(Constants.INDENTATION + "  " + string);
        System.out.println(Constants.INDENTATION + endingPhrase + "\n" + Constants.HORIZONTAL_LINE);
    }

    public void displayList(ArrayList<Task> tasks) {
        System.out.println(Constants.HORIZONTAL_LINE + Constants.INDENTATION + "Here are the tasks in your list:");
        int i = 1;
        while( i < tasks.size()) {
            System.out.println(Constants.INDENTATION + i + ". " + tasks.get(i - 1));
            i++;
        }
        System.out.println(Constants.INDENTATION + i + ". " + tasks.get(i - 1));
        System.out.println(Constants.HORIZONTAL_LINE);
    }
}
