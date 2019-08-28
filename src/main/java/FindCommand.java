import java.util.ArrayList;

public class FindCommand extends Command {

    String keyword = "";

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the matching tasks in your list:");
        ArrayList<Task> matchingList = tasks.generateMatchingList(keyword);
        displayList(matchingList);
    }

    public void displayList(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            int itemIndex = i + 1;
            String itemDisplay = itemIndex + "." + list.get(i).toString();
            System.out.println(itemDisplay);
        }
    }
}
