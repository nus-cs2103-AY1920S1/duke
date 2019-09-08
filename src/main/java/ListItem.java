public class ListItem {

    private String description;
    private String status;
    private boolean isDone = false;
    private Date date;


    public ListItem(String description, String command) {
        try {
            switch (command) {
                case "todo":
                    this.status = "[T]";
                    this.description = description;
                    break;
                case "event":
                    this.status = "[E]";
                    this.description = description.split("/")[0];
                    this.date = new Date(description.split("/",2)[1] + ")");
                    break;
                case "deadline":
                    this.status = "[D]";
                    this.description = description.split("/")[0];
                    this.date = new Date(description.split("/",2)[1] + ")");
                    break;
            }
        }
        catch (Error e) {
            throw e;
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
