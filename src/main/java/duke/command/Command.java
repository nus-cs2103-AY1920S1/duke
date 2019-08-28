package duke.command;

public interface Command {
    public String getTaskType();
    public int getIndex();
    public String getTask();
    public String getDate();
    public String getKeyword();
}
