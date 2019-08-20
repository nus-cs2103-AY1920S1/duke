import java.util.ArrayList;
import java.util.List;

public class ListOfInput {
    private List<Task> list;

    public ListOfInput() {
        list = new ArrayList<>();
    }

    public void addToList(String input) {
        String[] arrOfWords = input.split(" ");
        String taskWithoutType = input.replace(arrOfWords[0], "");
        Task task = new Task(input);
        switch (arrOfWords[0]) {
            case "todo":
                task = new ToDo(taskWithoutType);
                list.add(task);
                break;
            case "deadline":
                String[] arrOfWordsDeadline = taskWithoutType.split(" /by ");
                task = new Deadline(arrOfWordsDeadline[0], arrOfWordsDeadline[1]);
                list.add(task);
                break;
            case "event":
                String[] arrOfWordsEvent = taskWithoutType.split(" /at ");
                task = new Event(arrOfWordsEvent[0], arrOfWordsEvent[1]);
                list.add(task);
                break;
        }
        print("    Got it. I've added this task:");
        System.out.println("        " + task);
        print("    Now you have " + list.size() + " tasks in the list");
    } // may have problem with this

    public void markAsDone(int num) {
        Task task = list.get(num - 1);
        task.isDone();
        print("    Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    public void printList() {
        int i = 1;
        print("    Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println("    " + i + ". " + task);
            i++;
        }
    }

    private void print(String x) {
        System.out.println(x);
    }
}
