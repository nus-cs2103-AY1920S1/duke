import java.util.ArrayList;

public class KeyCommand extends Command {
    protected String keyword;

    public KeyCommand(String keyword) {
        this.keyword = keyword;
    }

    public String execute(TaskList tasks, Storage storage) {
        String printable = "";
        printable = "Here are the matching tasks in your list: " + "\n";
        ArrayList<String> resultList = new ArrayList<String>();

        for (int i = 0; i < tasks.size(); i++) {
            String[] taskDesc = tasks.get(i).getDesc().split(" ");

            for (int j = 0; j < taskDesc.length; j++) {
                if (taskDesc[j].equals(this.keyword)) {
                    resultList.add(tasks.get(i).toString());
                    break;
                }
            }
        }

        for (int i = 0; i < resultList.size(); i++) {
            printable += i + 1 + ". " + resultList.get(i) + "\n";
        }

        return printable;
    }

    public boolean isExit() {
        return false;
    }
}