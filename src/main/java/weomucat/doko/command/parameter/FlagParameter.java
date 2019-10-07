package weomucat.doko.command.parameter;

public class FlagParameter extends Parameter<Boolean> {

  public FlagParameter(String description) {
    super(description, false);
    setValue(false);
  }

  @Override
  Boolean parseLogic(String input) {
    return true;
  }

  @Override
  public String type() {
    return "Flag";
  }
}
