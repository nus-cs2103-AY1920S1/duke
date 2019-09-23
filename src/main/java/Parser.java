public class Parser {

    /**
     * The method takes in user input and updates the tasklist based on the input commands.
     * @param input the next user input which decides what the code does.
     * @param taskList the list of tasks currently saved.
     */
    public static String parse(String input, TaskList taskList, Ui ui) {
        Task[] tasks = taskList.getTasks();
        int NUM_OF_TASKS = TaskList.initialiseNumOfTasks(tasks);

        try {
            if (input.equals("bye")) {
                return ui.exit();
            } else if (input.equals("todo") || input.equals("event") || input.equals("deadline")) {
                throw new EmptyTaskException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
            }

            switch (input.split(" ")[0]) {
            case "list":
                return ui.printList(taskList);
            case "done":
                return done(input, taskList, ui);
            case "todo":
                return todo(input, taskList, ui, NUM_OF_TASKS);
            case "event":
                return event(input, taskList, ui, NUM_OF_TASKS);
            case "deadline":
                return deadline(input, taskList, ui, NUM_OF_TASKS);
            case "delete":
                return delete(input, taskList, ui, NUM_OF_TASKS);
            case "find":
                return find(input, taskList, ui);
            default:
                throw new UnknownInputException("☹ OOPS!!! I'm sorry, " +
                        "but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    private static String done(String input, TaskList taskList, Ui ui) throws DukeException{
        if (input.equals("done")) {
            throw new UnknownInputException("☹ OOPS!!! Which task would you like to mark as done?");
        } else {
            int index = Integer.parseInt(input.substring(5));
            taskList.get(index - 1).markAsDone();
            return ui.printDone(taskList.get(index - 1));
        }
    }

    private static String todo(String input, TaskList taskList,
                                   Ui ui, int NUM_OF_TASKS) {
        String tododescription = input.substring(5);
        Task todotask = new ToDo(tododescription);
        TaskList.add(todotask, NUM_OF_TASKS);
        NUM_OF_TASKS++;
        return ui.echo(taskList.get(NUM_OF_TASKS - 1), NUM_OF_TASKS);
    }

    private static String event(String input, TaskList taskList,
                                   Ui ui, int NUM_OF_TASKS) throws DukeException {
        String eventdescription = input.split(" /at ")[0].substring(6);
        String at = input.split(" /at ")[1];
        if (at.contains("/") && at.contains(" ")) {
            at = DateReader.readDate(new DateReader(at));
        }
        Event eventtask = new Event(eventdescription, at);
        TaskList.add(eventtask, NUM_OF_TASKS);
        NUM_OF_TASKS++;
        return ui.echo(taskList.get(NUM_OF_TASKS - 1), NUM_OF_TASKS);
    }

    private static String deadline(String input, TaskList taskList,
                                 Ui ui, int NUM_OF_TASKS) throws DukeException {
        String deadlinedescription = input.split(" /by ")[0].substring(9);
        String by = input.split(" /by ")[1];
        if (by.contains("/") && by.contains(" ")) {
            by = DateReader.readDate(new DateReader(by));
        }
        Deadline deadlinetask = new Deadline(deadlinedescription, by);
        TaskList.add(deadlinetask, NUM_OF_TASKS);
        NUM_OF_TASKS++;
        return ui.echo(taskList.get(NUM_OF_TASKS - 1), NUM_OF_TASKS);
    }

    private static String delete(String input, TaskList taskList,
                                 Ui ui, int NUM_OF_TASKS) throws DukeException {
        if (taskList.get(0) == null) {
            throw new TaskDoesNotExistException("There are no tasks to delete!");
        } else if (input.equals("delete")) {
            throw new UnknownInputException("☹ OOPS!!! Which task would you like to delete?");
        } else if (taskList.get(Integer.parseInt(input.substring(7)) - 1) == null) {
            throw new TaskDoesNotExistException("That task does not exist!");
        } else {
            int indextodel = Integer.parseInt(input.substring(7));
            Task removed = taskList.delete(indextodel);
            return ui.printDeleted(removed, --NUM_OF_TASKS);
        }
    }

    private static String find(String input, TaskList taskList, Ui ui) {
        String keyword = input.substring(5);
        return ui.printFound(keyword, taskList);
    }

}
