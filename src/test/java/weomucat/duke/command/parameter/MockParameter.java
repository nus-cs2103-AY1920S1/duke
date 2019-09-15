package weomucat.duke.command.parameter;

public class MockParameter extends Parameter<String> {

  /**
   * Creates a parameter option.
   *
   * @param required whether the parameter is required or optional
   */
  MockParameter(boolean required) {
    super("", required);
  }

  @Override
  String parseLogic(String input) {
    return "";
  }

  @Override
  public String type() {
    return null;
  }
}
