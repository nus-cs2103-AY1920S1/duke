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
        while (!input.equalsIgnoreCase("bye") && sc.hasNextLine()) {
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
        } else {
            throw new DukeException("OOPS!!! I don't know what this is :(");
        }
    }

    public static void evaluateDone(String input) {
        String number = input.substring(4, input.length()).strip();
        if (number.isEmpty()) {
            dukeOutput("Invalid input! Mention a valid task number.");
        } else {
            int taskNumber = Integer.parseInt(number);
            if (taskNumber > numberOfTasks) {
                dukeOutput("Task doesn't exist.");
            } else {
                tasks.get(taskNumber - 1).markAsDone();
                String output = "Nice! I've marked this task as done:"
                        + "\n  " + tasks.get(taskNumber - 1).toString();
                dukeOutput(output);
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
        tasks.add(new Event(desc, at));
        numberOfTasks++;
        String output = "Got it. I've added this task:\n"
                + "  " + tasks.get(numberOfTasks - 1).toString()
                + "\n" + getNumberOfTasks();
        dukeOutput(output);
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
        String output = "Got it. I've added this task:\n"
                + "  " + tasks.get(numberOfTasks - 1).toString()
                + "\n" + getNumberOfTasks();
        dukeOutput(output);
    }

    public static void addDeadline(String description) throws DukeException {
        int indexOfBy = description.indexOf("/by");
        if (indexOfBy == -1) {
            throw new DukeException("OOPS!!! The deadline description must contain a time following \"/by\"");
        }
        String desc = description.substring(8, indexOfBy).strip();
        String by = description.substring(indexOfBy + 3).strip();
        tasks.add(new Deadline(desc, by));
        numberOfTasks++;
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
        dukeOutput(output.toString());
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
