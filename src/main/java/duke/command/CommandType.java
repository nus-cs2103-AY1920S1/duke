package duke.command;

/**
 * Types of command.
 */
public enum CommandType {
    /**
     * Add command.
     * It includes todo, event and deadline command.
     */
    Add,
    /**
     * Delete command.
     */
    Delete,
    /**
     * Find command.
     */
    Find,
    /**
     * List command.
     */
    List,
    /**
     * Done command.
     */
    Done,
    /**
     * Exit command.
     */
    Exit;
}
