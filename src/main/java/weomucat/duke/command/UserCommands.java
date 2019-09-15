package weomucat.duke.command;

import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import weomucat.duke.exception.UnknownCommandException;

public class UserCommands {

  private static HashMap<String, Supplier<Command<?>>> commands;

  static {
    commands = new HashMap<>();
    addCommand(ByeCommand::new);
    addCommand(DeadlineCommand::new);
    addCommand(DeleteCommand::new);
    addCommand(DoneCommand::new);
    addCommand(EventCommand::new);
    addCommand(EventAtCommand::new);
    addCommand(FindCommand::new);
    addCommand(HelpCommand::new);
    addCommand(ListCommand::new);
    addCommand(SnoozeCommand::new);
    addCommand(TodoCommand::new);
  }

  /**
   * Returns a user command from a given string command keyword.
   *
   * @param command the command keyword
   * @return a Command
   * @throws UnknownCommandException if the command cannot be found
   */
  public static Command<?> get(String command) throws UnknownCommandException {
    Supplier<Command<?>> commandSupplier = commands.get(command);
    if (commandSupplier == null) {
      throw new UnknownCommandException();
    }
    return commandSupplier.get();
  }

  /**
   * Get all user commands.
   *
   * @return all user commands
   */
  public static List<Command<?>> getAll() {
    return commands.values()
        .stream()
        .map(Supplier::get)
        .collect(Collectors.toList());
  }

  private static void addCommand(Supplier<Command<?>> commandSupplier) {
    commands.put(commandSupplier.get().getKeyword(), commandSupplier);
  }
}
