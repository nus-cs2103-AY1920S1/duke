package duke;

import task.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ui {
    private String underline = "____________________________________________________________\n";
    private String doubleLine(String msg) {
        return underline + msg + "\n" + underline;
    }
    private BufferedReader br;

    /**
     * Initialises a Ui object which deals with interactions with the user.
     */
    public Ui() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Prints a welcome message for the user.
     * @param tasks tasks currently in the list.
     */
    public void showWelcome(TaskList tasks) {
        String nudeLogo = "  _   _           _      \n" +
                " | \\ | |_   _  __| | ___ \n" +
                " |  \\| | | | |/ _` |/ _ \\\n" +
                " | |\\  | |_| | (_| |  __/\n" +
                " |_| \\_|\\__,_|\\__,_|\\___|\n";
        String nukeLogo = "  _   _       _        \n" +
                " | \\ | |_   _| | _____ \n" +
                " |  \\| | | | | |/ / _ \\\n" +
                " | |\\  | |_| |   <  __/\n" +
                " |_| \\_|\\__,_|_|\\_\\___|\n";
        String dukeLogo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(underline + "Hello from\n" + nudeLogo);
        //System.out.println("You have " + tasks.getSize() + " task"
        //        + (tasks.getSize()==1?" ":"s ") + "in the list.\n");
        //for (int i = 0; i < tasks.getSize(); i++) {
        //    System.out.println((i + 1) + "." + tasks.getTask(i));
        //}
        System.out.println(tasks);
        System.out.println("What can i do for you?\n" + underline);
    }

    /**
     * Returns the command read from input.
     * @return the command read from input.
     * @throws IOException
     */
    public String readCommand() throws IOException {
        return br.readLine();
    }

    /**
     * Prints a message between two lines.
     * @param msg a message to be printed.
     */
    public void print(String msg) {
        System.out.print(underline + msg + "\n" + underline);
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println(underline);
    }

}
