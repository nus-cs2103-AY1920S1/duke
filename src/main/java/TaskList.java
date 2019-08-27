import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(List<String[]> tasks) throws DukeException {
        list = new ArrayList<>();
        if (tasks.isEmpty()) {
            throw new DukeException();
        }
        for (String[] task : tasks) {
            switch (task[0]) {
            case "T":
                list.add(new ToDo(task[2]));
                break;
            case "D":
                list.add(new Deadline(task[2], task[3]));
                break;
            case "E":
                list.add(new Event(task[2], task[3]));
                break;
            }
            if (task[1].equals("1")) {
                list.get(list.size() - 1).setDone();
            }
        }
    }

    public void addToList(String[] input) {
        Task task;
        switch (input[0]) {
        case "todo":
            task = new ToDo(input[1]);
            list.add(task);
            break;
        case "deadline":
            task = new Deadline(input[1], input[2]);
            list.add(task);
            break;
        case "event":
            task = new Event(input[1], input[2]);
            list.add(task);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + input[0]);
        }
        print("    Got it. I've added this task:");
        System.out.println("        " + task);
        print("    Now you have " + list.size() + " tasks in the list.");
    }

    public void markAsDone(int num) {
        Task task = list.get(num - 1);
        task.setDone();
        print("    Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    public void delete(int num) {
        Task deletedTask = list.get(num - 1);
        list.remove(num - 1);
        print("    Noted. I've removed this task:");
        System.out.println("        " + deletedTask);
        print("    Now you have " + list.size() + " tasks in the list.");
    }

    public void printList() {
        int i = 1;
        print("    Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println("    " + i + ". " + task);
            i++;
        }
    }

    public List<Task> getList() {
        return list;
    }

    private void print(String x) {
        System.out.println(x);
    }
}
