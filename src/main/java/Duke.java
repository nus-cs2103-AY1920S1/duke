import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    /**
     * Main function for the class.
     * @param args Arguments passed when running the program.
     */
    public static void main(String[] args) {
     
        // constants and required objects
        Scanner sc = new Scanner(System.in);
        final String welcomeStr = "Hello! I'm Duke :)\n     What can I do for you?";
        final String endStr = "Bye. Hope to see you again soon!";

        // Print initial welcome string
        prettyPrint(welcomeStr);

        // run the tasks till user says bye
        TaskList tl = new TaskList();
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            try {
                process(input, tl);
            } catch (DukeException e) {
                prettyPrint(String.format("☹ OOPS!!! %s", e.getMessage()));
            } catch (Exception e) {
                prettyPrint("☹ OOPS!!! An unknown error occurred. :(");
            }
            input = sc.nextLine();
        }
        sc.close();
        
        // Print exit string
        prettyPrint(endStr);
    }

    // pretty print a string
    private static void prettyPrint(String str) {
        System.out.println("    --------------------------------------------------");
        System.out.println("     " + str);
        System.out.println("    --------------------------------------------------");
    }

    // run a process
    private static void process(String input, TaskList tl) throws DukeException {
        String command = input.split(" ")[0];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        switch (command) {
        case "list":
            tl.listTasks();
            break;
        case "done":
            if (input.split(" ").length <= 1) {
                throw new DukeException("Please enter an index to delete.");
            }
            tl.taskDone(Integer.parseInt(input.split(" ")[1]));
            break;
        case "todo":
            if (input.split(" ").length <= 1) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            ToDo todo = new ToDo(input.split(" ", 2)[1]);
            tl.addTask(todo);
            break;
        case "deadline":
            if (input.split(" ", 2).length <= 1) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String deadlineStr = input.split(" ", 2)[1];
            if (deadlineStr.split(" /by ").length <= 1) {
                throw new DukeException("The date of a deadline cannot be empty.");
            }
            String deadlineName = deadlineStr.split(" /by ")[0];
            try {
                Date deadlineDate = format.parse(deadlineStr.split(" /by ")[1]);
                Deadline deadline = new Deadline(deadlineName, deadlineDate);
                tl.addTask(deadline);
                break;
            } catch (ParseException e) {
                throw new DukeException("Please enter date in the correct format. :(");
            }
        case "event":
            if (input.split(" ").length <= 1) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            String eventStr = input.split(" ", 2)[1];
            if (eventStr.split(" /at ").length <= 1) {
                throw new DukeException("The date of an event cannot be empty.");
            }
            String eventName = eventStr.split(" /at ")[0];
            try {
                Date eventDate = format.parse(eventStr.split(" /at ")[1]);
                Event event = new Event(eventName, eventDate);
                tl.addTask(event);
                break;
            } catch (ParseException e) {
                throw new DukeException("Please enter date in the correct format. :(");
            }
        case "delete":
            if (input.split(" ").length <= 1) {
                throw new DukeException("Please provide an index to delete.");
            }
            tl.removeTask(Integer.parseInt(input.split(" ")[1]));
            break;
        default:
            prettyPrint("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            break;
        }
    }
}
