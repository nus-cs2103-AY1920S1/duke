package command;

import exception.DukeException;
import filewriter.Storage;
import task.*;
import ui.Ui;

public class AddCommand extends Command {
    String specifics;
    public AddCommand(FullCommand taskType, String specifics){
        super.type = taskType;
        this.specifics = specifics;
    }

    public boolean isExit(){
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask;
        switch(type){
            case TODO:
            newTask = new Todo(specifics);
            break;
            case DEADLINE:
            newTask = new Deadline(specifics);
            break;
            default:
            newTask = new Event(specifics);
            break;
        }
        tasks.addTask(newTask);
        ui.readTask(newTask, tasks.task_Num);
        if (tasks.isFirst){
            tasks.isFirst = !tasks.isFirst;
        } else {
            storage.writeToFile(System.lineSeparator());
        }
        storage.writeToFile(newTask.toString());
    };
}
