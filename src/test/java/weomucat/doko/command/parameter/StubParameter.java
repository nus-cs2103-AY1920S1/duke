package weomucat.doko.command.parameter;

public class StubParameter extends Parameter<String> {
  /**
   * Creates a parameter option.
   */
  public StubParameter() {
    super("", false);
  }

  @Override
  String parseLogic(String input) {
    return input;
  }

  @Override
  public String type() {
    return null;
  }
}
