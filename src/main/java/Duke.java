import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        ui.greet();

        while (true) {
            String command = sc.nextLine();//read user input
            if (command.equals("bye")) {
                ui.exit();
                break;
            } else if (command.equals("list")) {
                ui.list(tasks);
            } else if (command.length() > 4 && command.substring(0, 4).equals("done")) {
                if (command.charAt(4) != ' ') {
                    try {
                        Task task = tasks.add(command);
                        ui.add(task, tasks);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        Task task = tasks.done(Integer.valueOf(command.substring(5)));
                        ui.done(task);
                        try {
                            storage.writeToFile(tasks.generateInfo());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                    } catch (Exception e) {
                        System.out.println("    ____________________________________________________________\n     " +
                                "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                                "\n    ____________________________________________________________\n");
                    }
                }
            } else if (command.length() > 6 && command.substring(0, 6).equals("delete")) {
                if (command.charAt(4) != ' ') {
                    try {
                        Task task = tasks.add(command);
                        ui.add(task, tasks);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        Task task = tasks.delete(Integer.valueOf(command.substring(7)));
                        ui.delete(task, tasks);
                        try {
                            storage.writeToFile(tasks.generateInfo());
                        } catch (IOException e) {
                            System.out.println("Something went wrong: " + e.getMessage());
                        }
                    } catch (Exception e) {
                        System.out.println("    ____________________________________________________________\n     " +
                                "\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(" +
                                "\n    ____________________________________________________________\n");
                    }
                }
            } else {
                try {
                    Task task = tasks.add(command);
                    ui.add(task, tasks);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                try {
                    storage.writeToFile(tasks.generateInfo());
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        new Duke("../data/duke.txt").run();
    }
}
