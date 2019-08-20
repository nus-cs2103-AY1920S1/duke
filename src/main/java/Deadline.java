import java.util.Scanner;

public class Deadline extends Task {
    //Deadlines can have (notes)
    private String description;
    private String notesInBrackets;

    private Deadline() {}
    private Deadline(String descr, boolean completed, int id) {
        super.completed = completed;
        super.id = id;
        super.taskType = TaskType.D;

        setupDetails(descr);
    }
    private void setupDetails(String input) {
        String[] tmp = input.split("/");
        //inputs should only have <=1 '/' characters
        this.description = tmp[0];
        Scanner tmp2 = new Scanner(tmp[1]);
        String term = tmp2.next();
        String date = tmp2.nextLine();
        tmp2.close();
        this.notesInBrackets = String.format("%s:%s", term, date);

        super.description = String.format("%s(%s)", this.description, this.notesInBrackets);
    }

    public static Deadline create(String descr) throws EmptyDescriptionException {
        if (descr.equals(""))
            throw new EmptyDescriptionException("a deadline");

        Task.totalNumOfTasks++;
        Deadline newTask = new Deadline(descr, false, Task.totalNumOfTasks);
        Task.taskList.add(newTask);
        return newTask;
    }
}
