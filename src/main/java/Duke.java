public class Duke {
    private UserInterface ui;
    private TaskList taskList;

    private Duke() {
        ui = new UserInterface();
        taskList = new TaskList();
    }

    private void run() {
        ui.showWelcomeMessage();
        Task task;
        while (true) {
            String inputLine = ui.readLine();
            String command = getCommandFrom(inputLine);
            ui.showLine();
            if (command.equals("bye")) {
                ui.exitProgram();
                break;
            } else if (command.equals("list")) {
                ui.showTaskList(taskList.getTaskNames());
            } else if (command.equals("done")) {
                task = taskList.markDone(getIndexFrom(inputLine));
                ui.showMarkDone(task);
            } else if (command.equals("todo")) {
                task = createTodoFrom(inputLine);
                taskList.addTask(task);
                ui.showAddition(task);
                ui.showTaskSize(taskList);
            } else if (command.equals("deadline")) {
                task = createDeadlineFrom(inputLine);
                taskList.addTask(task);
                ui.showAddition(task);
                ui.showTaskSize(taskList);
            } else if (command.equals("event")) {
                task = createEventFrom(inputLine);
                taskList.addTask(task);
                ui.showAddition(task);
                ui.showTaskSize(taskList);
            } else {
                ui.showToUser(ui.MESSAGE_INVALID_COMMAND_FORMAT);
            }
            ui.showLine();
        }
    }

    private String getCommandFrom(String inputLine) {
        return inputLine.strip().split(" ")[0];
    }

    private int getIndexFrom(String inputLine) {
        return Integer.parseInt(inputLine.strip().split(" ")[1]);
    }

    private Todo createTodoFrom(String inputLine) {
        return new Todo(inputLine.substring("todo".length()).strip());
    }

    private Deadline createDeadlineFrom(String inputLine) {
        String[] deadlinePart = inputLine.substring("deadline".length()).split("/by");
        return new Deadline(deadlinePart[0].strip(), deadlinePart[1].strip());
    }

    private Event createEventFrom(String inputLine) {
        String[] eventPart = inputLine.substring("event".length()).split("/at");
        return new Event(eventPart[0].strip(), eventPart[1].strip());
    }

    public static void main (String[]args){
        Duke duke = new Duke();
        duke.run();
    }
}
