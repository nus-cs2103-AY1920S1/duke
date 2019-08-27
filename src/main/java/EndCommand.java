class EndCommand extends Command {
    EndCommand() {}

    void execute(TaskList tasks, UserInterface ui, Storage storage) {
        //stop the while loop of actions
        isExit = true;

        //display goodbye message
        System.out.println("\tBye. Hope to see you again soon!");
    }
}
