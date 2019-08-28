import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Main function for the class.
     * @param args Arguments passed when running the program.
     */
    public static void main(String[] args) {
     
        Scanner sc = new Scanner(System.in);

        // Print initial welcome string
        prettyPrint("Hello! I'm Duke :)\n     What can I do for you?");

        // initialize a TaskList
        TaskList tl = new TaskList();

        // Run till user types "bye"
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                tl.listTasks();
            } else {
                String command = input.split(" ")[0];
                switch (command) {
                case "list":
                    tl.listTasks();
                    break;
                case "done":
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]);
                        tl.taskDone(index);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        prettyPrint("☹ OOPS!!! Please enter an index to delete.");
                    } finally {
                        break;
                    }
                case "todo":
                    try {
                        ToDo todo = new ToDo(input.split(" ", 2)[1]);
                        tl.addTask(todo);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        prettyPrint("☹ OOPS!!! The description of a todo cannot be empty.");
                    } finally {
                        break;
                    }
                case "deadline":
                    try {
                        String deadlineStr = input.split(" ", 2)[1];
                        String deadlineName = deadlineStr.split(" /by ")[0];
                        String deadlineDate = deadlineStr.split(" /by ")[1];
                        Deadline deadline = new Deadline(deadlineName, deadlineDate);
                        tl.addTask(deadline);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        prettyPrint("☹ OOPS!!! The description of a deadline cannot be empty.");
                    } finally {
                        break;
                    }
                case "event":
                    try {
                        String eventStr = input.split(" ", 2)[1];
                        String eventName = eventStr.split(" /at ")[0];
                        String eventDate = eventStr.split(" /at ")[1];
                        Event event = new Event(eventName, eventDate);
                        tl.addTask(event);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        prettyPrint("☹ OOPS!!! The description of an event cannot be empty.");
                    } finally {
                        break;
                    }
                case "delete":
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]);
                        tl.removeTask(index);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        prettyPrint("☹ OOPS!!! Please provide an index to delete.");
                    } finally {
                        break;
                    }
                default:
                    prettyPrint("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    break;
                }
            }
            input = sc.nextLine();
        }

        // Print exit string
        prettyPrint("Bye. Hope to see you again soon!");
    }

    // pretty print a string
    static void prettyPrint(String str) {
        System.out.println("    --------------------------------------------------");
        System.out.println("     " + str);
        System.out.println("    --------------------------------------------------");
    }
}
