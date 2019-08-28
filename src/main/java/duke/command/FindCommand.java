package duke.command;

public class FindCommand implements Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }
    public String getTaskType() {
        return "find";
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
        return keyword;
    };
}
