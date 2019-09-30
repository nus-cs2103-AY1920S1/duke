package duke.command;

import java.util.List;

public class ByeCommand implements Command {
    /**
     * Returns the message to show the user when they leave.
     *
     * @param words Array of words from the input line.
     * @return Message to show the user.
     */
    @Override
    public List<String> run(String[] words) {
        return List.of("Bye. Hope to see you again soon!");
    }

    /**
     * Returns whether the program should exit after this command.
     *
     * @return true since this command should cause the program to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
