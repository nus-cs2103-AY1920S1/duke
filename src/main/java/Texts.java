import java.util.LinkedList;

class Texts {
    private LinkedList<Task> texts = new LinkedList<>();
    private int counter = 0;
    public void add(String text, String status) {
        counter++;
        Task task = new Task(text, counter, status);
        texts.add(task);
    }
    public void print() {
        for (Task t : texts) {
            System.out.println(t.getIndex() + t.getStatus() + t);
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
}
