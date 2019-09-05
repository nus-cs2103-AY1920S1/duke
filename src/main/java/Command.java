public class Command {

    private String action;
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

    /**
     * Executes command
     *
     * @param tasks Access of the TaskList
     * @param ui Access of Ui
     * @param storage Access of storage to save tasks list
     */
	public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (action.equals("list")) {
            tasks.printList();

        } else if (action.equals("delete")) {
            System.out.println("     Noted. I've removed this task: ");
            System.out.println("       " + tasks.get(index));
            tasks.delete(index);
            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");

        } else if (action.equals("done")) {
            tasks.done(index);

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

        } else {
        	Task newTask = new Event(nameOfTask, dateStr);
            System.out.println("     Got it. I've added this task: ");
            System.out.println("       " + newTask);
            tasks.add(newTask);
        }

        storage.save(tasks.getList());
	}

    /**
     * Returns the state of exit
     */
	public boolean isExit() {
		return exit;
	}
}
