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
            } else {
                task = taskList.addTask(new Task(inputLine));
                ui.showAddition(task);
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

    public static void main (String[]args){
        Duke duke = new Duke();
        duke.run();
    }
}
