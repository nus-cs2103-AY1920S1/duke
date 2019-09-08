package duke.command;

import java.util.ArrayList;

import duke.task.Task;
import duke.parser.Comd;

public class Command {
    Comd comd;
    String input;
    String output;
    ArrayList<Task> taskList;

    public Command() {}

    public Command(String output, ArrayList<Task> taskList) {
        this.output = output;
        this.taskList = taskList;
    }

    public Command(Comd comd, String input) {
        this.comd = comd;
        this.input = input;
    }

    public Comd getComd() {
        return comd;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
}
