import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

public class Ui {

    private static boolean flag = true;
    private static SimpleDateFormat newFormatDeadline = new SimpleDateFormat("EEE MMM dd yyyy HH:mm a", Locale.ENGLISH);
    private static SimpleDateFormat newFormatEvStart = new SimpleDateFormat("EEE MMM dd yyyy HH:mm a", Locale.ENGLISH);
    private static SimpleDateFormat newFormatEvEnd = new SimpleDateFormat("HH:mm a", Locale.ENGLISH);

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public void scan() throws DukeIllegalInputException, DukeIllegalDescriptionException{
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

    public void printAddTask() {
        System.out.println("Got it. I've added this task:");
    }

    public void printCountTasks() {
        System.out.println("Now you have " + TaskList.getList().size() + " tasks in the list.");
    }

    public static void setFlag(boolean flag) {
        Ui.flag = flag;
    }

    public static boolean getFlag() {
        return Ui.flag;
    }

    public SimpleDateFormat getNewFormatDeadline() {
        return newFormatDeadline;
    }

    public SimpleDateFormat getNewFormatEvStart() {
        return newFormatEvStart;
    }

    public SimpleDateFormat getNewFormatEvEnd() {
        return newFormatEvEnd;
    }

    public void showLoadingError() {
        System.out.println("The file is unable to load!\nCreating a new file now...");
    }
}
