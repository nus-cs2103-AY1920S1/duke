import java.util.IllegalFormatException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static Scanner sc;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int numberOfTasks = 0;

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
        dukeOutput("Hello, this is Duke.\n" + "How may I help you?");
        readInputs();
    }

    public static void readInputs() {
        sc = new Scanner(System.in);
        String input = "";
        while (!input.equalsIgnoreCase("bye")) {
            input = sc.nextLine();
            try {
                evaluateInput(input);
            } catch (DukeException e) {
                dukeOutput(e.getMessage());
            }
        }
        sc.close();
    }

    public static void evaluateInput(String input) throws DukeException {
        if (input.equalsIgnoreCase("bye")) {
            dukeOutput("Bye. Have a nice day!");
        } else if (input.equalsIgnoreCase("list")) {
            printTasks();
        } else if (input.toLowerCase().startsWith("done")) {
            evaluateDone(input);
        } else if (input.toLowerCase().startsWith("todo")
                || input.toLowerCase().startsWith("deadline")
                || input.toLowerCase().startsWith("event")) {
            addTask(input);
        } else if (input.toLowerCase().startsWith("delete")) {
            deleteTask(input);
        } else {
            throw new DukeException("OOPS!!! I don't know what this is :(");
        }
    }

    public static void evaluateDone(String input) throws DukeException {
        String number = input.substring(4, input.length()).strip();
        if (number.isEmpty()) {
            dukeOutput("Invalid input! Mention a valid task number.");
        } else {
            try {
                int taskNumber = Integer.parseInt(number);
                if (taskNumber > numberOfTasks) {
                    dukeOutput("Task doesn't exist.");
                } else {
                    tasks.get(taskNumber - 1).markAsDone();
                    String output = "Nice! I've marked this task as done:"
                            + "\n  " + tasks.get(taskNumber - 1).toString();
                    dukeOutput(output);
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException("OOPS!!! The Done command must be followed by a valid task ID.");
            }
        }
    }

    public static void deleteTask(String input) throws DukeException {
        String[] tokens = input.split("\\s+");
        if (tokens.length != 2) {
            throw new DukeException("OOPS!!! Invalid delete command.");
        } else {
            try {
                int taskNumber = Integer.parseInt(tokens[1]);
                Task removedTask = tasks.get(taskNumber - 1);
                tasks.remove(taskNumber - 1);
                numberOfTasks--; // House-keeping
                String message = "Noted. I've deleted this task:\n"
                        + "  " + removedTask.toString()
                        + "\n" + getNumberOfTasks();
                dukeOutput(message);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! Please enter a valid number after delete.");
            }
        }
    }

    public static void addTask(String description) {
        String[] tokens = description.split("\\s+");
        String taskType = tokens[0];
        //System.out.println(taskType);
        try {
            if (taskType.equalsIgnoreCase("Event")) {
                addEvent(description);
            } else if (taskType.equalsIgnoreCase("Todo")) {
                addTodo(description);
            } else if (taskType.equalsIgnoreCase("Deadline")) {
                addDeadline(description);
            } else {
                dukeOutput("Invalid task!");
            }
        } catch (DukeException e) {
            dukeOutput(e.getMessage());
        }
    }

    public static void addEvent(String description) throws DukeException {
        int indexOfAt = description.indexOf("/at");
        if (indexOfAt == -1) {
            throw new DukeException("OOPS!!! The event description must contain a time following \"/at\"");
        }
        String desc = description.substring(5, indexOfAt).strip();
        String at = description.substring(indexOfAt + 3).strip();
        if (!Date.verifyDateTimeEvent(at)) {
            dukeOutput("Invalid format! Please stick to DD/MM/YYYY HHMM-HHMM");
        } else {
            tasks.add(new Event(desc, at));
            numberOfTasks++;
            printTaskAdded();
        }
    }

    public static void addTodo(String description) throws DukeException {
        String[] tokens = description.split("\\s+");
        StringBuilder desc = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            desc.append(tokens[i] + " ");
        }
        String newDescription = desc.toString().strip();
        tasks.add(new Todo(newDescription));
        numberOfTasks++;
        printTaskAdded();
    }

    public static void addDeadline(String description) throws DukeException {
        int indexOfBy = description.indexOf("/by");
        if (indexOfBy == -1) {
            throw new DukeException("OOPS!!! The deadline description must contain a time following \"/by\"");
        }
        String desc = description.substring(8, indexOfBy).strip();
        String by = description.substring(indexOfBy + 3).strip();
        if (!Date.verifyDateTimeDeadline(by)) {
            dukeOutput("Invalid time format! Please stick to DD/MM/YYYY HHMM");
        } else {
            tasks.add(new Deadline(desc, by));
            numberOfTasks++;
            printTaskAdded();
        }
    }

    public static void printTaskAdded() {
        String output = "Got it. I've added this task:\n"
                + "  " + tasks.get(numberOfTasks - 1).toString()
                + "\n" + getNumberOfTasks();
        dukeOutput(output);
    }

    public static void printTasks() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < numberOfTasks; i++) {
            output.append((i + 1) + ". " + tasks.get(i).toString());
            if (i != numberOfTasks - 1) {
                output.append("\n");
            }
        }
        if (output.toString().isBlank()) {
            dukeOutput("You have no tasks to do o_O!");
        } else {
            dukeOutput(output.toString());
        }
    }


    public static String getNumberOfTasks() {
        return "Now you have " + numberOfTasks + " tasks in the list.";
    }

    public static void dukeOutput(String out) {
        String bound = "_______________________________________";
        String newOutput = out.replace("\n", "\n    ");
        System.out.println("    " + bound + "\n    "
                + newOutput + "\n    " + bound);
    }

}
