public class ListItem {

    private String description;
    private String status;
    boolean isDone = false;
    private String date = "";


    public ListItem(String description, String command) {
        switch (command) {
            case "todo":
                this.status = "[T]";
                this.description = description;
                break;
            case "event":
                this.status = "[E]";
                this.description = description.split("/")[0];
                this.date = "(at: " + description.split("/")[1] + ")";
                break;
            case "deadline":
                this.status = "[D]";
                this.description = description.split("/")[0];
                this.date = "(by: " + description.split("/")[1] + ")";
                break;

        }
    }

    void done() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String toReturn;
        if (isDone) {
            toReturn = "[✓] " + this.description;
        }
        else {
            toReturn = "[✗] " + this.description;
        }

        return this.status + toReturn + this.date;
    }
}
