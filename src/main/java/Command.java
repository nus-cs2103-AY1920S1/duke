public class Command {

    private String action;   //list, delete, done, todo, deadline, event, bye
    private int index;
    private String nameOfTask; 
    private String dateStr;
	private boolean exit = false;

	public Command(String action, int index, String nameOfTask, String dateStr) {
		this.action = action;
		this.index = index;
		this.nameOfTask = nameOfTask;
        this.dateStr = dateStr;
	}

	public void execute(TaskList tasks, Ui ui, Storage storage) throws IncompleteCommandException, InvalidCommandException {
        if (action.equals("list")) {
            tasks.printList();

        } else if (action.equals("delete")) {
            System.out.println("     Noted. I've removed this task: ");
            System.out.println("       " + tasks.get(index));
            tasks.delete(index);
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");

        } else if (action.equals("done")) {
            tasks.get(index).markAsDone();

        } else if (action.equals("bye")) {
        	System.out.println("     Bye. Hope to see you again soon!");
            exit = true;

        } else if (action.equals("todo")) {
        	Task newTask = new ToDo(nameOfTask);
            System.out.println("     Got it. I've added this task: ");
            System.out.println("       " + newTask);
            tasks.add(newTask);

        } else if (action.equals("deadline")) {
        	Task newTask = new Deadline(nameOfTask, dateStr);
            System.out.println("     Got it. I've added this task: ");
            System.out.println("       " + newTask);
            tasks.add(newTask);

        } else if (action.equals("event")) {
        	Task newTask = new Event(nameOfTask, dateStr);
            System.out.println("     Got it. I've added this task: ");
            System.out.println("       " + newTask);
            tasks.add(newTask);

        } else {
        	throw new InvalidCommandException("Invalid Command");
        }
	}
	
	public boolean isExit() {
		return exit;
	}
}