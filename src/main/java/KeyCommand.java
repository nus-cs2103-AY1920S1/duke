import java.util.ArrayList;

public class KeyCommand extends Command {
    protected String[] keyword;

    public KeyCommand(String... keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine("Here are the matching tasks in your list: ");
        ArrayList<String> resultList = new ArrayList<String>();

        for (int i = 0; i < tasks.size(); i++) {
            String[] taskDesc = tasks.get(i).getDesc().split(" ");

            for (int j = 0; j < taskDesc.length; j++) {
                for(int k=0; k<keyword.length; k++) {
                    if (taskDesc[j].equals(keyword[k])) {
                        String toAdd = tasks.get(i).toString();

                        if(!resultList.contains(toAdd)) {
                            resultList.add(toAdd);
                        }

                        break;
                    }
                }

            }
        }

        for (int i = 0; i < resultList.size(); i++) {
            ui.showLine(i + 1 + ". " + resultList.get(i));
        }
    }

    public boolean isExit() {
        return false;
    }
}