package weomucat.duke.command.parameter;

public class StringParameter extends Parameter<String> {

  public StringParameter(String name, boolean required) {
    super(name, required, null);
  }

  @Override
  String parseLogic(String userInput) {
    return userInput;
  }
}
