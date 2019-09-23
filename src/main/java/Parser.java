public class Parser {

    /**
     * The method takes in user input and updates the tasklist based on the input commands.
     * @param input the next user input which decides what the code does.
     * @param taskList the list of tasks currently saved.
     */
    public static String Parse(String input, TaskList taskList) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        Task[] tasks = taskList.getTasks();

        int NUM_OF_TASKS;
        NUM_OF_TASKS = Task.initialiseNumOfTasks(tasks);

        while (!input.equals("bye")) {
            try {
                if (input.equals("todo") || input.equals("event") || input.equals("deadline")) {
                    throw new EmptyTaskException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
                } else {
                    assert false;
                }
                switch (input.split(" ")[0]) {
                    case "list":
                        return ui.printList(taskList);
                    case "done":
                        int index = Integer.parseInt(input.substring(5));
                        taskList.get(index - 1).markAsDone();
                        storage.save(taskList);
                        return ui.printDone(taskList.get(index - 1));
                    case "todo":
                        String tododescription = input.substring(5);
                        Task todotask = new ToDo(tododescription);
                        TaskList.add(todotask, NUM_OF_TASKS);
                        NUM_OF_TASKS++;
                        storage.save(taskList);
                        return ui.echo(taskList.get(NUM_OF_TASKS - 1), NUM_OF_TASKS);
                    case "event":
                        String eventdescription = input.split(" /at ")[0].substring(6);
                        String at = input.split(" /at ")[1];
                        if (at.contains("/") && at.contains(" ")) {
                            at = DateReader.readDate(new DateReader(at));
                        } else {}
                        Event eventtask = new Event(eventdescription, at);
                        TaskList.add(eventtask, NUM_OF_TASKS);
                        NUM_OF_TASKS++;
                        storage.save(taskList);
                        return ui.echo(taskList.get(NUM_OF_TASKS - 1), NUM_OF_TASKS);
                    case "deadline":
                        String deadlinedescription = input.split(" /by ")[0].substring(9);
                        String by = input.split(" /by ")[1];
                        if (by.contains("/") && by.contains(" ")) {
                            by = DateReader.readDate(new DateReader(by));
                        } else {}
                        Deadline deadlinetask = new Deadline(deadlinedescription, by);
                        TaskList.add(deadlinetask, NUM_OF_TASKS);
                        NUM_OF_TASKS++;
                        storage.save(taskList);
                        return ui.echo(taskList.get(NUM_OF_TASKS - 1), NUM_OF_TASKS);
                    case "delete":
                        if (taskList.get(0) == null) {
                            throw new TaskDoesNotExistException("There are no tasks to delete!");
                        } else if (input.equals("delete")) {
                            throw new UnknownInputException("☹ OOPS!!! Which task would you like to delete?");
                        } else if (taskList.get(Integer.parseInt(input.substring(7)) - 1) == null) {
                            throw new TaskDoesNotExistException("That task does not exist!");
                        } else {
                            int indextodel = Integer.parseInt(input.substring(7));
                            Task removed = taskList.delete(indextodel);
                            storage.save(taskList);
                            return ui.printDeleted(removed, --NUM_OF_TASKS);
                        }
                    case "find":
                        String keyword = input.substring(5);
                        return ui.printFound(keyword, taskList);
                    default:
                        throw new UnknownInputException("☹ OOPS!!! I'm sorry, " +
                                "but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                return ui.showError(e.getMessage());
            }
        }
        return ui.exit();
    }
}
