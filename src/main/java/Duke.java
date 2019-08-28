public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("../data/duke.txt").run();

    }

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
            // Exit
            if (command.equals("bye")) {
                ui.showExit();
                isExit = true;
                break;
            }
            try {
                execute(command);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

        // Save task list before exit
        try {
            storage.save(tasks);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    private void execute(String command) throws DukeException {
        if (command.equals("list")) {
            ui.showTaskList(tasks);
        } else if (command.matches("^done\\s+\\d+$")) {
            int taskId = Integer.parseInt(command.split("\\s+")[1]);
            completeTask(taskId);
        } else if (command.matches("^(todo|deadline|event).*")) {
            addTask(command);
        } else if (command.matches("^delete\\s+\\d+$")) {
            int taskId = Integer.parseInt(command.split("\\s+")[1]);
            deleteTask(taskId);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private void completeTask(int taskId) throws DukeException {
        Task task = tasks.get(taskId);
        task.markAsDone();
        ui.showTaskCompletionMsg(task);
    }

    private void addTask(String command) throws DukeException {
        String[] commandArgs = command.split("\\s+", 2);
        String taskType = commandArgs[0];

        Task newTask;

        switch (taskType) {
        case "todo":
            if (commandArgs.length < 2) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            newTask = new Todo(commandArgs[1]);
            break;
        case "deadline":
            if (commandArgs.length < 2 || commandArgs[1].equals("/by")) {
                throw new DukeException("The description and the due time of a deadline cannot be empty.");
            }
            if (commandArgs[1].startsWith("/by")) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            if (!commandArgs[1].contains("/by") || commandArgs[1].endsWith("/by")) {
                throw new DukeException("The due time of a deadline cannot be empty.");
            }
            String[] deadlineArgs = commandArgs[1].split("\\s*/by\\s*", 2);
            newTask = new Deadline(deadlineArgs[0], deadlineArgs[1]);
            break;
        case "event":
            if (commandArgs.length < 2 || commandArgs[1].equals("/at")) {
                throw new DukeException("The description and the time of an event cannot be empty.");
            }
            if (commandArgs[1].startsWith("/at")) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            if (!commandArgs[1].contains("/at") || commandArgs[1].endsWith("/at")) {
                throw new DukeException("The time of an event cannot be empty.");
            }
            String[] eventArgs = commandArgs[1].split("\\s*/at\\s*", 2);
            newTask = new Event(eventArgs[0], eventArgs[1]);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        tasks.add(newTask);
        ui.showTaskAdditionMsg(newTask, tasks);
    }

    private void deleteTask(int taskId) throws DukeException {
        Task task = tasks.remove(taskId);
        ui.showTaskDeletionMsg(task, tasks);
    }
}
