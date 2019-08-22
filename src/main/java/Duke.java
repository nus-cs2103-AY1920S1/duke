import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greet = "____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________";
        String bye = "____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(greet);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        String echo = sc.nextLine();

        while (echo != null) {
            if (echo.equals("bye")) {
                System.out.println(bye);
                break;

            } else if (echo.equals("list")) {
                System.out.println("____________________________________________________________\n" +
                        "Here are the tasks in your list:");
                int count = 1;
                for (Task t : taskList) {
                    System.out.println(count + "." + t.toString());
                    //System.out.printf("[%s]%d. %s%n", t.getStatusIcon(), count, t.description);
                    count++;
                }
                System.out.println("____________________________________________________________\n");
                echo = sc.nextLine();

            } else if (echo.startsWith("done")) {
                int taskNo = Character.getNumericValue(echo.charAt(echo.length() - 1));
                System.out.println("____________________________________________________________\n" +
                        "Nice! I've marked this task as done: ");
                taskList.get(taskNo - 1).markAsDone(taskList.get(taskNo - 1));
                System.out.println("[" + taskList.get(taskNo - 1).getStatusIcon() + "] " + taskList.get(taskNo - 1).getDescription());
                System.out.println("____________________________________________________________\n");
                echo = sc.nextLine();

            } else if (echo.startsWith("todo")) {
                if (echo.length() == 4) {
                    System.out.println("____________________________________________________________\n" +
                            "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                            "____________________________________________________________");
                } else {
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task: ");
                    Task todoTask = new Todo(echo.substring(5));
                    taskList.add(todoTask);
                    System.out.println(todoTask.toString());
                    System.out.println("Now you have " +  taskList.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");
                }
                echo = sc.nextLine();

            } else if (echo.startsWith("deadline")) {
                if (echo.length() == 8) {
                    System.out.println("____________________________________________________________\n" +
                            "     ☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                            "____________________________________________________________");
                } else {
                    int indexOfSlash = echo.indexOf("/");
                    Task deadlineTask = new Deadline(echo.substring(9, indexOfSlash - 1), echo.substring(indexOfSlash + 4));
                    taskList.add(deadlineTask);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task: ");
                    System.out.println(deadlineTask.toString());
                    System.out.println("Now you have " +  taskList.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");
                }
                echo = sc.nextLine();

            } else if (echo.startsWith("event")) {
                if (echo.length() == 5) {
                    System.out.println("____________________________________________________________\n" +
                            "     ☹ OOPS!!! The description of an event cannot be empty.\n" +
                            "____________________________________________________________");
                } else {
                    int indexOfSlash = echo.indexOf("/");
                    Task eventTask = new Event(echo.substring(6, indexOfSlash - 1), echo.substring(indexOfSlash + 4));
                    taskList.add(eventTask);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task: ");
                    System.out.println(eventTask.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________\n");
                }
                echo = sc.nextLine();
            } else if (echo.startsWith("delete")) {
                int taskNo = Character.getNumericValue(echo.charAt(echo.length() - 1));
                System.out.println("____________________________________________________________\n" +
                        "Noted. I've removed this task: \n" +
                        taskList.get(taskNo - 1).toString() + "\n" +
                        "Now you have " + (taskList.size() - 1) + " tasks in the list.\n" +
                        "____________________________________________________________");
                taskList.remove(taskNo - 1);
                echo = sc.nextLine();

            } else {
                System.out.println("____________________________________________________________\n" +
                        "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "____________________________________________________________");
                break;
            }
        }
    }
}
