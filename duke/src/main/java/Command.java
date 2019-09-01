class Command {
	private String command;

	public Command(String command) {
		this.command = command;
	}

	public isExit() {
		return this.command.equals("bye");
	}

	public void execute(TaskList tasks, Ui ui, Storage storage) {
		tasks.add(task);
	}
}