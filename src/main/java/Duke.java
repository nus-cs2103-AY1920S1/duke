import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(System.getProperty("user.dir"));
        String filePath = "." + File.separator + "data" + File.separator + "duke.txt";

        // Runs for as long as user does not terminate with "bye" command
        while(true) {

            String command = scanner.nextLine();

            try {
                // "bye" command to terminate the program
                if (command.equals("bye")) {
                    System.out.println(hLine);
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.printf("%s\n\n", hLine);
                    break;
                } else {
                    System.out.println(hLine);
                    // "list" command to list out all saved tasks
                    if (command.equals("list")) {
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 0; i < Task.itemsLst.size(); i++) {
                            System.out.printf("     %d.%s\n",
                                    i + 1, Task.itemsLst.get(i));
                        }
                    } else { // if command is neither list nor bye
                        Scanner tempSc = new Scanner(command);
                        String s = "";
                        if (tempSc.hasNext())
                            s = tempSc.next();
                        // "done" command to mark a task as done
                        if (s.equals("done")) {
                            int doneInt = tempSc.nextInt() - 1;
                            Task.itemsLst.get(doneInt).markAsDone();
                            System.out.printf("     Nice! I've marked this task as done:\n       %s\n"
                                    , Task.itemsLst.get(doneInt));
                        } else if (s.equals("delete")) {
                            // "delete" command to remove a task from the list
                            int delInt = tempSc.nextInt() - 1;
                            Task delTask = Task.itemsLst.remove(delInt);
                            System.out.printf("     Noted. I've removed this task:\n" +
                                            "       %s\n     Now you have %d tasks in the list.\n"
                                    , delTask, Task.itemsLst.size());
                        } else {
                            // Add a task to the list. A task is one of three types:
                            // ToDo, Deadline or Event task
                            if (command.startsWith("todo")) {
                                // Add a ToDo task
                                Scanner taskSc = new Scanner(command);
                                taskSc.next();
                                if (!taskSc.hasNextLine()) {
                                    throw new DukeException("     \u2639 OOPS!!! " +
                                            "The description of a todo cannot be empty.");
                                }
                                Task.itemsLst.add(new ToDo(taskSc.nextLine().substring(1), false));
                            } else if (command.startsWith("deadline")) {
                                // Add a Deadline task
                                Scanner taskSc = new Scanner(command);
                                taskSc.next();
                                String by = taskSc.nextLine().substring(1);
                                String description = "";
                                for (int i = 0; i < by.length(); i++) {
                                    char c = by.charAt(i);
                                    if (c != '/') {
                                        description += c;
                                    } else {
                                        by = by.substring(i + 4);
                                        // get rid of space
                                        description = description.substring(0, description.length() - 1);
                                        break;
                                    }
                                }
                                Task.itemsLst.add(new Deadline(description,
                                        LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")),
                                        false));
                            } else if (command.startsWith("event")) { // assume that task is an event
                                Scanner taskSc = new Scanner(command);
                                taskSc.next();
                                String at = taskSc.nextLine().substring(1);
                                String description = "";
                                for (int i = 0; i < at.length(); i++) {
                                    char c = at.charAt(i);
                                    if (c != '/') {
                                        description += c;
                                    } else {
                                        at = at.substring(i + 4);
                                        // get rid of space
                                        description = description.substring(0, description.length() - 1);
                                        break;
                                    }
                                }
                                Task.itemsLst.add(new Event(description,
                                        LocalDateTime.parse(at, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm")),
                                        false));
                            } else {
                                throw new DukeException("     \u2639 OOPS!!! I'm sorry, " +
                                        "but I don't know what that means :-(");
                            }
                            System.out.printf("     Got it. I've added this task:\n       %s\n" +
                                            "     Now you have %d tasks in the list.\n",
                                    Task.itemsLst.get(Task.itemsLst.size() - 1), Task.itemsLst.size());
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.printf("%s\n\n", hLine);

            // Save the new task list to the hard disk
            writeToFile(filePath);

        }
    }

    public static void main(String[] args) {
        String filePath = "." + File.separator + "data" + File.separator + "duke.txt";
        new Duke(filePath).run();
    }

}
