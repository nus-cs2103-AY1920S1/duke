package weomucat.duke.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import weomucat.duke.DukeConsumer;
import weomucat.duke.Pair;
import weomucat.duke.command.listener.DisplayCommandListener;
import weomucat.duke.command.parameter.CommandNameParameter;
import weomucat.duke.command.parameter.FlagParameter;
import weomucat.duke.command.parameter.Parameter;
import weomucat.duke.command.parameter.ParameterOptions;
import weomucat.duke.ui.Message;

public class HelpCommand extends Command<DisplayCommandListener> {

  private static final String KEYWORD = "help";
  private static final String DESCRIPTION = "Explains how a command works.";

  private static final String PARAMETER_ALL = "/all";

  private static final String DEFAULT_PARAMETER_DESCRIPTION = "The command name to find help for.";
  private static final String PARAMETER_ALL_DESCRIPTION =
      "If this flag is set, list all available commands.";

  private static final String DEFAULT_PARAMETER_NAME = "command";

  private CommandNameParameter command;
  private FlagParameter all;

  @Override
  public String getKeyword() {
    return KEYWORD;
  }

  @Override
  String getDescription() {
    return DESCRIPTION;
  }

  @Override
  public ParameterOptions getParameterOptions() {
    this.command = new CommandNameParameter(DEFAULT_PARAMETER_DESCRIPTION, false,
        DEFAULT_PARAMETER_NAME);
    this.all = new FlagParameter(PARAMETER_ALL_DESCRIPTION);
    return new ParameterOptions(this.command)
        .put(PARAMETER_ALL, this.all);
  }

  @Override
  Class<DisplayCommandListener> getListenerClass() {
    return DisplayCommandListener.class;
  }

  @Override
  DukeConsumer<DisplayCommandListener> getListenerConsumer() {
    Collection<Message> messages;
    if (this.all.value()) {
      messages = listAllCommands();
    } else {
      messages = explainCommand();
    }
    return listener -> listener.displayMessage(messages.toArray(new Message[0]));
  }

  private Collection<Message> listAllCommands() {
    ArrayList<Message> messages = new ArrayList<>();
    messages.add(new Message("Listing all commands:"));

    ArrayList<String> result = new ArrayList<>();
    List<Command<?>> commands = UserCommands.getAll();
    commands.sort(Comparator.comparing(Command::getKeyword));
    for (Command<?> command : commands) {
      result.add(String.format("===== %s =====\n%s",
          command.getKeyword(), command.getDescription()));
    }
    messages.add(new Message(String.join("\n\n", result)));

    return messages;
  }

  private Collection<Message> explainCommand() {
    Command<?> command;
    if (this.command.value() == null) {
      command = new HelpCommand();
    } else {
      command = this.command.value();
    }

    ArrayList<Message> messages = new ArrayList<>();
    messages.add(new Message(
        String.format("Help page for '%s' command:", command.getKeyword())));

    // Add usage
    ArrayList<String> usage = usage(command);
    messages.add(new Message(String.join(" ", usage)).setTitle("Usage"));

    // Add description
    messages.add(new Message(command.getDescription()).setTitle("Description"));

    // Add parameter info
    ArrayList<String> parameters = parameters(command);
    if (parameters != null) {
      messages.add(new Message(String.join("\n\n", parameters)).setTitle("Parameters"));
    }

    return messages;
  }

  private ArrayList<String> usage(Command<?> command) {
    ArrayList<String> result = new ArrayList<>();
    result.add(command.getKeyword());

    ParameterOptions parameterOptions = command.getParameterOptions();
    if (parameterOptions == null) {
      return result;
    }

    ArrayList<Pair<String, Parameter>> parameters = new ArrayList<>();
    Parameter defaultParameter = parameterOptions.getDefaultParameter();
    if (defaultParameter != null) {
      parameters.add(new Pair<>(null, defaultParameter));
    }
    for (Map.Entry<String, Parameter> p : command.getParameterOptions().entrySet()) {
      parameters.add(new Pair<>(p.getKey(), p.getValue()));
    }

    for (Pair<String, Parameter> pair : parameters) {
      String keyword = pair.key();
      Parameter parameter = pair.value();

      ArrayList<String> titleList = new ArrayList<>();
      if (keyword != null) {
        titleList.add(keyword);
      }
      if (parameter.name() != null) {
        titleList.add(String.format("<%s>", parameter.name()));
      }
      String title = String.join(" ", titleList);
      if (!parameter.isRequired()) {
        title = String.format("[%s]", title);
      }
      result.add(title);
    }

    return result;
  }

  private ArrayList<String> parameters(Command<?> command) {

    ParameterOptions parameterOptions = command.getParameterOptions();
    if (parameterOptions == null) {
      return null;
    }

    ArrayList<Pair<String, Parameter>> parameters = new ArrayList<>();
    Parameter defaultParameter = parameterOptions.getDefaultParameter();
    if (defaultParameter != null) {
      parameters.add(new Pair<>(null, defaultParameter));
    }
    for (Map.Entry<String, Parameter> p : command.getParameterOptions().entrySet()) {
      parameters.add(new Pair<>(p.getKey(), p.getValue()));
    }

    ArrayList<String> result = new ArrayList<>();
    for (Pair<String, Parameter> pair : parameters) {
      String keyword = pair.key();
      Parameter parameter = pair.value();

      ArrayList<String> titleList = new ArrayList<>();
      if (keyword != null) {
        titleList.add(keyword);
      }
      if (parameter.name() != null) {
        titleList.add(String.format("<%s>", parameter.name()));
      }

      ArrayList<String> parameterList = new ArrayList<>();
      parameterList.add(String.join(" ", titleList));
      parameterList.add(String.format("Required: %s", parameter.isRequired() ? "Yes" : "No"));
      parameterList.add(String.format("Type: %s", parameter.type()));
      parameterList.add(String.format("Description: %s", parameter.description()));
      result.add(String.join("\n", parameterList));
    }

    return result;
  }
}
