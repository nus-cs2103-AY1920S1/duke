package duke.command;

import duke.exceptions.DukeIllegalDescriptionException;
import duke.exceptions.DukeIllegalInputException;

import java.text.SimpleDateFormat;

import java.util.Locale;
import java.util.Scanner;

/**
 * This is the class used to interact with the users and print different messages for display.
 */
public class Ui {

    private static boolean flag = true;
    private static SimpleDateFormat newFormatDeadline = new SimpleDateFormat("EEE MMM dd yyyy HH:mm a", Locale.ENGLISH);
    private static SimpleDateFormat newFormatEvStart = new SimpleDateFormat("EEE MMM dd yyyy HH:mm a", Locale.ENGLISH);
    private static SimpleDateFormat newFormatEvEnd = new SimpleDateFormat("HH:mm a", Locale.ENGLISH);

    /**
     * To set the flag to be false;
     */
    static void setFlag() {
        Ui.flag = false;
    }

    /**
     * To get the boolean value of the flag.
     *
     * @return The boolean value of the flag.
     */
    public static boolean getFlag() {
        return Ui.flag;
    }

    /**
     * To print the greet message from Duke.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    /**
     * To scan the commands inputted from the users and pass it to Parser class to take actions.
     *
     * @throws DukeIllegalInputException       Invalid input from users which can not be recognized by Duke.
     * @throws DukeIllegalDescriptionException Invalid description after commands which can not be recognized by Duke.
     */
    public void scan() throws DukeIllegalInputException, DukeIllegalDescriptionException {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine(); //Read the command
            String[] action = input.split(" ", 2); /* Break the command into array with two parts */
            try {
                Parser parser = new Parser(action);
                parser.parse();
                if (!flag) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                throw new DukeIllegalInputException();
            }
        }
    }

    /**
     * To print the response of successfully adding the task.
     */
    void printAddTask() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * To print the total number of tasks.
     */
    void printCountTasks() {
        System.out.println("Now you have " + TaskList.getList().size() + " tasks in the list.");
    }

    /**
     * To get the date format of deadline.
     *
     * @return The date format of deadline.
     */
    SimpleDateFormat getNewFormatDeadline() {
        return newFormatDeadline;
    }

    /**
     * To get the date format of the beginning of event.
     *
     * @return The date format of the beginning of event.
     */
    SimpleDateFormat getNewFormatEvStart() {
        return newFormatEvStart;
    }

    /**
     * To get the date format of the end of event.
     *
     * @return The date format of the end of event.
     */
    SimpleDateFormat getNewFormatEvEnd() {
        return newFormatEvEnd;
    }

    /**
     * To print the loading error occurred during the start of program.
     */
    public void showLoadingError() {
        System.out.println("The file is unable to load!\nCreating a new file now...");
    }
}
