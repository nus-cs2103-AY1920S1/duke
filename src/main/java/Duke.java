public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException ex) {
            ui.showLoadingError(ex);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        while (true) {
            try {
                ui.showLine();
                String instruction = ui.readCommand();
                if (!parseInstruction(tasks, instruction)) {
                    break;
                }
            } catch (DukeException ex) {
                ui.showErrorMessage(ex);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    private void printTotalTask() {
        boolean isPlural = tasks.size() > 1;
        System.out.println("Now you have " + tasks.size() + " task"
                + (isPlural ? "s" : "") + " in the list.");
    }

    private void parseDeadlineTask(String instruction) throws DukeException{
        try {
            String suffix = instruction.split(" ", 2)[1];
            String description = suffix.split(" /by ", 2)[0];
            String by = suffix.split(" /by ", 2)[1];

            Task task = new Deadline(description, by);
            tasks.addTask(task);
            storage.save(tasks);

            ui.showMessage("Got it. I've added this task:");
            ui.showMessage("  " + task);
            printTotalTask();
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Deadline task formatting error.");
        }
    }

    private void parseTodoTask(String instruction) throws DukeException {
        try {
            String description = instruction.split(" ", 2)[1];

            Task task = new Todo(description);
            tasks.addTask(task);
            storage.save(tasks);

            ui.showMessage("Got it. I've added this task:");
            ui.showMessage("  " + task);
            printTotalTask();
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    private void parseEventTask(String instruction) throws DukeException {
        try {
            String suffix = instruction.split(" ", 2)[1];
            String description = suffix.split(" /at ", 2)[0];
            String at = suffix.split(" /at ", 2)[1];

            Task task = new Event(description, at);
            tasks.addTask(task);
            storage.save(tasks);

            ui.showMessage("Got it. I've added this task:");
            ui.showMessage("  " + task);
            printTotalTask();
        } catch(IndexOutOfBoundsException ex) {
            throw new DukeException("Event task formatting error.");
        }
    }

    private void parseDoneInstruction(TaskList tasks, String instruction) throws DukeException {
        int index = Integer.parseInt(instruction.split(" ")[1]);
        tasks.markAsDone(index);
        storage.save(tasks);

        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage("  " + tasks.get(index));
    }

    private void parseDeleteInstruction(TaskList tasks, String instruction) throws DukeException {
        int index = Integer.parseInt(instruction.split(" ")[1]);
        Task task = tasks.deleteTask(index);
        storage.save(tasks);

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        printTotalTask();
    }

    private boolean parseInstruction(TaskList tasks, String instruction) throws DukeException{
        if (instruction.equals("bye")) {
            ui.showMessage("Bye. Hope to see you again soon!");
            return false;
        } else if (instruction.equals("list")) {
            ui.showMessage("Here are the task in your list:");
            for (int i = 1; i <= tasks.size(); ++i) {
                ui.showMessage(i + "." + tasks.get(i));
            }
        } else if (instruction.matches("^done \\d+$")) {
            parseDoneInstruction(tasks, instruction);
        } else if (instruction.matches("^delete \\d+$")) {
            parseDeleteInstruction(tasks, instruction);
        } else if (instruction.startsWith("deadline")) {
            parseDeadlineTask(instruction);
        } else if  (instruction.startsWith("todo")) {
            parseTodoTask(instruction);
        } else if (instruction.startsWith("event")) {
            parseEventTask(instruction);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return true;
    }
}
