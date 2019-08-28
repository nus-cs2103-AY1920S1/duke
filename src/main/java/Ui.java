public class Ui {
	static String line = "____________________________________________________________";
	private TaskList tasks;
	
	/**
	 * Error from unidentified command.
	 */
	public void showLoadingError() {
		System.out.println(line);
		System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
		System.out.println(line + "\n");
	}
	
	/**
	 * Shows thrown exception.
	 * @param e Exception
	 */
	public void showException(Exception e) {
		System.out.println(line);
		System.out.println(" ☹ OOPS!!! " + e.toString());
		System.out.println(line + "\n");
	}
	
	/**
	 * Shows logo and intro.
	 */
	public void logoAndIntro() {
		String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
		System.out.println(" Hello! I'm Duke\n What can I do for you?");
		System.out.println(line + "\n");
	}

	/**
	 * Shows bye message.
	 */
	public void printBye() {
		System.out.println(line);
		System.out.println(" " + "Bye. Hope to see you again soon!");
		System.out.println(line + "\n");
	}

	/**
	 * Shows list of tasks.
	 */
	public void printList() {
		System.out.println(line);
		System.out.println(" " + "Here are the tasks in your list:");
		tasks.list();
		System.out.println(line + "\n");
	}

	/**
	 * Sets pointer to Duke tasklist.
	 * @param tasks Tasklist.
	 */
	public void setTaskList(TaskList tasks) {
		this.tasks = tasks;
	}

	/**
	 * Shows done task.
	 * @param index Index of done task.
	 */
	public void printDone(int index) {
		System.out.println(line);
		System.out.println(" " + "Nice! I've marked this task as done:");
		System.out.println("   " + tasks.getMemory().get(index).showTask());
		System.out.println(line + "\n");
	}
	
	/**
	 * Shows deleted task.
	 * @param t Deleted task.
	 */
	public void printDeleted(Task t) {
		System.out.println(line);
		System.out.println(" " + "Noted. I've removed this task:");
		System.out.println("   " + t.showTask());
		if (tasks.getMemory().size() == 1) System.out.println(" " + "Now you have 1 task in your list");
		else System.out.println(" " + "Now you have " + tasks.getMemory().size() + " tasks in your list.");
		System.out.println(line + "\n");
	}
	
	/**
	 * Shows added task.
	 */
	public void printAdded() {
		System.out.println(line);
		System.out.println(" " + "Got it. I've added this task:");
		System.out.println("   " + tasks.listLatest());
		if (tasks.getMemory().size() == 1) System.out.println(" " + "Now you have 1 task in your list");
		else System.out.println(" " + "Now you have " + tasks.getMemory().size() + " tasks in your list.");
		System.out.println(line + "\n");
	}
}