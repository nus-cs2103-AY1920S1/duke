import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Prints a block which contains all the lines given.
     * @param text a list of lines to be printed inside the block
     */
    private static void printBlock(String... text) {
        String horizontalLine = "____________________________________________________________";
        String indentation = "    ";
        System.out.println(indentation + horizontalLine);
        for (String line: text) {
            System.out.println(indentation + " " + line);
        }
        System.out.println(indentation + horizontalLine + "\n");
    }

    /**
     * Adds a task to the list and prints information.
     * @param task the new task to be added
     * @param list a list of tasks
     */
    private static void addTask(Task task, ArrayList<Task> list) {
        list.add(task);
        printBlock("Got it. I've added this task:", "  " + task,
                String.format("Now you have %d task%s in the list.",
                        list.size(), list.size() > 1 ? "s" : ""));
    }

    /**
     * Parses the command that typed by the user.
     * @param command the command typed by the user
     * @param list a list stores all the tasks
     * @throws IllegalIndexOfTaskException if the index provided is illegal
     * @throws IllegalDescriptionException if the description of the task is illegal
     * @throws IllegalCommandException is the command is illegal
     */
    private static void parseCommand(String command, ArrayList<Task> list)
            throws IllegalIndexOfTaskException, IllegalDescriptionException,
                    IllegalCommandException {
        if (command.equals("list")) {
            String[] text = new String[list.size() + 1];
            text[0] = "Here are the tasks in your list:";
            for (int i = 0; i < list.size(); i++) {
                text[i + 1] = (i + 1) + "." + list.get(i);
            }
            printBlock(text);
        } else {
            int indexOfSpace = command.indexOf(' ');
            //if there is no space, assume that the string is a command type
            if (indexOfSpace == -1) {
                indexOfSpace = command.length();
            }
            //seperate command and description of the task
            String type = command.substring(0, indexOfSpace);
            String description = command.substring(indexOfSpace).strip();

            if (type.equals("done")) {
                try {
                    int index = Integer.valueOf(description) - 1;
                    list.get(index).setDone();
                    printBlock("Nice! I've marked this task as done:", "  " + list.get(index));
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new IllegalIndexOfTaskException(
                            "Please provide an valid index of the task.");
                }
            } else if (type.equals("delete")) {
                try {
                    int index = Integer.valueOf(description) - 1;
                    Task removedTask = list.remove(index);
                    printBlock("Noted. I've removed this task:", "  " + removedTask,
                            String.format("Now you have %d task%s in the list.",
                                    list.size(), list.size() > 1 ? "s" : ""));
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new IllegalIndexOfTaskException(
                            "Please provide an valid index of the task.");
                }
            } else if (type.equals("todo")) {
                addTask(new ToDo(description.strip()), list);
            } else if (type.equals("deadline")) {
                int sep = description.indexOf("/by");
                if (sep == -1) {
                    throw new IllegalDescriptionException("The format of deadline task is wrong.");
                }
                addTask(new Deadline(description.substring(0, sep).strip(),
                                description.substring(sep + 3).strip()),
                        list);
            } else if (type.equals("event")) {
                int sep = description.indexOf("/at");
                if (sep == -1) {
                    throw new IllegalDescriptionException("The format of event task is wrong.");
                }
                addTask(new Event(description.substring(0, sep).strip(),
                                description.substring(sep + 3).strip()),
                        list);
            } else {
                throw new IllegalCommandException(
                        "I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    /** main method*/
    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke";
        String question = "What can I do for you?";
        printBlock(greeting, question);

        ArrayList<Task> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                printBlock("Bye. Hope to see you again soon!");
                break;
            }
            try {
                parseCommand(command, list);
            } catch (Exception e) {
                printBlock("OPPS!!! " + e.getMessage());
            }
        }
    }
}