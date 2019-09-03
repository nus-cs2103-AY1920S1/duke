import java.util.ArrayList;

public class AddCommand extends Command{
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task list. "
            + "Parameters: Task Name + (Start at/End by time)...\n"
            + "Example: " + COMMAND_WORD
            + " deadline return book /by Sunday ";
    public static final String MESSAGE_SUCCESS = "     Got it. I've added this task:";
    private final Task task;
    public void AddCommand(Task task,
                           ArrayList<Task> tasks,
                           boolean isDisplay) {

        if(task == null ){
            throw new StringIndexOutOfBoundsException();
        }
        if (isDisplay){
            System.out.println("    ____________________________________________________________");
            System.out.println(MESSAGE_SUCCESS);
            System.out.print("       ["+task.getType()+"]"+"["+ task.getStatus()+"] "+task.getTaskName());
            tasks.add(task);
            switch (task.getType()) {
                case 'T':
                    System.out.println();
                    break;
                case 'D':
                    Deadline dtask = (Deadline) task;
                    System.out.println(" by "+ dtask.getBy());
                    break;
                case 'E':
                    Event etask = (Event)task;
                    System.out.println(" start "+ etask.getStart());
                    break;
            }
            System.out.println("     Now you have "+tasks.size()+" tasks in the list");
            System.out.println("    ____________________________________________________________");
        }
    }

    public AddCommand (Task task) {
        this.task = task;
    }
    @Override
    public CommandResult execute() {
        taskList.addTaskIn(task, true);
        return new CommandResult(String.format(MESSAGE_SUCCESS, task));
    }
}
