package weomucat.duke.command.parameter;

import weomucat.duke.exception.InvalidParameterException;

/**
 * A parameter option. It defines:
 * - whether the parameter is required or optional
 * - the type of user input the parameter should expect
 *
 * @param <T> the type of user input
 */
public abstract class Parameter<T> {
  private String description;
  private boolean required;
  private T value;

  /**
   * Creates a parameter option.
   *
   * @param description  a description of the parameter
   * @param required     whether the parameter is required or optional
   * @param initialValue the initial value of the parameter before parsing
   */
  public Parameter(String description, boolean required, T initialValue) {
    this.description = description;
    this.required = required;
    this.value = initialValue;
  }

  /**
   * Parses user input into a defined type.
   *
   * @param userInput the user input
   * @throws InvalidParameterException if user input is empty when required, or cannot be parsed
   */
  public void parse(String userInput) throws InvalidParameterException {
    if (required) {
      if (userInput == null || userInput.equals("")) {
        throw new InvalidParameterException(String.format("'%s' cannot be empty!",
            this.description));
      }
    } else {
      if (userInput == null) {
        return;
      }
    }
    this.value = parseLogic(userInput);
  }

  abstract T parseLogic(String userInput) throws InvalidParameterException;

  String getDescription() {
    return description;
  }

  public T value() {
    return this.value;
  }
}
