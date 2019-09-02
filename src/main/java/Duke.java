import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printMessage(List.of("Hello! I'm Duke", "What can I do for you?"));

        Storage storage = new Storage("tasks.txt");
        List<Task> tasks;
        try {
            tasks = storage.load();
        } catch (IOException e) {
            if (!(e instanceof NoSuchFileException)) {
                printMessage(List.of("Could not load tasks. Proceeding may overwrite old tasks."));
                e.printStackTrace();
            }
            tasks = new ArrayList<>();
        }
        Parser parser = new Parser();
        parser.register("todo", ToDo.getCommand(tasks, storage));
        parser.register("deadline", Deadline.getCommand(tasks, storage));
        parser.register("event", Event.getCommand(tasks, storage));
        parser.register("list", new ListCommand(tasks));
        parser.register("done", new DoneCommand(tasks, storage));
        parser.register("delete", new DeleteCommand(tasks, storage));
        parser.register("bye", new ByeCommand());
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        while (!exit && input.hasNext()) {
            String[] words = input.nextLine().split(" ");
            try {
                Command command = parser.parse(words);
                printMessage(command.run(words));
                exit = command.isExit();
            } catch (DukeException e) {
                printMessage(List.of(e.getMessage()));
            } catch (IOException e) {
                e.printStackTrace();
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
