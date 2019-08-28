import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
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
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/tasks.txt").run();
    }

    public void run() {
        ui.showWelcome();
        Scanner scn = new Scanner(System.in);

        String input = scn.nextLine();

        while (!input.equals("bye")) {
            try {
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    int index = 0;
                    for (Task t : tasks.taskList) {
                        System.out.println((index + 1) + "." + t.toString());
                        index++;
                    }
                } else if (input.substring(0, 4).equals("done")) {
                    int do_Index = Integer.parseInt(input.substring(5)) - 1;
                    Task chosen_Task = tasks.getTask(do_Index);
                    chosen_Task.markAsDone();
                    try {
                        storage.updateTaskInFile(do_Index + 1);
                    } catch (IOException ex) {
                        System.out.println("Can't update task in the file");
                    }

                    System.out.println("Nice! I've marked this task as done:\n  " +
                            chosen_Task.toString());
                } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
                    int delete_Index = Integer.parseInt(input.substring(7)) - 1;
                    Task chosen_Task = tasks.getTask(delete_Index);
                    tasks.removeTask(delete_Index);
                    try {
                        storage.deleteFromFile(delete_Index + 1);
                        System.out.println("Noted. I've removed this task:\n  " + chosen_Task.toString());
                        System.out.println("Now you have " + tasks.taskList.size() +
                                (tasks.taskList.size() == 1 ? " task" : " tasks") + " in the list.");
                    } catch (IOException ex) {
                        System.out.print("No saved files");
                    }

                } else {
                    if (input.substring(0, 4).equals("todo")) {
                        if (input.length() == 4) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        tasks.addTask(new ToDo(input.substring(5)));
                    } else if (input.substring(0, 5).equals("event")) {
                        if (input.length() == 5) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        int i = input.indexOf('/');
                        String dateAndTime = input.substring(i + 4);
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                        LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, format);
                        tasks.addTask(new Event(input.substring(6, i - 1), dateTime));
                    } else if (input.substring(0, 8).equals("deadline")) {
                        if (input.length() == 8) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        int i = input.indexOf('/');
                        String dateAndTime = input.substring(i + 4);
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                        LocalDateTime dateTime = LocalDateTime.parse(dateAndTime, format);
                        tasks.addTask(new Deadline(input.substring(9, i - 1), dateTime));
                    } else {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    try {
                        storage.addToFile(tasks.getTask(tasks.getNumOfTasks() - 1).toSaveString());
                    } catch (IOException ex) {
                        System.out.println("Cannot save new task in file");
                    }

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.getTask(tasks.getNumOfTasks() - 1));
                    System.out.println("Now you have " + tasks.getNumOfTasks() +
                            (tasks.getNumOfTasks() == 1 ? " task" : " tasks") + " in the list.");
                }
            } catch (DukeException ex) {
                System.out.println(ex.getMessage());
            } catch (StringIndexOutOfBoundsException ex) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            catch (DateTimeParseException ex) {
                System.out.println("Invalid date and time format\n"
                        + "Please enter date and time as 'dd/MM/yyyy HHmm'");
            }

            input = scn.nextLine();
        }
        ui.showClosing();
    }
}