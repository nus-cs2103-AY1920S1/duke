import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


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

    /**
     * Prints out all the tasks in the tasks list.
     *
     */

    public void listout() {
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks.get(i));
        }
    }

    /**
     * Finds and prints the task that is in the list.
     *
     * @param found arraylist of the tasks
     */

    public void listfind(ArrayList<Task> found) {
        for (int i = 0; i < found.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + found.get(i));
        }
    }

    /**
     * Prints out all the individual tasks in the tasks list.
     *
     * @param found an Arraylist of the tasks in the file in String format
     * @return String
     */

    public String printlistfind(ArrayList<Task> found) {
        String out = "";
        if (found.size() == 0) {
            return out;
        } else {
            for (int i = 0; i < found.size(); i++) {
                int num = i + 1;
                out = out + "\n" + num + ". " + found.get(i);
            }
        }
        return out;
    }

    /**
     * Prints out the task that is being searched for.
     *
     * @param word to be found in String format
     * @return ArrayList of the tasks found with those words
     */

    public ArrayList<Task> find(String word) {
        ArrayList<Task> temp = new ArrayList<Task>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            String des = t.getDescription();
            if (hasWord(des, word)) {
                temp.add(t);
            }
        }
        return temp;
    }

    /**
     * Checks if the Description of the tasks has the word in it.
     *
     * @param description of the task
     * @param word that is being searched for
     * @return boolean if there exists the word in the description
     */

    public boolean hasWord(String description, String word) {
        String[] ls = description.split(" ");
        boolean a = false;
        for (int i = 0; i < ls.length; i++) {
            if (ls[i].equals(word)) {
                a = true;
                break;
            }
        }
        return a;
    }

    /**
     * Prints out all the tasks in the tasks list.
     *
     * @return String format
     */

    public String printlist() {
        String temp = "";
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            temp = temp + (num + ". " + tasks.get(i) + "\n");
        }
        return temp;
    }

    /**
     * Sorts the tasks in the list.
     *
     * @param sorter chooses how the tasks will be sorted
     */

    public void sort(String sorter) {
        if (sorter.equals("status")) {
            SortDone sdone = new SortDone();
            Collections.sort(tasks, sdone);
        } else if (sorter.equals("description")) {
            SortDescription sd = new SortDescription();
            Collections.sort(tasks, sd);
        } else if (sorter.equals("date")) {
            SortDate sdate = new SortDate();
            Collections.sort(tasks, sdate);
        }
    }
}
