package duke;

import duke.commands.Command;

import java.util.Scanner;

public class Duke {
    private static final int NUMBER_TASKS = 100;
    private static final String LINE_BREAK = "______________________________________________________________________";
    private static final String HELLO_STRING = "Hello! I'm duke.Duke\nWhat can I do for you?";
    private static final String BYE_STRING = "Bye. Hope to see you again soon!";

    private Scanner scanner;
    private boolean running;
    private TaskList tasks;

    public Duke() {
        scanner = new Scanner(System.in);
        running = true;
        tasks = new TaskList(NUMBER_TASKS);
    }

    public void start() {
        String input;
        say(HELLO_STRING);
        while (running) {
            input = scanner.nextLine().trim();
            try {
                Command command = Command.create(this, input);
                command.execute();
            } catch (DukeException e) {
                say(e.getMessage());
            }
        }
    }

    public void exit() {
        this.running = false;
        scanner.close();
        say(BYE_STRING);
    }

    public void say(String sequence) {
        String indented = sequence.replaceAll("(?m)^", "\t\t");
        System.out.printf(
                "\t%s\n%s\n\t%s\n",
                LINE_BREAK,
                indented,
                LINE_BREAK
        );
    }

    public TaskList getTasks() {
        return this.tasks;
    }
}

