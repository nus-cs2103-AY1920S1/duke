import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {

        this.list = new ArrayList<>();
    }

    public void addTask(Task current) {
        list.add(current);
        if(list.size() >1) {
            System.out.println("Got it. I've added this task: \n" + "   " + current.toString() + "\n" +
                    "Now you have " + list.size() + " tasks in the list. ");
        } else {
            System.out.println("Got it. I've added this task: \n" + "   " + current.toString() + "\n" +
                    "Now you have " + list.size() + " task in the list. ");
        }
    }

    public void deleteTask(int current) {
        Task deleted = list.get(current);
        list.remove(current);
        if(list.size() > 1) {
            System.out.println("Noted. I've removed this task: \n" + "   " + deleted.toString() + "\n" +
                    "Now you have " + list.size() + " tasks in the list. ");
        } else {
            System.out.println("Noted. I've removed this task: \n" + "   " + deleted.toString() + "\n" +
                    "Now you have " + list.size() + " task in the list. ");
        }
    }

    public void completeTask(int taskNumber) {
        Task completed = list.get(taskNumber);

        completed.markAsDone();
        System.out.println("Nice! I've marked this task as done: \n" + "   " + completed);

    }

    public void printList() {
        int n = 1;

        if(list.isEmpty()){

            System.out.println("List is empty");

        } else {

            for (Task item : list) {
                System.out.println(n + "." + item);
                n++;
            }

        }
    }

}
