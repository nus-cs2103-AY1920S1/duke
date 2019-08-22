import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printMessage(List.of("Hello! I'm Duke", "What can I do for you?"));

        List<Task> tasks = new ArrayList<>();
        Parser parser = new Parser();
        parser.register("todo", ToDo.getCommand(tasks));
        parser.register("deadline", Deadline.getCommand(tasks));
        parser.register("event", Event.getCommand(tasks));
        parser.register("list", new ListCommand(tasks));
        parser.register("done", new Done(tasks));
        parser.register("delete", new Delete(tasks));
        parser.register("bye", new Bye());
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        while (!exit && input.hasNext()) {
            String line = input.nextLine();
            String[] words = line.split(" ");
            try {
                Command command = parser.parse(words);
                printMessage(command.run(words));
                exit = command.isExit();
            } catch (DukeException e) {
                printMessage(List.of(e.getMessage()));
            }
        }
    }

    private static void printMessage(List<String> messages) {
        System.out.println("    ____________________________________________________________");
        for (String message : messages) {
            System.out.println("     " + message);
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
