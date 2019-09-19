public class Command {

    private String action;
    private int index;
    private String nameOfTask; 
    private String dateStr;
    private String wordToFind;
	private boolean exit = false;

	public Command(String action, int index, String nameOfTask, String dateStr, String wordToFind) {
		this.action = action;
		this.index = index;
		this.nameOfTask = nameOfTask;
        this.dateStr = dateStr;
        this.wordToFind = wordToFind;
	}

    /**
     * Executes command
     *
     * @param tasks Access of the TaskList
     * @param ui Access of Ui
     * @param storage Access of storage to save tasks list
     */
	public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (action.equals("list")) {
            return tasks.printList();

        } else if (action.equals("delete")) {
            String delete = "Noted. I've removed this task: \n" +
                    "  " + tasks.get(index) + "\n" +
                    "Now you have " + (tasks.size() - 1) + " tasks in the list.";
            tasks.delete(index);
            storage.save(tasks.getList());
            return delete;

        } else if (action.equals("done")) {
            String done = tasks.done(index);
            storage.save(tasks.getList());
            return done;

        } else if (action.equals("bye")) {
            System.out.println("     Bye. Hope to see you again soon!");
            exit = true;
            return "Bye. Hope to see you again soon!";

        } else if (action.equals("todo")) {
            Task newTask = new ToDo(nameOfTask);
            System.out.println("     Got it. I've added this task: ");
            System.out.println("       " + newTask);
            String todo = "Got it. I've added this task: \n  " + newTask;
            tasks.add(newTask);
            storage.save(tasks.getList());
            return todo;

        } else if (action.equals("deadline")) {
            Task newTask = new Deadline(nameOfTask, dateStr);
            System.out.println("     Got it. I've added this task: ");
            System.out.println("       " + newTask);
            tasks.add(newTask);
            storage.save(tasks.getList());
            String deadline = "Got it. I've added this task: \n  " + newTask;
            return deadline;

        } else if (action.equals("event")){
            Task newTask = new Event(nameOfTask, dateStr);
            System.out.println("     Got it. I've added this task: ");
            System.out.println("       " + newTask);
            tasks.add(newTask);
            String event = "Got it. I've added this task: \n  " + newTask;
            storage.save(tasks.getList());
            return event;

        } else {
            return tasks.printListWithKeyword(wordToFind);
        }
	}

    /**
     * Returns the state of exit
     */
	public boolean isExit() {
		return exit;
	}
}
