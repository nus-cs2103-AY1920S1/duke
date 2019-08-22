import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Task[] data = new Task[100];
        int x = 0;

        while (!input.equals("bye")) {
            try {
                if (input.equals("todo") || input.equals("event") || input.equals("deadline")) {
                    throw new EmptyTaskException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
                } else {
                    switch (input.split(" ")[0]) {
                        case "list":
                            printList(data);
                            input = sc.nextLine();
                            break;
                        case "done":
                            int index = Integer.parseInt(input.substring(5));
                            data[index - 1].markAsDone();
                            printDone(data[index - 1]);
                            input = sc.nextLine();
                            break;
                        case "todo":
                            data[x] = new ToDo(input.substring(5));
                            x++;
                            echo(data[x - 1], x);
                            input = sc.nextLine();
                            break;
                        case "event":
                            data[x] = new Event(input.split(" /at ")[0].substring(6), input.split(" /at ")[1]);
                            x++;
                            echo(data[x - 1], x);
                            input = sc.nextLine();
                            break;
                        case "deadline":
                            data[x] = new Deadline(input.split(" /by ")[0].substring(9), input.split(" /by ")[1]);
                            x++;
                            echo(data[x - 1], x);
                            input = sc.nextLine();
                            break;
                        default:
                            throw new UnknownInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException e) {
                String indentedline = "    ____________________________________________________________";
                System.out.println(indentedline);
                System.out.println("     " + e.getMessage());
                System.out.println(indentedline);
                input = sc.nextLine();
            }
        }
        exit();
    }

    public static void greet() {
        echo("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void exit() {
        echo("Bye. Hope to see you again soon!");
    }

    public static void echo(String s) {
        String[] arr = s.split("\n");
        String indentedline = "    ____________________________________________________________";
        System.out.println(indentedline);
        for (String str : arr) {
            System.out.println("     " + str);
        }
        System.out.println(indentedline);
    }

    public static void echo(Task t, int x) {
        if (x == 1) {
            echo("Got it. I've added this task: \n  " + t + "\nNow you have " + x + " task in the list.");
        } else {
            echo("Got it. I've added this task: \n  " + t + "\nNow you have " + x + " tasks in the list.");
        }
    }

    public static void printList(Object[] array) {
        String indentedline = "    ____________________________________________________________";
        System.out.println(indentedline);
        System.out.println("     Here are the tasks in your list:");
        int y = 0;
        while (array[y] != null) {
            System.out.println("     " + (y+1) + ". " + array[y]);
            y++;
        }
        System.out.println(indentedline);
    }

    public static void printDone(Task t) {
        String indentedline = "    ____________________________________________________________";
        System.out.println(indentedline);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + t);
        System.out.println(indentedline);
    }
}
