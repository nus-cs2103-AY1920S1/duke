package duke.ui;

import duke.task.Task;
import duke.logic.TaskList;

import java.text.ParseException;
import java.time.DateTimeException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

/**
 * User interaction class to take care of all user interactions in the program.
 */
public class Ui {
    private String input = "";
    private String[] inputArr;
    private String response;
    private Scanner sc = new Scanner(System.in);

    /**
     * Converts a date and time string from dd/mm/yy hh:mm format to Day of Month, Year hh.mm(am/pm) format.
     *
     * @param dateTime date and time in dd/mm/yy hh:mm format.
     * @return Day of Month, Year hh.mm(am/pm) format. Example: 2nd of December 2019, 6pm.
     */

    public static String toDateString(String dateTime) {
        try {
            SimpleDateFormat parser = null;
            SimpleDateFormat formatter = null;
            if (dateTime.split(" ").length == 2) {
                parser = new SimpleDateFormat("dd/MM/yyyy HHmm");
                formatter = new SimpleDateFormat("dd MMMM yyyy, h.mma");
            } else {
                parser = new SimpleDateFormat("dd/MM/yyyy");
                formatter = new SimpleDateFormat("dd MMMM yyyy");
            }
            Date convertedDate = parser.parse(dateTime);
            String output = formatter.format(convertedDate);
            String[] outputArr = output.split(" ");
            int day = Integer.valueOf(outputArr[0]) % 10;
            if (day > 3 || day == 0) {
                outputArr[0] += "th of";
            } else if (day == 1) {
                outputArr[0] += "st of";
            } else if (day == 2) {
                outputArr[0] += "nd of";
            } else if (day == 3) {
                outputArr[0] += "rd of";
            }
            String result = String.join(" ", outputArr);
            return result;
        } catch (DateTimeException | ParseException e) {
            return dateTime;
        }
    }

    /**
     * Prints message wrapped in "-------".
     *
     * @param message message before formatting with "-----".
     */
    public static void print(String message) {
        System.out.println(
                "    ------------------------------------------------------------\n"
                        +
                        "     " + message + "\n"
                        +
                        "    ------------------------------------------------------------");
    }

    /**
     * Sets the input from the user.
     */
    public void setInput() {
        String newInput = sc.nextLine();
        this.input = newInput;
        this.inputArr = newInput.split(" ");
    }

    /**
     * Returns input from the user.
     *
     * @return input from the user.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Returns input of user as a String array.
     *
     * @return input of user as a String array.
     */
    public String[] getInputArr() {
        return this.inputArr;
    }

    private void setResponse(String response) {
        this.response = response + "\n";

    }

    /**
     * Sets response for Add command.
     *
     * @param task task added.
     * @param size size of TaskList after adding task.
     */
    public void setAddTaskResponse(Task task, int size) {
        setResponse("Got it. I've added this task:\n"
                +
                task + "\n"
                +
                "Now you have " + size + " tasks in the list.");
    }

    /**
     * Sets response for anomaly with adding tasks with clashed schedules.
     *
     * @param task         added task.
     * @param clashedTasks TaskList of clashed tasks.
     * @param size         size of TaskList after adding task.
     */
    public void setClashedTaskResponse(Task task, TaskList clashedTasks, int size) {
        StringBuilder clashedTasksStr = new StringBuilder();
        for (Task clashedTask : clashedTasks.getTasks()) {
            clashedTasksStr.append("    " + clashedTask + "\n");
        }
        setResponse("Take note, newly added task's schedule clashes with existing tasks:\n"
                +
                clashedTasksStr
                +
                "I've added this task:\n"
                +
                task + "\n"
                +
                "Now you have " + size + " tasks in the list.");
    }

    /**
     * Sets response for List command.
     *
     * @param list list of task.
     */
    public void setListResponse(String list) {
        setResponse("Here are your tasks:\n " + list);
    }

    /**
     * Sets response for Done command.
     *
     * @param task task to be marked as done.
     */
    public void setDoneResponse(Task task) {
        setResponse("Nice! I've marked this task as done:\n"
                +
                task);
    }

    /**
     * Sets response for Find command.
     *
     * @param result result of the find command.
     */
    public void setFindResponse(String result) {
        setResponse("Here are the matching tasks in your list:" + "\n" + result);
    }

    /**
     * Sets response for Exit command.
     *
     * @param message exit message.
     */
    public void setExitResponse(String message) {
        setResponse(message);
    }

    /**
     * Sets response for Error messages.
     *
     * @param error error message.
     */
    public void setErrorResponse(String error) {
        setResponse(error);
    }

    /**
     * Sets response for Welcome message.
     *
     * @param message welcome message.
     */
    public void setWelcomeResponse(String message) {
        setResponse(message);
    }

    /**
     * Sets response for Delete message.
     *
     * @param task task to be deleted.
     * @param size size of TaskList after deletion.
     */
    public void setDeleteResponse(Task task, int size) {
        setResponse("Noted. I've removed this task:\n"
                +
                task + "\n"
                +
                "Now you have "
                +
                size
                +
                " tasks in the list.");

    }

    /**
     * Prints response in CLI.
     */
    public void printResponse() {
        print(response);
    }

    /**
     * Gets the UI response.
     *
     * @return UI response attribute.
     */
    public String getResponse() {
        return this.response;
    }

}
