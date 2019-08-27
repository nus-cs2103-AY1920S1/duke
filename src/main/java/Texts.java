import java.util.LinkedList;

class Texts {
    private LinkedList<Task> texts = new LinkedList<>();

    public void add(Task task) {
        texts.add(task);
    }
    public void print() {
        for (int i = 0; i < texts.size(); i++) {
            int index = i + 1;
            Task t = texts.get(i);
            System.out.println(index + "." + t);
        }


    }
    public Task get(int n) {
        return texts.get(n);
    }
    public Task getLast() {
        return texts.getLast();
    }
    public int getNumber() {
        return texts.size();
    }

    public Task remove(int i) {
        return texts.remove(i);
    }
}
