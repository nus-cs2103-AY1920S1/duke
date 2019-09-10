import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.IOException;
import java.text.ParseException;

public class Duke {

    private ArrayList<Task> list;
    private Saved savedFile;
    private Scanner scan;

    public static void main(String[] args) throws IOException, ParseException {
        new Duke("src/main/java/data.txt").run();
    }

    public Duke(String filePath) throws IOException {
        scan = new Scanner(System.in);
        savedFile = new Saved(filePath);
        list = savedFile.loadData();
    }

    public void run() throws IOException, ParseException {

        final String LINE = "____________________________________________________________";
        final String TAB = "    ";
        final String GOT_IT = "Got it. I've added this task:";
        String cmd;

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
                    if (list.isEmpty()) {
                        printDuke("There is nothing in your list.");
                        printDuke(LINE);
                        break;
                    }
                    System.out.println("    Here are the tasks in your list:");
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
                        printDuke("\u2639 OOPS!!! The description of a todo cannot be empty.");
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
                            printDuke("\u2639 OOPS!!! Please enter when the deadline is due");
                            printDuke(LINE);
                        }
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        printDuke(LINE);
                        printDuke("\u2639 OOPS!!! The description of a deadline cannot be empty.");
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
                            printDuke("\u2639 OOPS!!! Please enter when the event is happening");
                            printDuke(LINE);
                        }
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        printDuke(LINE);
                        printDuke("\u2639 OOPS!!! The description of an event cannot be empty.");
                        printDuke(LINE);
                    }
                    break;

                case "delete":
                    try {
                        int deleteNum = Integer.valueOf(cmdArr[1]);
                        Task deletedTask = list.get(deleteNum - 1);
                        list.remove(deleteNum - 1);

                        printDuke(LINE);
                        printDuke("Noted. I've removed this task:");
                        printDuke(deletedTask.toString());
                        printDuke("Now you have " + list.size() + " tasks in the list");
                        printDuke(LINE);
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        printDuke(LINE);
                        printDuke("\u2639 OOPS!!! Please add the task number");
                        printDuke(LINE);
                    }
                    break;

                default:
                printDuke(LINE);
                printDuke("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printDuke(LINE);
            }
        }
        savedFile.saveToFile(list);
    }

    public static void printDuke(String toPrint) {
        System.out.println("    " + toPrint);
    }
}