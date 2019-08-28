package duke.command;

public class PrintCommand implements Command {
    public String getTaskType() {
        return "list";
    };

    public int getIndex() {
        return 0;
    };

    public String getTask() {
        return "";
    };

    public String getDate() {
        return "";
    };

    public String getKeyword() {
        return "error";
    }
}
