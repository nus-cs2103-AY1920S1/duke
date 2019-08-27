import java.text.ParseException;

public class Parser {
    private String command;
    private String taskDetails;
    private Ui ui;
    private TaskList list;

    public Parser(String line, Ui ui, TaskList list) {
        this.ui = ui;
        this.list = list;
        String[] words = line.split(" ");
        this.command = words[0];

        String taskDetails = "";
        for (int i = 1; i < words.length; i++) {
            if (i == words.length - 1) {
                taskDetails += words[i];
            } else {
                taskDetails += words[i] + " ";
            }
        }
        this.taskDetails = taskDetails;
    }

    public String getCommand() {
        return command;
    }

    public void doCommand() throws ParseException {
        if (taskDetails.equals("")) {
            if (command.equals("bye") || command.equals("list") ) {

            } else {
                ui.showDescriptionEmptyError();
                return;
            }
        }
        switch (command) {
            case "list":
                ui.list(list.getList());
                break;
            case "done":
                try {
                    Task task = list.doTask(taskDetails);
                    ui.taskDone(task);
                } catch (Exception ex) {
                    ui.noSuchTaskError();
                }
                break;
            case "delete":
                try {
                    Task task = list.deleteTask(taskDetails);
                    ui.taskDeleted(task);
                    ui.showNumberOfTasks(list.getList());
                } catch (Exception ex) {
                    ui.noSuchTaskError();
                }
                break;
            case "todo":
                Todo task = new Todo(taskDetails);
                list.addTask(task);
                ui.taskCreated(task);
                ui.showNumberOfTasks(list.getList());
                break;
            case "deadline": {
                //split the string by /
                String[] halves = taskDetails.split("/by");
                String description = halves[0];
                String by = halves[1];
                Deadline deadline = new Deadline(description, by);
                list.addTask(deadline);
                ui.taskCreated(deadline);
                ui.showNumberOfTasks(list.getList());
                break;
            }
            case "event": {
                String[] halves = taskDetails.split("/at");
                String description = halves[0];
                String by = halves[1];
                Event event = new Event(description, by);
                list.addTask(event);
                ui.taskCreated(event);
                ui.showNumberOfTasks(list.getList());
                break;
            }
            default:
                ui.showWrongCommandError();
                break;
        }
    }
}
