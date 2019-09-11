import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

//import java.io.IOException;
//import java.text.ParseException;

public class Duke {

    private ArrayList<Task> list;
    private Saved savedFile;
    private Scanner scan;
    final private static String LINE = "____________________________________________________________";

    public static void main(String[] args) throws IOException, ParseException {
        new Duke("src/main/java/data.txt").run();
    }

    public Duke(String filePath) throws IOException, ParseException {
        scan = new Scanner(System.in);
        savedFile = new Saved(filePath);
        list = savedFile.loadData();
    }

    public void run() throws IOException, ParseException {

        final String GOT_IT = "Got it. I've added this task:";
        String cmd;

        printIntro();

        Boolean isNotBye = true;
        while(isNotBye) {
            cmd = scan.nextLine();
            String cmdArr[] = cmd.split(" ", 2);
            String firstWord = cmdArr[0];

            switch (firstWord) {
            case "bye":
                printLine();
                printDuke("Bye. Hope to see you again soon!");
                printLine();
                isNotBye = false;
                break;

            case "list":
                printLine();
                if (list.isEmpty()) {
                    System.out.println("    There is nothing in your list.");
                    printLine();
                    break;
                }
                printList(list);
                break;

            case "done":
                printLine();
                int taskNum = Integer.valueOf(cmdArr[1]);
                list.get(taskNum - 1).isDone(true);
                printDuke("Nice! I've marked this task as done");
                System.out.println("    " + taskNum + ". [" + list.get(taskNum - 1).getStatusIcon() + "]"
                                        + list.get(taskNum - 1).getDesc());
                printLine();
                break;

            case "todo":
                try {
                    String task = cmdArr[1];
                    Todo todo = new Todo(task);
                    list.add(todo);

                    //output
                    printLine();
                    printDuke(GOT_IT);
                    printDuke(todo.toString());
                    printDuke("Now you have " + list.size() + " tasks in the list");
                    printLine();
                } catch (ArrayIndexOutOfBoundsException exception) {
                    printLine();
                    printDuke("\u2639 OOPS!!! The description of a todo cannot be empty.");
                    printLine();
                }
                break;

            case "deadline":
                try {
                    String taskAndDate = cmdArr[1];
                    String dl[] = taskAndDate.split("/", 2);
                    try {
                        String by = dl[1].substring(3);
                        Deadline deadline = new Deadline(dl[0], by);
                        list.add(deadline);
                        //output
                        printDuke(GOT_IT);
                        printDuke(deadline.toString());
                        printDuke("Now you have " + list.size() + " tasks in the list");
                        printLine();
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        printLine();
                        printDuke("\u2639 OOPS!!! Please enter when the deadline is due");
                        printLine();
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    printLine();
                    printDuke("\u2639 OOPS!!! The description of a deadline cannot be empty.");
                    printLine();
                    }
                break;

            case "event":
                try {
                    String eventAndDate = cmdArr[1];
                    String e[] = eventAndDate.split("/", 2);
                    try {
                        String at = e[1].substring(3);
                        Event event = new Event(e[0], at);
                        list.add(event);

                        //output
                        printLine();
                        printDuke(GOT_IT);
                        printDuke(event.toString());
                        printDuke("Now you have " + list.size() + " tasks in the list");
                        printLine();
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        printLine();
                        printDuke("\u2639 OOPS!!! Please enter when the event is happening");
                        printLine();
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    printLine();
                    printDuke("\u2639 OOPS!!! The description of an event cannot be empty.");
                    printLine();
                }
                break;

            case "delete":
                try {
                    int deleteNum = Integer.valueOf(cmdArr[1]);
                    Task deletedTask = list.get(deleteNum - 1);
                    list.remove(deleteNum - 1);

                    printLine();
                    printDuke("Noted. I've removed this task:");
                    printDuke(deletedTask.toString());
                    printDuke("Now you have " + list.size() + " tasks in the list");
                    printLine();
                } catch (ArrayIndexOutOfBoundsException exception) {
                    printLine();
                    printDuke("\u2639 OOPS!!! Please add the task number");
                    printLine();
                }
                break;

            case "find":
                String keyword = cmdArr[1];
                ArrayList<Task> matchingTasks = new ArrayList<>();
                for (Task task : list) {
                    if (task.getDesc().contains(keyword)) {
                        matchingTasks.add(task);
                    }
                }
                printList(matchingTasks);
                break;
            default:
                printLine();
                printDuke("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printLine();
            }
        }
        savedFile.saveToFile(list);
    }

    public static void printIntro() {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        printLine();
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        printLine();
    }
    public static void printLine() {
        System.out.println("    " + LINE);
    }

    public static void printDuke(String toPrint) {
        System.out.println("    " + toPrint);
    }
    public void printList(ArrayList<Task> list) {
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println("    " + (i + 1) + ". " + task.toString());

        }

        printLine();
    }
}