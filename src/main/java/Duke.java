import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        final String LINE = "____________________________________________________________";
        final String TAB = "    ";
        final String GOT_IT = "Got it. I've added this task:";
        String cmd;
        Scanner scan = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();

        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        printDuke(LINE);
        printDuke("Hello! I'm Duke");
        printDuke("What can I do for you?");
        printDuke(LINE);

        Boolean isNotBye = true;
        while(isNotBye) {
            cmd = scan.nextLine();
            String cmdArr[] = cmd.split(" ", 2);
            String firstWord = cmdArr[0];

            switch (firstWord) {
                case "bye":
                    printDuke(LINE);
                    printDuke("Bye. Hope to see you again soon!");
                    printDuke(LINE);
                    isNotBye = false;
                    break;

                case "list":
                    printDuke(LINE);
                    System.out.println("    Here are the tasks in your list:");
                    if (list.isEmpty()) {
                        break;
                    }
                    for (int i = 0; i < list.size(); i++) {
                        Task task = list.get(i);
                        printDuke((i + 1) + ". [" + task.getType() + "][" +
                                    task.getStatusIcon() + "]"  + task.getDesc());
                    }

                    printDuke(LINE);
                    break;

                case "done":
                    printDuke(LINE);
                    int taskNum = Integer.valueOf(cmdArr[1]);
                    list.get(taskNum - 1).isDone(true);
                    printDuke("Nice! I've marked this task as done");
                    System.out.println("    " + taskNum + ". [" + list.get(taskNum - 1).getStatusIcon() + "]"
                                        + list.get(taskNum - 1).getDesc());
                    //printDuke();
                    printDuke(LINE);
                    break;

                case "todo":
                    try {
                        String task = cmdArr[1];
                        Todo todo = new Todo(task);
                        list.add(todo);

                        //output
                        printDuke(LINE);
                        printDuke(GOT_IT);
                        printDuke(todo.toString());
                        printDuke("Now you have " + list.size() + " tasks in the list");
                        printDuke(LINE);
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        printDuke(LINE);
                        printDuke("☹ OOPS!!! The description of a todo cannot be empty.");
                        printDuke(LINE);
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
                            printDuke(LINE);
                            printDuke(GOT_IT);
                            printDuke(deadline.toString());
                            printDuke("Now you have " + list.size() + " tasks in the list");
                            printDuke(LINE);
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            printDuke(LINE);
                            printDuke("☹ OOPS!!! Please enter when the deadline is due");
                            printDuke(LINE);
                        }
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        printDuke(LINE);
                        printDuke("☹ OOPS!!! The description of a deadline cannot be empty.");
                        printDuke(LINE);
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

                            //output(LINE);
                            printDuke(LINE);
                            printDuke(GOT_IT);
                            printDuke(event.toString());
                            printDuke("Now you have " + list.size() + " tasks in the list");
                            printDuke(LINE);
                        } catch (ArrayIndexOutOfBoundsException exception) {
                            printDuke(LINE);
                            printDuke("☹ OOPS!!! Please enter when the event is happening");
                            printDuke(LINE);
                        }
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        printDuke(LINE);
                        printDuke("☹ OOPS!!! The description of an event cannot be empty.");
                        printDuke(LINE);
                    }
                    break;
                default:
                printDuke(LINE);
                printDuke("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printDuke(LINE);
            }
        }
    }

    public static void printDuke(String toPrint) {
        System.out.println("    " + toPrint);
    }
}