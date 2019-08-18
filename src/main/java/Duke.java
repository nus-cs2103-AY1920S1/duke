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
            } else if (command.equals("list")) {
                String[] text = new String[list.size() + 1];
                text[0] = "Here are the tasks in your list:";
                for (int i = 0; i < list.size(); i++) {
                    text[i + 1] = (i + 1) + "." + list.get(i);
                }
                printBlock(text);
            } else {
                String type = command.substring(0, command.indexOf(" "));
                String description = command.substring(command.indexOf(" ") + 1);
                if (type.equals("done")) {
                    int index = Integer.valueOf(description) - 1;
                    list.get(index).setDone();
                    printBlock("Nice! I've marked this task as done:", "  " + list.get(index));
                } else if (type.equals("todo")) {
                    addTask(new ToDo(description), list);
                } else if (type.equals("deadline")) {
                    int sep = description.indexOf(" /by ");
                    addTask(new Deadline(description.substring(0, sep),
                            description.substring(sep + 5)),
                            list);
                } else if (type.equals("event")) {
                    int sep = description.indexOf(" /at ");
                    addTask(new Event(description.substring(0, sep),
                                    description.substring(sep + 5)),
                            list);
                }
            }
        }
    }
}