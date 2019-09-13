package weomucat.duke.command.parameter;

public class FlagParameter extends Parameter<Boolean> {
  public FlagParameter() {
    super("", false, false);
  }

  @Override
  Boolean parseLogic(String userInput) {
    return true;
  }
}
