import java.io.FileNotFoundException;

import java.util.ArrayList;

public class Duke {
    private Ui ui;
    private Storage storage;
    private ArrayList<Task> tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        try {
            tasks = storage.getTasks();
        } catch (FileNotFoundException e) {
            tasks = new ArrayList<>();
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new ArrayList<>();
        }

        ui.printGreeting();

        while (ui.hasInput()) {
            String input = ui.readInput();

            if (input.equals("bye")) {
                try {
                    storage.saveTasks(tasks);
                } catch (DukeException e) {
                    ui.printError(e);
                }

                ui.printExit();
                break;
            }

            String[] inputArr = input.split(" ", 2);
            String command = inputArr[0];
            Task task;
            String[] detailsArr;

            try {
                switch (command) {
                case "list":
                    ui.printTasks(tasks);
                    break;
                case "done":
                    handleDone(Integer.parseInt(inputArr[1]) - 1);
                    break;
                case "todo":
                    if (inputArr.length <= 1) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }

                    task = new Todo(inputArr[1]);
                    handleAddTask(task);
                    break;
                case "deadline":
                    if (inputArr.length <= 1) {
                        throw new DukeException("The description of a deadline cannot be empty.");
                    }

                    detailsArr = inputArr[1].split(" /by ");
                    if (detailsArr.length <= 1) {
                        throw new DukeException("Please specify the deadline with /by.");
                    }

                    task = new Deadline(detailsArr[0], detailsArr[1]);
                    handleAddTask(task);
                    break;
                case "event":
                    if (inputArr.length <= 1) {
                        throw new DukeException("The description of a event cannot be empty.");
                    }

                    detailsArr = inputArr[1].split(" /at ");
                    if (detailsArr.length <= 1) {
                        throw new DukeException("Please specify when the event is on with /at.");
                    }

                    task = new Event(detailsArr[0], detailsArr[1]);
                    handleAddTask(task);
                    break;
                case "delete":
                    handleDelete(Integer.parseInt(inputArr[1]) - 1);
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                ui.printError(e);
            }
        }
    }

    private void handleDone(int taskIndex) throws DukeException {
        if (taskIndex >= tasks.size()) {
            throw new DukeException("Task not found!");
        }

        Task task = tasks.get(taskIndex);
        task.markAsDone();
        ui.printMarkTaskAsDone(task);
    }

    private void handleDelete(int taskIndex) throws DukeException {
        if (taskIndex >= tasks.size()) {
            throw new DukeException("Task not found!");
        }

        Task task = tasks.remove(taskIndex);
        ui.printDeleteTask(task);
    }


    private void handleAddTask(Task task) {
        tasks.add(task);

        ui.printAddTask(task, tasks.size());
    }
}
