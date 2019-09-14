package weomucat.duke.command.parameter;

public class MockParameter extends Parameter<String> {

  /**
   * Creates a parameter option.
   *
   * @param required whether the parameter is required or optional
   */
  MockParameter(boolean required) {
    super("Mock Parameter", required, null);
  }

  @Override
  String parseLogic(String userInput) {
    return "";
  }
}
