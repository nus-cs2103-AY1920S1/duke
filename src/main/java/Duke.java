import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {
    private static final String FRONTSPACES = "     "; // 5 spaces
    private static final String LINE = "    ___________________________________________________________________________\n";

    private List<Task> taskList;

    public Duke() {
        taskList = new LinkedList<>();
    }

    public static void main(String[] args) {
        new Duke().start();
    }

    private void start() {
        printWelcomeMessage();
        receiveCommand();
    }

    public void receiveCommand() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine().trim();

            try {
                if (command.startsWith("todo")) {
                    addToDo(command);
                } else if (command.startsWith("deadline")) {
                    addDeadline(command);
                } else if (command.startsWith("event")) {
                    addEvent(command);
                } else if ("list".equals(command)) {
                    list();
                } else if (command.startsWith("delete")) {
                    deleteTask(command);
                } else if (command.startsWith("done")) {
                    markTaskAsDone(command);
                } else if ("bye".equals(command)) {
                    echo("Bye. Hope to see you again!");
                    break;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                echo(e.getMessage());
            }
        }
    }

    private void addToDo(String command) throws DukeException {
        String topic = command.substring(4).trim();

        if (topic.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        addTask(new ToDo(topic));
    }

    private void addDeadline(String command) throws DukeException {
        String[] details = command.substring(8).trim().split("/by");

        if (details.length == 1 || "".equals(details[0].trim()) || "".equals(details[1].trim() == "")) {
            throw new DukeException("The description and deadline of a deadline cannot be empty.");
        }

        String topic = details[0].stripTrailing();
        String deadline = details[1].stripLeading();
        addTask(new Deadline(topic, deadline));
    }

    private void addEvent(String command) throws DukeException {
        String[] details = command.substring(5).trim().split("/at");

        if (details.length == 1 || "".equals(details[0].trim()) || "".equals(details[1].trim())) {
            throw new DukeException("The description and date of an event cannot be empty.");
        }

        String topic = details[0].stripTrailing();
        String date = details[1].stripLeading();
        addTask(new Event(topic, date));
    }

    private void addTask(Task task) {
        taskList.add(task);
        List<String> middle = new LinkedList<>();
        middle.add(String.format("  %s", task.toString()));
        echo(new String[]{"Got it. I've added this task:"},
                middle,
                new String[]{String.format("Now you have %d tasks in the list.", taskList.size())});
    }

    private void deleteTask(String command) throws DukeException {
        try {
            int index = Integer.parseInt(command.substring(6).trim());

            if (!indexIsValid(index)) {
                throw new DukeException("Index must be between 1 and the number of task added!");
            }

            Task task = taskList.remove(index - 1);

            echo("Noted. I've removed this task:",
                    String.format("  %s", task.toString()),
                    String.format("Noted you have %d tasks in the list.", taskList.size()));
        } catch (NumberFormatException e) {
            echo(String.format("%s There can only be an integer after the word \"delete\"", DukeException.PREFIX ));
        }
    }

    private void markTaskAsDone(String command) throws DukeException {
        try {
            int index = Integer.parseInt(command.substring(4).trim());

            if (!indexIsValid(index)) {
                throw new DukeException("Index must be between 1 and the number of task added!");
            }

            Task task = taskList.get(index - 1);
            task.markAsDone();
            echo("Nice! I've marked this task as done:",
                    String.format("  [\u2713] %s", task.getTitle()));
        } catch (NumberFormatException e) {
            echo(String.format("%s There can only be an integer after the word \"done\"", DukeException.PREFIX ));
        }
    }

    private void list() {
        System.out.print(LINE);
        System.out.print(FRONTSPACES + "Here are the tasks in your list:\n");
        ListIterator<Task> iterator = taskList.listIterator();

        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%s%d.%s\n", FRONTSPACES, i + 1, iterator.next());
        }

        System.out.print(LINE);
        System.out.print('\n');
    }

    /**
     * print the strings provided line by line, enclosed within two lines.
     */
    public void echo(String... strings) {
        echo(strings, null, null);
    }

    /**
     * print the strings provided followed by the list provided line by line, enclosed within two lines.
     * if list is null, then print strings only (and vice versa).
     * @param list list of items to be printed.
     * @param strings strings to be printed.
     */
    public void echo(List<?> list, String... strings) {
        echo(strings, list, null);
    }

    private void echo(String[] beginning, List<?> list, String[] ending) {
        System.out.print(LINE);

        if (beginning != null) {
            for (String string : beginning) {
                System.out.print(FRONTSPACES + string + "\n");
            }
        }

        if (list != null) {
            list.forEach(thing -> {
                System.out.print(String.format("%s%s\n", FRONTSPACES, thing));
            });
        }

        if (ending != null) {
            for (String string : ending) {
                System.out.print(FRONTSPACES + string + "\n");
            }
        }

        System.out.print(LINE);
        System.out.print("\n");
    }

    private void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        echo(logo, "Hello! I'm Duke", "What can I do for you?");
    }

    private boolean indexIsValid(int index) {
        return index > 0 && index <= taskList.size();
    }
}
