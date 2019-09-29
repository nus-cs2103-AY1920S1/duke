import java.util.Arrays;

/**
 * Parser is a class that aids in parsing through the user input
 * and understanding the commands and executing them accordingly.
 * Commands are passed into a method and appropriate actions are
 * taken to create tasks, remove tasks, or add tasks etc.
 */
public class Parser {
    private Ui ui;
    private String taskType;
    private String taskDesc;
    private boolean isExit = false;
    Priority taskPriority = Priority.LOW;

    /**
     * Constructs a Parser object.
     * Parser object is constructed and passed a Ui object to
     * help with printing the outputs back to the user.
     * @param ui ui object for user interaction
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses through the user's input and makes changes to the
     * task list accordingly.
     * First token of the string is checked to see if it is a valid
     * instruction. If invalid an IncorrectInputException is thrown.
     * If a task is given without description a NoDescriptionException
     * is thrown. Otherwise the task is processed accordingly.
     * @param input String input from the user.
     * @param taskList The current list of tasks
     * @throws DukeException Throws exception to handle incorrect user input
     */
    public String parseCommand(String input, TaskList taskList) throws DukeException {
        assert input != null;
        preProcessInput(input);

        if (taskType.equals("bye")) {

            isExit = true;
            return ui.showGoodbye();

        } else if (taskType.equals("list")) {

            return ui.showTaskList(taskList);

        } else if (taskType.equals("priority")) {

            String output = ui.showPriorityTaskList(
                    taskList.getPriorityTaskList());
            taskList.updateQueue();
            return output;

        } else if (taskDesc.isEmpty()) {

            throw new NoDescriptionException(
                    ":( OOPS!!! The description of " + taskType + " cannot be empty.");

        } else if (taskType.equals("done")) {

            return doneCommand(taskDesc, taskList);

        } else if (taskType.equals("delete")) {

            return deleteCommand(taskDesc, taskList);

        } else if (taskType.equals("find")) {

            return ui.showFoundTasks(taskList.searchFor(taskDesc));

        } else if (taskType.equals("todo")) {

            return todoCommand(taskDesc, taskList);

        } else if (taskType.equals("deadline")) {

            return deadlineCommand(taskDesc, taskList);

        } else  if (taskType.equals("event")) {

            return eventCommand(taskDesc, taskList);

        } else {
            assert false;
            return "assert testing"; //Returning a string since method must return string
        }
    }

    /**
     * Processes the input to assign priority to the task if necessary.
     * Parses through the string and checks if priority is assigned. If priority
     * is not assigned, it is set to low by default
     * @param input Input string from teh user
     * @throws DukeException To check if input is correct
     */
    private void preProcessInput(String input) throws DukeException {
        String[] inputArr = input.split(" ");

        if (inputArr[0].equals("high")) {
            taskPriority = Priority.HIGH;
            inputArr = Arrays.copyOfRange(inputArr,1, inputArr.length);
        } else if (inputArr[0].equals("medium")) {
            taskPriority = Priority.MEDIUM;
            inputArr = Arrays.copyOfRange(inputArr,1, inputArr.length);
        } else if (inputArr[0].equals("low")) {
            inputArr = Arrays.copyOfRange(inputArr,1, inputArr.length);
        } else {}

        //Extracting task type and description from input
        taskType = inputArr[0];
        taskDesc = getDesc(inputArr);

        if (!correctInput(taskType)) {
            throw new IncorrectInputException(
                    ":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Performs actions to do a task.
     * Takes in the index of a task, and extracts it from the task list. Checks if
     * the task is done. If the task is not done the Task#doTask is called to change
     * the necessary attributes of the task, and the output from the UI is returned.
     * @param taskDesc String of the index of the task
     * @param taskList List of the tasks
     * @return String String output from the UI
     */
    private String doneCommand(String taskDesc, TaskList taskList) {
        int taskNum = Integer.parseInt(taskDesc);
        Task task = taskList.getTask(taskNum);

        if (task.isDone()) {
            return ui.showTaskAlreadyDone(task);
        } else {
            task.doTask();
            return ui.showTaskDone(task);
        }
    }

    /**
     * Performs actions to delete a task.
     * Takes in the index of a task, and extracts it from the task list. Task#deleteTask
     * is called to delete the task. Output from teh UI is returned.
     * @param taskDesc String of the index of the task
     * @param taskList List of the tasks
     * @return String String output from the UI
     */
    private String deleteCommand(String taskDesc, TaskList taskList) {
        int taskNum = Integer.parseInt(taskDesc);
        Task task = taskList.getTask(taskNum);
        taskList.deleteTask(taskNum);

        return ui.showTaskDeleted(task, taskList);
    }

    /**
     * Adds a To do task to the task list.
     * Takes in the description of the to do task, and task list. Creates a to do task
     * object, and adds it to the task list. Returns the output from the UI.
     * @param taskDesc Description of task
     * @param taskList List of tasks
     * @return String String output from the UI
     */
    private String todoCommand(String taskDesc, TaskList taskList) {
        Task newTodo = new Todo(taskDesc, taskPriority);
        taskList.addTask(newTodo);

        return ui.showTaskAdded(newTodo, taskList);
    }

    /**
     * Adds a deadline task to the task list.
     * Takes in the description of the deadline task, and task list. Creates a deadline task
     * object, and adds it to the task list. Returns the output from the UI.
     * @param taskDesc Description of task
     * @param taskList List of tasks
     * @return String String output from the UI
     */
    private String deadlineCommand(String taskDesc, TaskList taskList) {
        String[] taskDescArr = taskDesc.split(" /");

        Task newDeadline = new Deadline(taskDescArr[0], taskDescArr[1], taskPriority);

        taskList.addTask(newDeadline);

        return ui.showTaskAdded(newDeadline, taskList);
    }

    /**
     * Adds a event task to the task list.
     * Takes in the description of the event task, and task list. Creates a event task
     * object, and adds it to the task list. Returns the output from the UI.
     * @param taskDesc Description of task
     * @param taskList List of tasks
     * @return String String output from the UI
     */
    private String eventCommand(String taskDesc, TaskList taskList) {
        String[] taskDescArr = taskDesc.split(" /");

        Task newEvent = new Event(taskDescArr[0], taskDescArr[1], taskPriority);

        taskList.addTask(newEvent);

        return ui.showTaskAdded(newEvent, taskList);
    }

    /**
     * Extracts the description and date of the task
     * leaving the command out.
     * @param inputArr String[] of the whole input by the user
     * @return String String representing the description and date.
     */
    public static String getDesc(String[] inputArr) {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i < inputArr.length; i++) {
            if (i != inputArr.length - 1) {
                builder.append(inputArr[i]);
                builder.append(" ");
            } else {
                builder.append(inputArr[i]);
            }
        }

        return builder.toString();
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Checks if the command is correct.
     * @param input Command
     * @return boolean Whether input is correct
     */
    public static boolean correctInput(String input) {
        return input.equals("todo")
                || input.equals("event")
                || input.equals("deadline")
                || input.equals("list")
                || input.equals("done")
                || input.equals("bye")
                || input.equals("delete")
                || input.equals("find")
                || input.equals("priority");
    }
}