package weomucat.duke.command.parameter;

public class StringParameter extends Parameter<String> {

  public StringParameter(String description, boolean required, String name) {
    super(description, required, name);
  }

  @Override
  String parseLogic(String input) {
    return input;
  }

  @Override
  public String type() {
    return "String";
  }
}
