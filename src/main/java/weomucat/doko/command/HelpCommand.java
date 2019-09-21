package weomucat.doko.command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import weomucat.doko.DokoConsumer;
import weomucat.doko.Pair;
import weomucat.doko.command.listener.DisplayCommandListener;
import weomucat.doko.command.parameter.CommandNameParameter;
import weomucat.doko.command.parameter.FlagParameter;
import weomucat.doko.command.parameter.Parameter;
import weomucat.doko.command.parameter.ParameterOptions;
import weomucat.doko.ui.message.Message;
import weomucat.doko.ui.message.MessageContent;
import weomucat.doko.ui.message.element.MessageText;

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
  DokoConsumer<DisplayCommandListener> getListenerConsumer() {
    List<Message> messages;
    if (this.all.value()) {
      messages = listAllCommands();
    } else {
      messages = explainCommand();
    }
    return listener -> listener.displayMessage(messages.toArray(new Message[0]));
  }

  private List<Message> listAllCommands() {
    ArrayList<Message> messages = new ArrayList<>();
    messages.add(new Message().addBody("Listing all commands:"));

    Message result = new Message();
    List<Command<?>> commands = UserCommands.getAll();
    commands.sort(Comparator.comparing(Command::getKeyword));
    for (Command<?> command : commands) {
      result.addBody(new MessageContent()
          .addText(command.getKeyword(), MessageText.Type.SECONDARY)
          .addText(" - ")
          .addText(command.getDescription()));
    }
    messages.add(result);

    return messages;
  }

  private List<Message> explainCommand() {
    Command<?> command;
    if (this.command.value() == null) {
      command = new HelpCommand();
    } else {
      command = this.command.value();
    }

    List<Message> messages = new ArrayList<>();
    messages.add(new Message().addBody(
        new MessageContent()
            .addText("Help page for '")
            .addText(command.getKeyword(), MessageText.Type.SECONDARY)
            .addText("' command:")));

    // Add usage
    List<MessageContent> usage = usage(command);
    messages.add(new Message()
        .setTitle("Usage")
        .addBody(MessageContent.join(new MessageContent().addText(" "), usage)));

    // Add description
    messages.add(new Message()
        .setTitle("Description")
        .addBody(command.getDescription()));

    // Add parameter info
    Message parameters = parameters(command);
    if (parameters != null) {
      messages.add(parameters);
    }

    return messages;
  }

  private List<MessageContent> usage(Command<?> command) {
    List<MessageContent> result = new ArrayList<>();
    result.add(new MessageContent().addText(command.getKeyword()));

    ParameterOptions parameterOptions = command.getParameterOptions();
    if (parameterOptions == null) {
      return result;
    }

    List<Pair<String, Parameter>> parameters = new ArrayList<>();
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

      MessageContent content = new MessageContent();
      if (keyword != null) {
        content.addText(keyword);
      }
      if (parameter.name() != null) {
        content.addText(String.format("<%s>", parameter.name()), MessageText.Type.SECONDARY);
      }
      content.insertBetween(new MessageContent().addText(" "));

      if (parameter.isRequired()) {
        result.add(content);
      } else {
        result.add(new MessageContent()
            .addText("[")
            .add(content)
            .addText("]"));
      }
    }

    return result;
  }

  private Message parameters(Command<?> command) {
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

    Message result = new Message().setTitle("Parameters");

    List<MessageContent> descriptions = new ArrayList<>();
    for (Pair<String, Parameter> pair : parameters) {
      String keyword = pair.key();
      Parameter parameter = pair.value();

      MessageContent description = new MessageContent();
      if (keyword != null) {
        description.addText(keyword, MessageText.Type.SECONDARY);
      }
      if (parameter.name() != null) {
        description.addText(String.format("<%s>", parameter.name()), MessageText.Type.SECONDARY);
      }
      description.insertBetween(new MessageContent().addText(" "))
          .add(MessageContent.newLine(1))
          .addText(String.format("Required: %s", parameter.isRequired() ? "Yes" : "No"))
          .add(MessageContent.newLine(1))
          .addText(String.format("Type: %s", parameter.type()))
          .add(MessageContent.newLine(1))
          .addText(String.format("Description: %s", parameter.description()));
      descriptions.add(description);
    }
    return result.addBody(MessageContent.join(MessageContent.newLine(2), descriptions));
  }
}
