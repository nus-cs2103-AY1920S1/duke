import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greetUser();

        ArrayList<Task> tasks = new ArrayList<Task>();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                exit();
                scanner.close();
                break;
            } else if (command.equals("list")) {
                list(tasks);
            } else if (command.startsWith("done")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                done(index, tasks);
            } else {
                try {
                    addTask(command, tasks);
                } catch (DukeException exception){
                    StringBuilder sb = new StringBuilder();
                    sb.append("\t ---------------------------------------------------------- \n");
                    sb.append("\t " + exception.getMessage() + "\n");
                    sb.append("\t ---------------------------------------------------------- \n");
                    System.out.print(sb.toString());
                }
            }
        }
    }

    public static void greetUser() {
        printLine();
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        printLine();
    }

    public static void list(ArrayList<Task> tasks) {
        printLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println("\t " + i + ". " + tasks.get(i - 1));
        }
        printLine();
    }

    public static void done(int index, ArrayList<Task> tasks) {
        Task doneTask = tasks.get(index - 1);
        doneTask.markAsDone();
        printLine();
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.print("\t \t");
        System.out.println(doneTask);
        printLine();
    }

    public static void addTask(String command, ArrayList<Task> tasks) throws DukeException {
        printLine();
        Task newTask;
        if (command.startsWith("todo")) {
            String taskDetailsString = command.replaceFirst("todo", "").trim();
            if (taskDetailsString.length() == 0) {
                throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            }
            newTask = new ToDo(taskDetailsString);
        } else {
            String[] taskDetails;
            if (command.startsWith("deadline")) {
                String taskDetailsString = command.replaceFirst("deadline", "");
                if (taskDetailsString.length() == 0) {
                    throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
                }
                taskDetails = taskDetailsString.split("/by");
                if (taskDetails.length < 2) {
                    throw new DukeException("\u2639 OOPS!!! The date/time of a deadline cannot be empty.");
                }
                String taskDescription = taskDetails[0].trim();
                String taskSpecifics = taskDetails[1].trim();
                newTask = new Deadline(taskDescription, taskSpecifics);
            } else if (command.startsWith("event")){
                String taskDetailsString = command.replaceFirst("event", "");
                if (taskDetailsString.length() == 0) {
                    throw new DukeException("\u2639 OOPS!!! The description of an event cannot be empty.");
                }
                taskDetails = taskDetailsString.split("/at");
                String taskDescription = taskDetails[0].trim();
                String taskSpecifics = taskDetails[1].trim();
                if (taskDetails.length < 2) {
                    throw new DukeException("\u2639 OOPS!!! The date/time of an event cannot be empty.");
                }
                newTask = new Event(taskDescription, taskSpecifics);
            } else {
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        tasks.add(newTask);
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t \t " + newTask);
        System.out.println("\t Now you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ") + "in the " +
                "list.");
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("\t ----------------------------------------------------------");
    }

}
