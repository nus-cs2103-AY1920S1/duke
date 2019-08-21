public class Parser {

    String time;
    String name;
    boolean timing;
    boolean firstInName;
    boolean firstInTime;
    Task task = null;

    public Parser() {}

    public Task createNewTask(int taskNo, String info, String...arr) {
        time = "";
        name = "";
        timing = false;
        firstInName = true;
        firstInTime = true;
        for (String word : arr) {
            if (word.startsWith("/")) {
                timing = true;
            } else {
                if (firstInName) {
                    name = word;
                    firstInName = false;
                } else if (timing && firstInTime) {
                    time = word;
                    firstInTime = false;
                } else if (timing) {
                    time += " " + word;
                } else {
                    name += " " + word;
                }
            }
        }
        switch (info) {
            case "todo":
                task = new Todo(taskNo, name, "T");
                break;
            case "deadline":
                task = new Deadline(taskNo, name, time, "D");
                break;
            case "event":
                task = new Event(taskNo, name, time, "E");
                break;
        }
        return task;
    }

}
