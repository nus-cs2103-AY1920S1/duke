import java.util.LinkedList;

class Texts {
    private LinkedList<Task> texts = new LinkedList<>();

    public void add(String text, String status) {
        Task task = new Task(text, status);
        texts.add(task);
    }
    public void print() {
        for (Task t : texts) {
            int index = texts.indexOf(t) + 1;
            System.out.println( index + t.getStatus()+ t);
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
