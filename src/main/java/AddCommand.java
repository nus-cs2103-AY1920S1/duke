class AddCommand implements Command {
    private Action action;
    private String taskString;

    public AddCommand(Action action, String taskString) {
        this.action = action;
        this.taskString = taskString;
    }

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

    private Task deadlineCommand(Ui uiManager, TaskList taskList) throws DukeException {
        String[] taskArr = taskString.split(" /by ", 2);
        if (taskArr.length == 1) {
            uiManager.throwTaskFormatError(action);
            return null;
        } else {
            return new Deadline(taskArr[0], taskArr[1]);
        }
    }

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