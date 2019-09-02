import java.io.IOException;

/**
 * AddCommand is a Command, specifically for todo, event and deadlines.
 */
public class AddCommand extends Command {

    String type;
    String taskDescription;
    String time;

    /**
     * Constructor for AddCommand, for type of: event or deadlines.
     */
    public AddCommand(String type, String taskDescription, String time) {
        this.type = type;
        this.taskDescription = taskDescription;
        this.time = time;
    }

    /**
     * Constructor for AddCommand, for type of: todo.
     */
    public AddCommand(String type, String taskDescription) {
        this.type = type;
        this.taskDescription = taskDescription;
    }

    /**
     * Executes the given task, prints output and updates the taskList.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {

        if (type.equals("todo")) {
            // Create ToDo object
            ToDo t = new ToDo(taskDescription);
            tasks.addTask(t);

            // Printing output
            ui.showTopBorder();
            System.out.println("\n\tGot it! I've added this task: ");
            System.out.println("\n\t" + t.toString());
            System.out.println("\n\tNow you have " + tasks.getSize() + " tasks in the list.");
            ui.showBottomBorder();

            // Saving to text file
            String textToAdd = "T | 0 | " + taskDescription + "\n";
            storage.saveTask(textToAdd);

        } else if (type.equals("deadline")) {
            // Create DeadLine object
            DeadLine d = new DeadLine(taskDescription, time);
            tasks.addTask(d);

            // Printing Output
            ui.showTopBorder();
            System.out.println("\n\tGot it! I've added this task: ");
            System.out.println("\n\t" + d.toString());
            System.out.println("\n\tNow you have " + tasks.getSize() + " tasks in the list.");
            ui.showBottomBorder();

            // Saving to text file
            String textToAdd = "D | 0 | " + taskDescription + " | " + time + "\n";
            storage.saveTask(textToAdd);

        } else if (type.equals("event")) {
            // Create Event object
            Event e = new Event(taskDescription, time);
            tasks.addTask(e);

            // Printing Output
            ui.showTopBorder();
            System.out.println("\n\tGot it! I've added this task: ");
            System.out.println("\n\t" + e.toString());
            System.out.println("\n\tNow you have " + tasks.getSize() + " tasks in the list.");
            ui.showBottomBorder();

            // Saving to text file
            String textToAdd = "E | 0 | " + taskDescription + " | " + time + "\n";
            storage.saveTask(textToAdd);
        }
    }
}