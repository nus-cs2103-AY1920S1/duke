import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates a Ui to deal with user interactions.
 */
public class Ui {
    Scanner scanner;
    private boolean isExit;
    private Parser parser;
    private StringBuilder string;

    /**
     * Creates a Ui object tagged with scanner, whether the program is exited, and parser.
     */
    public Ui() {
        scanner = new Scanner(System.in);
        isExit = false;
        parser = new Parser();
    }

    /**
     * Displays a welcome message.
     */
    public String showWelcome() {
        return "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Executes the given user commands.
     *
     * @param tasks Tasks to mark as completed, add to, delete, or view based on user commands.
     */
    public String execute(String command, TaskList tasks) {
        string = new StringBuilder();
        if (command.equals("bye")) {
            isExit = true;

        } else if (command.equals("list")) {
            string.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.getTasks().size(); i++) {
                string.append(String.format("     %d.%s\n", i + 1, tasks.getTasks().get(i).toString()));
            }

        } else {
            String[] commands = parser.parse(command);
            if (commands[0].equals("delete")) {
                string.append("Noted. I've removed this task:\n");
                int index = Integer.valueOf(commands[1]) - 1;
                string.append("       " + tasks.getTasks().get(index).toString() + "\n");
                tasks.delete(index);
                string.append(String.format("Now you have %d tasks in the list.\n", tasks.getTasks().size()));

            } else if (commands[0].equals("done")) {
                int index = Integer.valueOf(commands[1]) - 1;
                Task t = tasks.getTasks().get(index);
                tasks.done(index);
                string.append("Nice! I've marked this task as done:\n");
                string.append("       " + t.toString() + "\n");

            } else if (commands[0].equals("find")) {
                ArrayList<Task> results = new ArrayList<>();
                for (Task task : tasks.getTasks()) {
                    if (task.getName().contains(commands[1])) {
                        results.add(task);
                    }
                }
                string.append("Here are the matching tasks in your list:\n");
                for (int i = 0; i < results.size(); i++) {
                    string.append(String.format("     %d.%s\n", i + 1, results.get(i).toString()));
                }

            } else {
                String taskName = "";
                for (int j = 1; j < commands.length; j++) {
                    if (j != commands.length - 1) {
                        taskName += commands[j] + " ";
                    } else {
                        taskName += commands[j];
                    }
                }

                Task task;
                if (commands[0].equals("todo") || commands[0].equals("deadline") || commands[0].equals("event")) {
                    if (commands.length == 1) {
                        if (commands[0].equals("event")) {
                            string.append("OOPS!!! The description of an event cannot be empty.\n");
                        } else {
                            string.append(String.format("OOPS!!! The description of a %s cannot be empty.\n",
                                    commands[0]));
                        }

                    } else {
                        if (commands[0].equals("todo")) {
                            task = new Todo(taskName);

                        } else if (commands[0].equals("deadline")) {
                            String[] details = taskName.split(" /by ");
                            task = new Deadline(details[0], details[1]);

                        } else {
                            String[] details = taskName.split(" /at ");
                            task = new Event(details[0], details[1]);

                        }

                        tasks.add(task);
                        string.append("Got it. I've added this task:\n");
                        string.append("       " + task.toString() + "\n");
                        if (tasks.getTasks().size() == 1) {
                            string.append("Now you have 1 task in the list.\n");

                        } else {
                            string.append(String.format("Now you have %d tasks in the list.\n", tasks.getTasks()
                                    .size()));

                        }
                    }

                } else {
                    string.append("OOPS!!! I'm sorry, but I don't know what that means :-(\n");

                }
            }
        }
        return string.toString();
    }

    /**
     * Displays error messages.
     *
     * @param error Name of error.
     */
    public void showError(String error) {
        if (error.equals("load")) {
            System.out.println("OOPS!!! The list of tasks cannot be loaded.");
        } else if (error.equals("filepath")) {
            System.out.println("OOPS!!! There is something wrong with the filepath.");
        } else if (error.equals("store")) {
            System.out.println("OOPS!!! There is something wrong with the file.");
        }
    }

    /**
     * Returns if program is to be exited.
     *
     * @return Boolean depending on whether program is to be exited.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Shows exit message for stopping program.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!\n";
    }
}
