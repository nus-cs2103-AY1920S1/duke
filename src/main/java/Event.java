import java.util.Scanner;

public class Event extends Task {
    //Deadlines can have (notes)
    private String description;
    private String notesInBrackets;

    private Event() {
        super.description = "";
        super.completed = false;
        super.id = 0;
        super.taskType = "";
        this.description = "";
        this.notesInBrackets = "";
    }
    private Event(String descr, boolean completed, int id) {
        super.completed = completed;
        super.id = id;
        super.taskType = "E";

        setupDetails(descr);
    }
    private void setupDetails(String input) {
        String[] tmp = input.split("/");
        //inputs should only have <=1 '/' characters
        this.description = tmp[0];
        Scanner tmp2 = new Scanner(tmp[1]);
        String term = tmp2.next();
        String datetime = tmp2.nextLine();
        tmp2.close();
        this.notesInBrackets = String.format("%s:%s", term, datetime);

        super.description = String.format("%s(%s)", this.description, this.notesInBrackets);
    }

    public static Event create(String descr) {
        //assume legit; not using optional for now
        Task.totalNumOfTasks++;
        Event newTask = new Event(descr, false, Task.totalNumOfTasks);
        Task.taskList.add(newTask);
        return newTask;
    }
}
