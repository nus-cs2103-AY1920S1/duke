import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Deadline extends Task {
    //Deadlines can have (notes)
    private String description;
    private String notesInBrackets;
    private Date date;

    private Deadline() {}
    private Deadline(String descr, boolean completed, int id) throws IncorrectTaskFormatException{
        super.completed = completed;
        super.id = id;
        super.taskType = TaskType.D;

        setupDetails(descr);
    }
    private void setupDetails(String input) throws IncorrectTaskFormatException {
        String[] tmp = input.split("/by");
        //inputs should only have <=1 '/' characters
        this.description = tmp[0];

        if (tmp.length < 2) {
            throw new IncorrectTaskFormatException("by");
        }

        /*
        Scanner tmp2 = new Scanner(tmp[1]);
        String term = tmp2.next();
        String date = "";
        if (tmp2.hasNext()) {
            date = tmp2.nextLine();
        }
        tmp2.close();
         */
        String term = "by";
        String date = tmp[1];

        if (date.equals(""))
            throw new IncorrectTaskFormatException("by");
        this.notesInBrackets = String.format("%s:%s", term, date);

        super.description = String.format("%s(%s)", this.description, this.notesInBrackets);

        try {
            this.date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Deadline create(String descr) throws EmptyDescriptionException, IncorrectTaskFormatException {
        if (descr.equals(""))
            throw new EmptyDescriptionException("a deadline");

        Task.totalNumOfTasks++;
        Deadline newTask = new Deadline(descr, false, Task.totalNumOfTasks);
        Task.taskList.add(newTask);
        return newTask;
    }
}
