import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> xs;
    private int numOfTasks;

    public TaskList() {
        xs = new ArrayList<Task>();
        numOfTasks = 0;
    }

    public TaskList(String currentList) {
        xs = new ArrayList<Task>();
        numOfTasks = 0;
        integrateList(xs, currentList);
    }

    private void integrateList (ArrayList<Task> list, String content) {
        Scanner s = new Scanner(content);
        while (s.hasNextLine()) {
            String text = s.nextLine();
            String[] itemArr = text.split(" [|] ");
            switch (itemArr[0]) {
            case "T":
                xs.add(new ToDos(itemArr[2], itemArr[1]));
                numOfTasks++;
                break;

            case "D":
                xs.add(new Deadlines(itemArr[2], itemArr[1], itemArr[3]));
                numOfTasks++;
                break;

            case "E":
                xs.add(new Event(itemArr[2], itemArr[1], itemArr[3]));
                numOfTasks++;
                break;
            }
        }
        s.close();
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }

    public ArrayList<Task> getTaskList() {
        return xs;
    }

    public String addTask(Task t) {
        numOfTasks++;
        xs.add(t);
        return String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n", t, numOfTasks);
    }

    public String tickTask(int num) {
        xs.get(num - 1).markAsDone();
        return String.format("Nice! I've marked this task as done:\n%s\n", xs.get(num - 1));
    }

    public String removeTask(int num) {
        numOfTasks--;
        Task t = xs.get(num - 1);
        xs.remove(num - 1);
        return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.\n", t, numOfTasks);
    }

    public String printTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        for (int i = 1; i <= xs.size(); i++) {
            sb.append("\n" + String.format("%d.%s", i, xs.get(i - 1)));
        }
        return sb.toString();
    }

    public String printMatchingTasks() {
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 1; i <= xs.size(); i++) {
            sb.append("\n" + String.format("%d.%s", i, xs.get(i - 1)));
        }
        return sb.toString();
    }
}
