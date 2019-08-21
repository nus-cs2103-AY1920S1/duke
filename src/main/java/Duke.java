import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> myList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printMessage("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                printMessage("Bye. Hope to see you again soon!");
                break;
            } else if (input.contains("todo")) {
                String taskName;
                try {
                    taskName = input.substring(5);
                } catch (StringIndexOutOfBoundsException e) {
                    taskName = "";
                }
                try {
                    Task myTask = new Todo(taskName);
                    myList.add(myTask);
                    printMessage("Got it. I've added this task: \n  " + myTask + "\nNow you have " + pluralize("task", myList.size()) + " in the list.");
                } catch (DukeException e) {
                    printException(e);
                }
            } else if (input.contains("deadline")) {
                String taskName;
                try {
                    if (input.contains("/")) {
                        taskName = input.substring(9, input.indexOf("/") - 1);
                    } else {
                        taskName = input.substring(9);
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    taskName = "";
                }
                String param;
                if (input.contains("/by")) {
                    param = input.substring(input.indexOf("/by") + 4);
                } else {
                    param = "";
                }
                try {
                    Task myTask = new Deadline(taskName, param);
                    myList.add(myTask);
                    printMessage("Got it. I've added this task: \n  " + myTask + "\nNow you have " + pluralize("task", myList.size()) + " in the list.");
                } catch (DukeException e) {
                    printException(e);
                }
            } else if (input.contains("event")) {
                String taskName;
                try {
                    if (input.contains("/")) {
                        taskName = input.substring(6, input.indexOf("/") - 1);
                    } else {
                        taskName = input.substring(6);
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    taskName = "";
                }
                String param;
                if (input.contains("/at")) {
                    param = input.substring(input.indexOf("/at") + 4);
                } else {
                    param = "";
                }
                try {
                    Task myTask = new Event(taskName, param);
                    myList.add(myTask);
                    printMessage("Got it. I've added this task: \n  " + myTask + "\nNow you have " + pluralize("task", myList.size()) + " in the list.");
                } catch (DukeException e) {
                    printException(e);
                }
            } else if (input.equals("list")) {
                StringBuilder myBuilder = new StringBuilder();
                myBuilder.append("Here are the tasks in your list:\n");
                for (int i = 1; i <= myList.size(); i++) {
                    Task myTask = myList.get(i - 1);
                    myBuilder.append(i + "." + myTask);
                    if (i < myList.size()) {
                        myBuilder.append("\n");
                    }
                }
                printMessage(myBuilder.toString());
            } else if (input.contains("done")) {
                int myNum = Integer.valueOf(input.substring(5));
                myList.get(myNum - 1).markAsDone();
                printMessage("Nice! I've marked this task as done:\n  " + myList.get(myNum - 1));
            } else if (input.contains("delete")) {
                int myNum = Integer.valueOf(input.substring(7));
                Task myTask = myList.get(myNum - 1);
                printMessage("Noted. I've removed this task: \n  " + myTask + "\nNow you have " + pluralize("task", myList.size() - 1) + " in the list.");
                myList.remove(myNum - 1);
            } else {
                printException(new DukeException("I'm sorry, but I don't know what that means :-("));
            }
        }
        in.close();
    }

    private static void printMessage(String message) {
        String[] messages = message.split("\n");
        System.out.println("    ____________________________________________________________");
        for (String line : messages) {
            System.out.println("     " + line);
        }
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    private static void printException(DukeException e) {
        printMessage("☹ OOPS!!! " + e.getMessage());
    }

    private static String pluralize(String item, Integer quantity) {
        if (quantity == 1) {
            return "1 " + item;
        } else {
            return quantity + " " + item + "s";
        }
    }
}
