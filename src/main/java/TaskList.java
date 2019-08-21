import java.util.ArrayList;
import java.util.List;

public class TaskList implements MyList {
    private List list;

    public TaskList() {
        list = new ArrayList<String>();
    }

    //adds a task to the list
    @Override
    public void add(String task) {
        list.add(task);
    }

    //returns the list of Strings
    @Override
    public List<String> getList() {
        return list;
    }


}
