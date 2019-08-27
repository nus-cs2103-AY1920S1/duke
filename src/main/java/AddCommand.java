class AddCommand extends Command {
    Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    void execute(TaskList tasks, UserInterface ui, Storage storage) {
        //add task into the task list
        tasks.add(task);

        //display successful message and task count
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t " + task.toString());
        System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
