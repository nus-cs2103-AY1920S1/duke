import java.util.ArrayList;


public class Tasklist {

    public ArrayList<Task> tasks;
    private UI ui;
    private Storage storage;
    private Parser parser;

    public Tasklist() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        this.tasks = tasks;
    }

    public void addTodo(Todo todo) {
        tasks.add(todo);
    }

    public void addEvent(Event event) {
        tasks.add(event);
    }

    public void addDeadline(Deadline deadline) {
        tasks.add(deadline);
    }

    public ArrayList<Task> returnTasks() {
        return tasks;
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }

    public void delete(int num) {
        tasks.remove(num);
    }

    public void listout() {
        for (int i = 0; i < tasks.size(); i ++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks.get(i));
        }
    }

    public void listfind(ArrayList<Task> found) {
        for (int i = 0; i < found.size(); i ++) {
            int num = i + 1;
            System.out.println(num + ". " + found.get(i));
        }
    }

    public String printlistfind(ArrayList<Task> found) {
        String out = "";
        if (found.size() == 0) {
            return out;
        } else {
            for (int i = 0; i < found.size(); i ++) {
                int num = i + 1;
                out = out + "\n" + num + ". " + found.get(i);
            }
        }
        return out;
    }

    public ArrayList<Task> find(String word) {
        ArrayList<Task> temp = new ArrayList<Task>();
        for (int i = 0; i < tasks.size(); i ++) {
            Task t = tasks.get(i);
            String des = t.getDescription();
            if (hasWord(des, word)) {
                temp.add(t);
            }
        }
        return temp;
    }

    public boolean hasWord(String description, String word) {
        String[] ls = description.split(" ");
        boolean a = false;
        for (int i = 0; i < ls.length; i ++) {
            if (ls[i].equals(word)) {
                a = true;
                break;
            } else {

            }
        }
        return a;
    }

    public String printlist() {
        String temp = "";
        for (int i = 0; i < tasks.size(); i ++) {
            int num = i + 1;
            temp = temp + (num + ". " + tasks.get(i) + "\n");
        }
        return temp;
    }
}