package duke;

import duke.logic.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;

/**
 * The main class in this programme.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        while (true) {
            Parser p = new Parser(ui.readNextLine());
            p.parse();
            String command = p.getCommand();
            String taskDetails = p.getTaskDetails();
            if (command.equals("bye")) {
                ui.showBye();
                break;
            } else if (command.equals("list")) {
                ui.showLine();
                if (tasks.getListOfTasks().isEmpty()) {
                    System.out.println("Sorry, there are no tasks in the list");
                } else {
                    System.out.println("     Here are the tasks in your list:");
                    tasks.printList();
                    ui.showLine();
                    ui.separationline();
                }
            } else if (command.equals("done")) {
                int number = Integer.parseInt(taskDetails);
                Task temp = tasks.getTask(number - 1);
                tasks.setTaskAsDone(number - 1);
                ui.showDone(temp);
                storage.write(tasks.getListOfTasks());
            } else if (command.equals("delete")) {
                int number = Integer.parseInt(taskDetails);
                Task temp = tasks.getTask(number - 1);
                tasks.deleteTask(number - 1);
                int size = tasks.getListOfTasks().size();
                ui.showDelete(temp, size);
                storage.write(tasks.getListOfTasks());
            } else if(command.equals("find")) {
                String keyword = taskDetails;
                ui.showMatchingTasks(tasks.find(keyword));
            }
            else {  //all other commands
                try {
                    if (command.equals("todo")) {
                        if (taskDetails.equals("")) { //will it be such that String[].get(1) will be zero i.e. error?
                            throw new DukeException("      â˜¹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        tasks.addTask(new Todo(taskDetails));
                    } else if (command.equals("deadline") || command.equals("event")) {
                        if(taskDetails.equals("")) {
                            throw new DukeException("      â˜¹ OOPS!!! The description of a " +
                                    command + " cannot be empty.");
                        }
                        //replace the first / so that the dates will not be split up
                        taskDetails = taskDetails.replaceFirst("/", ":");  //need to assign this to tempString so it is re-recorded
                        String[] tempStringArr = taskDetails.split(":");
                        String description = ((String) Array.get(tempStringArr, 0)).trim();  //to remove ending whitespace
                        String secondString = ((String) Array.get(tempStringArr, 1)).substring(3);
                        if (command.equals("deadline")) {
                            tasks.addTask(new Deadline(description, secondString));
                        } else {
                            tasks.addTask(new Event(description, secondString));
                        }
                    } else {//all other keywords not part of duke.Duke's duke.task handling schedule
                        throw new DukeException("      OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException de) {
                    ui.showLine();
                    System.err.println(de.getMessage());
                    ui.showLine();
                    ui.separationline();
                    continue;  //to prevent printing of below mentioned lines
                } catch (ArrayIndexOutOfBoundsException ae) {
                    ui.showLine();
                    System.err.println("      â˜¹ OOPS!!! You need to specify the " + command +
                            " time through a /by (deadline) and /at (event)");
                    ui.showLine();
                    ui.separationline();
                    continue;
                }
                Task temp = tasks.getTask(tasks.getListOfTasks().size() - 1);
                int size = tasks.getListOfTasks().size();
                ui.showAdd(temp, size);
                storage.write(tasks.getListOfTasks());
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}



