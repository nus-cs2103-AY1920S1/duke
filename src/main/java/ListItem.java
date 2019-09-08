public class ListItem {

    private String description;
    private String status;
    private boolean isDone = false;
    private String date = " ";


    ListItem(String description, String command, String date) {
        try {
            switch (command) {
                case "todo":
                case "[T]":
                    this.status = "[T]";
                    this.description = description;
                    this.date = " ";
                    break;
                case "event":
                case "[E]":
                    this.status = "[E]";
                    this.description = description;
                    this.date = "(at: " + date + ")";
                    break;
                case "deadline":
                case "[D]":
                    this.status = "[D]";
                    this.description = description;
                    this.date = "(by: " + date + ")";
                    break;
            }
        }
        catch (Error e) {
            System.out.println("Item Creation Error");
            throw e;
        }
    }

    void done() {
        this.isDone = true;
    }

    String format() {
        return isDone + "@" + this.status + "@" + this.description + "@" + this.date + "\n";
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
