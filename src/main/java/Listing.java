import java.util.ArrayList;

public class Listing {
    private ArrayList<Task> list;

    public Listing() {
        this.list = new ArrayList<>();
    }

    public void add(String msg) {
        Task task = new Task(msg);
        list.add(task);
        System.out.println(String.format("added: %s", msg));
    }

    public void addTodo(String msg) {
        Task task = new Todo(msg);
        list.add(task);
        System.out.println(String.format(
                "Got it. I\'ve added this task:\n  "
                + task + "\nNow you have %s tasks in the list", Task.getNoOfTask()));
    }

    public void addDeadline(String msg) {
        String[] msgs = msg.split("/by");
        Task task = new Deadline(msgs[0], msgs[1]);
        list.add(task);
        System.out.println(String.format(
                "Got it. I\'ve added this task:\n  "
                        + task + "\nNow you have %s tasks in the list", Task.getNoOfTask()));
    }
    public void addEvent(String msg) {
        String[] msgs = msg.split("/at");
        Task task = new Event(msgs[0], msgs[1]);
        list.add(task);
        System.out.println(String.format(
                "Got it. I\'ve added this task:\n  "
                        + task + "\nNow you have %s tasks in the list", Task.getNoOfTask()));
    }
    public void mark(int index) {
        Task tk = list.get(index - 1);
        tk.markAsDone();
        System.out.println(
                String.format("Nice! I've marked this task as done:\n"
                        + "  " +tk));

    }

    public void listing() {
        for (int i = 0; i < list.size(); i++) {
            Task tk = list.get(i);
            System.out.println( i + 1 + "." + tk);
        }
    }
}
