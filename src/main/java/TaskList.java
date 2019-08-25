import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private ArrayList<Task> ls;

    public TaskList() {
        ls = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> ls){
        this.ls = ls;
    }

    public void add(Task t) {
        ls.add(t);
    }

    public String getSerialized(){
        StringBuilder sb = new StringBuilder();
        Iterator<Task> it = ls.iterator();
        while(it.hasNext()) {
            sb.append(it.next().toFileString())
                    .append((char) 30);
        }
        if(sb.length() > 0)
            sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public String toUiString(){
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        int i = 1;
        Iterator<Task> it = ls.iterator();
        while (it.hasNext()) {
            sb.append(i++)
                    .append(".")
                    .append(it.next());
        }
        return sb.toString();
    }

    public int size(){
        return ls.size();
    }

    public Task remove(int index) throws IndexOutOfBoundsDukeException {
        try {
            return ls.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsDukeException();
        }
    }

    public Task setDone(int index) throws DukeException{
        try {
            Task t = ls.get(index - 1);
            t.setDone();
            return t;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsDukeException();
        }
    }
}
