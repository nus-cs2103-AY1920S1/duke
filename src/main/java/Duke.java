import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static String divider = "\t____________________________________________________________\n";
    private static String intro = "\t Hello! I'm Duke\n\t What can I do for you?\n";
    private static String goodbye = "\t Bye. Hope to see you again soon!\n";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        print(intro);

        Scanner scanner = new Scanner(System.in);
        Driver driver = new Driver();

        boolean run = true;
        int index;

        while (run) {
            try {
                String[] input = scanner.nextLine().split(" ");
                String command = input[0];
                String[] params = Arrays.copyOfRange(input, 1, input.length);

                switch (command) {
                case "bye":
                    print(goodbye);
                    run = false;
                    break;

                case "list":
                    print(driver.list());
                    break;

                case "done":
                    try {
                        index = Integer.valueOf(params[0]);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("You need to specify a task ID to mark as done.");
                    }
                    print(driver.markAsDone(index));
                    break;

                case "delete":
                    try {
                        index = Integer.valueOf(params[0]);
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("You need to specify a task ID to delete.");
                    }
                    print(driver.delete(index));
                    break;

                case "todo":
                    print(driver.createTodo(params));
                    break;

                case "deadline":
                    print(driver.createDeadline(params));
                    break;

                case "event":
                    print(driver.createEvent(params));
                    break;

                default:
                    throw new DukeException("I'm sorry, but I don't know what that means.");

                }
            } catch (DukeException e) {
                print(e.toString());
            }

        }

    }

    private static void print (String s) {
        System.out.print(divider);
        System.out.print(s);
        System.out.print(divider);
    }

}
