package weomucat.doko.command.parameter;

import weomucat.doko.exception.DokoException;
import weomucat.doko.exception.InvalidParameterException;

/**
 * A parameter option. It defines:
 * - whether the parameter is required or optional
 * - the type of user input the parameter should expect
 *
 * @param <T> the type of user input
 */
public abstract class Parameter<T> {

  private String name;
  private String description;
  private boolean required;
  private T value;

  /**
   * Creates a parameter option.
   *
   * @param description the description of the parameter
   * @param required    whether the parameter is required or optional
   */
  public Parameter(String description, boolean required) {
    this.description = description;
    this.required = required;
  }

  /**
   * Creates a parameter option.
   *
   * @param description the description of the parameter
   * @param required    whether the parameter is required or optional
   * @param name        the name of the parameter
   */
  public Parameter(String description, boolean required, String name) {
    this(description, required);
    this.name = name;
  }

  /**
   * Parses user input into a defined type.
   *
   * @param userInput the user input
   * @throws InvalidParameterException if user input is empty when required, or cannot be parsed
   */
  public void parse(String userInput) throws DokoException {
    if (required) {
      if (userInput == null || userInput.equals("")) {
        throw new InvalidParameterException(String.format("'%s' cannot be empty!",
            this.name));
      }
    } else {
      if (userInput == null) {
        return;
      }
    }
    this.value = parseLogic(userInput);
  }

  abstract T parseLogic(String input) throws DokoException;


  public abstract String type();

  public String name() {
    return name;
  }

  public String description() {
    return description;
  }

  public boolean isRequired() {
    return required;
  }

  void setValue(T value) {
    this.value = value;
  }

  public T value() {
    return this.value;
  }
}
