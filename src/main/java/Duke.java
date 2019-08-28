import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private TaskList taskList;
    private Storage storage;
    private UI ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new UI();
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<Task>());
        }
    }

    public void run() {
        ui.greet();
    }

    public void end() {
        ui.bye();
        storage.rewriteData();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("C:\\Users\\hooncp\\Desktop\\duke\\data\\data.txt");
        duke.run();

        Scanner scanner = new Scanner(System.in);
        String command = "";
        if (scanner.hasNextLine()) {
            command = scanner.nextLine();
        }
        while (!command.equals("") && !command.equalsIgnoreCase("bye")) {
            try {
                if (command.equalsIgnoreCase("list")) {
                    duke.taskList.list();
                } else {
                    String[] commandSplit = command.split(" ");
                    String deadline = "deadline";
                    String event = "event";
                    String todo = "todo";
                    if (commandSplit[0].equalsIgnoreCase("done")) {
                        int index = Integer.parseInt(commandSplit[1]);
                        duke.taskList.done(index);
                    } else if (commandSplit[0].equalsIgnoreCase("delete")) {
                        int index = Integer.parseInt(commandSplit[1]);
                        duke.taskList.delete(index);
                    } else if (commandSplit[0].equalsIgnoreCase(deadline)) {
                        String details = command.substring(deadline.length()).trim();
                        if (details.length() == 0) {
                            throw new InputMismatchException("The description of a deadline cannot be empty.");
                        }
                        String[] detail = details.split(" /by ");

                        Task addTask = new Deadline(detail[0], detail[1]);
                        duke.taskList.add(addTask);
                    } else if (commandSplit[0].equalsIgnoreCase(event)) {
                        String details = command.substring(event.length()).trim();
                        if (details.length() == 0) {
                            throw new InputMismatchException("The description of a event cannot be empty.");
                        }
                        String[] detail = details.split(" /at ");
                        Task addTask = new Event(detail[0], detail[1]);
                        duke.taskList.add(addTask);
                    } else if (commandSplit[0].equalsIgnoreCase(todo)) {
                        String details = command.substring(todo.length()).trim();
                        if (details.length() == 0) {
                            throw new InputMismatchException("The description of a todo cannot be empty.");
                        }
                        Task addTask = new Todo(details);
                        duke.taskList.add(addTask);
                    } else {
                        throw new InputMismatchException("I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            }
            command = scanner.nextLine();
        }
        duke.end();
    }
}