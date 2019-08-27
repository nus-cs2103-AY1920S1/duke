import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private String taskName;
    public FindCommand(String taskName) {
        super(null);
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList) {
        List<Task> list = new ArrayList<Task>();
        for(int i = 0; i < taskList.size(); i ++) {
            if(taskList.get(i).getName().contains(this.taskName)) {
                list.add(taskList.get(i));
            }
        }
        if(list.size() == 0) {
            UI.printNoKeywordMessage(this.taskName);
        } else {
            UI.printFoundKeywordMessage();
            for(int i = 0; i < list.size(); i ++) {
                System.out.println((i+1) + "." + list.get(i));
            }
        }
    }
}
