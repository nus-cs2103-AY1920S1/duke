package command;

import core.Storage;
import exception.DukeIllegalArgumentException;
import ui.Ui;
import exception.DukeException;
import task.TaskList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Command class.
 *
 * <p>Parent class for all Duke commands.
 *
 * @author Marcus Ong
 */
public abstract class Command {
    protected CommandType type;
    private String commandString;
    private boolean exit;

    /** HashMap containing key-value pairs of command type (CommandType) to aliases (ArrayList&lt;String&gt;). */
    protected static HashMap<CommandType, ArrayList<String>> commandAliasesMap = new HashMap<>();

    /**
     * Command Constructor.
     *
     * @param type Type of command.
     * @param commandString full String of the user input.
     */
    public Command(CommandType type, String commandString) {
        this.type = type;
        this.commandString = commandString;
        this.exit = false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public static ArrayList<String> getAliases(CommandType type) {
        return commandAliasesMap.get(type);
    }

    /**
     * Add aliases for a command.
     *
     * @param type CommandType of command to add an alias for.
     * @param aliasesToAdd Aliases to add.
     * @throws DukeIllegalArgumentException If there is a clash between existing aliases and one of the aliases to add.
     */
    public static void addAlias(CommandType type, String...aliasesToAdd) throws DukeIllegalArgumentException {
        commandAliasesMap.putIfAbsent(type, new ArrayList<String>());

        boolean hasClashingAlias = checkClashingAlias(aliasesToAdd);
        if (hasClashingAlias) {
            throw new DukeIllegalArgumentException("OOPS!!! Can't add an alias which clashes with existing ones!");
        }

        for (String a : aliasesToAdd) {
            commandAliasesMap.get(type).add(a);
        }
    }

    private static boolean checkClashingAlias(String...aliasesToAdd) {
        for (Map.Entry<CommandType, ArrayList<String>> entry: commandAliasesMap.entrySet()) {
            ArrayList<String> typeAliases = entry.getValue();
            for (String alias : aliasesToAdd) {
                for (String typeAlias : typeAliases) {
                    boolean hasClash = alias.equalsIgnoreCase(typeAlias);
                    if (hasClash) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Delete aliases for a command.
     *
     * @param type CommandType of command to delete aliases for.
     * @param aliasesToDelete Aliases to delete.
     */
    public static void deleteAlias(CommandType type, String...aliasesToDelete) {
        ArrayList<String> aliases = commandAliasesMap.get(type);
        for (String a : aliasesToDelete) {
            aliases.remove(a);
        }
    }
}
