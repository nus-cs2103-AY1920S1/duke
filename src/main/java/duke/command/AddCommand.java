package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskEnum;

public class AddCommand extends Command {

    private String description = "";
    private String date = "";
    private TaskEnum taskType;

    public AddCommand(String description, TaskEnum taskType) {
        this.description = description;
        this.taskType = taskType;
    }

    public AddCommand(String description, String date, TaskEnum taskType) {
        this.description = description;
        this.date = date;
        this.taskType = taskType;
    }

    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Task task = null;
        switch(taskType){
        case TODO:
            task = taskList.add(description, TaskEnum.TODO);
        break;
        case DEADLINE:
            task = taskList.add(description, date, TaskEnum.DEADLINE);
        break;
        case EVENT:
            task = taskList.add(description, date, TaskEnum.EVENT);
        break;
        }

        if(task != null) {
            ui.printOutput("  " + task,
                    "Got it. I've added this task: ",
                    taskList.getTaskList().size());
            storage.save(taskList.getTaskList());
        }
    }
}
