package duke;

public class AddCommand extends Command {
    private static final String AT_DELIMITER = "/at";
    private static final String BY_DELIMITER = "/by";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String ADDED_TASK_STRING = "Got it. I've added this task:";

    private String taskType;

    public AddCommand(Duke duke, String input) {
        super(duke, input);
        String[] args = input.split(" ");
        this.taskType = args[0];
    }

    public void execute() {
        Task newTask;
        switch (taskType) {
            case TODO_COMMAND:
                newTask = new TodoTask(
                        input.split(TODO_COMMAND)[1]);
                break;
            case "event":
                String[] eventArgs =
                        input.split(EVENT_COMMAND)[1]
                             .split(AT_DELIMITER);
                newTask = new EventTask(
                        eventArgs[0], eventArgs[1]);
                break;
            case "deadline":
                String[] deadlineArgs =
                        input.split(DEADLINE_COMMAND)[1]
                                .split(BY_DELIMITER);
                newTask = new DeadlineTask(
                        deadlineArgs[0], deadlineArgs[1]);
                break;
            default:
                return;
        }
        duke.getTasks().addTask(newTask);
        duke.say(
                String.format("%s\n\t%s\nNow you have %d tasks in the list.",
                        ADDED_TASK_STRING, newTask, duke.getTasks().numTasks())
        );
    }

}
