/**
 * Represents an command which adds a task to the Task List.
 */
class AddCommand implements Command {
    private Action action;
    private String taskString;

    /**
     * Constructor for AddCommand, which instantiates an Action and String to create a Task.
     * 
     * @param action An action to differentiate instantiation of class during execution phase
     * @param taskString A String which indicates the task given by the user.
     */
    public AddCommand(Action action, String taskString) {
        this.action = action;
        this.taskString = taskString;
    }

    /**
     * This method executes the Add Command, which calculates which type of Task to add
     * to the Task List.
     * After adding, uiManager and storeManager will do their respective task by printing
     * and storing the information.
     * 
     * @param uiManager Ui System which scans, prints and throws DukeExceptions for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks.
     * @param storeManager Storage to serialize the TaskList into a Tasks.sav file.
     * @throws DukeException
     * @see {@link Command#execute(Ui, TaskList, Storage)}
     */
    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException {
        Task task = null;
        switch (this.action) {
        case TODO :
            task = new Todo(taskString);
            break;
        case DEADLINE :
            task = deadlineCommand(uiManager, taskList);
            break;
        case EVENT :
            task = eventCommand(uiManager, taskList);
            break;
        default :
            uiManager.throwGeneralError();
        }
        if(task == null) {
            uiManager.throwGeneralError();
        } else {
            taskList.addTask(task);
            uiManager.printAddTask(task, taskList.listSize());
            storeManager.store(taskList, uiManager);
        }
    }

    /**
     * Returns a Task, or specifically, a Deadline.
     * <p>
     * The taskArr string is split with ' /by ', which the taskArr[0] is the given
     * task, while taskArr[1] is the date, time or both.
     * Otherwise, it will throw a DukeException, if the format is wrong.
     * 
     * @param uiManager Ui System which scans, prints and throws DukeExceptions for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks.
     * @return A Deadline Task to be added.
     * @throws DukeException
     * @see {@link Deadline#Deadline(String, String)}
     */
    private Task deadlineCommand(Ui uiManager, TaskList taskList) throws DukeException {
        String[] taskArr = taskString.split(" /by ", 2);
        if (taskArr.length == 1) {
            uiManager.throwTaskFormatError(action);
            return null;
        } else {
            return new Deadline(taskArr[0], taskArr[1]);
        }
    }

    /**
     * Returns a Task, or specifically, an Event.
     * <p>
     * The taskArr string is split with ' /by ', which the taskArr[0] is the given
     * task, while taskArr[1] is the date, time or both.
     * Otherwise, it will throw a DukeException, if the format is wrong.
     * 
     * @param uiManager Ui System which scans, prints and throws DukeExceptions for the User.
     * @param taskList TaskList, an ArrayList which stores Tasks.
     * @return An Event Task to be added.
     * @throws DukeException
     * @see {@link Event#Event(String, String)}
     */
    private Task eventCommand(Ui uiManager, TaskList taskList) throws DukeException {
        String[] taskArr = taskString.split(" /at ", 2);
        if (taskArr.length == 1) {
            uiManager.throwTaskFormatError(action);
            return null;
        } else {
            return new Event(taskArr[0], taskArr[1]);
        }
    }
}