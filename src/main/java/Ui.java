import java.util.ArrayList;

public class Ui {
    public Ui() {

    }

    public void list(ArrayList<Task> store) throws DukeException {
        if (store.size() == 0) {
            throw new DukeException("☹ OOPS!!! Your list is still empty!");
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < store.size(); i++) {
            int taskNumber = i + 1;
            String taskString = Integer.toString(taskNumber);
            System.out.println(taskString + ".[" + store.get(i).getStatusIcon() + "] " + store.get(i).toString());
        }
    }
    
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}