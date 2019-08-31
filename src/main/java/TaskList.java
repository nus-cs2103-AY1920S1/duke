import java.util.ArrayList;

/**
 * Class to represent a list of Tasks.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Constructs a TaskList with the given size.
     * 
     * @param size Size of the TaskList
     */
    public TaskList(int size) {
        super(size);
    }
    
    /**
     * Search the TaskList for all Tasks which have description containing the search string.
     * 
     * @param searchStr Search term
     * @return A TaskList of all Tasks whose descriptions have the search term as a substring
     */
    public TaskList search(String searchStr) {
        
        TaskList newList = new TaskList(super.size());

        for (int i = 0; i < super.size(); i++) {
            Task current = super.get(i);

            if (current
                .getDescription()
                .toLowerCase()
                .contains(
                    searchStr.toLowerCase()
                )
            ) {
                newList.add(current);
            } else {
                continue;
            }
        }

        return newList;
    }
}