package duke.command;

import java.util.ArrayList;

import duke.task.Task;
import duke.parser.Comd;

/**
 * A class for storing information for commands.
 */
public class Command {
    private Comd comd;
    private String input;
    private String output;
    private ArrayList<Task> taskList;

    public Command() {
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

    public void addOutput(String input) {
        StringBuilder sb = new StringBuilder(output);
        sb.append("\n").append(input);
        output = sb.toString();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
}
