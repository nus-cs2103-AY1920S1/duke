import java.text.DateFormatSymbols;
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

    static String toDateString(String dateTime) {
        try {
            SimpleDateFormat parser = null;
            SimpleDateFormat formatter = null;
            if (dateTime.split(" ").length == 2) {
                parser = new SimpleDateFormat("dd/MM/yyyy HHmm");
                formatter = new SimpleDateFormat("dd MMMM yyyy, h.mm a");
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
     * Prints message wrapped in "_______".
     *
     * @param message message before formatting with "_____".
     */
    public static void print(String message) {
        System.out.println(
                "    ____________________________________________________________\n"
                        +
                        "     " + message + "\n"
                        +
                        "    ____________________________________________________________");
    }

    /**
     * Sets the input from the user.
     */
    void setInput() {
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
        this.response = "    ______________________________________________\n"
                +
                "     " + response + "\n"
                +
                "    ______________________________________________";
    }

    public void setAddTaskResponse(Task task, int size) {
        setResponse("Got it. I've added this task:\n"
                +
                "       " + task + "\n"
                +
                "     Now you have " + size + " tasks in the list.");
    }

    public void setClashedTaskResponse(Task task, TaskList clashedTasks, int size) {
        StringBuilder clashedTasksStr = new StringBuilder();
        for (Task clashedTask : clashedTasks.getTasks()) {
            clashedTasksStr.append("    " + clashedTask + "\n");
        }
        setResponse("Take note, newly added task's schedule clashes with existing tasks:\n"
                +
                clashedTasksStr
                +
                "    I've added this task:\n"
                +
                "       " + task + "\n"
                +
                "    Now you have " + size + " tasks in the list.");
    }

    public void setListResponse(TaskList tasks) {
        setResponse(tasks.listTasks());
    }

    public void setDoneResponse(Task task) {
        setResponse("     Nice! I've marked this task as done:\n"
                +
                "     "
                +
                task);
    }

    public void setFindResponse(String result) {
        setResponse("Here are the matching tasks in your list:" + "\n" + result);
    }

    public void setExitResponse(String message) {
        setResponse(message);
    }

    public void setErrorResponse(String error) {
        setResponse(error);
    }

    public void setWelcomeResponse(String message) {
        setResponse(message);
    }

    public void setDeleteResponse(Task task, int size) {
        setResponse("Noted. I've removed this task:\n"
                +
                "     " + task + "\n"
                +
                "     Now you have "
                +
                size
                +
                " tasks in the list.");

    }

    void printResponse() {
        print(response);
    }

    String getResponse() {
        return this.response;
    }

}
