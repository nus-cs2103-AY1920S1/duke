public class ListItem {

    private String description;
    boolean isDone = false;


    public ListItem(String description) {
        this.description = description;
    }

    void done() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[✓] " + this.description;
        }
        else {
            return "[✗] " + this.description;
        }
    }
}
