package duke;

import task.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ui {
    String underline = "____________________________________________________________\n";
    String nudeLogo = "  _   _           _      \n"
            + " | \\ | |_   _  __| | ___ \n"
            + " |  \\| | | | |/ _` |/ _ \\\n"
            + " | |\\  | |_| | (_| |  __/\n"
            + " |_| \\_|\\__,_|\\__,_|\\___|\n";
    String nukeLogo = "  _   _       _        \n"
            + " | \\ | |_   _| | _____ \n"
            + " |  \\| | | | | |/ / _ \\\n"
            + " | |\\  | |_| |   <  __/\n"
            + " |_| \\_|\\__,_|_|\\_\\___|\n";
    String dukeLogo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void showWelcome(TaskList tasks) {
        System.out.println(underline + "Hello from\n" + nudeLogo);
        System.out.println("You have " + tasks.getSize() + " task"
                + (tasks.getSize() == 1 ? " " : "s ") + "in the list.\n");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + "." + tasks.getTask(i));
        }
        System.out.println("\nWhat can i do for you?\n" + underline);
    }

    public String readCommand() throws IOException {
        return br.readLine();
    }

    public void print(String msg) {
        System.out.print(underline + msg + "\n" + underline);
    }

    public void printList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.print(underline);
    }

    public void showLine() {
        System.out.println(underline);
    }

}
