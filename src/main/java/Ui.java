import java.util.Scanner;

public class Ui {
    // Strings that Duke will output
    public static final String LONG_LINE = "____________________________________________________________";
    public static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String BYE_STR = "Bye. Hope to see you again soon!";
    public static final String LIST_STR = "Here are the tasks in your list:";
    public static final String DONE_STR = "Nice! I've marked this task as done:";

    private Scanner input = new Scanner(System.in);

    public void printGoodbye() {
        printWithLongLines(BYE_STR);
        input.close();
    }

    public void printGreeting() {
        printWithLongLines(GREETING);
    }

    public String nextLine() {
        return input.nextLine();
    }

    public void ackDeletion(Task deletedTask, int listSize) {
        printWithLongLines(
            "Noted. I've removed this task:\n"
            + deletedTask
            + "\nNow you have " 
            + listSize
            + " tasks in the list."
        );
    }

    public void ackDone(Task doneTask) {
        printWithLongLines(
            DONE_STR
            + "\n"
            + doneTask
        );
    }

    public void ackAddition(Task newTask, int listSize) {
        printWithLongLines(
            "Got it. I've added this task:\n"
            + newTask
            + "\nNow you have " 
            + listSize
            + " tasks in the list."
        );
    }

    public void printList(TaskList taskList) {
        String wholeList = LIST_STR + "\n";
        
        for (int i = 0; i < taskList.size(); i++) {
            wholeList += String.valueOf(i + 1)
                + "."
                + taskList.get(i);
            
            if (i < taskList.size() - 1) {
                wholeList += "\n";
            }
        }

        printWithLongLines(wholeList);
    }

    public void adviseDateFormat(String format) {
        printWithLongLines("Required date format: " + format);
    }

    public void printException(Exception e) {
        printWithLongLines(e.getMessage());
    }

    private void printWithLongLines(String stringToPrint) {
        System.out.println(
            LONG_LINE
            + "\n"
            + stringToPrint
            + "\n"
            + LONG_LINE
            + "\n"
        );
    }
}