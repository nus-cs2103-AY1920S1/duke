import java.util.ArrayList;

class Parser {
    public static Command parse(String input) {
        String[] inputs = input.trim().split(" ", 2);
        String command = inputs[0];

        switch (command) {
        case "todo":
            return new Command() {
                public void execute(ArrayList<Task> tasks, Ui ui) throws DukeException {
                    try {
                        tasks.add(new Todo(inputs[1]));
                        SaveManager.saveTasks(tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Oops! Todo task description cannot be blank.");
                    }
                }
            };
        case "deadline":
            return new Command() {
                public void execute(ArrayList<Task> tasks, Ui ui) throws DukeException {
                    try {
                        String[] strings = inputs[1].split("/by", 2);
                        String desc = strings[0].trim();
                        String by = strings[1].trim();

                        tasks.add(new Deadline(desc, by));
                        SaveManager.saveTasks(tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Oops! Deadline task description or deadline cannot be blank.");
                    }
                }
            };
        case "event":
            return new Command() {
                public void execute(ArrayList<Task> tasks, Ui ui) throws DukeException {
                    try {
                        String[] strings = inputs[1].split("/by", 2);
                        String desc = strings[0].trim();
                        String at = strings[1].trim();

                        tasks.add(new Event(desc, at));
                        SaveManager.saveTasks(tasks);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("Oops! Event task description or start time cannot be blank.");
                    }
                }
            };
        case "list":
            return new Command() {
                public void execute(ArrayList<Task> tasks, Ui ui) throws DukeException {
                    if (tasks.isEmpty()) {
                        throw new DukeException("Oops! You have no tasks yet.");
                    }
                    int index = 1;
                    for (Task task : tasks) {
                        ui.printLine(String.format("%d.%s\n", index, task));
                        index++;
                    }
                }
            };
        case "done":
            return new Command() {
                public void execute(ArrayList<Task> tasks, Ui ui) throws DukeException {
                    try {
                        int taskNumber = Integer.parseInt(inputs[1].trim());
                        Task taskDone = tasks.get(taskNumber - 1);
                        taskDone.markAsDone();
                        SaveManager.saveTasks(tasks);

                        ui.printLine("Nice! I've marked this task as done:");
                        ui.printLine(String.format("%s\n", taskDone));
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Oops! Your task cannot be found!");
                    }
                }
            };
        case "delete":
            return new Command() {
                public void execute(ArrayList<Task> tasks, Ui ui) throws DukeException {
                    try {
                        int taskNumber = Integer.parseInt(inputs[1].trim());
                        Task taskRemoved = tasks.remove(taskNumber - 1);
                        SaveManager.saveTasks(tasks);

                        ui.printLine("Got it. I've removed this task:");
                        ui.printLine(String.format("%s\n", taskRemoved));
                        ui.printLine(String.format("Now you have %d tasks in the list.\n", tasks.size()));
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("Oops! Your task cannot be found!");
                    } catch (Exception e) {
                        throw new DukeException("Oops! Please write in this format: delete <number>");
                    }
                }
            };
        case "bye":
            return new Command() {
                public void execute(ArrayList<Task> tasks, Ui ui) {
                }

                public boolean isExit() {
                    return true;
                }
            };
        default:
            return new Command() {
                public void execute(ArrayList<Task> tasks, Ui ui) throws DukeException {
                    throw new DukeException("Oops! You entered an invalid command.");
                }
            };
        }
    }
}