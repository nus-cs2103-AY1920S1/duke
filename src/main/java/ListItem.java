public class ListItem {

    private String description;


    public ListItem(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
