package weomucat.duke.command.parameter;

public class StubParameter extends Parameter<String> {
  /**
   * Creates a parameter option.
   */
  public StubParameter() {
    super("Stub Parameter", false, null);
  }

  @Override
  String parseLogic(String userInput) {
    return userInput;
  }
}
