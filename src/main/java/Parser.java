public class Parser {
    private Ui ui;
    private boolean isExit = false;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    public void parseCommand(String input, TaskList taskList) throws DukeException {
        String[] inputArr = input.split(" ");
        String taskType = inputArr[0];
        String taskDesc = getDesc(inputArr);

        if (!correctInput(taskType)) {
            throw new IncorrectInputException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (taskType.equals("bye")) {
            isExit = true;
        } else if (taskType.equals("list")) {

            ui.printTaskList(taskList);

        } else if (taskType.equals("done")) {

            int taskNum = Integer.parseInt(taskDesc);
            Task task = taskList.getTask(taskNum - 1);

            if (task.isDone()) {
                ui.showTaskAlreadyDone(task);
            } else {
                task.doTask();
                ui.showTaskDone(task);
            }

        } else if (taskType.equals("delete")) {

            int taskNum = Integer.parseInt(taskDesc);
            Task task = taskList.getTask(taskNum);
            taskList.deleteTask(taskNum - 1);

            ui.showTaskDeleted(task, taskList);

        } else if (inputArr.length == 1) {

            throw new NoDescriptionException(":( OOPS!!! The description of " + inputArr[0] + " cannot be empty.");

        } else if (taskType.equals("todo")) {

            Task newTodo = new Todo(taskDesc);
            taskList.addTask(newTodo);

            ui.showTaskAdded(newTodo, taskList);

        } else if (taskType.equals("deadline")) {

            String[] taskDescArr = taskDesc.split(" /");

            Task newDeadline = new Deadline(taskDescArr[0], taskDescArr[1]);

            taskList.addTask(newDeadline);

            ui.showTaskAdded(newDeadline, taskList);

        } else {
            String[] taskDescArr = taskDesc.split(" /");

            Task newEvent = new Event(taskDescArr[0], taskDescArr[1]);

            taskList.addTask(newEvent);

            ui.showTaskAdded(newEvent, taskList);
        }
    }

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

    public static boolean correctInput(String input) {
        if (input.equals("todo") ||
                input.equals("event") ||
                input.equals("deadline") ||
                input.equals("list") ||
                input.equals("done") ||
                input.equals("bye") ||
                input.equals("delete")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExit() {
        return isExit;
    }
}