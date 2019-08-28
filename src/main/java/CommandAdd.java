public class CommandAdd extends Command {
    private String taskType;
    private String taskDescription;

    public CommandAdd(String taskType, String taskDescription) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = null;

        // create new task of specified type
        switch (taskType) {
        case "todo":
            newTask = new ToDo(taskDescription);
            break;
        case "deadline":
            String[] descriptionAndDeadline = taskDescription.split(" /by ", 2);
            if (descriptionAndDeadline.length != 2) {
                throw new DukeException("Deadline format incorrect, should be e.g. deadline description /by time");
            }
            newTask = new Deadline(
                    descriptionAndDeadline[0],
                    Parser.parseDateTimeString(descriptionAndDeadline[1])
            );
            break;
        case "event":
            String[] descriptionAndTime = taskDescription.split(" /at ", 2);
            if (descriptionAndTime.length != 2) {
                throw new DukeException("Event format incorrect, should be e.g. event description /at time");
            }
            newTask = new Event(
                    descriptionAndTime[0],
                    Parser.parseDateTimeString(descriptionAndTime[1])
            );
            break;
        }

        tasks.add(newTask);

        storage.save(tasks);

        ui.printTaskAdded(tasks, newTask);
    }
}
