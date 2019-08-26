import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {
        Task[] tasks = new Task[100];

        readSavedList(tasks);

        greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        int x = 0;

        while (!input.equals("bye")) {
            try {
                if (input.equals("todo") || input.equals("event") || input.equals("deadline")) {
                    throw new EmptyTaskException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
                } else {
                    switch (input.split(" ")[0]) {
                    case "list":
                        printList(tasks);
                        input = sc.nextLine();
                        break;
                    case "done":
                        int index = Integer.parseInt(input.substring(5));
                        tasks[index - 1].markAsDone();
                        printDone(tasks[index - 1]);
                        Task.save(tasks);
                        input = sc.nextLine();
                        break;
                    case "todo":
                        tasks[x] = new ToDo(input.substring(5));
                        x++;
                        echo(tasks[x - 1], x);
                        Task.save(tasks);
                        input = sc.nextLine();
                        break;
                    case "event":
                        tasks[x] = new Event(input.split(" /at ")[0].substring(6), input.split(" /at ")[1]);
                        x++;
                        echo(tasks[x - 1], x);
                        Task.save(tasks);
                        input = sc.nextLine();
                        break;
                    case "deadline":
                        tasks[x] = new Deadline(input.split(" /by ")[0].substring(9), input.split(" /by ")[1]);
                        x++;
                        echo(tasks[x - 1], x);
                        Task.save(tasks);
                        input = sc.nextLine();
                        break;
                    case "delete":
                        ArrayList<Task> taskarraylist = new ArrayList<Task>(Arrays.asList(tasks));
                        int indextodel = Integer.parseInt(input.substring(7));
                        Task removed = taskarraylist.remove(indextodel - 1);
                        printDeleted(removed, --x);
                        tasks = new Task[100];
                        for (int i = 0; i < taskarraylist.size(); i++) {
                            tasks[i] = taskarraylist.get(i);
                        }
                        Task.save(tasks);
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

    public static void printList(Task[] tasklist) {
        String indentedline = "    ____________________________________________________________";
        System.out.println(indentedline);
        if (tasklist[0] == null) {
            System.out.println("     There are no tasks in your list.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            int y = 0;
            while (tasklist[y] != null) {
                System.out.println("     " + (y + 1) + ". " + tasklist[y]);
                y++;
            }
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

    public static void printDeleted(Task t, int x) {
        String indentedline = "    ____________________________________________________________";
        System.out.println(indentedline);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + t);
        if (x == 1) {
            System.out.println("     Now you have " + x + " task in the list.");
        } else {
            System.out.println("     Now you have " + x + " tasks in the list.");
        }
        System.out.println(indentedline);
    }

    public static void readFileContents(String filePath, Task[] tasks) throws FileNotFoundException {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            int x = 0;
            while (s.hasNext()) {
                String currtask = s.nextLine();
                tasks[x] = Task.readString(currtask);
                x++;
            }
        } catch (UnknownInputException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void readSavedList(Task[] tasks) {
        try {
            readFileContents("DukeList.txt", tasks);
        } catch (FileNotFoundException e) {
        }
    }

}